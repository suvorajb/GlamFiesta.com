package com.app.beta.glam.fiesta;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.beta.glam.fiesta.cmn.GlamCmn;
import com.app.beta.glam.fiesta.cmn.OfyService;
import com.app.beta.glam.fiesta.db.TopStreetStyleDO;
import com.app.beta.glam.fiesta.db.GlobalTagsDO;
import com.app.beta.glam.fiesta.db.UserDO;
import com.app.beta.glam.fiesta.db.UserLookbookDO;
import com.app.beta.glam.fiesta.form.LookbookForm;
import com.app.beta.glam.fiesta.form.MiscForm;
import com.app.beta.glam.fiesta.form.TopStyleForm;
import com.app.beta.glam.fiesta.model.LookbookBO;
import com.app.beta.glam.fiesta.model.UserBO;
import com.app.beta.glam.fiesta.model.UsrContainer;

@Controller
public class AdmnLookbookController {
	
	private static final Logger logger = Logger.getLogger(FashionistasController.class.getCanonicalName());
	
	@RequestMapping(value = "/mnda/ftrdlkbk", method = RequestMethod.GET)
	public String goAdmnFtrdLkbks(HttpServletRequest req, Model uiModel) {
		UserBO usrbo = null;
		UserDO usrdo = null;
		LookbookForm lkbkform = new LookbookForm();

		if (GlamCmn.chkLoggdInStatus(req)) {
			UsrContainer uc = GlamCmn.getLoggedInUsrDtls(req);
			usrbo = uc.getUsrbo();
			usrdo = uc.getUsrdo();

			uiModel.addAttribute("usrbo", usrbo);
			uiModel.addAttribute("lkbkform", lkbkform);

			if (StringUtils.equalsIgnoreCase(usrdo.getAccount_username(),
					"suvoraj239")) {
				return "admn_mng_ftrdlkbk";
			}
		}

		return "redirect:/dashboard";
	}

	@RequestMapping(value = "/mnda/topstyle", method = RequestMethod.POST)
	public String doMngFtrdLkbks(@ModelAttribute TopStyleForm topstyleform,
			BindingResult result, Model uiModel, HttpServletRequest req) {

		UserBO usrbo = null;
		UserDO usrdo = null;
		
		List<UserLookbookDO> lkbklist = null;
		UserLookbookDO ulkbkdo = null;
		StringBuilder succsmssg = new StringBuilder();
		UserDO udo = null;
		
		if (GlamCmn.chkLoggdInStatus(req)) {
			UsrContainer uc = GlamCmn.getLoggedInUsrDtls(req);
			usrbo = uc.getUsrbo();
			usrdo = uc.getUsrdo();

			if (StringUtils.equalsIgnoreCase(usrdo.getAccount_username(),
					"suvoraj239")) {
				String fashn_seo_url = topstyleform.getLkbk_seo_url();

				lkbklist = OfyService.ofy().load()
										  .type(UserLookbookDO.class)
										  .filter("lookbook_seo_url", fashn_seo_url)
										  .list();
				if(lkbklist!=null && lkbklist.size()>0) {
					ulkbkdo = lkbklist.get(0);
					TopStreetStyleDO ftrdlkbkdo = new TopStreetStyleDO();

					ftrdlkbkdo.setLkbk_id(ulkbkdo.getLookbookid());
					ftrdlkbkdo.setLkbk_nm(ulkbkdo.getLookbookname());
					ftrdlkbkdo.setLkbk_seo_url(ulkbkdo.getLookbook_seo_url());
					ftrdlkbkdo.setLkbk_imgstr(ulkbkdo.getGcs_photo_url1());

					udo = OfyService.ofy().load()
										  .type(UserDO.class)
										  .id(ulkbkdo.getUserid().longValue())
										  .get();

					ftrdlkbkdo.setLkbk_username(udo.getAccount_username());
					ftrdlkbkdo.setLkbk_user_locn(GlamCmn.getUsrLocn(udo));
					ftrdlkbkdo.setLkbk_uavatar(udo.getUavatar());

					OfyService.ofy().save().entity(ftrdlkbkdo).now();
					
				}
				

				succsmssg.append(" Top street style saved successfully");
			}
		}
		
		uiModel.addAttribute("succsmssg", succsmssg);
		uiModel.addAttribute("usrbo", usrbo);
		
		return "redirect:/dashboard";
	}

