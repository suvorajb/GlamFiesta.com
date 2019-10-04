package com.app.beta.glam.fiesta;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.beta.glam.fiesta.cmn.GlamCnst;
import com.app.beta.glam.fiesta.cmn.OfyService;
import com.app.beta.glam.fiesta.db.UserLookbookDO;

@Controller
public class LookbookController {

	private static final Logger logger = Logger.getLogger(LookbookController.class.getCanonicalName());

	@RequestMapping(value = "/u/{uname}", method = RequestMethod.GET)
	public void goUsrProfilePage(@PathVariable String uname, HttpServletRequest req, HttpServletResponse response, Model uiModel) {
		String _U_REDIR_URL = GlamCnst._GF_URL + "/fashionistas/" + uname;
		logger.info("**** redirecting to the new URL - " + _U_REDIR_URL);
		
		response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
        response.setHeader("Location", _U_REDIR_URL);
        response.setHeader("Connection", "close");
	}


	@RequestMapping(value = "/u/{uname}/lookbook/view/{lookbookid}/{lookbooknm}", method = RequestMethod.GET)
	public void goLookbookViewPage(@PathVariable("uname") String uname, @PathVariable("lookbookid") long lookbookid,
			@PathVariable("lookbooknm") String looknooknm, HttpServletRequest req, HttpServletResponse response,
			Model uiModel) {
		
		UserLookbookDO lookbookdo = OfyService.ofy().load().type(UserLookbookDO.class).id(lookbookid).get();
		
		String _LKBK_REDIR_URL = GlamCnst._GF_URL + "/fashion/" + lookbookdo.getLookbook_seo_url();
		logger.info("**** redirecting to the new URL - " + _LKBK_REDIR_URL);
		
		response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
        response.setHeader("Location", _LKBK_REDIR_URL);
        response.setHeader("Connection", "close");

		/*
		 * UserBO usrbo = new UserBO(); LookbookBO lookbook = new LookbookBO();
		 * 
		 * UserDO usrdo = null;
		 * 
		 * LookbookBO latestlook = null; LookbookBO morelook = null;
		 * 
		 * UserLookbookDO lookbookdo = null; UserLookbookDO morelookbookdo =
		 * null;
		 * 
		 * List<LookbookBO> latestlooks = new ArrayList<LookbookBO>();
		 * List<LookbookBO> morelooks = new ArrayList<LookbookBO>();
		 * 
		 * List<UserDO> usrdolist = null;
		 * 
		 * int cntPhoto = 0; int tot_likes = 0; int tot_cmmnts = 0;
		 * 
		 * // logger.info("uname & lookbookid passed- " + uname + " - " + //
		 * lookbookid);
		 * 
		 * if (StringUtils.isNotBlank(uname) &&
		 * StringUtils.isNotBlank(String.valueOf(lookbookid))) { usrdolist =
		 * OfyService.ofy().load().type(UserDO.class).filter("account_username",
		 * uname).list();
		 * 
		 * // logger.info("**** usrdolist - " + usrdolist);
		 * 
		 * if (usrdolist != null && usrdolist.size() > 0) { usrdo = (UserDO)
		 * usrdolist.get(0); // long uid = usrdo.getUserid().longValue();
		 * 
		 * String[] ignorefldsUsr = { "account_email" };
		 * BeanUtils.copyProperties(usrdo, usrbo, ignorefldsUsr);
		 * 
		 * usrbo.setTotal_glampoints(GlamCmn.intToCoolFormatStr(usrdo.
		 * getMy_glam_points())); if (usrdo.getMy_followers() != null &&
		 * usrdo.getMy_followers().size() > 0) {
		 * usrbo.setTotal_followers(GlamCmn.intToCoolFormatStr(usrdo.
		 * getMy_followers().size())); } else { usrbo.setTotal_followers("0"); }
		 * 
		 * List<UserLookbookDO> lookbookdolist =
		 * OfyService.ofy().load().type(UserLookbookDO.class) .filter("userid",
		 * usrbo.getUserid()).filter("lookbook_publish_status", "Yes").list();
		 * if (lookbookdolist != null && lookbookdolist.size() > 0) {
		 * usrbo.setTotal_lookbooks(lookbookdolist.size()); } else {
		 * usrbo.setTotal_lookbooks(0); }
		 * 
		 * lookbookdo =
		 * OfyService.ofy().load().type(UserLookbookDO.class).id(lookbookid).get
		 * ();
		 * 
		 * lookbook.setIslookbook_liked_by_login_usr(false);
		 * 
		 * // check if user has liked the lookbook yet or not UsrLoginBO ulobj =
		 * (UsrLoginBO) req.getSession().getAttribute("ul"); if (ulobj != null)
		 * { UserDO likedusrdo =
		 * OfyService.ofy().load().type(UserDO.class).id(ulobj.getUserid()).get(
		 * ); if (likedusrdo.getMy_liked_lookbooks() != null &&
		 * (likedusrdo.getMy_liked_lookbooks().contains(lookbookid) == true)) {
		 * lookbook.setIslookbook_liked_by_login_usr(true); } // save the recent
		 * visit RecentVisitsDO recentvisit = new RecentVisitsDO();
		 * List<RecentVisitsDO> recentvisitlist =
		 * OfyService.ofy().load().type(RecentVisitsDO.class)
		 * .filter("visiting_userid", ulobj.getUserid()).filter("lookbookid",
		 * lookbookid).list(); if (recentvisitlist != null &&
		 * recentvisitlist.size() > 0) { recentvisit = recentvisitlist.get(0);
		 * recentvisit.setVisiting_datetime(new Date()); } else {
		 * recentvisit.setLookbookid(lookbookid);
		 * recentvisit.setLookbookname(lookbookdo.getLookbookname());
		 * recentvisit.setLookbook_seo_url(lookbookdo.getLookbook_seo_url());
		 * recentvisit.setLookbookimgstr(lookbookdo.getGcs_photo_url1());
		 * 
		 * recentvisit.setVisiting_datetime(new Date()); String ipAddress =
		 * req.getHeader("X-FORWARDED-FOR"); if (ipAddress == null) { ipAddress
		 * = req.getRemoteAddr(); } recentvisit.setVisiting_ip_addr(ipAddress);
		 * recentvisit.setVisiting_userid(ulobj.getUserid());
		 * recentvisit.setVisiting_username(ulobj.getAccount_username());
		 * recentvisit.setUavatar(ulobj.getUavatar()); }
		 * 
		 * OfyService.ofy().save().entity(recentvisit).now(); }
		 * 
		 * // logger.info("**** lookbookdo - " + lookbookdo); String[]
		 * ignorefldsLkbk = { "tags" }; if (lookbookdo != null) {
		 * BeanUtils.copyProperties(lookbookdo, lookbook, ignorefldsLkbk); if
		 * (StringUtils.isNotEmpty(lookbookdo.getLookbookstory())) {
		 * lookbook.setLookbookstory(GlamCmn.convrtAllWebAddrs(lookbookdo.
		 * getLookbookstory())); }
		 * 
		 * if (lookbookdo.getTags() != null && lookbookdo.getTags().size() > 0)
		 * { List<String> tagslist = lookbookdo.getTags(); List<String>
		 * updttagslist = new ArrayList<String>();
		 * 
		 * for (String tag : tagslist) { StringBuilder tmpTag = new
		 * StringBuilder();
		 * tmpTag.append(StringUtils.replace(StringUtils.trim(tag), " ", "+"));
		 * updttagslist.add(tmpTag.toString()); } String[] tags =
		 * tagslist.toArray(new String[tagslist.size()]);
		 * lookbook.setTags(tags);
		 * lookbook.setUpdtdtags(updttagslist.toArray(new
		 * String[updttagslist.size()])); }
		 * 
		 * if (StringUtils.isNotBlank(lookbook.getGcs_photo_url1())) {
		 * cntPhoto++;
		 * lookbook.setGcs_photo_url_slider_thumb1(lookbook.getGcs_photo_url1()
		 * + "=s200"); }
		 * 
		 * if (StringUtils.isNotBlank(lookbook.getGcs_photo_url2())) {
		 * cntPhoto++;
		 * lookbook.setGcs_photo_url_slider_thumb2(lookbook.getGcs_photo_url2()
		 * + "=s200"); }
		 * 
		 * if (StringUtils.isNotBlank(lookbook.getGcs_photo_url3())) {
		 * cntPhoto++;
		 * lookbook.setGcs_photo_url_slider_thumb3(lookbook.getGcs_photo_url3()
		 * + "=s200"); }
		 * 
		 * lookbook.setTotal_no_photos(cntPhoto);
		 * 
		 * if (lookbook.getLookbookstyle() != null &&
		 * lookbook.getLookbookstyle().length > 0) { StringBuilder stylebldr =
		 * new StringBuilder(); for (String style : lookbook.getLookbookstyle())
		 * { stylebldr.append("<a href='/search?style=" +
		 * StringUtils.replace(style, " ", "+") + "'>" + "#" + style + "</a> ");
		 * }
		 * 
		 * lookbook.setLookbookstylestr(stylebldr.toString()); }
		 * 
		 * if (lookbookdo.getComments_by_users() != null) { tot_cmmnts =
		 * lookbookdo.getComments_by_users().size(); }
		 * 
		 * if (lookbookdo.getPublisheddt() != null) {
		 * lookbook.setPublished_dttm_str(GlamCmn.printDtttmStr(lookbookdo.
		 * getPublisheddt().toString())); } else {
		 * lookbook.setPublished_dttm_str(GlamCmn.printDtttmStr(lookbookdo.
		 * getCreateddt().toString())); }
		 * 
		 * // update the SEO url
		 * //lookbook.setLookbook_seo_url(GlamCmn.updtSEOUrl(lookbook));
		 * 
		 * List<Long> liked_by_users_id_list = lookbookdo.getLiked_by_users();
		 * 
		 * if (liked_by_users_id_list != null) { tot_likes =
		 * liked_by_users_id_list.size(); List<UserBO> likedbyusrs = new
		 * ArrayList<UserBO>();
		 * 
		 * List<Long> likedusr_rndm_list =
		 * GlamCmn.getRandmList(liked_by_users_id_list,
		 * GlamCnst._LKBK_LIKE_USRS_LST);
		 * 
		 * if (likedusr_rndm_list != null && likedusr_rndm_list.size() > 0) {
		 * for (int i = 0; i < likedusr_rndm_list.size(); i++) { long liked_uid
		 * = likedusr_rndm_list.get(i).longValue();
		 * 
		 * UserDO liked_usrdo =
		 * OfyService.ofy().load().type(UserDO.class).id(liked_uid).get();
		 * UserBO liked_usr = new UserBO();
		 * 
		 * liked_usr.setUserid(liked_uid);
		 * liked_usr.setAccount_username(liked_usrdo.getAccount_username());
		 * 
		 * // if(StringUtils.contains(liked_usrdo.getUavatar(), // "resources"))
		 * { liked_usr.setUavatar_thumb_profile(liked_usrdo.getUavatar()); //
		 * }else { //
		 * liked_usr.setUavatar_thumb_profile(liked_usrdo.getUavatar() // +
		 * GlamCmn._UAVTR_PARAM_S65); // } likedbyusrs.add(liked_usr); } }
		 * 
		 * lookbook.setLikedbyusrs(likedbyusrs); }
		 * 
		 * lookbook.setTotal_comments(String.valueOf(tot_cmmnts));
		 * lookbook.setTotal_likes(String.valueOf(tot_likes));
		 * 
		 * // pickup some latest posted Lookbooks order by date
		 * List<UserLookbookDO> latestlookbookdolist = null;
		 * List<UserLookbookDO> morelookbookdolist = null;
		 * 
		 * latestlookbookdolist =
		 * OfyService.ofy().load().type(UserLookbookDO.class)
		 * .filter("lookbook_publish_status",
		 * "Yes").order("-createddt").limit(10).list();
		 * 
		 * morelookbookdolist =
		 * OfyService.ofy().load().type(UserLookbookDO.class) .filter("userid",
		 * lookbook.getUserid()).filter("lookbook_publish_status", "Yes")
		 * .order("createddt").list();
		 * 
		 * // logger.info("*********** latestlookbookdolist - " + //
		 * latestlookbookdolist); int i = 0;
		 * 
		 * if (latestlookbookdolist != null && latestlookbookdolist.size() > 0)
		 * { Iterator<UserLookbookDO> lookbookItr =
		 * latestlookbookdolist.iterator(); while (lookbookItr.hasNext()) {
		 * 
		 * lookbookdo = (UserLookbookDO) lookbookItr.next();
		 * 
		 * if (lookbookdo != null && lookbookdo.isDisapproved()) { // do nothing
		 * } else { latestlook = new LookbookBO();
		 * 
		 * BeanUtils.copyProperties(lookbookdo, latestlook, ignorefldsLkbk);
		 * latestlook.setLookbook_owner(GlamCmn.getUsrnmStr(lookbookdo.getUserid
		 * ()));
		 * 
		 * // update the SEO url
		 * //latestlook.setLookbook_seo_url(GlamCmn.updtSEOUrl(latestlook));
		 * 
		 * // truncate the lookbook story latestlook.setLookbookstory(
		 * GlamCmn.truncateAfterWords(10, lookbookdo.getLookbookstory()) +
		 * "...");
		 * 
		 * if (StringUtils.isNotBlank(lookbookdo.getGcs_photo_url1())) {
		 * latestlook.setGcs_photo_url_slider_thumb1(lookbookdo.
		 * getGcs_photo_url1() + "=s200"); }
		 * 
		 * if (i < 4) { if (lookbookid != latestlook.getLookbookid()) {
		 * latestlooks.add(latestlook); i++; } } } } }
		 * 
		 * // logger.info("**** morelookbookdolist - " + // morelookbookdolist);
		 * int j = 0; if (morelookbookdolist != null &&
		 * morelookbookdolist.size() > 0) { Iterator<UserLookbookDO>
		 * morelookbookItr = morelookbookdolist.iterator(); while
		 * (morelookbookItr.hasNext()) { morelookbookdo = (UserLookbookDO)
		 * morelookbookItr.next(); if (lookbookdo != null &&
		 * lookbookdo.isDisapproved()) { // do nothing } else { morelook = new
		 * LookbookBO(); BeanUtils.copyProperties(morelookbookdo, morelook,
		 * ignorefldsLkbk); morelook.setLookbook_owner(uname);
		 * 
		 * if (StringUtils.isNotBlank(morelookbookdo.getGcs_photo_url1())) {
		 * morelook.setGcs_photo_url_slider_thumb1(
		 * morelookbookdo.getGcs_photo_url1() + "=s150"); }
		 * 
		 * // update the SEO url
		 * //morelook.setLookbook_seo_url(GlamCmn.updtSEOUrl(morelook));
		 * 
		 * if (j < 4) { if (lookbookid != morelook.getLookbookid()) {
		 * morelooks.add(morelook); j++; } }
		 * 
		 * } } }
		 * 
		 * } } }
		 * 
		 * List<LookbookBO> rndmList = LookbookCmn.genRandomLooks(); //
		 * System.out.println("rndmList - " + rndmList);
		 * 
		 * CommentForm commentform = new CommentForm();
		 * commentform.setLookbookid(lookbookid);
		 * 
		 * uiModel.addAttribute("usrbo", usrbo);
		 * 
		 * uiModel.addAttribute("lookbook", lookbook);
		 * 
		 * uiModel.addAttribute("latestlooks", latestlooks);
		 * uiModel.addAttribute("morelooks", morelooks);
		 * uiModel.addAttribute("randomlooks", rndmList);
		 * 
		 * uiModel.addAttribute("commentform", commentform);
		 * 
		 * return "view_lookbook";
		 * 
		 */
	}

}
