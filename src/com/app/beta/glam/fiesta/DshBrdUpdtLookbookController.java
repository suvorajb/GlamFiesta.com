package com.app.beta.glam.fiesta;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.app.beta.glam.fiesta.cmn.GlamCmn;
import com.app.beta.glam.fiesta.cmn.GlamCnst;
import com.app.beta.glam.fiesta.cmn.OfyService;
import com.app.beta.glam.fiesta.db.UserDO;
import com.app.beta.glam.fiesta.db.UserLookbookDO;
import com.app.beta.glam.fiesta.db.UserLookbookTagsDO;
import com.app.beta.glam.fiesta.form.LookbookForm;
import com.app.beta.glam.fiesta.model.LookbookBO;
import com.app.beta.glam.fiesta.model.UserBO;
import com.app.beta.glam.fiesta.model.UsrContainer;
import com.google.appengine.api.blobstore.BlobInfo;
import com.google.appengine.api.blobstore.BlobInfoFactory;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.blobstore.UploadOptions;

@Controller
public class DshBrdUpdtLookbookController {

	private static final Logger logger = Logger.getLogger(DshBrdUpdtLookbookController.class.getCanonicalName());

	/********************************************
	 * manage existing lookbook
	 ******************************************/

	@RequestMapping(value = "/dashboard/lookbook/manage", method = RequestMethod.GET)
	public String goMngLookbookPage(@RequestParam long lookbookid, HttpServletRequest req, Model uiModel) {

		UserBO usrbo = null;
		UserDO usrdo = null;

		LookbookForm lookbookform = new LookbookForm();
		UserLookbookDO lookbookdo = null;
		LookbookBO lookbookbo = new LookbookBO();
		StringBuilder tagbldr = new StringBuilder();

		if (GlamCmn.chkLoggdInStatus(req)) {
			UsrContainer uc = GlamCmn.getLoggedInUsrDtls(req);
			usrbo = uc.getUsrbo();
			usrdo = uc.getUsrdo();

			// retrieve all lookbook that user has created
			lookbookdo = OfyService.ofy().load().type(UserLookbookDO.class).id(lookbookid).get();

			if (lookbookdo != null) {
				String[] ignorefields = { "lookbookme", "tags", "disapproved" };
				BeanUtils.copyProperties(lookbookdo, lookbookform, ignorefields);
				
				if (StringUtils.isNotBlank(lookbookdo.getGcs_photo_url1())) {
					lookbookbo.setGcs_photo_url1(lookbookdo.getGcs_photo_url1());
				}

				if (StringUtils.isNotBlank(lookbookdo.getGcs_photo_url2())) {
					lookbookbo.setGcs_photo_url2(lookbookdo.getGcs_photo_url2());
				}

				if (StringUtils.isNotBlank(lookbookdo.getGcs_photo_url3())) {
					lookbookbo.setGcs_photo_url3(lookbookdo.getGcs_photo_url3());
				}

				lookbookbo.setLookbookid(lookbookdo.getLookbookid());

				if (lookbookdo.getTags() != null && lookbookdo.getTags().size() > 0) {
					for (String tag : lookbookdo.getTags()) {
						tagbldr.append(tag + ",");
					}
					lookbookform.setTags(tagbldr.toString());
				} else {
					lookbookform.setTags("OOTD, new look");
				}

				if (lookbookdo.isLookbookme() == true) {
					lookbookform.setLookbookme("Yes");
				} else {
					lookbookform.setLookbookme("No");
				}

				if (lookbookdo.getComments_by_users() != null && lookbookdo.getComments_by_users().size() > 0) {
					lookbookbo.setTotal_comments(String.valueOf(lookbookdo.getComments_by_users().size()));
				} else {
					lookbookbo.setTotal_comments("0");
				}
			}

			uiModel.addAttribute("usrbo", usrbo);
			uiModel.addAttribute("lookbook", lookbookbo);
			uiModel.addAttribute("lookbookform", lookbookform);

			return GlamCnst._VIEW_DSHBRD_LKBK_MNG;
		} else {
			return "redirect:/login";
		}

	}

	/********************************************
	 * manage existing lookbook photos
	 ******************************************/

