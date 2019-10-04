package com.app.beta.glam.fiesta;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.beta.glam.fiesta.cmn.GlamCmn;
import com.app.beta.glam.fiesta.cmn.OfyService;
import com.app.beta.glam.fiesta.db.UserDO;
import com.app.beta.glam.fiesta.db.UserLookbookDO;
import com.app.beta.glam.fiesta.model.UserBO;
import com.app.beta.glam.fiesta.model.UsrContainer;

@Controller
public class AdmnIndxBldrController {

	private static final Logger logger = Logger.getLogger(AdmnIndxBldrController.class.getCanonicalName());

	@RequestMapping(value = "/mnda/indx/build/lkbk", method = RequestMethod.GET)
	public String bldLkbkIndx(HttpServletRequest req, Model uiModel) {
		UserBO usrbo = null;
		UserDO usrdo = null;

		if (GlamCmn.chkLoggdInStatus(req)) {
			UsrContainer uc = GlamCmn.getLoggedInUsrDtls(req);
			usrbo = uc.getUsrbo();
			usrdo = uc.getUsrdo();

			if (StringUtils.equalsIgnoreCase(usrdo.getAccount_username(), "suvoraj239")) {
				List<UserLookbookDO> lookbookdolist = OfyService.ofy().load().type(UserLookbookDO.class)
						.filter("lookbook_publish_status", "Yes").list();
				for (int i = 0; i < lookbookdolist.size(); i++) {
					UserLookbookDO lookbookdo = (UserLookbookDO) lookbookdolist.get(i);
					OfyService.ofy().save().entity(lookbookdo).now();
				}
			}
		}

		uiModel.addAttribute("usrbo", usrbo);
		return "redirect:/dashboard";

	}

	@RequestMapping(value = "/mnda/lkbkseo", method = RequestMethod.GET)
	public String bldLookbookSeoUrl(HttpServletRequest req, Model uiModel) {
		UserBO usrbo = null;
		UserDO usrdo = null;
		String lkbkseourl = "";
		String unmstr = "";

		if (GlamCmn.chkLoggdInStatus(req)) {
			UsrContainer uc = GlamCmn.getLoggedInUsrDtls(req);
			usrbo = uc.getUsrbo();
			usrdo = uc.getUsrdo();

			if (StringUtils.equalsIgnoreCase(usrdo.getAccount_username(), "suvoraj239")) {
				List<UserLookbookDO> lookbookdolist = OfyService.ofy().load().type(UserLookbookDO.class)
						.filter("lookbook_publish_status", "Yes").list();
				logger.info("**** Total lookbook fetched: " + lookbookdolist.size());

				for (int i = 0; i < lookbookdolist.size(); i++) {
					UserLookbookDO lookbookdo = (UserLookbookDO) lookbookdolist.get(i);
					if (StringUtils.isBlank(lookbookdo.getLookbook_seo_url())) {
						logger.info("**** Lookbook to update: " + lookbookdo.getLookbookname());
						unmstr = GlamCmn.getUsrnmStr(lookbookdo.getUserid());
						lkbkseourl = GlamCmn.genUnqueLookbookSEOUrl(lookbookdo.getLookbookname(), unmstr);

						lookbookdo.setUsername(unmstr);
						lookbookdo.setLookbook_seo_url(lkbkseourl);
						OfyService.ofy().save().entity(lookbookdo).now();
					}
				}
			}
		}

		uiModel.addAttribute("usrbo", usrbo);
		return "redirect:/dashboard";

	}

	@RequestMapping(value = "/mnda/indx/build/usr", method = RequestMethod.GET)
	public String bldUsrIndx(HttpServletRequest req, Model uiModel) {
		UserBO usrbo = null;
		UserDO usrdo = null;

		if (GlamCmn.chkLoggdInStatus(req)) {
			UsrContainer uc = GlamCmn.getLoggedInUsrDtls(req);
			usrbo = uc.getUsrbo();
			usrdo = uc.getUsrdo();

			List<String> guys = new ArrayList<String>();
			guys.add("4817071640674304");
			guys.add("5764106560733184");
			guys.add("5728526581891072");
			guys.add("6266885700059136");
			guys.add("6285671887011840");
			guys.add("5740615169998848");
			guys.add("6221912157978624");

			if (StringUtils.equalsIgnoreCase(usrdo.getAccount_username(), "suvoraj239")) {
				List<UserDO> usrdolist = OfyService.ofy().load().type(UserDO.class).list();
				for (int i = 0; i < usrdolist.size(); i++) {
					UserDO udo = (UserDO) usrdolist.get(i);
					// udo.setSignup_dttm(GlamCmn.getDtFrmStr(udo.getSignup_datetime()));
					if (guys.contains(String.valueOf(udo.getUserid()))) {
						udo.setGender("Male");
					} else {
						udo.setGender("Female");
					}
					OfyService.ofy().save().entity(udo).now();
				}
			}
		}

		uiModel.addAttribute("usrbo", usrbo);
		return "redirect:/dashboard";
	}
}
