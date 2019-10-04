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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.beta.glam.fiesta.cmn.GlamCmn;
import com.app.beta.glam.fiesta.cmn.GlamCnst;
import com.app.beta.glam.fiesta.cmn.LookbookCmn;
import com.app.beta.glam.fiesta.cmn.OfyService;
import com.app.beta.glam.fiesta.db.RecentVisitsDO;
import com.app.beta.glam.fiesta.db.UserDO;
import com.app.beta.glam.fiesta.db.UserLookbookDO;
import com.app.beta.glam.fiesta.form.CommentForm;
import com.app.beta.glam.fiesta.model.LookbookBO;
import com.app.beta.glam.fiesta.model.LookbookBOWrapper;
import com.app.beta.glam.fiesta.model.UserBO;
import com.app.beta.glam.fiesta.model.UsrLoginBO;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.QueryResultIterator;
import com.googlecode.objectify.cmd.Query;

@Controller
public class FashionController {

	private static final Logger logger = Logger.getLogger(FashionController.class.getCanonicalName());

	@RequestMapping(value = "/fashion", method = RequestMethod.GET)
	public String goFashionPage(HttpServletRequest req, Model uiModel) {
		
		List<LookbookBO> looks = new ArrayList<LookbookBO>();
		List<UserLookbookDO> lookbookdolist = new ArrayList<UserLookbookDO>();
		
		/*lookbookdolist = OfyService.ofy().load().type(UserLookbookDO.class).filter("lookbook_publish_status", "Yes")
				.filter("lookbookseason", season).order("-createddt").list();*/

		return GlamCnst._VIEW_FASHION;
	}

