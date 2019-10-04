package com.app.beta.glam.fiesta;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.beta.glam.fiesta.cmn.FashionistasCmn;
import com.app.beta.glam.fiesta.cmn.GlamCmn;
import com.app.beta.glam.fiesta.cmn.GlamCnst;
import com.app.beta.glam.fiesta.cmn.OfyService;
import com.app.beta.glam.fiesta.db.UserActivitiesDO;
import com.app.beta.glam.fiesta.db.UserDO;
import com.app.beta.glam.fiesta.db.UserGlamPointsActivitiesDO;
import com.app.beta.glam.fiesta.db.UserLookbookDO;
import com.app.beta.glam.fiesta.model.LookbookBO;
import com.app.beta.glam.fiesta.model.UserBO;
import com.app.beta.glam.fiesta.model.UsrActivityBO;
import com.app.beta.glam.fiesta.model.UsrContainer;

@Controller
public class DashBoardController {

	private static final Logger logger = Logger.getLogger(DashBoardController.class.getCanonicalName());

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String goDashboard(HttpServletRequest req, Model uiModel) {
		UserBO usrbo = null;
		UserDO usrdo = null;

		String succsmssg = req.getParameter("succsmssg");

		if (GlamCmn.chkLoggdInStatus(req)) {
			UsrContainer uc = GlamCmn.getLoggedInUsrDtls(req);
			usrbo = uc.getUsrbo();
			usrdo = uc.getUsrdo();

			if (usrdo.getMy_followers() != null && usrdo.getMy_followers().size() > 0) {
				usrbo.setTotal_followers(GlamCmn.intToCoolFormatStr(usrdo.getMy_followers().size()));
			}

			if (usrdo.getMe_following() != null && usrdo.getMe_following().size() > 0) {
				usrbo.setTotal_me_following(GlamCmn.intToCoolFormatStr(usrdo.getMe_following().size()));
			}

			usrbo.setTotal_glampoints(GlamCmn.intToCoolFormatStr(usrdo.getMy_glam_points()));

			List<UserBO> randmFashionistas = FashionistasCmn.getRandomFashionistasBO(15, usrbo);

			uiModel.addAttribute("usrbo", usrbo);
			uiModel.addAttribute("rndmfashionistas", randmFashionistas);
			uiModel.addAttribute("succsmssg", succsmssg);

			return GlamCnst._VIEW_DSHBRD;
		} else {
			return "redirect:/login";
		}
	}

	@RequestMapping(value = "/dashboard/mylookbooks", method = RequestMethod.GET)
	public String goDashboardMyLookbookPg(HttpServletRequest req, Model uiModel) {
		UserBO usrbo = null;
		UserDO usrdo = null;

		List<LookbookBO> lookbooks = new ArrayList<LookbookBO>();
		List<UserLookbookDO> lookbookdos = null;

		if (GlamCmn.chkLoggdInStatus(req)) {
			UsrContainer uc = GlamCmn.getLoggedInUsrDtls(req);
			usrbo = uc.getUsrbo();
			usrdo = uc.getUsrdo();

			// retrieve all lookbook that user has created
			lookbookdos = OfyService.ofy().load().type(UserLookbookDO.class).filter("userid", usrdo.getUserid())
					.order("lookbookid").list();

			String[] ignorefldsLkbk = { "tags" };
			if (lookbookdos != null && lookbookdos.size() > 0) {
				for (int i = 0; i < lookbookdos.size(); i++) {
					UserLookbookDO lookbookdo = (UserLookbookDO) lookbookdos.get(i);
					LookbookBO lookbook = new LookbookBO();
					BeanUtils.copyProperties(lookbookdo, lookbook, ignorefldsLkbk);

					if (GlamCmn.getDaysDiffWithToday(lookbookdo.getCreateddt().toString()) > GlamCnst._LKBK_CAN_EDT) {
						lookbook.setCan_edit(false);
					} else {
						lookbook.setCan_edit(true);
					}

					if (lookbookdo.isDisapproved() == true) {
						lookbook.setIsrejected(true);
					}

					lookbooks.add(lookbook);
				}
			}

			uiModel.addAttribute("usrbo", usrbo);
			uiModel.addAttribute("lookbooks", lookbooks);

			return GlamCnst._VIEW_DSHBRD_MYLKBKS;
		} else {
			return "redirect:/login";
		}
	}

	@RequestMapping(value = "/dashboard/myactivities", method = RequestMethod.GET)
	public String goDashboardMyAcvtysPg(HttpServletRequest req, Model uiModel) {

		UserBO usrbo = null;
		UserDO usrdo = null;

		List<UsrActivityBO> activities = new ArrayList<UsrActivityBO>();
		List<UserActivitiesDO> activitydos = null;

		String succsmssg = req.getParameter("succsmssg");

		if (GlamCmn.chkLoggdInStatus(req)) {
			UsrContainer uc = GlamCmn.getLoggedInUsrDtls(req);
			usrbo = uc.getUsrbo();
			usrdo = uc.getUsrdo();

			// retrieve all user activities
			activitydos = OfyService.ofy().load().type(UserActivitiesDO.class).filter("userid", usrdo.getUserid())
					.order("- actvtdt").list();

			if (activitydos != null && activitydos.size() > 0) {
				for (int j = 0; j < activitydos.size(); j++) {
					UserActivitiesDO actvtydo = (UserActivitiesDO) activitydos.get(j);
					UsrActivityBO usractvty = new UsrActivityBO();
					BeanUtils.copyProperties(actvtydo, usractvty);
					usractvty.setUseravatar(GlamCmn.getAvatarImg(actvtydo.getUserid(), ""));
					usractvty.setActvtydttm(actvtydo.getActvtdt().toString());
					activities.add(usractvty);
				}
			}

			uiModel.addAttribute("usrbo", usrbo);
			uiModel.addAttribute("activities", activities);
			uiModel.addAttribute("succsmssg", succsmssg);

			return GlamCnst._VIEW_DSHBRD_MYACTVTS;
		} else {
			return "redirect:/login";
		}

	}

