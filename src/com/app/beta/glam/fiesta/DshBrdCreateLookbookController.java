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
public class DshBrdCreateLookbookController {

	private static final Logger logger = Logger.getLogger(DshBrdCreateLookbookController.class.getCanonicalName());

	/********************************************************
	 * Create New Lookbook
	 *************************************************************/

	@RequestMapping(value = "/dashboard/lookbook/postnew", method = RequestMethod.GET)
	public String goPostNewLookbookPage(HttpServletRequest req, Model uiModel) {
		UserBO usrbo = null;
		UserDO usrdo = null;

		String photoUploadUrl = "";
		BlobstoreService blobstoreService = null;
		UploadOptions uploadoptions = null;

		if (GlamCmn.chkLoggdInStatus(req)) {
			UsrContainer uc = GlamCmn.getLoggedInUsrDtls(req);
			usrbo = uc.getUsrbo();
			usrdo = uc.getUsrdo();

			LookbookForm lookbookform = new LookbookForm();
			lookbookform.setLookbookme("Yes");
			lookbookform.setLookbook_publish_status("Yes");
			lookbookform.setTags("OOTD, new look, ");

			String _BUCKET_NAME = GlamCnst._LKBK_BUCKET_NAME + usrbo.getAccount_username();

			blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
			uploadoptions = UploadOptions.Builder.withGoogleStorageBucketName(_BUCKET_NAME);
			photoUploadUrl = blobstoreService.createUploadUrl("/dashboard/lookbook/postnew", uploadoptions);

			uiModel.addAttribute("photoUploadUrl", photoUploadUrl);
			uiModel.addAttribute("usrbo", usrbo);
			uiModel.addAttribute("lookbookform", lookbookform);

			return GlamCnst._VIEW_DSHBRD_LKBK_POST_NEW;
		} else {
			return "redirect:/login";
		}
	}

	@RequestMapping(value = "/dashboard/lookbook/postnew/step2", method = RequestMethod.GET)
	public String goSaveLookbookStep2(HttpServletRequest req, Model uiModel) {

		UserBO usrbo = null;

		if (GlamCmn.chkLoggdInStatus(req)) {
			UsrContainer uc = GlamCmn.getLoggedInUsrDtls(req);
			usrbo = uc.getUsrbo();

			LookbookForm lookbookform = new LookbookForm();

			uiModel.addAttribute("usrbo", usrbo);
			uiModel.addAttribute("lookbookform", lookbookform);

			return GlamCnst._VIEW_DSHBRD_LKBK_NEW_STEP2;
		} else {
			return "redirect:/login";
		}

	}


	/***************************************
	 * Request Type - POST
	 **************************************/