	@RequestMapping(value = "/fashion/{lkbkseourl}", method = RequestMethod.GET)
	public String goLookbookFashionPage(@PathVariable("lkbkseourl") String lkbkseourl, HttpServletRequest req,
			Model uiModel) {
		UserBO usrbo = new UserBO();
		LookbookBO lookbook = new LookbookBO();

		LookbookBO latestlook = null;
		LookbookBO morelook = null;

		UserLookbookDO lookbookdo = null;
		UserLookbookDO morelookbookdo = null;

		List<LookbookBO> latestlooks = new ArrayList<LookbookBO>();
		List<LookbookBO> morelooks = new ArrayList<LookbookBO>();

		int cntPhoto = 0;
		int tot_likes = 0;
		int tot_cmmnts = 0;
		String uname = "";
		long lookbookid = 0;

		// logger.info("******got lkbkseourl - " + lkbkseourl);

		if (StringUtils.isNotBlank(lkbkseourl)) {

			List<UserLookbookDO> lookbookdolist = OfyService.ofy().load().type(UserLookbookDO.class)
					.filter("lookbook_seo_url", lkbkseourl + ".htm").list();

			if (lookbookdolist != null && lookbookdolist.size() > 0) {
				lookbookdo = lookbookdolist.get(0);

				// logger.info("**** - lookbook : " +lookbookdo);
				lookbook.setIslookbook_liked_by_login_usr(false);

				// check if user has liked the lookbook yet or not
				UsrLoginBO ulobj = (UsrLoginBO) req.getSession().getAttribute("ul");
				if (ulobj != null) {
					UserDO likedusrdo = OfyService.ofy().load().type(UserDO.class).id(ulobj.getUserid()).get();
					if (likedusrdo.getMy_liked_lookbooks() != null
							&& (likedusrdo.getMy_liked_lookbooks().contains(lookbookid) == true)) {
						lookbook.setIslookbook_liked_by_login_usr(true);
					}
					// save the recent visit
					RecentVisitsDO recentvisit = new RecentVisitsDO();
					List<RecentVisitsDO> recentvisitlist = OfyService.ofy().load().type(RecentVisitsDO.class)
							.filter("visiting_userid", ulobj.getUserid()).filter("lookbookid", lookbookid).list();
					if (recentvisitlist != null && recentvisitlist.size() > 0) {
						recentvisit = recentvisitlist.get(0);
						recentvisit.setVisiting_datetime(new Date());
					} else {
						recentvisit.setLookbookid(lookbookid);
						recentvisit.setLookbookname(lookbookdo.getLookbookname());
						recentvisit.setLookbook_seo_url(lookbookdo.getLookbook_seo_url());
						recentvisit.setLookbookimgstr(lookbookdo.getGcs_photo_url1());

						recentvisit.setVisiting_datetime(new Date());
						String ipAddress = req.getHeader("X-FORWARDED-FOR");
						if (ipAddress == null) {
							ipAddress = req.getRemoteAddr();
						}
						recentvisit.setVisiting_ip_addr(ipAddress);
						recentvisit.setVisiting_userid(ulobj.getUserid());
						recentvisit.setVisiting_username(ulobj.getAccount_username());
						recentvisit.setUavatar(ulobj.getUavatar());
					}

					OfyService.ofy().save().entity(recentvisit).now();
				}

				logger.info("**** lookbookdo - " + lookbookdo);
				String[] ignorefldsLkbk = { "tags" };
				if (lookbookdo != null) {

					uname = lookbookdo.getUsername();
					lookbookid = lookbookdo.getLookbookid();

					usrbo = GlamCmn.getUsrInfo(uname);

					BeanUtils.copyProperties(lookbookdo, lookbook, ignorefldsLkbk);
					if (StringUtils.isNotEmpty(lookbookdo.getLookbookstory())) {
						lookbook.setLookbookstory(GlamCmn.convrtAllWebAddrs(lookbookdo.getLookbookstory()));
					}

					if (lookbookdo.getTags() != null && lookbookdo.getTags().size() > 0) {
						List<String> tagslist = lookbookdo.getTags();
						List<String> updttagslist = new ArrayList<String>();

						for (String tag : tagslist) {
							StringBuilder tmpTag = new StringBuilder();
							tmpTag.append(StringUtils.replace(StringUtils.trim(tag), " ", "+"));
							updttagslist.add(tmpTag.toString());
						}
						String[] tags = tagslist.toArray(new String[tagslist.size()]);
						lookbook.setTags(tags);
						lookbook.setUpdtdtags(updttagslist.toArray(new String[updttagslist.size()]));
					}

					if (StringUtils.isNotBlank(lookbook.getGcs_photo_url1())) {
						cntPhoto++;
						lookbook.setGcs_photo_url_slider_thumb1(lookbook.getGcs_photo_url1() + "=s200");
					}

					if (StringUtils.isNotBlank(lookbook.getGcs_photo_url2())) {
						cntPhoto++;
						lookbook.setGcs_photo_url_slider_thumb2(lookbook.getGcs_photo_url2() + "=s200");
					}

					if (StringUtils.isNotBlank(lookbook.getGcs_photo_url3())) {
						cntPhoto++;
						lookbook.setGcs_photo_url_slider_thumb3(lookbook.getGcs_photo_url3() + "=s200");
					}

					lookbook.setTotal_no_photos(cntPhoto);

					if (lookbook.getLookbookstyle() != null && lookbook.getLookbookstyle().length > 0) {
						StringBuilder stylebldr = new StringBuilder();
						for (String style : lookbook.getLookbookstyle()) {
							stylebldr.append("<a href='/search?style=" + StringUtils.replace(style, " ", "+") + "'>"
									+ "#" + style + "</a> ");
						}

						lookbook.setLookbookstylestr(stylebldr.toString());
					}

					if (lookbookdo.getComments_by_users() != null) {
						tot_cmmnts = lookbookdo.getComments_by_users().size();
					}

					if (lookbookdo.getPublisheddt() != null) {
						lookbook.setPublished_dttm_str(GlamCmn.printDtttmStr(lookbookdo.getPublisheddt().toString()));
					} else {
						lookbook.setPublished_dttm_str(GlamCmn.printDtttmStr(lookbookdo.getCreateddt().toString()));
					}

					List<Long> liked_by_users_id_list = lookbookdo.getLiked_by_users();

					if (liked_by_users_id_list != null) {
						tot_likes = liked_by_users_id_list.size();
						List<UserBO> likedbyusrs = new ArrayList<UserBO>();

						List<Long> likedusr_rndm_list = GlamCmn.getRandmList(liked_by_users_id_list,
								GlamCnst._LKBK_LIKE_USRS_LST);

						if (likedusr_rndm_list != null && likedusr_rndm_list.size() > 0) {
							for (int i = 0; i < likedusr_rndm_list.size(); i++) {
								long liked_uid = likedusr_rndm_list.get(i).longValue();

								UserDO liked_usrdo = OfyService.ofy().load().type(UserDO.class).id(liked_uid).get();
								UserBO liked_usr = new UserBO();

								liked_usr.setUserid(liked_uid);
								liked_usr.setAccount_username(liked_usrdo.getAccount_username());

								liked_usr.setUavatar_thumb_profile(liked_usrdo.getUavatar());
								likedbyusrs.add(liked_usr);
							}
						}

						lookbook.setLikedbyusrs(likedbyusrs);
					}

					lookbook.setTotal_comments(String.valueOf(tot_cmmnts));
					lookbook.setTotal_likes(String.valueOf(tot_likes));

					// pickup some latest posted Lookbooks order by date
					List<UserLookbookDO> latestlookbookdolist = null;
					List<UserLookbookDO> morelookbookdolist = null;

					latestlookbookdolist = OfyService.ofy().load().type(UserLookbookDO.class)
							.filter("lookbook_publish_status", "Yes").order("-createddt").limit(10).list();

					morelookbookdolist = OfyService.ofy().load().type(UserLookbookDO.class)
							.filter("userid", lookbook.getUserid()).filter("lookbook_publish_status", "Yes")
							.order("createddt").list();

					// logger.info("*********** latestlookbookdolist - " +
					// latestlookbookdolist);
					int i = 0;

					if (latestlookbookdolist != null && latestlookbookdolist.size() > 0) {
						Iterator<UserLookbookDO> lookbookItr = latestlookbookdolist.iterator();
						while (lookbookItr.hasNext()) {

							lookbookdo = (UserLookbookDO) lookbookItr.next();

							if (lookbookdo != null && lookbookdo.isDisapproved()) {
								// do nothing
							} else {
								latestlook = new LookbookBO();

								BeanUtils.copyProperties(lookbookdo, latestlook, ignorefldsLkbk);
								latestlook.setLookbook_owner(GlamCmn.getUsrnmStr(lookbookdo.getUserid()));

								// truncate the lookbook story
								latestlook.setLookbookstory(
										GlamCmn.truncateAfterWords(10, lookbookdo.getLookbookstory()) + "...");

								if (StringUtils.isNotBlank(lookbookdo.getGcs_photo_url1())) {
									latestlook.setGcs_photo_url_slider_thumb1(lookbookdo.getGcs_photo_url1() + "=s200");
								}

								if (i < 4) {
									if (lookbookid != latestlook.getLookbookid()) {
										latestlooks.add(latestlook);
										i++;
									}
								}
							}
						}
					}

					// logger.info("**** morelookbookdolist - " +
					// morelookbookdolist);
					int j = 0;
					if (morelookbookdolist != null && morelookbookdolist.size() > 0) {
						Iterator<UserLookbookDO> morelookbookItr = morelookbookdolist.iterator();
						while (morelookbookItr.hasNext()) {
							morelookbookdo = (UserLookbookDO) morelookbookItr.next();
							if (lookbookdo != null && lookbookdo.isDisapproved()) {
								// do nothing
							} else {
								morelook = new LookbookBO();
								BeanUtils.copyProperties(morelookbookdo, morelook, ignorefldsLkbk);
								morelook.setLookbook_owner(uname);

								if (StringUtils.isNotBlank(morelookbookdo.getGcs_photo_url1())) {
									morelook.setGcs_photo_url_slider_thumb1(
											morelookbookdo.getGcs_photo_url1() + "=s150");
								}

								if (j < 4) {
									if (lookbookid != morelook.getLookbookid()) {
										morelooks.add(morelook);
										j++;
									}
								}

							}
						}
					}

				}
			}
		}

		List<LookbookBO> rndmList = LookbookCmn.genRandomLooks();
		// System.out.println("rndmList - " + rndmList);

		CommentForm commentform = new CommentForm();
		commentform.setLookbookid(lookbookid);

		uiModel.addAttribute("usrbo", usrbo);

		uiModel.addAttribute("lookbook", lookbook);

		uiModel.addAttribute("latestlooks", latestlooks);
		uiModel.addAttribute("morelooks", morelooks);
		uiModel.addAttribute("randomlooks", rndmList);

		uiModel.addAttribute("commentform", commentform);

		return GlamCnst._VIEW_LKBK;
	}

