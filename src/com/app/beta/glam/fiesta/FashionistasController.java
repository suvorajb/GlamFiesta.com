package com.app.beta.glam.fiesta;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.beta.glam.fiesta.cmn.GlamCmn;
import com.app.beta.glam.fiesta.cmn.GlamCnst;
import com.app.beta.glam.fiesta.cmn.OfyService;
import com.app.beta.glam.fiesta.db.FeaturedUsersDO;
import com.app.beta.glam.fiesta.db.RecentVisitsDO;
import com.app.beta.glam.fiesta.db.UserDO;
import com.app.beta.glam.fiesta.db.UserLookbookDO;
import com.app.beta.glam.fiesta.model.FtrdFashionistaBO;
import com.app.beta.glam.fiesta.model.LookbookBO;
import com.app.beta.glam.fiesta.model.UserBO;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.QueryResultIterator;
import com.googlecode.objectify.cmd.Query;

@Controller
public class FashionistasController {

	private static final Logger logger = Logger.getLogger(FashionistasController.class.getCanonicalName());

	@RequestMapping(value = "/fashionistas", method = RequestMethod.GET)
	public String showAllFashionistas(HttpServletRequest req, Model uiModel) {

		List<UserDO> usrdolist = new ArrayList<UserDO>();
		List<UserBO> fashionistas = new ArrayList<UserBO>();
		List<UserBO> leaders = new ArrayList<UserBO>();
		List<UserDO> alluserslist = null;
		StringBuilder mssg_usr = new StringBuilder("");

		String next_cursor_str = "";

		String next_page = req.getParameter("next_page");
		String refrr = req.getParameter("refrr");

		String curr_page = null;

		if (StringUtils.isNotBlank(next_page)) {
			curr_page = next_page;
		}

		Query<UserDO> qry_usr = null;

		if (StringUtils.isNotBlank(curr_page)) {
			qry_usr = OfyService.ofy().load().type(UserDO.class).order("-signup_dttm")
					.startAt(Cursor.fromWebSafeString(curr_page)).limit(GlamCmn._HOME_LKBK_LMT);
		} else {
			logger.info("******** curr_page is null ********");
			if (StringUtils.isNotBlank(refrr) && StringUtils.equalsIgnoreCase(refrr, "sitemap")) {
				logger.info("******** request came from - " + refrr);
				// request came from sitemap page
				qry_usr = OfyService.ofy().load().type(UserDO.class);
			} else {
				logger.info("******** request came from index page ");
				// request came from index page
				qry_usr = OfyService.ofy().load().type(UserDO.class).order("-signup_dttm")
						.limit(GlamCmn._HOME_LKBK_LMT);
			}
		}

		QueryResultIterator<UserDO> usrDoItr = qry_usr.iterator();

		// user has clicked next page
		next_cursor_str = usrDoItr.getCursor().toWebSafeString();

		while (usrDoItr.hasNext()) {
			UserDO usrdo = usrDoItr.next();
			usrdolist.add(usrdo);
		}

		if (usrdolist != null && usrdolist.size() > 0) {
			logger.info("usrdolist size - " + usrdolist.size());
			for (int i = 0; i < usrdolist.size(); i++) {
				UserDO usrdo = usrdolist.get(i);
				UserBO fashionista = new UserBO();
				long uid = usrdo.getUserid().longValue();

				String[] ignorefldsUsr = { "account_email" };
				BeanUtils.copyProperties(usrdo, fashionista, ignorefldsUsr);

				fashionista.setLocation(GlamCmn.getUsrLocn(fashionista));
				fashionista.setTotal_glampoints(GlamCmn.intToCoolFormatStr(usrdo.getMy_glam_points()));

				if (usrdo.getMy_followers() != null && usrdo.getMy_followers().size() > 0) {
					fashionista.setTotal_followers(GlamCmn.intToCoolFormatStr(usrdo.getMy_followers().size()));
				} else {
					fashionista.setTotal_followers(GlamCmn.intToCoolFormatStr(0));
				}

				int lkbkcount = OfyService.ofy().load().type(UserLookbookDO.class).filter("userid", uid)
						.filter("lookbook_publish_status", "Yes").count();

				if (lkbkcount > 0) {
					// user has lookbook so user is fashionista
					fashionista.setTotal_lookbooks(lkbkcount);
					fashionistas.add(fashionista);
				} else {
					fashionista.setTotal_lookbooks(0);
				}
			}
		}

		// populate the leaders
		alluserslist = OfyService.ofy().load().type(UserDO.class).order("-my_glam_points").limit(100).list();

		if (alluserslist != null && alluserslist.size() > 0) {
			// populate the leaders
			Iterator<UserDO> leadersItr = alluserslist.iterator();
			while (leadersItr.hasNext()) {
				UserDO leaderdo = leadersItr.next();
				UserBO leader = new UserBO();

				long uid = leaderdo.getUserid().longValue();

				String[] ignorefldsUsr = { "account_email" };
				BeanUtils.copyProperties(leaderdo, leader, ignorefldsUsr);
				leader.setUavatar_thumb_profile(GlamCmn.getResizeImg(leaderdo, 2));
				leader.setLocation(GlamCmn.getUsrLocn(leader));

				int cntlkbk = OfyService.ofy().load().type(UserLookbookDO.class).filter("userid", uid)
						.filter("lookbook_publish_status", "Yes").count();
				if (cntlkbk > 2) {
					leaders.add(leader);
				}

				if (leaders.size() > 9)
					break;
			}
		}

		if (usrdolist != null && usrdolist.size() > 0) {
			next_page = next_cursor_str;
		} else {
			mssg_usr.append(
					" You have reached last page... please click here to go back to first page to load the fresh looks ");
		}

		logger.info("**** total fashionistas - " + fashionistas.size());

		uiModel.addAttribute("fashionistas", fashionistas);
		uiModel.addAttribute("leaders", leaders);
		uiModel.addAttribute("next_page", next_page);
		uiModel.addAttribute("mssg_usr", mssg_usr);

		return GlamCnst._VIEW_FASHIONISTAS;
	}