	@RequestMapping(value = "/dashboard/mypoints", method = RequestMethod.GET)
	public String goDashboardMyPointsPg(HttpServletRequest req, Model uiModel) {

		UserBO usrbo = null;
		UserDO usrdo = null;

		List<String> glampoints_history = new ArrayList<String>();
		List<UserGlamPointsActivitiesDO> gpoints_actvtsdos = null;

		String succsmssg = req.getParameter("succsmssg");

		if (GlamCmn.chkLoggdInStatus(req)) {
			UsrContainer uc = GlamCmn.getLoggedInUsrDtls(req);
			usrbo = uc.getUsrbo();
			usrdo = uc.getUsrdo();

			// retrieve all user activities
			gpoints_actvtsdos = OfyService.ofy().load().type(UserGlamPointsActivitiesDO.class)
					.filter("userid", usrdo.getUserid()).order("userpointacvtid").list();

			if (gpoints_actvtsdos != null && gpoints_actvtsdos.size() > 0) {
				for (int k = 0; k < gpoints_actvtsdos.size(); k++) {
					UserGlamPointsActivitiesDO gpoint_hst = gpoints_actvtsdos.get(k);
					String gpts_hst = gpoint_hst.getPointacvtstr();
					glampoints_history
							.add(gpts_hst + " On " + GlamCmn.printDtttmStr(gpoint_hst.getActvtdt().toString()));
				}
			}

			usrbo.setTotal_glampoints(GlamCmn.intToCoolFormatStr(usrdo.getMy_glam_points()));

			uiModel.addAttribute("usrbo", usrbo);
			uiModel.addAttribute("gpoints_hsts", glampoints_history);
			uiModel.addAttribute("succsmssg", succsmssg);

			return GlamCnst._VIEW_DSHBRD_MYGPTS;
		} else {
			return "redirect:/login";
		}

	}

	@RequestMapping(value = "/dashboard/friends", method = RequestMethod.GET)
	public String goDashboardFrnds(HttpServletRequest req, Model uiModel) {
		UserBO usrbo = null;
		UserDO usrdo = null;
		List<Long> followrs_ids = null;
		List<Long> following_ids = null;

		List<UserBO> my_followers = new ArrayList<UserBO>();
		List<UserBO> me_followings = new ArrayList<UserBO>();

		if (GlamCmn.chkLoggdInStatus(req)) {
			UsrContainer uc = GlamCmn.getLoggedInUsrDtls(req);
			usrbo = uc.getUsrbo();
			usrdo = uc.getUsrdo();

			// retrieve all user followers
			followrs_ids = usrdo.getMy_followers();
			following_ids = usrdo.getMe_following();

			// logger.info("**** followrs_ids - " + followrs_ids);
			// logger.info("**** following_ids - " + following_ids);

			if (followrs_ids != null && followrs_ids.size() > 0) {
				for (int i = 0; i < followrs_ids.size(); i++) {
					UserDO followerdo = OfyService.ofy().load().type(UserDO.class).id(followrs_ids.get(i).longValue())
							.get();
					UserBO follower = new UserBO();

					String[] ignorefldsUsr = { "account_email" };
					BeanUtils.copyProperties(followerdo, follower, ignorefldsUsr);

					int total_lkbk = OfyService.ofy().load().type(UserLookbookDO.class)
							.filter("userid", follower.getUserid()).filter("lookbook_publish_status", "Yes").count();
					follower.setTotal_lookbooks(total_lkbk);
					follower.setLocation(GlamCmn.getUsrLocn(follower));
					follower.setTotal_glampoints(GlamCmn.intToCoolFormatStr(followerdo.getMy_glam_points()));

					my_followers.add(follower);
				}
			}

			if (following_ids != null && following_ids.size() > 0) {
				for (int j = 0; j < following_ids.size(); j++) {
					UserDO followingdo = OfyService.ofy().load().type(UserDO.class).id(following_ids.get(j).longValue())
							.get();
					UserBO following = new UserBO();

					String[] ignorefldsUsr = { "account_email" };
					BeanUtils.copyProperties(followingdo, following, ignorefldsUsr);

					int total_lkbk = OfyService.ofy().load().type(UserLookbookDO.class)
							.filter("userid", following.getUserid()).filter("lookbook_publish_status", "Yes").count();
					following.setTotal_lookbooks(total_lkbk);
					following.setLocation(GlamCmn.getUsrLocn(following));
					following.setTotal_glampoints(GlamCmn.intToCoolFormatStr(followingdo.getMy_glam_points()));

					me_followings.add(following);
				}
			}
			//logger.info("**** my_followers - " + my_followers);

			uiModel.addAttribute("usrbo", usrbo);
			uiModel.addAttribute("followers", my_followers);
			uiModel.addAttribute("followings", me_followings);

			return GlamCnst._VIEW_DSHBRD_FRNDS;
		} else {
			return "redirect:/login";
		}
	}
}