	@RequestMapping(value = "/mnda/lkbk/{lookbookid}", method = RequestMethod.GET)
	public String goAdmnLkbkMngPage(
			@PathVariable("lookbookid") long lookbookid,
			HttpServletRequest req, Model uiModel) {

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


			if (StringUtils.equalsIgnoreCase(usrdo.getAccount_username(),
					"suvoraj239")) {

				// retrieve all lookbook that user has created
				lookbookdo = OfyService.ofy().load().type(UserLookbookDO.class)
						.id(lookbookid).get();
				if (lookbookdo != null) {
					String[] ignorefields = { "disapproved", "lookbookme",
							"tags" };
					BeanUtils.copyProperties(lookbookdo, lookbookform,
							ignorefields);

					if (StringUtils.isNotBlank(lookbookdo.getGcs_photo_url1())) {
						lookbookbo.setGcs_photo_url1(lookbookdo
								.getGcs_photo_url1());
					}

					if (StringUtils.isNotBlank(lookbookdo.getGcs_photo_url2())) {
						lookbookbo.setGcs_photo_url2(lookbookdo
								.getGcs_photo_url2());
					}

					if (StringUtils.isNotBlank(lookbookdo.getGcs_photo_url3())) {
						lookbookbo.setGcs_photo_url3(lookbookdo
								.getGcs_photo_url3());
					}

					lookbookbo.setLookbookid(lookbookdo.getLookbookid());

					if (lookbookdo.getTags() != null
							&& lookbookdo.getTags().size() > 0) {
						for (String tag : lookbookdo.getTags()) {
							tagbldr.append(tag + ",");
						}
						lookbookform.setTags(tagbldr.toString());
					}

					if (lookbookdo.isLookbookme() == true) {
						lookbookform.setLookbookme("Yes");
					} else {
						lookbookform.setLookbookme("No");
					}

					if (lookbookdo.getComments_by_users() != null
							&& lookbookdo.getComments_by_users().size() > 0) {
						lookbookbo.setTotal_comments(String.valueOf(lookbookdo
								.getComments_by_users().size()));
					} else {
						lookbookbo.setTotal_comments("0");
					}
				}

				uiModel.addAttribute("usrbo", usrbo);
				uiModel.addAttribute("lookbook", lookbookbo);
				uiModel.addAttribute("lookbookform", lookbookform);

				return "admn_mng_lookbook";
			}
		}