	@RequestMapping(value = "/fashionistas/{uname}", method = RequestMethod.GET)
	public String goUsrLookbookProfilePage(@PathVariable String uname, HttpServletRequest req, Model uiModel) {
		UserBO usrbo = new UserBO();
		UserDO usrdo = null;
		String tempUname = StringUtils.lowerCase(uname);

		List<UserLookbookDO> lookbookdolist = null;
		List<UserDO> usrdolist = null;
		List<LookbookBO> lookbooks = new ArrayList<LookbookBO>();

		// List<UsrActivityBO> activities = new ArrayList<UsrActivityBO>();
		List<RecentVisitsDO> recentvisitlist = null;
		List<LookbookBO> visitedlooks = new ArrayList<LookbookBO>();
		// List<UserActivitiesDO> activitydos = null;

		long uid = 0;

		if (StringUtils.isNotBlank(tempUname)) {
			usrdolist = OfyService.ofy().load().type(UserDO.class).filter("account_username", tempUname).list();
			if (usrdolist == null) {
				// try with first letter uppercase
				usrdolist = OfyService.ofy().load().type(UserDO.class)
						.filter("account_username", StringUtils.capitalize(tempUname)).list();
			}

			if (usrdolist != null && usrdolist.size() > 0) {
				usrdo = (UserDO) usrdolist.get(0);
				uid = usrdo.getUserid().longValue();

				String[] ignorefldsUsr = { "account_email" };
				BeanUtils.copyProperties(usrdo, usrbo, ignorefldsUsr);

				usrbo.setLocation(GlamCmn.getUsrLocn(usrbo));
				usrbo.setUavatar_thumb_profile(usrdo.getUavatar() + "=s100");

				usrbo.setTwitter_ac(GlamCmn.checkForHttp(usrdo.getTwitter_ac()));
				usrbo.setFacebook_ac(GlamCmn.checkForHttp(usrdo.getFacebook_ac()));
				usrbo.setGoogle_plus_ac(GlamCmn.checkForHttp(usrdo.getGoogle_plus_ac()));
				usrbo.setYoutube_ac(GlamCmn.checkForHttp(usrdo.getYoutube_ac()));
				usrbo.setLinkedin_ac(GlamCmn.checkForHttp(usrdo.getLinkedin_ac()));
				usrbo.setPinterest_ac(GlamCmn.checkForHttp(usrdo.getPinterest_ac()));
				usrbo.setInstagram_ac(GlamCmn.checkForHttp(usrdo.getInstagram_ac()));
				usrbo.setTumblr_ac(GlamCmn.checkForHttp(usrdo.getTumblr_ac()));
				usrbo.setWebsite_blog_url(GlamCmn.checkForHttp(usrdo.getWebsite_blog_url()));

				usrbo.setTotal_glampoints(GlamCmn.intToCoolFormatStr(usrdo.getMy_glam_points()));
				if (usrdo.getMy_followers() != null && usrdo.getMy_followers().size() > 0) {
					usrbo.setTotal_followers(GlamCmn.intToCoolFormatStr(usrdo.getMy_followers().size()));
				} else {
					usrbo.setTotal_followers("0");
				}

				lookbookdolist = OfyService.ofy().load().type(UserLookbookDO.class).filter("userid", uid)
						.filter("lookbook_publish_status", "Yes").list();

				recentvisitlist = OfyService.ofy().load().type(RecentVisitsDO.class).filter("visiting_userid", uid)
						.limit(8).list();

				if (recentvisitlist != null && recentvisitlist.size() > 0) {
					Iterator<RecentVisitsDO> rcntvstItr = recentvisitlist.iterator();

					while (rcntvstItr.hasNext()) {
						RecentVisitsDO rcntvstdo = rcntvstItr.next();
						if (rcntvstdo != null) {
							// logger.info("**** rcntvstdo.getLookbookid() - " +
							// rcntvstdo.getLookbookid());
							if (rcntvstdo.getLookbookid() != 0) {
								UserLookbookDO templookbookdo = OfyService.ofy().load().type(UserLookbookDO.class)
										.id(rcntvstdo.getLookbookid()).get();
								String[] ignorefldsLkbk = { "tags" };
								if (templookbookdo != null) {

									LookbookBO rcntvstlook = new LookbookBO();
									BeanUtils.copyProperties(templookbookdo, rcntvstlook, ignorefldsLkbk);

									// get the lookbook owner name
									rcntvstlook.setLookbook_owner(GlamCmn.getUsrnmStr(templookbookdo.getUserid()));

									// update the SEO url
									visitedlooks.add(rcntvstlook);
								}
							}
						}
					}
				}

				if (lookbookdolist != null && lookbookdolist.size() > 0) {
					for (int i = 0; i < lookbookdolist.size(); i++) {
						UserLookbookDO lookbookdo = null;
						LookbookBO lookbook = new LookbookBO();
						String[] ignoreFieldsLkbk = { "tags" };
						int tot_likes = 0;
						int tot_cmmnts = 0;

						lookbookdo = (UserLookbookDO) lookbookdolist.get(i);
						BeanUtils.copyProperties(lookbookdo, lookbook, ignoreFieldsLkbk);

						// update total no of comments and likings for each
						// lookbook

						if (lookbookdo.getComments_by_users() != null) {
							tot_cmmnts = lookbookdo.getComments_by_users().size();
						}

						if (lookbookdo.getLiked_by_users() != null) {
							tot_likes = lookbookdo.getLiked_by_users().size();
						}

						lookbook.setTotal_comments(String.valueOf(tot_cmmnts));
						lookbook.setTotal_likes(String.valueOf(tot_likes));
						lookbook.setIsrejected(false);

						if (lookbookdo.isDisapproved() == true) {
							lookbook.setIsrejected(true);
						}

						if (lookbook.isIsrejected() == false) {
							lookbooks.add(lookbook);
						}
					}
				}
			}
			if (lookbooks != null && lookbooks.size() > 0) {
				usrbo.setTotal_lookbooks(lookbooks.size());
			} else {
				usrbo.setTotal_lookbooks(0);
			}

			uiModel.addAttribute("lookbooks", lookbooks);
			uiModel.addAttribute("usrbo", usrbo);
			uiModel.addAttribute("visitedlooks", visitedlooks);

		}

		return GlamCnst._VIEW_PRFLE_LKBKS;

	}