	@RequestMapping(value = "/fashion/street-style", method = RequestMethod.GET)
	public String newStreetStylePage(HttpServletRequest req, Model uiModel) {

		List<UserLookbookDO> lookbookdolist = new ArrayList<UserLookbookDO>();
		List<LookbookBO> looks = new ArrayList<LookbookBO>();

		LookbookBOWrapper lookbookWrapper = new LookbookBOWrapper();

		List<UserBO> newusers = new ArrayList<UserBO>();

		List<UserBO> leaders = new ArrayList<UserBO>();

		Query<UserLookbookDO> queryLookbook = null;

		String next_cursor_str = "";
		String prev_cursor_str = "";

		String next_page = req.getParameter("next_page");

		String curr_page = null;

		if (StringUtils.isNotBlank(next_page)) {
			curr_page = next_page;
		}

		StringBuilder mssg_usr = new StringBuilder("");

		if (StringUtils.isNotBlank(curr_page)) {
			queryLookbook = OfyService.ofy().load().type(UserLookbookDO.class).filter("lookbook_publish_status", "Yes")
					.filter("disapproved", false).order("-createddt").startAt(Cursor.fromWebSafeString(curr_page))
					.limit(GlamCmn._HOME_LKBK_LMT);
		} else {
			queryLookbook = OfyService.ofy().load().type(UserLookbookDO.class).filter("lookbook_publish_status", "Yes")
					.filter("disapproved", false).order("-createddt").limit(GlamCmn._HOME_LKBK_LMT);
		}

		QueryResultIterator<UserLookbookDO> lookbookDoItr = queryLookbook.iterator();

		if (StringUtils.isBlank(next_page)) {
			// this is page# 1 load so prev page is blank
			prev_cursor_str = "";
			next_cursor_str = lookbookDoItr.getCursor().toWebSafeString();

		} else if (StringUtils.isNotBlank(next_page)) {
			// user has clicked next page
			prev_cursor_str = next_page;
			next_cursor_str = lookbookDoItr.getCursor().toWebSafeString();
		}

		while (lookbookDoItr.hasNext()) {
			UserLookbookDO lkdo = lookbookDoItr.next();
			lookbookdolist.add(lkdo);
		}

		if (lookbookdolist != null && lookbookdolist.size() > 0) {
			Iterator<UserLookbookDO> lookbookItr = lookbookdolist.iterator();
			if (lookbookItr != null) {

				while (lookbookItr.hasNext()) {

					UserLookbookDO lookbookdo = (UserLookbookDO) lookbookItr.next();

					LookbookBO lookbook = new LookbookBO();
					UserBO usr = null;
					String[] ignoreFieldsLkbk = { "tags" };

					BeanUtils.copyProperties(lookbookdo, lookbook, ignoreFieldsLkbk);

					// truncate the lookbook story
					lookbook.setLookbookstory(GlamCmn.truncateAfterWords(50, lookbookdo.getLookbookstory()) + "...");

					if (lookbookdo.getTags() != null && lookbookdo.getTags().size() > 0) {
						List<String> tagslist = lookbookdo.getTags();
						String[] tags = tagslist.toArray(new String[tagslist.size()]);
						lookbook.setTags(tags);
					}

					// update the user info
					usr = GlamCmn.getUsrInfo(lookbookdo.getUserid());
					usr.setLocation(GlamCmn.getUsrLocn(usr));

					lookbook.setUsrinfo(usr);
					if (lookbookdo.getPublisheddt() != null) {
						lookbook.setPublished_dttm_str(GlamCmn.printDtttmStr(lookbookdo.getPublisheddt().toString()));
					} else if (lookbookdo.getCreateddt() != null) {
						lookbook.setPublished_dttm_str(GlamCmn.printDtttmStr(lookbookdo.getCreateddt().toString()));
					}

					looks.add(lookbook);
				} // end while
			}
		}

		if (looks != null && looks.size() > 0) {
			// Collections.shuffle(looks);
			lookbookWrapper.setLookbooklist(looks);
			lookbookWrapper.setNext_page(next_cursor_str);
			lookbookWrapper.setPrev_page(prev_cursor_str);
		} else {
			mssg_usr.append(
					" You have reached last page... please click here to go back to first page to load the fresh looks ");
			lookbookWrapper.setPrev_page(prev_cursor_str);
		}

		List<UserDO> leaderslist = OfyService.ofy().load().type(UserDO.class).order("-my_glam_points").limit(100)
				.list();

		if (leaderslist != null && leaderslist.size() > 0) {
			// populate the new fashionistsas list
			Iterator<UserDO> leadersItr = leaderslist.iterator();
			while (leadersItr.hasNext()) {
				UserDO leaderdo = leadersItr.next();
				UserBO leader = new UserBO();

				long uid = leaderdo.getUserid().longValue();

				String[] ignorefldsUsr = { "account_email" };
				BeanUtils.copyProperties(leaderdo, leader, ignorefldsUsr);
				leader.setUavatar_thumb_profile(GlamCmn.getResizeImg(leaderdo, 2));
				leader.setLocation(GlamCmn.getUsrLocn(leader));

				leaders.add(leader);

				if (leaders.size() > 19)
					break;
			}

		}

		List<UserDO> newuserlist = OfyService.ofy().load().type(UserDO.class).order("-signup_dttm").limit(100).list();

		if (newuserlist != null && newuserlist.size() > 0) {
			// populate the new fashionistsas list
			Iterator<UserDO> newusersItr = newuserlist.iterator();
			while (newusersItr.hasNext()) {
				UserDO newuserdo = newusersItr.next();
				UserBO newuser = new UserBO();

				long uid = newuserdo.getUserid().longValue();

				String[] ignorefldsUsr = { "account_email" };
				BeanUtils.copyProperties(newuserdo, newuser, ignorefldsUsr);
				newuser.setUavatar_thumb_profile(GlamCmn.getResizeImg(newuserdo, 2));
				newuser.setLocation(GlamCmn.getUsrLocn(newuser));

				if (newuserdo.getMy_glam_points() >= 300) {
					newusers.add(newuser);
				}

				if (newusers.size() > 19)
					break;
			}
		}

		uiModel.addAttribute("lookbookwrapper", lookbookWrapper);
		uiModel.addAttribute("globaltags", GlamCmn.globaltags);
		uiModel.addAttribute("newusers", newusers);
		uiModel.addAttribute("leaders", leaders);
		uiModel.addAttribute("usrmssg", mssg_usr);

		return GlamCnst._VIEW_NEW_STRT_STYLE;
	}

	@RequestMapping(value = "/fashion/editor-choice-top-street-style", method = RequestMethod.GET)
	public String topStreetStylePage(HttpServletRequest req, Model uiModel) {

		return GlamCnst._VIEW_NEW_STRT_STYLE;
	}
}