		uiModel.addAttribute("usrbo", usrbo);
		return "redirect:/dashboard";
	}

	@RequestMapping(value = "/mnda/lkbk/glbltags", method = RequestMethod.GET)
	public String showLkbkGlobalTags(HttpServletRequest req, Model uiModel) {
		UserBO usrbo = null;
		UserDO usrdo = null;
		MiscForm mscForm = new MiscForm();

		if (GlamCmn.chkLoggdInStatus(req)) {
			UsrContainer uc = GlamCmn.getLoggedInUsrDtls(req);
			usrbo = uc.getUsrbo();
			usrdo = uc.getUsrdo();

			if (StringUtils.equalsIgnoreCase(usrdo.getAccount_username(),
					"suvoraj239")) {

				uiModel.addAttribute("usrbo", usrbo);
				uiModel.addAttribute("mscForm", mscForm);

				return "admn_mng_globaltags";
			}
		}

		uiModel.addAttribute("usrbo", usrbo);

		return "redirect:/dashboard";
	}

	@RequestMapping(value = "/mnda/lkbk/glbltags", method = RequestMethod.POST)
	public String sveLkbkGlobalTags(@ModelAttribute MiscForm mscForm,
			BindingResult result, Model uiModel, HttpServletRequest req) {
		UserBO usrbo = null;
		UserDO usrdo = null;
		StringBuilder succsmssg = new StringBuilder();

		if (GlamCmn.chkLoggdInStatus(req)) {
			UsrContainer uc = GlamCmn.getLoggedInUsrDtls(req);
			usrbo = uc.getUsrbo();
			usrdo = uc.getUsrdo();

			if (StringUtils.equalsIgnoreCase(usrdo.getAccount_username(),
					"suvoraj239")) {

				GlobalTagsDO globaltags = new GlobalTagsDO();
				globaltags.setGlbl_tagname(mscForm.getEditor_picks_glbltags());
				globaltags.setGlbl_tagdesc(mscForm
						.getEditor_picks_glbltags_desc());
				globaltags.setTagadddatetm(new Date());
				globaltags.setIstagactivated(true);

				OfyService.ofy().save().entity(globaltags).now();

				succsmssg.append("Tags added successfully");

				uiModel.addAttribute("usrbo", usrbo);
				uiModel.addAttribute("succsmssg", succsmssg);
				uiModel.addAttribute("mscForm", mscForm);

				return "admn_mng_globaltags";
			}
		}

		uiModel.addAttribute("usrbo", usrbo);

		return "redirect:/dashboard";
	}

	@RequestMapping(value = "/mnda/lkbk/lkbktags", method = RequestMethod.GET)
	public String showEditorPicksMngPage(HttpServletRequest req, Model uiModel) {
		UserBO usrbo = null;
		UserDO usrdo = null;
		MiscForm mscForm = new MiscForm();

		if (GlamCmn.chkLoggdInStatus(req)) {
			UsrContainer uc = GlamCmn.getLoggedInUsrDtls(req);
			usrbo = uc.getUsrbo();
			usrdo = uc.getUsrdo();
			List<String> taglist = new ArrayList<String>();

			if (StringUtils.equalsIgnoreCase(usrdo.getAccount_username(),
					"suvoraj239")) {
				
				List<GlobalTagsDO> globaltags = OfyService.ofy().load()
						.type(GlobalTagsDO.class).list();
				
				if (globaltags != null && globaltags.size() > 0) {
					// mscForm.setEditor_picks_glbltags_list(editor_picks_glbltags_list);
					Iterator<GlobalTagsDO> tagsItr = globaltags.iterator();
					while (tagsItr.hasNext()) {
						taglist.add((tagsItr.next()).getGlbl_tagname());
					}
				}
				
				uiModel.addAttribute("usrbo", usrbo);
				uiModel.addAttribute("mscForm", mscForm);
				uiModel.addAttribute("taglist", taglist);

				return "admn_mng_tagslkbk";
			}
		}

		uiModel.addAttribute("usrbo", usrbo);

		return "redirect:/dashboard";
	}

	@RequestMapping(value = "/mnda/lkbk", method = RequestMethod.POST)
	public String doMngLookbook(@ModelAttribute LookbookForm lookbookform,
			BindingResult result, Model uiModel, HttpServletRequest req) {

		UserBO usrbo = null;
		UserDO usrdo = null;

		UserLookbookDO lookbookdo = null;
		LookbookBO lookbookbo = new LookbookBO();
		StringBuilder succsmssg = new StringBuilder();

		if (GlamCmn.chkLoggdInStatus(req)) {
			UsrContainer uc = GlamCmn.getLoggedInUsrDtls(req);
			usrbo = uc.getUsrbo();
			usrdo = uc.getUsrdo();

			if (StringUtils.equalsIgnoreCase(usrdo.getAccount_username(),
					"suvoraj239")) {
				// retrieve the lookbook first based on the lookbookid
				lookbookdo = OfyService.ofy().load().type(UserLookbookDO.class)
						.id(lookbookform.getLookbookid()).get();

				String[] ignoreFlds = { "lookbookid", "disapproved",
						"lookbookme", "tags" };

				BeanUtils.copyProperties(lookbookform, lookbookdo, ignoreFlds);

				if (lookbookform.getTags() != null) {
					String[] tagsarr = StringUtils.split(
							lookbookform.getTags(), ",");

					List<String> tagslist = new ArrayList<String>();

					for (String tag : tagsarr) {
						tagslist.add(tag);
					}

					lookbookdo.setTags(tagslist);
				}

				if (StringUtils.equalsIgnoreCase(lookbookform.getLookbookme(),
						"Yes")) {
					lookbookdo.setLookbookme(true);
				} else {
					lookbookdo.setLookbookme(false);
				}

				if (StringUtils.equalsIgnoreCase(lookbookform.getDisapproved(),
						"Yes")) {
					lookbookdo.setDisapproved(true);
				} else {
					lookbookdo.setDisapproved(false);
				}

				// save the updated lookbook
				logger.info("********** saving the updated lookbook with updated info ******************");
				OfyService.ofy().save().entity(lookbookdo).now();

				succsmssg.append(" Lookbook update successful ");

			}
		}

		uiModel.addAttribute("usrbo", usrbo);
		uiModel.addAttribute("succsmssg", succsmssg);
		uiModel.addAttribute("lookbook", lookbookbo);
		uiModel.addAttribute("lookbookform", lookbookform);

		return "redirect:/dashboard";
	}

}