	@RequestMapping(value = "/fashionistas/{uname}/lookbook", method = RequestMethod.GET)
	public String goUsrProfilePage(@PathVariable String uname, HttpServletRequest req, Model uiModel) {
		return "redirect:/fashionistas/" + uname;
	}

	@RequestMapping(value = "/fashionistas/risingstars", method = RequestMethod.GET)
	public String showAllRisingStarFashionistas(HttpServletRequest req, Model uiModel) {

		String[] ignoreFieldsLkbk = { "tags", "lookbookstory" };

		List<UserBO> risingstars = new ArrayList<UserBO>();

		// Date today_dt = new Date();

		List<UserDO> risingstarlist = OfyService.ofy().load().type(UserDO.class).order("-signup_dttm").limit(100)
				.list();

		if (risingstarlist != null && risingstarlist.size() > 0) {
			Iterator<UserDO> risingstarItr = risingstarlist.iterator();
			while (risingstarItr.hasNext()) {
				UserDO risingstardo = risingstarItr.next();
				UserBO risingstar = new UserBO();

				long uid = risingstardo.getUserid().longValue();

				String[] ignorefldsUsr = { "account_email" };
				BeanUtils.copyProperties(risingstardo, risingstar, ignorefldsUsr);

				risingstar.setLocation(GlamCmn.getUsrLocn(risingstar));
				risingstar.setTotal_glampoints(GlamCmn.intToCoolFormatStr(risingstardo.getMy_glam_points()));

				if (risingstardo.getMy_followers() != null && risingstardo.getMy_followers().size() > 0) {
					risingstar.setTotal_followers(GlamCmn.intToCoolFormatStr(risingstardo.getMy_followers().size()));
				} else {
					risingstar.setTotal_followers(GlamCmn.intToCoolFormatStr(0));
				}

				List<UserLookbookDO> lkbkdolist = OfyService.ofy().load().type(UserLookbookDO.class)
						.filter("userid", uid).filter("lookbook_publish_status", "Yes").list();

				if (lkbkdolist != null && lkbkdolist.size() > 0) {
					List<LookbookBO> lookbooks = new ArrayList<LookbookBO>();
					Iterator<UserLookbookDO> lkbkItr = lkbkdolist.iterator();
					int totlkbk = 0;

					while (lkbkItr.hasNext()) {
						UserLookbookDO lkbkdo = (UserLookbookDO) lkbkItr.next();

						if (lkbkdo != null) {
							LookbookBO lookbook = new LookbookBO();
							BeanUtils.copyProperties(lkbkdo, lookbook, ignoreFieldsLkbk);
							lookbooks.add(lookbook);
						}
						totlkbk++;
						if (totlkbk == 3)
							break;
					}

					if (lookbooks != null && lookbooks.size() > 0) {
						risingstar.setLookbookList(lookbooks);
					}

					risingstar.setTotal_lookbooks(lkbkdolist.size());

					risingstars.add(risingstar);
				}

			}
		}

		uiModel.addAttribute("risingstars", risingstars);

		return GlamCnst._VIEW_RSNG_STAR;
	}

