package com.app.beta.glam.fiesta;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.beta.glam.fiesta.cmn.AuthUtil;
import com.app.beta.glam.fiesta.cmn.GlamCmn;
import com.app.beta.glam.fiesta.cmn.GlamCnst;
import com.app.beta.glam.fiesta.cmn.OfyService;
import com.app.beta.glam.fiesta.db.UserDO;
import com.app.beta.glam.fiesta.form.UserForm;
import com.app.beta.glam.fiesta.model.UserBO;
import com.app.beta.glam.fiesta.model.UsrContainer;
import com.app.beta.glam.fiesta.model.UsrLoginBO;
import com.google.appengine.api.blobstore.BlobInfo;
import com.google.appengine.api.blobstore.BlobInfoFactory;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.blobstore.UploadOptions;

@Controller
public class DshBrdProfileController {

	private static final Logger logger = Logger.getLogger(DshBrdProfileController.class.getCanonicalName());

	@RequestMapping(value = "/dashboard/updtprofile", method = RequestMethod.GET)
	public String goUpdtProfilePage(HttpServletRequest req, Model uiModel) {
		UserBO usrbo = null;
		//UserDO usrdo = null;
		
		String avatarUploadUrl = "";
		BlobstoreService blobstoreService = null;
		UploadOptions uploadoptions = null;
		
		if (GlamCmn.chkLoggdInStatus(req)) {
			UsrContainer uc = GlamCmn.getLoggedInUsrDtls(req);
			usrbo = uc.getUsrbo();
			//usrdo = uc.getUsrdo();
			
			UserForm userform 		= new UserForm();
			UserForm avatarform 	= new UserForm();
			UserForm pwdform 		= new UserForm();
			
			blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
			uploadoptions = UploadOptions.Builder.withGoogleStorageBucketName(GlamCnst._AVTR_BUCKET_NAME);
			avatarUploadUrl = blobstoreService.createUploadUrl("/dashboard/updtavatar", uploadoptions);
			
			uiModel.addAttribute("avatarUploadUrl", avatarUploadUrl);
			
			BeanUtils.copyProperties(usrbo, userform);

			uiModel.addAttribute("userform", userform);
			uiModel.addAttribute("avatarform", avatarform);
			uiModel.addAttribute("pwdform", pwdform);
			
			uiModel.addAttribute("usrbo", usrbo);

			return GlamCnst._VIEW_DSHBRD_UPDT_PRFLE;
		} else {
			return "redirect:/login";
		}
	}
	
	

	
	/*************************************** Request Type - POST **************************************/

	@RequestMapping(value = "/dashboard/updtprofile", method = RequestMethod.POST)
	public String doUpdtProfile(@ModelAttribute UserForm userform, BindingResult result, Model uiModel, HttpServletRequest req) {
		StringBuilder errmssg = new StringBuilder();
		StringBuilder succsmssg = new StringBuilder();
		String avatarUploadUrl = "";
		BlobstoreService blobstoreService = null;
		UploadOptions uploadoptions = null;
		
		UserBO usrbo = null;
		UserDO usrdo = null;

		if (GlamCmn.chkLoggdInStatus(req)) {
			UsrContainer uc = GlamCmn.getLoggedInUsrDtls(req);
			usrbo = uc.getUsrbo();
			usrdo = uc.getUsrdo();
			
			if (userform != null) {
				// save it
				String[] ignoreflds = { "account_username", "account_password",	"account_email" };
				BeanUtils.copyProperties(userform, usrdo, ignoreflds);
				
				OfyService.ofy().save().entity(usrdo).now();
					
				// update the user activity
				GlamCmn.saveUsrActivity(usrdo.getUserid(), usrdo.getAccount_username(), GlamCnst._ATVY_PRFL_UPDTE, 0, "", "");
				String[] ignoreflds2 = { "account_username", "account_password", "account_email" };
				BeanUtils.copyProperties(usrdo, usrbo, ignoreflds2);
				
				succsmssg.append("Your profile and account information has been updated successfully");
				//}
				
			}
			
			//return "redirect:/dashboard/updtprofile";
			UserForm avatarform = new UserForm();
			UserForm pwdform = new UserForm();
			
			blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
			uploadoptions = UploadOptions.Builder.withGoogleStorageBucketName(GlamCnst._AVTR_BUCKET_NAME);
			avatarUploadUrl = blobstoreService.createUploadUrl("/dashboard/updtavatar", uploadoptions);
			
			
			uiModel.addAttribute("avatarUploadUrl", avatarUploadUrl);			
			uiModel.addAttribute("errmssg", errmssg.toString());
			uiModel.addAttribute("succsmssg", succsmssg.toString());
			uiModel.addAttribute("userform", userform);
			uiModel.addAttribute("avatarform", avatarform);
			uiModel.addAttribute("pwdform", pwdform);
			uiModel.addAttribute("usrbo", usrbo);

			return GlamCnst._VIEW_DSHBRD_UPDT_PRFLE;
		} else {
			return "redirect:/login";
		}
	}