	@RequestMapping(value = "/dashboard/lookbook/manage/photos", method = RequestMethod.GET)
	public String goMngLookbookPhotosPage(@RequestParam long lookbookid, HttpServletRequest req, Model uiModel) {
		UserBO usrbo = null;
		UserDO usrdo = null;

		UserLookbookDO lookbookdo = null;
		LookbookForm lookbookform = new LookbookForm();
		LookbookBO lookbookbo = new LookbookBO();
		String photoUploadUrl = "";

		BlobstoreService blobstoreService = null;
		UploadOptions uploadoptions = null;

		if (GlamCmn.chkLoggdInStatus(req)) {
			UsrContainer uc = GlamCmn.getLoggedInUsrDtls(req);
			usrbo = uc.getUsrbo();
			usrdo = uc.getUsrdo();

			String _BUCKET_NAME = GlamCnst._LKBK_BUCKET_NAME + usrbo.getAccount_username();
			blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
			uploadoptions = UploadOptions.Builder.withGoogleStorageBucketName(_BUCKET_NAME);

			photoUploadUrl = blobstoreService.createUploadUrl("/dashboard/lookbook/manage/photos", uploadoptions);

			// retrieve the lookbook that user has created
			lookbookdo = OfyService.ofy().load().type(UserLookbookDO.class).id(lookbookid).get();

			if (lookbookdo != null) {

				if (StringUtils.isNotBlank(lookbookdo.getGcs_photo_url1())) {
					lookbookbo.setGcs_photo_url1(lookbookdo.getGcs_photo_url1());
				}

				lookbookbo.setLookbookid(lookbookdo.getLookbookid());
				lookbookbo.setLookbookname(lookbookbo.getLookbookname());
				lookbookform.setLookbookid(lookbookid);
			}

			uiModel.addAttribute("usrbo", usrbo);
			uiModel.addAttribute("lookbook", lookbookbo);
			uiModel.addAttribute("lookbookform", lookbookform);
			uiModel.addAttribute("photoUploadUrl", photoUploadUrl);

			return GlamCnst._VIEW_DSHBRD_LKBK_MNG_PHOTOS;
		} else {
			return "redirect:/login";
		}
	}

	/***************************************
	 * Request Type - POST
	 **************************************/

	@RequestMapping(value = "/dashboard/lookbook/manage", method = RequestMethod.POST)
	public String doUpdateLookbook(@ModelAttribute LookbookForm lookbookform, BindingResult result, Model uiModel,
			HttpServletRequest req) {

		UserBO usrbo = null;
		UserDO usrdo = null;

		UserLookbookDO lookbookdo = null;
		List<UserLookbookTagsDO> taglist = new ArrayList<UserLookbookTagsDO>();

		logger.info("********** Inside manage POST ******************");

		if (GlamCmn.chkLoggdInStatus(req)) {

			UsrContainer uc = GlamCmn.getLoggedInUsrDtls(req);
			usrbo = uc.getUsrbo();
			usrdo = uc.getUsrdo();

			// retrieve the lookbook first based on the lookbookid
			lookbookdo = OfyService.ofy().load().type(UserLookbookDO.class).id(lookbookform.getLookbookid()).get();
			//logger.info("********** inside manage POST - lookbook found-" + lookbookdo);

			String[] ignoreFlds = { "lookbookid", "lookbookme", "tags", "disapproved" };

			BeanUtils.copyProperties(lookbookform, lookbookdo, ignoreFlds);
			
			// update unique SEO URL
			lookbookdo.setLookbook_seo_url(GlamCmn.genUnqueLookbookSEOUrl(lookbookform.getLookbookname(),
					usrdo.getAccount_username()));

			if (lookbookform.getTags() != null) {
				String[] tagsarr = StringUtils.split(lookbookform.getTags(), ",");

				List<String> tagslist = new ArrayList<String>();

				for (String tag : tagsarr) {
					tagslist.add(tag);
					if (StringUtils.isNotBlank(tag)) {
						UserLookbookTagsDO tagdo = new UserLookbookTagsDO();
						tagdo.setLookbookid(lookbookdo.getLookbookid());
						tagdo.setTagstr(tag);
						taglist.add(tagdo);
					}
				}

				lookbookdo.setTags(tagslist);
			}

			if (StringUtils.equalsIgnoreCase(lookbookform.getLookbookme(), "Yes")) {
				lookbookdo.setLookbookme(true);
			} else {
				lookbookdo.setLookbookme(false);
			}

			lookbookdo.setLastupdateddt(new Date());

			// save the updated lookbook
			logger.info("********** saving the updated lookbook with updated info ******************");
			OfyService.ofy().save().entity(lookbookdo).now();

			// save the tags
			Iterator<UserLookbookTagsDO> tagItr = taglist.iterator();
			while (tagItr.hasNext()) {
				OfyService.ofy().save().entity((UserLookbookTagsDO) tagItr.next());
			}

			// update the user activity
			GlamCmn.saveUsrActivity(usrbo.getUserid(), usrbo.getAccount_username(), GlamCnst._ATVY_LKBK_UPDTE,
					lookbookdo.getLookbookid(), lookbookdo.getLookbook_seo_url(), "");

			uiModel.addAttribute("usrbo", usrbo);
			uiModel.addAttribute("lookbookform", lookbookform);

			return "redirect:/dashboard/lookbook/manage?lookbookid=" + lookbookform.getLookbookid();
		} else {
			return "redirect:/login";
		}

	}