	@RequestMapping(value = "/fashionistas/topleaders", method = RequestMethod.GET)
	public String showAllTopLeadersFashionistas(HttpServletRequest req, Model uiModel) {

		String[] ignoreFieldsLkbk = { "tags", "lookbookstory" };

		List<UserBO> leaders = new ArrayList<UserBO>();

		// List<UserDO> leaderslist =
		// OfyService.ofy().load().type(UserDO.class).order("-signup_datetime").list();
		List<UserDO> leaderslist = OfyService.ofy().load().type(UserDO.class).order("-my_glam_points").limit(100)
				.list();
		// logger.info("*** leaders - " + leaderslist.size());

		if (leaderslist != null && leaderslist.size() > 0) {
			Iterator<UserDO> leadersItr = leaderslist.iterator();
			while (leadersItr.hasNext()) {
				UserDO leaderdo = leadersItr.next();
				UserBO leader = new UserBO();

				long uid = leaderdo.getUserid().longValue();

				String[] ignorefldsUsr = { "account_email" };
				BeanUtils.copyProperties(leaderdo, leader, ignorefldsUsr);

				leader.setLocation(GlamCmn.getUsrLocn(leader));
				leader.setTotal_glampoints(GlamCmn.intToCoolFormatStr(leaderdo.getMy_glam_points()));

				if (leaderdo.getMy_followers() != null && leaderdo.getMy_followers().size() > 0) {
					leader.setTotal_followers(GlamCmn.intToCoolFormatStr(leaderdo.getMy_followers().size()));
				} else {
					leader.setTotal_followers(GlamCmn.intToCoolFormatStr(0));
				}

				// logger.info("---- uid - " + uid);
				List<UserLookbookDO> lkbkdolist = OfyService.ofy().load().type(UserLookbookDO.class)
						.filter("userid", uid).filter("lookbook_publish_status", "Yes").list();
				// logger.info("---- lkbkdolist - " + lkbkdolist.size());

				if (lkbkdolist != null && lkbkdolist.size() > 0) {
					List<LookbookBO> lookbooks = new ArrayList<LookbookBO>();
					Iterator<UserLookbookDO> lkbkItr = lkbkdolist.iterator();
					int totlkbk = 0;

					while (lkbkItr.hasNext()) {
						UserLookbookDO lkbkdo = (UserLookbookDO) lkbkItr.next();

						if (lkbkdo != null) {
							LookbookBO lookbook = new LookbookBO();
							BeanUtils.copyProperties(lkbkdo, lookbook, ignoreFieldsLkbk);

							// update the SEO url
							lookbook.setLookbook_seo_url(lkbkdo.getLookbook_seo_url());
							lookbooks.add(lookbook);
						}
						totlkbk++;
						if (totlkbk == 3)
							break;
					}

					if (lookbooks != null && lookbooks.size() > 0) {
						leader.setLookbookList(lookbooks);
					}

					leader.setTotal_lookbooks(lkbkdolist.size());
				}
				leaders.add(leader);
			}
		}

		uiModel.addAttribute("leaders", leaders);

		return GlamCnst._VIEW_TOP_LDRS;
	}

