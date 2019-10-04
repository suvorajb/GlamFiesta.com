package com.app.beta.glam.fiesta;

import java.util.ArrayList;
import java.util.Collections;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.beta.glam.fiesta.cmn.GlamCmn;
import com.app.beta.glam.fiesta.cmn.GlamCnst;
import com.app.beta.glam.fiesta.cmn.OfyService;
import com.app.beta.glam.fiesta.db.LookbookCommentsDO;
import com.app.beta.glam.fiesta.db.UserDO;
import com.app.beta.glam.fiesta.db.UserLookbookDO;
import com.app.beta.glam.fiesta.form.CommentForm;
import com.app.beta.glam.fiesta.form.MssgBean;
import com.app.beta.glam.fiesta.model.CommentBO;
import com.app.beta.glam.fiesta.model.LookbookBO;
import com.app.beta.glam.fiesta.model.LookbookBOWrapper;
import com.app.beta.glam.fiesta.model.UserBO;
import com.app.beta.glam.fiesta.model.UsrContainer;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.QueryResultIterator;
import com.google.gson.Gson;
import com.googlecode.objectify.cmd.Query;

@Controller
public class IndxPgController {

	private static final Logger logger = Logger.getLogger(IndxPgController.class.getCanonicalName());

	@RequestMapping("/*")
	public String home(Model uiModel) {
		return "index";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getNewLooks(HttpServletRequest req, Model uiModel) {

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

		return GlamCnst._VIEW_HOME;
	}

}