	@RequestMapping(value = "/dashboard/lookbook/postnew", method = RequestMethod.POST)
	public String doSaveLookbookStep1(@ModelAttribute LookbookForm lookbookform, BindingResult result, Model uiModel,
			HttpServletRequest req) {
		UserBO usrbo = null;

		UserLookbookDO lookbookdo = new UserLookbookDO();
		LookbookBO lookbook = new LookbookBO();

		UserDO usrdo = null;

		long lookbookid = 0;
		StringBuilder errormssg = new StringBuilder();

		BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

		String photoUploadUrl = "";
		UploadOptions uploadoptions = null;

		List<BlobKey> outfit1Keys = null;
		// List<BlobKey> outfit2Keys = null;
		// List<BlobKey> outfit3Keys = null;

		BlobKey outfit1_key = null;
		// BlobKey outfit2_key = null;
		// BlobKey outfit3_key = null;

		String imgUrlStr1 = "";
		// String imgUrlStr2 = "";
		// String imgUrlStr3 = "";

		BlobInfo blobInfo1 = null;
		// BlobInfo blobInfo2 = null;
		// BlobInfo blobInfo3 = null;

		boolean reqd_fields_empty = false;
		List<UserLookbookTagsDO> taglist = new ArrayList<UserLookbookTagsDO>();
		long gpoints = 0;

		// logger.info("********** Lookbookform : " + lookbookform);

		if (GlamCmn.chkLoggdInStatus(req)) {
			UsrContainer uc = GlamCmn.getLoggedInUsrDtls(req);
			usrbo = uc.getUsrbo();
			usrdo = uc.getUsrdo();

			// copy the submitted form data to the DO classes
			if (lookbookform != null) {

				if (StringUtils.isBlank(lookbookform.getLookbookname())
						|| StringUtils.isBlank(lookbookform.getLookbookme())) {
					logger.info("**** any of the required field is missing ... ");
					reqd_fields_empty = true;
				}

				if (reqd_fields_empty) {
					errormssg.append(" One of the required field is empty, pls correct...");

					// String _BUCKET_NAME = GlamCnst._LKBK_BUCKET_NAME +
					// usrbo.getAccount_username();
					String _BUCKET_NAME = GlamCnst._LKBK_BUCKET_NAME;

					uploadoptions = UploadOptions.Builder.withGoogleStorageBucketName(_BUCKET_NAME);
					photoUploadUrl = blobstoreService.createUploadUrl("/dashboard/lookbook/postnew", uploadoptions);

					uiModel.addAttribute("photoUploadUrl", photoUploadUrl);
					uiModel.addAttribute("usrbo", usrbo);
					uiModel.addAttribute("lookbookform", lookbookform);

					return GlamCnst._VIEW_DSHBRD_LKBK_POST_NEW;

				} else {
					lookbookdo.setLookbookname(StringUtils
							.trim(StringUtils.remove(StringUtils.remove(lookbookform.getLookbookname(), "/"), "?")));
					if (StringUtils.equalsIgnoreCase(lookbookform.getLookbookme(), "Yes")) {
						lookbookdo.setLookbookme(true);
					} else {
						lookbookdo.setLookbookme(false);
					}
					lookbookdo.setUserid(usrbo.getUserid());
					lookbookdo.setUsername(usrdo.getAccount_username());
					lookbookdo.setCreateddt(new Date());
					lookbookdo.setLastupdateddt(new Date());
					lookbookdo.setLookbook_publish_status("Yes");
					lookbookdo.setLookbookstyle(lookbookform.getLookbookstyle());
					lookbookdo.setLookbookseason(lookbookform.getLookbookseason());
					lookbookdo.setLookbookstory(lookbookform.getLookbookstory());
					lookbookdo.setLookbook_youtube_videourl(lookbookform.getLookbook_youtube_videourl());

					lookbookdo.setDisapproved(false);

					// prepare and upload photos and save the uploaded photo
					// links
					Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);

					// get the first uploaded image
					outfit1Keys = blobs.get("outfit1");
					// outfit2Keys = blobs.get("outfit2");
					// outfit3Keys = blobs.get("outfit3");
					boolean isuploadsccss = false;

					try {
						if (outfit1Keys != null && outfit1Keys.size() > 0) {
							logger.info("****** outfit1Keys size-" + outfit1Keys.size());
							outfit1_key = outfit1Keys.get(0);
							logger.info("****** outfit1_key str-" + outfit1_key.getKeyString());

							// check if a zero size file is uploaded or no file
							// is uploaded
							blobInfo1 = new BlobInfoFactory().loadBlobInfo(outfit1_key);

							if (blobInfo1 != null && blobInfo1.getSize() > 0) {
								imgUrlStr1 = GlamCmn.getUpldPhotoUrl(outfit1_key);
								lookbookdo.setGcs_photo_url1(imgUrlStr1);
								lookbookdo.setGcs_photo_key1(outfit1_key.getKeyString());
								gpoints = GlamCnst._GLAM_PTS_LKBK_CRT_RWRD1;
								isuploadsccss = true;
							} else {
								// no file is uploaded or zero size file is
								// uploaded so immediately delete it
								blobstoreService.delete(outfit1_key);
							}
						}
					} catch (Exception ex) {
						logger.info("**** error *** " + ex.getMessage());
						errormssg.append(" A generic system exception has occurred, please try again ");
					}

					// save the lookbook now
					if (isuploadsccss) {
						// generate unique SEO URL
						lookbookdo.setLookbook_seo_url(GlamCmn.genUnqueLookbookSEOUrl(lookbookdo.getLookbookname(),
								usrdo.getAccount_username()));
						
						if (lookbookform.getTags() != null) {
							String[] tagsarr = StringUtils.split(lookbookform.getTags(), ",");

							List<String> tagslist = new ArrayList<String>();

							for (String tag : tagsarr) {
								tagslist.add(StringUtils.trim(tag));
								if (StringUtils.isNotBlank(tag)) {
									UserLookbookTagsDO tagdo = new UserLookbookTagsDO();
									tagdo.setLookbookid(lookbookdo.getLookbookid());
									tagdo.setTagstr(tag);
									taglist.add(tagdo);
								}
							}

							lookbookdo.setTags(tagslist);
						}
						
						logger.info("************************ Saving the lookbook now **************************** ");
						lookbookid = OfyService.ofy().save().entity(lookbookdo).now().getId();

						// save the tags
						Iterator<UserLookbookTagsDO> tagItr = taglist.iterator();
						while (tagItr.hasNext()) {
							UserLookbookTagsDO temptag = (UserLookbookTagsDO) tagItr.next();
							temptag.setLookbookid(lookbookid);
							OfyService.ofy().save().entity(temptag);
						}

						String[] ignoreFieldsLkbk = { "tags" };
						BeanUtils.copyProperties(lookbookdo, lookbook, ignoreFieldsLkbk);

						// update the glam points of the user
						usrdo.setMy_glam_points(usrdo.getMy_glam_points() + gpoints);

						OfyService.ofy().save().entity(usrdo).now();

						GlamCmn.saveGlamPointsActivity(usrdo.getUserid(), usrdo.getAccount_username(),
								GlamCnst._ATVY_PTS_ADDED, Long.valueOf(gpoints).intValue(),
								" Creating lookbook <font color=red>" + lookbookdo.getLookbookname() + "</font>");
						
						// update the user activity
						GlamCmn.saveUsrActivity(usrdo.getUserid(), usrdo.getAccount_username(),
								GlamCnst._ATVY_LKBK_CREATE, lookbookid, lookbookdo.getLookbook_seo_url(), "");

						String email_txt = "Dear Fashionista, <br/>" + "<p>Thanks for posting your lookbook with us."
								+ " To see the lookbook visit the following link "
								+ "<b><a href='http://www.glamfiesta.com/fashion/" + lookbookdo.getLookbook_seo_url()
								+ "'>" + lookbookdo.getLookbookname() + "</a></b> </p>"
								+ "<p> Collaborate with us and post more looks and inspire other Fashionistas.</p>"
								+ "<p> Cheers!! <br/><br/> - Editor, Glam Fiesta <br/><br/> "
								+ "<b>Proudly running on Google Cloud Platform "
								+ "( <a href=www.glamfiesta.com/aboutus.htm>www.glamfiesta.com/aboutus.htm</a> )</b> </p>";
						GlamCmn.sendEmailToUsr(usrdo.getAccount_email(), usrdo.getAccount_username(),
								"Thanks for posting your lookbook at Glam Fiesta !!", email_txt, true);

						// now send lookbook email to next 25 fashionistas
						List<UserDO> newusrlist = OfyService.ofy().load().type(UserDO.class).order("-signup_dttm")
								.limit(35).list();
						if (newusrlist != null && newusrlist.size() > 0) {
							Iterator<UserDO> newusrlistItr = newusrlist.iterator();
							while (newusrlistItr.hasNext()) {
								UserDO newusrdo = newusrlistItr.next();
								String notify_email_txt = "<!doctype html><html xmlns='http://www.w3.org/1999/xhtml' xmlns:v='urn:schemas-microsoft-com:vml' xmlns:o='urn:schemas-microsoft-com:office:office'>	<head>		<!-- NAME: 1 COLUMN - BANDED -->		<!--[if gte mso 15]>		<xml>			<o:OfficeDocumentSettings>			<o:AllowPNG/>			<o:PixelsPerInch>96</o:PixelsPerInch>			</o:OfficeDocumentSettings>		</xml>		<![endif]-->		<meta charset='UTF-8'>        <meta http-equiv='X-UA-Compatible' content='IE=edge'>        <meta name='viewport' content='width=device-width, initial-scale=1'>		<title>GlamFiesta - Social Network for Fashion Lovers</title>            <style type='text/css'>		p{			margin:10px 0;			padding:0;		}		table{			border-collapse:collapse;		}		h1,h2,h3,h4,h5,h6{			display:block;			margin:0;			padding:0;		}		img,a img{			border:0;			height:auto;			outline:none;			text-decoration:none;		}		body,#bodyTable,#bodyCell{			height:100%;			margin:0;			padding:0;			width:100%;		}		#outlook a{			padding:0;		}		img{			-ms-interpolation-mode:bicubic;		}		table{			mso-table-lspace:0pt;			mso-table-rspace:0pt;		}		.ReadMsgBody{			width:100%;		}		.ExternalClass{			width:100%;		}		p,a,li,td,blockquote{			mso-line-height-rule:exactly;		}		a[href^=tel],a[href^=sms]{			color:inherit;			cursor:default;			text-decoration:none;		}		p,a,li,td,body,table,blockquote{			-ms-text-size-adjust:100%;			-webkit-text-size-adjust:100%;		}		.ExternalClass,.ExternalClass p,.ExternalClass td,.ExternalClass div,.ExternalClass span,.ExternalClass font{			line-height:100%;		}		a[x-apple-data-detectors]{			color:inherit !important;			text-decoration:none !important;			font-size:inherit !important;			font-family:inherit !important;			font-weight:inherit !important;			line-height:inherit !important;		}		.templateContainer{			max-width:600px !important;		}		a.mcnButton{			display:block;		}		.mcnImage{			vertical-align:bottom;		}		.mcnTextContent{			word-break:break-word;		}		.mcnTextContent img{			height:auto !important;		}		.mcnDividerBlock{			table-layout:fixed !important;		}	/*	@tab Page	@section Background Style	@tip Set the background color and top border for your email. You may want to choose colors that match your company's branding.	*/		body,#bodyTable{			/*@editable*/background-color:#FAFAFA;		}	/*	@tab Page	@section Background Style	@tip Set the background color and top border for your email. You may want to choose colors that match your company's branding.	*/		#bodyCell{			/*@editable*/border-top:0;		}	/*	@tab Page	@section Heading 1	@tip Set the styling for all first-level headings in your emails. These should be the largest of your headings.	@style heading 1	*/		h1{			/*@editable*/color:#202020;			/*@editable*/font-family:Helvetica;			/*@editable*/font-size:26px;			/*@editable*/font-style:normal;			/*@editable*/font-weight:bold;			/*@editable*/line-height:125%;			/*@editable*/letter-spacing:normal;			/*@editable*/text-align:left;		}	/*	@tab Page	@section Heading 2	@tip Set the styling for all second-level headings in your emails.	@style heading 2	*/		h2{			/*@editable*/color:#202020;			/*@editable*/font-family:Helvetica;			/*@editable*/font-size:22px;			/*@editable*/font-style:normal;			/*@editable*/font-weight:bold;			/*@editable*/line-height:125%;			/*@editable*/letter-spacing:normal;			/*@editable*/text-align:left;		}	/*	@tab Page	@section Heading 3	@tip Set the styling for all third-level headings in your emails.	@style heading 3	*/		h3{			/*@editable*/color:#202020;			/*@editable*/font-family:Helvetica;			/*@editable*/font-size:20px;			/*@editable*/font-style:normal;			/*@editable*/font-weight:bold;			/*@editable*/line-height:125%;			/*@editable*/letter-spacing:normal;			/*@editable*/text-align:left;		}	/*	@tab Page	@section Heading 4	@tip Set the styling for all fourth-level headings in your emails. These should be the smallest of your headings.	@style heading 4	*/		h4{			/*@editable*/color:#202020;			/*@editable*/font-family:Helvetica;			/*@editable*/font-size:18px;			/*@editable*/font-style:normal;			/*@editable*/font-weight:bold;			/*@editable*/line-height:125%;			/*@editable*/letter-spacing:normal;			/*@editable*/text-align:left;		}	/*	@tab Preheader	@section Preheader Style	@tip Set the background color and borders for your email's preheader area.	*/		#templatePreheader{			/*@editable*/background-color:#FAFAFA;			/*@editable*/border-top:0;			/*@editable*/border-bottom:0;			/*@editable*/padding-top:9px;			/*@editable*/padding-bottom:9px;		}	/*	@tab Preheader	@section Preheader Text	@tip Set the styling for your email's preheader text. Choose a size and color that is easy to read.	*/		#templatePreheader .mcnTextContent,#templatePreheader .mcnTextContent p{			/*@editable*/color:#656565;			/*@editable*/font-family:Helvetica;			/*@editable*/font-size:12px;			/*@editable*/line-height:150%;			/*@editable*/text-align:left;		}	/*	@tab Preheader	@section Preheader Link	@tip Set the styling for your email's preheader links. Choose a color that helps them stand out from your text.	*/		#templatePreheader .mcnTextContent a,#templatePreheader .mcnTextContent p a{			/*@editable*/color:#656565;			/*@editable*/font-weight:normal;			/*@editable*/text-decoration:underline;		}	/*	@tab Header	@section Header Style	@tip Set the background color and borders for your email's header area.	*/		#templateHeader{			/*@editable*/background-color:#FFFFFF;			/*@editable*/border-top:0;			/*@editable*/border-bottom:0;			/*@editable*/padding-top:9px;			/*@editable*/padding-bottom:0;		}	/*	@tab Header	@section Header Text	@tip Set the styling for your email's header text. Choose a size and color that is easy to read.	*/		#templateHeader .mcnTextContent,#templateHeader .mcnTextContent p{			/*@editable*/color:#202020;			/*@editable*/font-family:Helvetica;			/*@editable*/font-size:16px;			/*@editable*/line-height:150%;			/*@editable*/text-align:left;		}	/*	@tab Header	@section Header Link	@tip Set the styling for your email's header links. Choose a color that helps them stand out from your text.	*/		#templateHeader .mcnTextContent a,#templateHeader .mcnTextContent p a{			/*@editable*/color:#2BAADF;			/*@editable*/font-weight:normal;			/*@editable*/text-decoration:underline;		}	/*	@tab Body	@section Body Style	@tip Set the background color and borders for your email's body area.	*/		#templateBody{			/*@editable*/background-color:#FFFFFF;			/*@editable*/border-top:0;			/*@editable*/border-bottom:0;			/*@editable*/padding-top:9px;			/*@editable*/padding-bottom:9px;		}	/*	@tab Body	@section Body Text	@tip Set the styling for your email's body text. Choose a size and color that is easy to read.	*/		#templateBody .mcnTextContent,#templateBody .mcnTextContent p{			/*@editable*/color:#202020;			/*@editable*/font-family:Helvetica;			/*@editable*/font-size:16px;			/*@editable*/line-height:150%;			/*@editable*/text-align:left;		}	/*	@tab Body	@section Body Link	@tip Set the styling for your email's body links. Choose a color that helps them stand out from your text.	*/		#templateBody .mcnTextContent a,#templateBody .mcnTextContent p a{			/*@editable*/color:#2BAADF;			/*@editable*/font-weight:normal;			/*@editable*/text-decoration:underline;		}	/*	@tab Footer	@section Footer Style	@tip Set the background color and borders for your email's footer area.	*/		#templateFooter{			/*@editable*/background-color:#FAFAFA;			/*@editable*/border-top:0;			/*@editable*/border-bottom:0;			/*@editable*/padding-top:9px;			/*@editable*/padding-bottom:9px;		}	/*	@tab Footer	@section Footer Text	@tip Set the styling for your email's footer text. Choose a size and color that is easy to read.	*/		#templateFooter .mcnTextContent,#templateFooter .mcnTextContent p{			/*@editable*/color:#656565;			/*@editable*/font-family:Helvetica;			/*@editable*/font-size:12px;			/*@editable*/line-height:150%;			/*@editable*/text-align:center;		}	/*	@tab Footer	@section Footer Link	@tip Set the styling for your email's footer links. Choose a color that helps them stand out from your text.	*/		#templateFooter .mcnTextContent a,#templateFooter .mcnTextContent p a{			/*@editable*/color:#656565;			/*@editable*/font-weight:normal;			/*@editable*/text-decoration:underline;		}	@media only screen and (min-width:768px){		.templateContainer{			width:600px !important;		}}	@media only screen and (max-width: 480px){		body,table,td,p,a,li,blockquote{			-webkit-text-size-adjust:none !important;		}}	@media only screen and (max-width: 480px){		body{			width:100% !important;			min-width:100% !important;		}}	@media only screen and (max-width: 480px){		#bodyCell{			padding-top:10px !important;		}}	@media only screen and (max-width: 480px){		.mcnImage{			width:100% !important;		}}	@media only screen and (max-width: 480px){		.mcnCaptionTopContent,.mcnCaptionBottomContent,.mcnTextContentContainer,.mcnBoxedTextContentContainer,.mcnImageGroupContentContainer,.mcnCaptionLeftTextContentContainer,.mcnCaptionRightTextContentContainer,.mcnCaptionLeftImageContentContainer,.mcnCaptionRightImageContentContainer,.mcnImageCardLeftTextContentContainer,.mcnImageCardRightTextContentContainer{			max-width:100% !important;			width:100% !important;		}}	@media only screen and (max-width: 480px){		.mcnBoxedTextContentContainer{			min-width:100% !important;		}}	@media only screen and (max-width: 480px){		.mcnImageGroupContent{			padding:9px !important;		}}	@media only screen and (max-width: 480px){		.mcnCaptionLeftContentOuter .mcnTextContent,.mcnCaptionRightContentOuter .mcnTextContent{			padding-top:9px !important;		}}	@media only screen and (max-width: 480px){		.mcnImageCardTopImageContent,.mcnCaptionBlockInner .mcnCaptionTopContent:last-child .mcnTextContent{			padding-top:18px !important;		}}	@media only screen and (max-width: 480px){		.mcnImageCardBottomImageContent{			padding-bottom:9px !important;		}}	@media only screen and (max-width: 480px){		.mcnImageGroupBlockInner{			padding-top:0 !important;			padding-bottom:0 !important;		}}	@media only screen and (max-width: 480px){		.mcnImageGroupBlockOuter{			padding-top:9px !important;			padding-bottom:9px !important;		}}	@media only screen and (max-width: 480px){		.mcnTextContent,.mcnBoxedTextContentColumn{			padding-right:18px !important;			padding-left:18px !important;		}}	@media only screen and (max-width: 480px){		.mcnImageCardLeftImageContent,.mcnImageCardRightImageContent{			padding-right:18px !important;			padding-bottom:0 !important;			padding-left:18px !important;		}}	@media only screen and (max-width: 480px){		.mcpreview-image-uploader{			display:none !important;			width:100% !important;		}}	@media only screen and (max-width: 480px){	/*	@tab Mobile Styles	@section Heading 1	@tip Make the first-level headings larger in size for better readability on small screens.	*/		h1{			/*@editable*/font-size:22px !important;			/*@editable*/line-height:125% !important;		}}	@media only screen and (max-width: 480px){	/*	@tab Mobile Styles	@section Heading 2	@tip Make the second-level headings larger in size for better readability on small screens.	*/		h2{			/*@editable*/font-size:20px !important;			/*@editable*/line-height:125% !important;		}}	@media only screen and (max-width: 480px){	/*	@tab Mobile Styles	@section Heading 3	@tip Make the third-level headings larger in size for better readability on small screens.	*/		h3{			/*@editable*/font-size:18px !important;			/*@editable*/line-height:125% !important;		}}	@media only screen and (max-width: 480px){	/*	@tab Mobile Styles	@section Heading 4	@tip Make the fourth-level headings larger in size for better readability on small screens.	*/		h4{			/*@editable*/font-size:16px !important;			/*@editable*/line-height:150% !important;		}}	@media only screen and (max-width: 480px){	/*	@tab Mobile Styles	@section Boxed Text	@tip Make the boxed text larger in size for better readability on small screens. We recommend a font size of at least 16px.	*/		.mcnBoxedTextContentContainer .mcnTextContent,.mcnBoxedTextContentContainer .mcnTextContent p{			/*@editable*/font-size:14px !important;			/*@editable*/line-height:150% !important;		}}	@media only screen and (max-width: 480px){	/*	@tab Mobile Styles	@section Preheader Visibility	@tip Set the visibility of the email's preheader on small screens. You can hide it to save space.	*/		#templatePreheader{			/*@editable*/display:block !important;		}}	@media only screen and (max-width: 480px){	/*	@tab Mobile Styles	@section Preheader Text	@tip Make the preheader text larger in size for better readability on small screens.	*/		#templatePreheader .mcnTextContent,#templatePreheader .mcnTextContent p{			/*@editable*/font-size:14px !important;			/*@editable*/line-height:150% !important;		}}	@media only screen and (max-width: 480px){	/*	@tab Mobile Styles	@section Header Text	@tip Make the header text larger in size for better readability on small screens.	*/		#templateHeader .mcnTextContent,#templateHeader .mcnTextContent p{			/*@editable*/font-size:16px !important;			/*@editable*/line-height:150% !important;		}}	@media only screen and (max-width: 480px){	/*	@tab Mobile Styles	@section Body Text	@tip Make the body text larger in size for better readability on small screens. We recommend a font size of at least 16px.	*/		#templateBody .mcnTextContent,#templateBody .mcnTextContent p{			/*@editable*/font-size:16px !important;			/*@editable*/line-height:150% !important;		}}	@media only screen and (max-width: 480px){	/*	@tab Mobile Styles	@section Footer Text	@tip Make the footer content text larger in size for better readability on small screens.	*/		#templateFooter .mcnTextContent,#templateFooter .mcnTextContent p{			/*@editable*/font-size:14px !important;			/*@editable*/line-height:150% !important;		}}</style></head>    <body>        <center>            <table align='center' border='0' cellpadding='0' cellspacing='0' height='100%' width='100%' id='bodyTable'>                <tr>                    <td align='center' valign='top' id='bodyCell'>                        <!-- BEGIN TEMPLATE // -->                        <table border='0' cellpadding='0' cellspacing='0' width='100%'>                            <tr>								<td align='center' valign='top' id='templatePreheader'>									<!--[if gte mso 9]>									<table align='center' border='0' cellspacing='0' cellpadding='0' width='600' style='width:600px;'>									<tr>									<td align='center' valign='top' width='600' style='width:600px;'>									<![endif]-->									<table align='center' border='0' cellpadding='0' cellspacing='0' width='100%' class='templateContainer'>										<tr>                                			<td valign='top' class='preheaderContainer'><table border='0' cellpadding='0' cellspacing='0' width='100%' class='mcnCaptionBlock'>    <tbody class='mcnCaptionBlockOuter'>        <tr>            <td class='mcnCaptionBlockInner' valign='top' style='padding:9px;'>                <table border='0' cellpadding='0' cellspacing='0' class='mcnCaptionRightContentOuter' width='100%'>    <tbody><tr>        <td valign='top' class='mcnCaptionRightContentInner' style='padding:0 9px ;'>            <table align='left' border='0' cellpadding='0' cellspacing='0' class='mcnCaptionRightImageContentContainer'>                <tbody><tr>                    <td class='mcnCaptionRightImageContent' valign='top'>                                                                    <img alt='' src='https://gallery.mailchimp.com/2967e55656c8ddb374d53f550/images/5dd89c75-3983-4fb6-92b7-e46266e9daf2.png' width='246' style='max-width:246px;' class='mcnImage'>                                                                </td>                </tr>            </tbody></table>            <table class='mcnCaptionRightTextContentContainer' align='right' border='0' cellpadding='0' cellspacing='0' width='263'>                <tbody><tr>                    <td valign='top' class='mcnTextContent'>                        <span style='font-family:comic sans ms,marker felt-thin,arial,sans-serif'>Social Network for Fashion Lovers &amp; YouTube Fashionistas...</span>                    </td>                </tr>            </tbody></table>        </td>    </tr></tbody></table>            </td>        </tr>    </tbody></table></td>										</tr>									</table>									<!--[if gte mso 9]>									</td>									</tr>									</table>									<![endif]-->								</td>                            </tr>							<tr>								<td align='center' valign='top' id='templateHeader'>									<!--[if gte mso 9]>									<table align='center' border='0' cellspacing='0' cellpadding='0' width='600' style='width:600px;'>									<tr>									<td align='center' valign='top' width='600' style='width:600px;'>									<![endif]-->									<table align='center' border='0' cellpadding='0' cellspacing='0' width='100%' class='templateContainer'>										<tr>                                			<td valign='top' class='headerContainer'><table border='0' cellpadding='0' cellspacing='0' width='100%' class='mcnCaptionBlock'>    <tbody class='mcnCaptionBlockOuter'>        <tr>            <td class='mcnCaptionBlockInner' valign='top' style='padding:9px;'>                <table border='0' cellpadding='0' cellspacing='0' class='mcnCaptionRightContentOuter' width='100%'>    <tbody><tr>        <td valign='top' class='mcnCaptionRightContentInner' style='padding:0 9px ;'>            <table align='left' border='0' cellpadding='0' cellspacing='0' class='mcnCaptionRightImageContentContainer'>                <tbody><tr>                    <td class='mcnCaptionRightImageContent' valign='top'>                                                                    "
										+ "<img alt='' src='" + lookbookdo.getGcs_photo_url1()
										+ "' width='263' style='max-width:512px;' class='mcnImage'>                                                                </td>                </tr>            </tbody></table>            <table class='mcnCaptionRightTextContentContainer' align='right' border='0' cellpadding='0' cellspacing='0' width='263'>                <tbody><tr>                    <td valign='top' class='mcnTextContent'>                        "
										+ "<span style='font-family:comic sans ms,marker felt-thin,arial,sans-serif'>"
										+ "<b><a href='http://www.glamfiesta.com/fashionistas/" + usrdo.getAccount_username()
										+ "'>" + usrdo.getAccount_username()
										+ "</a></b> has just created a lookbook. Be the first to Like it.<br><br>"
										+ "<a href='http://www.glamfiesta.com/fashion/"
										+ lookbookdo.getLookbook_seo_url()
										+ "' target='_blank'>View Lookbook</a></span> </td> </tr>  "
										+ "</tbody></table></td>    </tr></tbody></table> </td> </tr>    </tbody></table></td>"
										+ "</tr></table><!--[if gte mso 9]>									</td>									</tr>									</table>									<![endif]-->								</td>                            </tr>							<tr>								<td align='center' valign='top' id='templateBody'>									<!--[if gte mso 9]>									<table align='center' border='0' cellspacing='0' cellpadding='0' width='600' style='width:600px;'>									<tr>									<td align='center' valign='top' width='600' style='width:600px;'>									<![endif]-->									<table align='center' border='0' cellpadding='0' cellspacing='0' width='100%' class='templateContainer'>										<tr>                                			<td valign='top' class='bodyContainer'><table border='0' cellpadding='0' cellspacing='0' width='100%' class='mcnShareBlock' style='min-width:100%;'>    <tbody class='mcnShareBlockOuter'>            <tr>                <td valign='top' style='padding:9px' class='mcnShareBlockInner'>                    <table border='0' cellpadding='0' cellspacing='0' width='100%' class='mcnShareContentContainer' style='min-width:100%;'>    <tbody><tr>        <td align='center' style='padding-top:0; padding-left:9px; padding-bottom:0; padding-right:9px;'>            <table align='center' border='0' cellpadding='0' cellspacing='0' width='100%' style='min-width:100%;' class='mcnShareContent'>                <tbody><tr>                    <td align='center' valign='top' class='mcnShareContentItemContainer' style='padding-top:9px; padding-right:9px; padding-left:9px;'>                        <table align='center' border='0' cellpadding='0' cellspacing='0'>                            <tbody><tr>                                <td align='left' valign='top'>                                    <!--[if mso]>                                    <table align='center' border='0' cellspacing='0' cellpadding='0'>                                    <tr>                                    <![endif]-->                                                                            <!--[if mso]>                                        <td align='center' valign='top'>                                        <![endif]-->                                        <table align='left' border='0' cellpadding='0' cellspacing='0'>                                            <tbody><tr>                                                <td valign='top' style='padding-right:9px; padding-bottom:9px;' class='mcnShareContentItemContainer'>                                                    <table border='0' cellpadding='0' cellspacing='0' width='' class='mcnShareContentItem' style='border-collapse: separate; border-radius: 3px;'>                                                        <tbody><tr>                                                            <td align='left' valign='middle' style='padding-top:5px; padding-right:9px; padding-bottom:5px; padding-left:9px;'>                                                                <table align='left' border='0' cellpadding='0' cellspacing='0' width=''>                                                                    <tbody><tr>                                                                        <td align='center' valign='middle' width='24' class='mcnShareIconContent'>                                                                            <a href='http://www.facebook.com/sharer/sharer.php?u=http://www.glamfiesta.com' target='_blank'><img src='http://cdn-images.mailchimp.com/icons/social-block-v2/outline-dark-facebook-48.png' style='display:block;' height='24' width='24' class=''></a>                                                                        </td>                                                                        <td align='left' valign='middle' class='mcnShareTextContent' style='padding-left:5px;'>                                                                            <a href='http://www.facebook.com/sharer/sharer.php?u=http://www.glamfiesta.com' target='' style='color: #202020;font-family: Arial;font-size: 12px;font-weight: normal;line-height: normal;text-align: center;text-decoration: none;'>Share</a>                                                                        </td>                                                                    </tr>                                                                </tbody></table>                                                            </td>                                                        </tr>                                                    </tbody></table>                                                </td>                                            </tr>                                        </tbody></table>                                        <!--[if mso]>                                        </td>                                        <![endif]-->                                                                            <!--[if mso]>                                        <td align='center' valign='top'>                                        <![endif]-->                                        <table align='left' border='0' cellpadding='0' cellspacing='0'>                                            <tbody><tr>                                                <td valign='top' style='padding-right:9px; padding-bottom:9px;' class='mcnShareContentItemContainer'>                                                    <table border='0' cellpadding='0' cellspacing='0' width='' class='mcnShareContentItem' style='border-collapse: separate; border-radius: 3px;'>                                                        <tbody><tr>                                                            <td align='left' valign='middle' style='padding-top:5px; padding-right:9px; padding-bottom:5px; padding-left:9px;'>                                                                <table align='left' border='0' cellpadding='0' cellspacing='0' width=''>                                                                    <tbody><tr>                                                                        <td align='center' valign='middle' width='24' class='mcnShareIconContent'>                                                                            <a href='http://twitter.com/intent/tweet?text=http://www.glamfiesta.com' target='_blank'><img src='http://cdn-images.mailchimp.com/icons/social-block-v2/outline-dark-twitter-48.png' style='display:block;' height='24' width='24' class=''></a>                                                                        </td>                                                                        <td align='left' valign='middle' class='mcnShareTextContent' style='padding-left:5px;'>                                                                            <a href='http://twitter.com/intent/tweet?text=http://www.glamfiesta.com' target='' style='color: #202020;font-family: Arial;font-size: 12px;font-weight: normal;line-height: normal;text-align: center;text-decoration: none;'>Tweet</a>                                                                        </td>                                                                    </tr>                                                                </tbody></table>                                                            </td>                                                        </tr>                                                    </tbody></table>                                                </td>                                            </tr>                                        </tbody></table>                                        <!--[if mso]>                                        </td>                                        <![endif]-->                                                                            <!--[if mso]>                                        <td align='center' valign='top'>                                        <![endif]-->                                        <table align='left' border='0' cellpadding='0' cellspacing='0'>                                            <tbody><tr>                                                <td valign='top' style='padding-right:9px; padding-bottom:9px;' class='mcnShareContentItemContainer'>                                                    <table border='0' cellpadding='0' cellspacing='0' width='' class='mcnShareContentItem' style='border-collapse: separate; border-radius: 3px;'>                                                        <tbody><tr>                                                            <td align='left' valign='middle' style='padding-top:5px; padding-right:9px; padding-bottom:5px; padding-left:9px;'>                                                                <table align='left' border='0' cellpadding='0' cellspacing='0' width=''>                                                                    <tbody><tr>                                                                        <td align='center' valign='middle' width='24' class='mcnShareIconContent'>                                                                            <a href='mailto:' target='_blank'><img src='http://cdn-images.mailchimp.com/icons/social-block-v2/outline-dark-forwardtofriend-48.png' style='display:block;' height='24' width='24' class=''></a>                                                                        </td>                                                                        <td align='left' valign='middle' class='mcnShareTextContent' style='padding-left:5px;'>                                                                            <a href='mailto:' target='' style='color: #202020;font-family: Arial;font-size: 12px;font-weight: normal;line-height: normal;text-align: center;text-decoration: none;'>Forward</a>                                                                        </td>                                                                    </tr>                                                                </tbody></table>                                                            </td>                                                        </tr>                                                    </tbody></table>                                                </td>                                            </tr>                                        </tbody></table>                                        <!--[if mso]>                                        </td>                                        <![endif]-->                                                                            <!--[if mso]>                                        <td align='center' valign='top'>                                        <![endif]-->                                        <table align='left' border='0' cellpadding='0' cellspacing='0'>                                            <tbody><tr>                                                <td valign='top' style='padding-right:9px; padding-bottom:9px;' class='mcnShareContentItemContainer'>                                                    <table border='0' cellpadding='0' cellspacing='0' width='' class='mcnShareContentItem' style='border-collapse: separate; border-radius: 3px;'>                                                        <tbody><tr>                                                            <td align='left' valign='middle' style='padding-top:5px; padding-right:9px; padding-bottom:5px; padding-left:9px;'>                                                                <table align='left' border='0' cellpadding='0' cellspacing='0' width=''>                                                                    <tbody><tr>                                                                        <td align='center' valign='middle' width='24' class='mcnShareIconContent'>                                                                            <a href='https://plus.google.com/share?url=http://www.glamfiesta.com' target='_blank'><img src='http://cdn-images.mailchimp.com/icons/social-block-v2/outline-dark-googleplus-48.png' style='display:block;' height='24' width='24' class=''></a>                                                                        </td>                                                                        <td align='left' valign='middle' class='mcnShareTextContent' style='padding-left:5px;'>                                                                            <a href='https://plus.google.com/share?url=http://www.glamfiesta.com' target='' style='color: #202020;font-family: Arial;font-size: 12px;font-weight: normal;line-height: normal;text-align: center;text-decoration: none;'>+1</a>                                                                        </td>                                                                    </tr>                                                                </tbody></table>                                                            </td>                                                        </tr>                                                    </tbody></table>                                                </td>                                            </tr>                                        </tbody></table>                                        <!--[if mso]>                                        </td>                                        <![endif]-->                                                                            <!--[if mso]>                                        <td align='center' valign='top'>                                        <![endif]-->                                        <table align='left' border='0' cellpadding='0' cellspacing='0'>                                            <tbody><tr>                                                <td valign='top' style='padding-right:0; padding-bottom:9px;' class='mcnShareContentItemContainer'>                                                    <table border='0' cellpadding='0' cellspacing='0' width='' class='mcnShareContentItem' style='border-collapse: separate; border-radius: 3px;'>                                                        <tbody><tr>                                                            <td align='left' valign='middle' style='padding-top:5px; padding-right:9px; padding-bottom:5px; padding-left:9px;'>                                                                <table align='left' border='0' cellpadding='0' cellspacing='0' width=''>                                                                    <tbody><tr>                                                                        <td align='center' valign='middle' width='24' class='mcnShareIconContent'>                                                                            <a href='https://www.pinterest.com/pin/find/?url=http://www.glamfiesta.com' target='_blank'><img src='http://cdn-images.mailchimp.com/icons/social-block-v2/outline-dark-pinterest-48.png' style='display:block;' height='24' width='24' class=''></a>                                                                        </td>                                                                        <td align='left' valign='middle' class='mcnShareTextContent' style='padding-left:5px;'>                                                                            <a href='https://www.pinterest.com/pin/find/?url=http://www.glamfiesta.com' target='' style='color: #202020;font-family: Arial;font-size: 12px;font-weight: normal;line-height: normal;text-align: center;text-decoration: none;'>Pin</a>                                                                        </td>                                                                    </tr>                                                                </tbody></table>                                                            </td>                                                        </tr>                                                    </tbody></table>                                                </td>                                            </tr>                                        </tbody></table>                                        <!--[if mso]>                                        </td>                                        <![endif]-->                                                                        <!--[if mso]>                                    </tr>                                    </table>                                    <![endif]-->                                </td>                            </tr>                        </tbody></table>                    </td>                </tr>            </tbody></table>        </td>    </tr></tbody></table>                </td>            </tr>    </tbody></table></td>										</tr>									</table>									<!--[if gte mso 9]>									</td>									</tr>									</table>									<![endif]-->								</td>                            </tr>                            <tr>								<td align='center' valign='top' id='templateFooter'>									<!--[if gte mso 9]>									<table align='center' border='0' cellspacing='0' cellpadding='0' width='600' style='width:600px;'>									<tr>									<td align='center' valign='top' width='600' style='width:600px;'>									<![endif]-->									<table align='center' border='0' cellpadding='0' cellspacing='0' width='100%' class='templateContainer'>										<tr>                                			<td valign='top' class='footerContainer'><table border='0' cellpadding='0' cellspacing='0' width='100%' class='mcnDividerBlock' style='min-width:100%;'>    <tbody class='mcnDividerBlockOuter'>        <tr>            <td class='mcnDividerBlockInner' style='min-width: 100%; padding: 10px 18px 25px;'>                <table class='mcnDividerContent' border='0' cellpadding='0' cellspacing='0' width='100%' style='min-width: 100%;border-top-width: 2px;border-top-style: solid;border-top-color: #EEEEEE;'>                    <tbody><tr>                        <td>                            <span></span>                        </td>                    </tr>                </tbody></table><!--                            <td class='mcnDividerBlockInner' style='padding: 18px;'>                <hr class='mcnDividerContent' style='border-bottom-color:none; border-left-color:none; border-right-color:none; border-bottom-width:0; border-left-width:0; border-right-width:0; margin-top:0; margin-right:0; margin-bottom:0; margin-left:0;' />-->            </td>        </tr>    </tbody></table></td>										</tr>									</table>									<!--[if gte mso 9]>									</td>									</tr>									</table>									<![endif]-->								</td>                            </tr>                        </table>                        <!-- // END TEMPLATE -->                    </td>                </tr>            </table>        </center>    </body></html>";
								// if(StringUtils.equalsIgnoreCase(newusrdo.getAccount_email(),
								// "joshfashion1990@myway.com")) {
								// logger.info("**** sending email to " +
								// newusrdo.getAccount_email() + " with content
								// - ");
								// logger.info(notify_email_txt);
								GlamCmn.sendEmailToUsr(newusrdo.getAccount_email(), newusrdo.getAccount_username(),
										"Recently posted lookbook you may like... !!", notify_email_txt, true);
								// }

							}
						}
					}
					uiModel.addAttribute("succsmssg", "Your Lookbook has been created successfully...");
					uiModel.addAttribute("errormssg", errormssg.toString());
					uiModel.addAttribute("lookbook", lookbook);

				}
			}

			uiModel.addAttribute("usrbo", usrbo);
			// uiModel.addAttribute("lookbookform", lookbookform);

			return GlamCnst._VIEW_DSHBRD_LKBK_NEW_SUCCSS;
		} else {
			return "redirect:/login";
		}
	}
}