	@RequestMapping(value = "/fashionistas/featured/{fashionistanm}", method = RequestMethod.GET)
	public String showFtrdFashionistasByName(@PathVariable("fashionistanm") String fashionistanm,
			HttpServletRequest req, Model uiModel) {
		FtrdFashionistaBO ftrdFashionista = null;
		StringBuilder errormssg = new StringBuilder();
		boolean isfashionista_found = false;

		if (fashionistanm != null) {

			// first check if the fashionista really exists or not
			List<UserDO> usrdolist = OfyService.ofy().load().type(UserDO.class)
					.filter("account_username", fashionistanm).list();
			if (usrdolist != null && usrdolist.size() > 0) {
				// user exists so get the user details
				UserDO fashionistado = (UserDO) usrdolist.get(0);
				long uid = fashionistado.getUserid().longValue();

				// now pull all featured fashionistas list
				List<FeaturedUsersDO> ftrdfashionistaslist = OfyService.ofy().load().type(FeaturedUsersDO.class).list();

				// now check if the user's id falls in the list or not
				if (ftrdfashionistaslist != null && ftrdfashionistaslist.size() > 0) {
					Iterator<FeaturedUsersDO> ftrdItr = ftrdfashionistaslist.iterator();
					while (ftrdItr.hasNext()) {
						FeaturedUsersDO tempFtrdUsr = ftrdItr.next();
						if (tempFtrdUsr.getUserid() == uid) {
							// we found the featured fashionista so populate the
							// BO and go on
							ftrdFashionista = new FtrdFashionistaBO();

							ftrdFashionista.setAccount_username(fashionistado.getAccount_username());
							ftrdFashionista.setNote_from_editor(tempFtrdUsr.getNote_from_editor());
							ftrdFashionista.setYoutube_video_url1(tempFtrdUsr.getYoutube_video_url1());
							ftrdFashionista.setYoutube_video_url2(tempFtrdUsr.getYoutube_video_url2());
							ftrdFashionista.setYoutube_video_url3(tempFtrdUsr.getYoutube_video_url3());

							ftrdFashionista.setFashion_blog_url1(tempFtrdUsr.getFashion_blog_url1());

							ftrdFashionista.setFrom_location(GlamCmn.getUsrLocn(fashionistado));
							ftrdFashionista.setUavatar(fashionistado.getUavatar());
							ftrdFashionista
									.setTotal_glampoints(GlamCmn.intToCoolFormatStr(fashionistado.getMy_glam_points()));
							isfashionista_found = true;
						}
					}

					if (isfashionista_found == false) {
						errormssg.append(
								" This fashionista has not been chosen as Featured Fashionista by Glam Fiesta Editor yet");
					}
				}
			} else {
				errormssg.append(" The Fashionista doesn't exist");
			}

		}

		uiModel.addAttribute("errormssg", errormssg);
		uiModel.addAttribute("ftrdFashionista", ftrdFashionista);

		return GlamCnst._VIEW_FTRD_FASHIONISTAS;
	}

}
