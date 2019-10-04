package com.app.beta.glam.fiesta;

import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.beta.glam.fiesta.cmn.GlamCmn;
import com.app.beta.glam.fiesta.cmn.GlamCnst;
import com.app.beta.glam.fiesta.cmn.OfyService;
import com.app.beta.glam.fiesta.db.UserGiveawayWinnerDO;
import com.app.beta.glam.fiesta.form.MiscForm;
import com.app.beta.glam.fiesta.model.UserBO;
import com.app.beta.glam.fiesta.model.UsrContainer;

@Controller
public class StaticPgController {

	private static final Logger logger = Logger.getLogger(StaticPgController.class.getCanonicalName());

	@RequestMapping(value = "/privacy_policy.htm", method = RequestMethod.GET)
	public String goPpcyPg(HttpServletRequest req, Model uiModel) {
		UsrContainer uc = GlamCmn.getLoggedInUsrDtls(req);
		UserBO usrbo = uc.getUsrbo();

		uiModel.addAttribute("usrbo", usrbo);

		return GlamCnst._VIEW_PRVCY_PLCY;
	}

	@RequestMapping(value = "/tos.htm", method = RequestMethod.GET)
	public String goTosPg(HttpServletRequest req, Model uiModel) {
		UsrContainer uc = GlamCmn.getLoggedInUsrDtls(req);
		UserBO usrbo = uc.getUsrbo();

		uiModel.addAttribute("usrbo", usrbo);

		return GlamCnst._VIEW_TOS;
	}
	
	@RequestMapping(value = "/sitemap.htm", method = RequestMethod.GET)
	public String goSitemapPg(HttpServletRequest req, Model uiModel) {
		UsrContainer uc = GlamCmn.getLoggedInUsrDtls(req);
		UserBO usrbo = uc.getUsrbo();

		uiModel.addAttribute("usrbo", usrbo);

		return GlamCnst._VIEW_SITEMAP_HTM;
	}
	
	@RequestMapping(value = "/robots.txt", method = RequestMethod.GET)
	public String goRobotsTxtPg(HttpServletRequest req, Model uiModel) {
		UsrContainer uc = GlamCmn.getLoggedInUsrDtls(req);
		UserBO usrbo = uc.getUsrbo();

		uiModel.addAttribute("usrbo", usrbo);

		return GlamCnst._VIEW_RBTS_TXT;
	}

	@RequestMapping(value = "/aboutus.htm", method = RequestMethod.GET)
	public String goAboutUsPg(HttpServletRequest req, Model uiModel) {
		UsrContainer uc = GlamCmn.getLoggedInUsrDtls(req);
		UserBO usrbo = uc.getUsrbo();

		uiModel.addAttribute("usrbo", usrbo);

		return GlamCnst._VIEW_ABTUS;
	}

	@RequestMapping(value = "/how-to-post-look.htm", method = RequestMethod.GET)
	public String goHowToPostLookPg(HttpServletRequest req, Model uiModel) {
		UsrContainer uc = GlamCmn.getLoggedInUsrDtls(req);
		UserBO usrbo = uc.getUsrbo();

		uiModel.addAttribute("usrbo", usrbo);

		return GlamCnst._VIEW_HW_POST_LOOK;
	}

	@RequestMapping(value = "/faq.htm", method = RequestMethod.GET)
	public String goFaqPg(HttpServletRequest req, Model uiModel) {
		UsrContainer uc = GlamCmn.getLoggedInUsrDtls(req);
		UserBO usrbo = uc.getUsrbo();

		uiModel.addAttribute("usrbo", usrbo);

		return GlamCnst._VIEW_FAQ;
	}

	@RequestMapping(value = "/contactus.htm", method = RequestMethod.GET)
	public String goContactUsPg(HttpServletRequest req, Model uiModel) {
		UsrContainer uc = GlamCmn.getLoggedInUsrDtls(req);
		UserBO usrbo = uc.getUsrbo();

		uiModel.addAttribute("usrbo", usrbo);

		return GlamCnst._VIEW_CONTACTUS;
	}


	@RequestMapping(value = "/giveaway.htm", method = RequestMethod.GET)
	public String goGiveawayPg(HttpServletRequest req, Model uiModel) {
		UsrContainer uc = GlamCmn.getLoggedInUsrDtls(req);
		UserBO usrbo = uc.getUsrbo();
		MiscForm contestform = new MiscForm();

		List<UserGiveawayWinnerDO> winners = OfyService.ofy().load().type(UserGiveawayWinnerDO.class).list();

		uiModel.addAttribute("winners", winners);
		uiModel.addAttribute("usrbo", usrbo);
		uiModel.addAttribute("contestform", contestform);

		return GlamCnst._VIEW_GIVEAWY;
	}

	@RequestMapping(value = "/giveaway", method = RequestMethod.POST)
	public String doContestPost(@ModelAttribute MiscForm contestform, BindingResult result, Model uiModel,
			HttpServletRequest req) {
		boolean reqd_fields_empty = false;
		StringBuilder errmssg = new StringBuilder();
		StringBuilder succsmssg = new StringBuilder();

		if (contestform != null) {
			if (StringUtils.isBlank(contestform.getGiveaway_email())
					|| StringUtils.isBlank(contestform.getGiveaway_uname())
					|| StringUtils.isBlank(contestform.getGiveaway_look_url1())
					|| StringUtils.isBlank(contestform.getGiveaway_look_url2())) {
				reqd_fields_empty = true;
			}

			if (reqd_fields_empty) {
				errmssg.append(" One of the required field is empty, pls correct...");
			} else {
				// send user email with login details
				String email_txt = "<p>A new giveaway entry is posted. Details are-" + "User email: "
						+ contestform.getGiveaway_email() + "<br/>" + "User uname: " + contestform.getGiveaway_uname()
						+ "<br/>" + "Lookbook url1: " + contestform.getGiveaway_look_url1() + "</br>"
						+ "Lookbook url2: " + contestform.getGiveaway_look_url2() + "</p>";

				GlamCmn.sendEmailToUsr("editor.glamfiesta@gmail.com", "Glam Fiesta", "Giveaway entry", email_txt, true);

				succsmssg.append(
						"Thanks for posting your lookbook and " + "filling up the form below. We'll verify your "
								+ "entry and if everything looks good then within "
								+ "3-5 days we'll email you the Amazon e-gift card "
								+ "in your email id which you have entered in " + "the form...");
			}
		}

		logger.info("**** errmssg - " + errmssg.toString());

		List<UserGiveawayWinnerDO> winners = OfyService.ofy().load().type(UserGiveawayWinnerDO.class).list();

		uiModel.addAttribute("winners", winners);
		uiModel.addAttribute("succsmssg", succsmssg.toString());
		uiModel.addAttribute("errmssg", errmssg.toString());

		return GlamCnst._VIEW_GIVEAWY_SCCSS;
	}
}
