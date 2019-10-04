package com.app.beta.glam.fiesta;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.beta.glam.fiesta.cmn.GlamCmn;
import com.app.beta.glam.fiesta.cmn.GlamCnst;
import com.app.beta.glam.fiesta.cmn.LookbookCmn;
import com.app.beta.glam.fiesta.cmn.OfyService;
import com.app.beta.glam.fiesta.db.UserLookbookDO;
import com.app.beta.glam.fiesta.db.UserLookbookTagsDO;
import com.app.beta.glam.fiesta.model.LookbookBO;
import com.app.beta.glam.fiesta.model.UserBO;

@Controller
public class SearchController {
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String doSearch(HttpServletRequest req, Model uiModel) {
		
		String season 						= req.getParameter("season");
		String tag							= req.getParameter("tag");
		String style						= req.getParameter("style");
		String searchterm					= "";
		
		//long lkbkid;
		
		style								= StringUtils.replace(style, "+", " ");
		//System.out.println("style - " + style);
		
		List<LookbookBO> looks 				= new ArrayList<LookbookBO>();
		List<UserLookbookDO> lookbookdolist = new ArrayList<UserLookbookDO>();
		//List<Long> lkbkidlist = new ArrayList<Long>();
		
		//List<UserLookbookTagsDO> taglkbklist = null;
		
		if(StringUtils.isNotBlank(season)) {
			if(StringUtils.equals(season, "all")) {
				season = "All Season";
			}else {
				season = StringUtils.capitalize(season);
			}
			//System.out.println("Season found " + season);
			lookbookdolist = OfyService.ofy().load().type(UserLookbookDO.class).filter("lookbook_publish_status", "Yes")
																			   .filter("lookbookseason", season)
																			   .order("-createddt")
																			   .list();
			searchterm = season;
		}else if(StringUtils.isNotBlank(style)) {
			//System.out.println("style found " + style);
			
			lookbookdolist = OfyService.ofy().load().type(UserLookbookDO.class)
													.filter("lookbook_publish_status", "Yes")
												    .filter("lookbookstyle", style)
												    .order("-createddt")
												    .list();
			searchterm = style;
		}
		else if(StringUtils.isNotBlank(tag)) {
			String updtTag = StringUtils.replace(tag, "+", " ");
			//System.out.println("updtTag found - " + updtTag);
			lookbookdolist = OfyService.ofy().load().type(UserLookbookDO.class)
													.filter("lookbook_publish_status", "Yes")
												    .filter("tags", updtTag)
												    .order("-createddt")
												    .list();
			
			searchterm = updtTag;
		}
		
		if(lookbookdolist != null && lookbookdolist.size()>0) {
			for(int i=0; i<lookbookdolist.size(); i++) {
				UserLookbookDO lookbookdo = null;
				LookbookBO lookbook = new LookbookBO();
				UserBO usr = null;
				String[] ignoreFieldsLkbk = {"tags"};
				
				int tot_likes = 0;
				int tot_cmmnts = 0;
				
				lookbookdo =  (UserLookbookDO)lookbookdolist.get(i);
				
				BeanUtils.copyProperties(lookbookdo, lookbook, ignoreFieldsLkbk);
				
				if(lookbookdo.getTags()!=null && lookbookdo.getTags().size()>0) {
					List<String> tagslist = lookbookdo.getTags();
					String[] tags = tagslist.toArray(new String[tagslist.size()]);
					lookbook.setTags(tags);
				}
								
				if(lookbookdo.getComments_by_users()!=null) {
					tot_cmmnts = lookbookdo.getComments_by_users().size();
				}
				
				if(lookbookdo.getLiked_by_users()!=null) {
					tot_likes = lookbookdo.getLiked_by_users().size();
				}
				
				// update the user info
				usr = GlamCmn.getUsrInfo(lookbookdo.getUserid());
				usr.setLocation(GlamCmn.getUsrLocn(usr));
				
				lookbook.setUsrinfo(usr);
				lookbook.setTotal_comments(String.valueOf(tot_cmmnts));
				lookbook.setTotal_likes(String.valueOf(tot_likes));
				if(lookbookdo.getPublisheddt() != null) {
					lookbook.setPublished_dttm_str(GlamCmn.printDtttmStr(lookbookdo.getPublisheddt().toString()));
				}else if(lookbookdo.getCreateddt()!=null) {
					lookbook.setPublished_dttm_str(GlamCmn.printDtttmStr(lookbookdo.getCreateddt().toString()));
				}
				
				looks.add(lookbook);
			}
		}
		
		List<LookbookBO> rndmList = LookbookCmn.genRandomLooks();
				
		uiModel.addAttribute("looks", looks);
		uiModel.addAttribute("searchterm", searchterm);
		uiModel.addAttribute("randomlooks", rndmList);

		return GlamCnst._VIEW_SEARCH_RSLTS;
	}
}
