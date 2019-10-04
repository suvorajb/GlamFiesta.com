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

import com.app.beta.glam.fiesta.cmn.GlamCmn;
import com.app.beta.glam.fiesta.cmn.GlamCnst;
import com.app.beta.glam.fiesta.cmn.OfyService;
import com.app.beta.glam.fiesta.db.UserLookbookDO;
import com.app.beta.glam.fiesta.model.LookbookBO;
import com.app.beta.glam.fiesta.model.UserBO;

@Controller
public class FashionSeasonController {

	private static final Logger logger = Logger.getLogger(FashionSeasonController.class.getCanonicalName());

	@RequestMapping(value = "/fashion/explore-summer-style", method = RequestMethod.GET)
	public String goSummerFashionPage(HttpServletRequest req, Model uiModel) {
		
		List<LookbookBO> looks = new ArrayList<LookbookBO>();
		List<UserLookbookDO> lookbookdolist = new ArrayList<UserLookbookDO>();
		
		lookbookdolist = OfyService.ofy().load().type(UserLookbookDO.class)
												.filter("lookbook_publish_status", "Yes")
												.filter("lookbookseason", GlamCnst._SMMR_SEASN)
												.order("-createddt")
												.list();
		
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

				// update the user info
				//usr = GlamCmn.getUsrInfo(lookbookdo.getUserid());
				//usr.setLocation(GlamCmn.getUsrLocn(usr));
				
				//lookbook.setUsrinfo(usr);
				if(lookbookdo.getPublisheddt() != null) {
					lookbook.setPublished_dttm_str(GlamCmn.printDtttmStr(lookbookdo.getPublisheddt().toString()));
				}else if(lookbookdo.getCreateddt()!=null) {
					lookbook.setPublished_dttm_str(GlamCmn.printDtttmStr(lookbookdo.getCreateddt().toString()));
				}
				
				looks.add(lookbook);
			}
		}
		
		uiModel.addAttribute("looks", looks);
		
		return GlamCnst._VIEW_SUMMR_FASHION;
	}


	@RequestMapping(value = "/fashion/explore-spring-style", method = RequestMethod.GET)
	public String goSpringFashionPage(HttpServletRequest req, Model uiModel) {
		
		/*List<LookbookBO> looks = new ArrayList<LookbookBO>();
		List<UserLookbookDO> lookbookdolist = new ArrayList<UserLookbookDO>();
		
		lookbookdolist = OfyService.ofy().load().type(UserLookbookDO.class).filter("lookbook_publish_status", "Yes")
				.filter("lookbookseason", season).order("-createddt").list();*/

		return GlamCnst._VIEW_SPRNG_FASHION;
	}

	
	@RequestMapping(value = "/fashion/explore-fall-style", method = RequestMethod.GET)
	public String goFallFashionPage(HttpServletRequest req, Model uiModel) {
		
		/*List<LookbookBO> looks = new ArrayList<LookbookBO>();
		List<UserLookbookDO> lookbookdolist = new ArrayList<UserLookbookDO>();
		
		lookbookdolist = OfyService.ofy().load().type(UserLookbookDO.class).filter("lookbook_publish_status", "Yes")
				.filter("lookbookseason", season).order("-createddt").list();*/

		return GlamCnst._VIEW_FALL_FASHION;
	}	
	
	@RequestMapping(value = "/fashion/explore-winter-style", method = RequestMethod.GET)
	public String goWinterFashionPage(HttpServletRequest req, Model uiModel) {
		
		/*List<LookbookBO> looks = new ArrayList<LookbookBO>();
		List<UserLookbookDO> lookbookdolist = new ArrayList<UserLookbookDO>();
		
		lookbookdolist = OfyService.ofy().load().type(UserLookbookDO.class).filter("lookbook_publish_status", "Yes")
				.filter("lookbookseason", season).order("-createddt").list();*/

		return GlamCnst._VIEW_WNTR_FASHION;
	}	
	
	@RequestMapping(value = "/fashion/explore-all-season-style", method = RequestMethod.GET)
	public String goAllSeasonFashionPage(HttpServletRequest req, Model uiModel) {
		
		/*List<LookbookBO> looks = new ArrayList<LookbookBO>();
		List<UserLookbookDO> lookbookdolist = new ArrayList<UserLookbookDO>();
		
		lookbookdolist = OfyService.ofy().load().type(UserLookbookDO.class).filter("lookbook_publish_status", "Yes")
				.filter("lookbookseason", season).order("-createddt").list();*/

		return GlamCnst._VIEW_ALLSN_FASHION;
	}	
}