	@RequestMapping(value = "/dashboard/updtavatar", method = RequestMethod.POST)
	public String doUpdtAvatar(@ModelAttribute UserForm avatarform,	BindingResult result, Model uiModel, HttpServletRequest req) {
		BlobstoreService blobstoreService = null;
		List<BlobKey> avatarkey = null;
		BlobKey avatar_key = null;
		String avtrUrlStr = "";
		BlobInfo blobInfo = null;

		//UserBO usrbo = null;
		UserDO usrdo = null;

		if (GlamCmn.chkLoggdInStatus(req)) {

			UsrContainer uc = GlamCmn.getLoggedInUsrDtls(req);
			//usrbo = uc.getUsrbo();
			usrdo = uc.getUsrdo();
			
			blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
			Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
			
			// get the first uploaded image
			avatarkey = blobs.get("avatar");

			try {
				if (avatarkey != null && avatarkey.size() > 0) {
					logger.info("****** avatarkey size-" + avatarkey.size());
					avatar_key = avatarkey.get(0);
					logger.info("****** avatar_key str-" + avatar_key.getKeyString());

					// check if a zero size file is uploaded or no file is uploaded
					blobInfo = new BlobInfoFactory().loadBlobInfo(avatar_key);

					if (blobInfo != null && blobInfo.getSize() > 0) {
						avtrUrlStr = GlamCmn.getUpldPhotoUrl(avatar_key);
						usrdo.setUavatar(avtrUrlStr);
						usrdo.setUavatar_is_uploaded("Yes");
					} else {
						// no file is uploaded or zero size file is uploaded so
						// immediately delete it
						blobstoreService.delete(avatar_key);
					}
				}
			} catch (Exception ex) {
				logger.info("**** error *** " + ex.getMessage());
			}

			OfyService.ofy().save().entity(usrdo).now();
			
			// update the user activity
			GlamCmn.saveUsrActivity(usrdo.getUserid(), usrdo.getAccount_username(), GlamCnst._ATVY_PRFL_AVTR_UPDT, 0, "", "");						

			return "redirect:/dashboard/updtprofile";
		} else {
			return "redirect:/login";
		}
	}



	@RequestMapping(value = "/dashboard/updtpwd", method = RequestMethod.POST)
	public String doUpdtPwd(@ModelAttribute UserForm pwdform, BindingResult result, Model uiModel, HttpServletRequest req) {
		String hexed_passwd = "";
		String saltkey = "";
		StringBuilder errmssg = new StringBuilder();
		StringBuilder succsmssg = new StringBuilder();
		boolean signupfailr = false;

		String avatarUploadUrl = "";
		BlobstoreService blobstoreService = null;
		UploadOptions uploadoptions = null;
		
		UserBO usrbo = null;
		UserDO usrdo = null;

		if (GlamCmn.chkLoggdInStatus(req)) {
			
			UsrContainer uc = GlamCmn.getLoggedInUsrDtls(req);
			usrbo = uc.getUsrbo();
			usrdo = uc.getUsrdo();
			
			if(StringUtils.isNotBlank(pwdform.getAccount_password())) {
				try {
					saltkey = AuthUtil.getGenrtdRndmAuthSalt();						
					hexed_passwd = AuthUtil.generateAuthPsswd(pwdform.getAccount_password(), saltkey);
					usrdo.setAccount_password(hexed_passwd);
					usrdo.setAccount_pwd_salt(saltkey);	
				}catch (NoSuchAlgorithmException ne) {
					signupfailr = true;
				}catch (InvalidKeySpecException ie) {
					signupfailr = true;
				}
			}
			
			if(!signupfailr) {
				OfyService.ofy().save().entity(usrdo).now();
				succsmssg.append(" Your account password has been updated successfully ...");
			}else {
				errmssg.append("Generic system exception has occurred... pls try again later...");				
			}
			
			UserForm userform 		= new UserForm();
			UserForm avatarform 	= new UserForm();
			
			blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
			uploadoptions = UploadOptions.Builder.withGoogleStorageBucketName(GlamCnst._AVTR_BUCKET_NAME);
			avatarUploadUrl = blobstoreService.createUploadUrl("/dashboard/updtavatar", uploadoptions);
			
			uiModel.addAttribute("avatarUploadUrl", avatarUploadUrl);
			uiModel.addAttribute("userform", userform);
			uiModel.addAttribute("avatarform", avatarform);
			uiModel.addAttribute("pwdform", pwdform);
			uiModel.addAttribute("usrbo", usrbo);

			return GlamCnst._VIEW_DSHBRD_UPDT_PRFLE;
		} else {
			return "redirect:/login";
		}
	}
}