	@RequestMapping(value = "/dashboard/lookbook/manage/photos", method = RequestMethod.POST)
	public String doUpdateLookbookPhotos(@ModelAttribute LookbookForm lookbookform, BindingResult result, Model uiModel,
			HttpServletRequest req) {

		UserBO usrbo = null;
		UserDO usrdo = null;

		BlobstoreService blobstoreService = null;

		List<BlobKey> outfit1Keys = null;
		// List<BlobKey> outfit2Keys = null;
		// List<BlobKey> outfit3Keys = null;
		// List<BlobKey> outfit4Keys = null;
		// List<BlobKey> outfit5Keys = null;

		BlobKey outfit1_key = null;
		// BlobKey outfit2_key = null;
		// BlobKey outfit3_key = null;
		// BlobKey outfit4_key = null;
		// BlobKey outfit5_key = null;

		String imgUrlStr1 = "";
		// String imgUrlStr2 = "";
		// String imgUrlStr3 = "";
		// String imgUrlStr4 = "";
		// String imgUrlStr5 = "";

		BlobInfo blobInfo1 = null;
		// BlobInfo blobInfo2 = null;
		// BlobInfo blobInfo3 = null;
		// BlobInfo blobInfo4 = null;
		// BlobInfo blobInfo5 = null;

		UserLookbookDO lookbookdo = null;
		StringBuilder upldPhotoUrl = new StringBuilder("");

		//logger.info("********** inside lookbook photo manage POST ******************");

		if (GlamCmn.chkLoggdInStatus(req)) {

			UsrContainer uc = GlamCmn.getLoggedInUsrDtls(req);
			usrbo = uc.getUsrbo();
			usrdo = uc.getUsrdo();

			lookbookdo = OfyService.ofy().load().type(UserLookbookDO.class).id(lookbookform.getLookbookid()).get();
			//logger.info("********** inside lookbook photo manage POST - lookbook found-" + lookbookdo);

			blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
			Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);

			// get the first uploaded image
			outfit1Keys = blobs.get("outfit1");
			// outfit2Keys = blobs.get("outfit2");
			// outfit3Keys = blobs.get("outfit3");
			// outfit4Keys = blobs.get("outfit4");
			// outfit5Keys = blobs.get("outfit5");

			try {
				if (outfit1Keys != null && outfit1Keys.size() > 0) {
					//logger.info("****** outfit1Keys size-" + outfit1Keys.size());
					outfit1_key = outfit1Keys.get(0);
					//logger.info("****** outfit1_key str-" + outfit1_key.getKeyString());

					// check if a zero size file is uploaded or no file is
					// uploaded
					blobInfo1 = new BlobInfoFactory().loadBlobInfo(outfit1_key);

					if (blobInfo1 != null && blobInfo1.getSize() > 0) {
						imgUrlStr1 = GlamCmn.getUpldPhotoUrl(outfit1_key);
						lookbookdo.setGcs_photo_url1(imgUrlStr1);
						upldPhotoUrl.append(imgUrlStr1 + "|");
						lookbookdo.setGcs_photo_key1(outfit1_key.getKeyString());
					} else {
						// no file is uploaded or zero size file is uploaded so
						// immediately delete it
						blobstoreService.delete(outfit1_key);
					}
				}
			} catch (Exception ex) {
				logger.info("**** error *** " + ex.getMessage());
			}

			// save the lookbook
			logger.info("********** saving the lookbook again with updated photo urls and photos ******************");
			OfyService.ofy().save().entity(lookbookdo).now();

			// update the user activity
			GlamCmn.saveUsrActivity(usrbo.getUserid(), usrbo.getAccount_username(), GlamCnst._ATVY_LKBK_PH_ADD,
					lookbookdo.getLookbookid(), lookbookdo.getLookbook_seo_url(), upldPhotoUrl.toString());

			uiModel.addAttribute("usrbo", usrbo);
			uiModel.addAttribute("lookbookform", lookbookform);

			return "redirect:/dashboard/lookbook/manage/photos?lookbookid=" + lookbookform.getLookbookid();
		} else {
			return "redirect:/login";
		}

	}

}
