package com.app.beta.glam.fiesta.cmn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.BeanUtils;

import com.app.beta.glam.fiesta.db.UserLookbookDO;
import com.app.beta.glam.fiesta.model.LookbookBO;

public class LookbookCmn {
	private static final Logger logger = Logger.getLogger(LookbookCmn.class.getCanonicalName());

	public final static String getLookBookTitle(long lookbookid) {
		String lookbook_title = "";
		// load the lookbook
		UserLookbookDO lookbook = OfyService.ofy().load().type(UserLookbookDO.class).id(lookbookid).get();
		if (lookbook != null) {
			lookbook_title = lookbook.getLookbookname();
		}

		return lookbook_title;
	}
	
	public final static String getLookBookSEOUrl(long lookbookid) {
		String lookbook_seo_url = "";
		// load the lookbook
		UserLookbookDO lookbook = OfyService.ofy().load().type(UserLookbookDO.class).id(lookbookid).get();
		if (lookbook != null) {
			lookbook_seo_url = lookbook.getLookbook_seo_url();
		}

		return lookbook_seo_url;
	}
	
	public static List<LookbookBO> genRandomLooks() {
		List<LookbookBO> lkbklist = new ArrayList<LookbookBO>();

		List<String> lkbksnLst = new ArrayList<String>();
		
		lkbksnLst.add("Spring");
		lkbksnLst.add("Summer");
		lkbksnLst.add("Fall");
		lkbksnLst.add("Winter");
		lkbksnLst.add("All Season");
				
		Collections.shuffle(lkbksnLst);
		List<UserLookbookDO> lookbookdolist = OfyService.ofy().load().type(UserLookbookDO.class)
				.filter("lookbookseason", lkbksnLst.get(0)).list();
		
		Collections.shuffle(lookbookdolist);

		if (lookbookdolist != null && lookbookdolist.size() > 4) {
			for (int i = 0; i < 4; i++) {
				UserLookbookDO lookbookdo = null;
				LookbookBO lookbook = new LookbookBO();
				String[] ignoreFieldsLkbk = { "tags" };
				int tot_likes = 0;
				int tot_cmmnts = 0;

				lookbookdo = (UserLookbookDO) lookbookdolist.get(i);
				BeanUtils.copyProperties(lookbookdo, lookbook, ignoreFieldsLkbk);
				lookbook.setLookbook_owner(GlamCmn.getUsrnmStr(lookbookdo.getUserid()));

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
					lkbklist.add(lookbook);
				}
			}
		}
		return lkbklist;
	}
}
