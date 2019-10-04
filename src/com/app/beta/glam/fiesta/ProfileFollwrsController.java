package com.app.beta.glam.fiesta;

import java.util.ArrayList;
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
import com.app.beta.glam.fiesta.db.UserActivitiesDO;
import com.app.beta.glam.fiesta.db.UserDO;
import com.app.beta.glam.fiesta.db.UserLookbookDO;
import com.app.beta.glam.fiesta.model.UserBO;
import com.app.beta.glam.fiesta.model.UsrActivityBO;

@Controller
public class ProfileFollwrsController {

	private static final Logger logger = Logger.getLogger(ProfileFollwrsController.class.getCanonicalName());
	
	@RequestMapping(value = "/u/{uname}/followers", method = RequestMethod.GET)
	public String goUsrProfileFollowersPage(@PathVariable String uname, HttpServletRequest req, Model uiModel) {

		UserBO usrbo = new UserBO();
		UserDO usrdo = null;
		
		List<UserDO> usrdolist = null;
		
		List<UserBO> followers = new ArrayList<UserBO>();
		List<Long> followrs_ids = null; 
		
		long uid = 0;
		int total_lkbk =0;
		
		if(uname!=null) {
			usrdolist = OfyService.ofy().load().type(UserDO.class).filter("account_username", uname).list();
			
			if(usrdolist!=null && usrdolist.size()>0) {
				usrdo = (UserDO) usrdolist.get(0); 
				uid = usrdo.getUserid().longValue();
				
				BeanUtils.copyProperties(usrdo, usrbo);
				
				usrbo.setLocation(GlamCmn.getUsrLocn(usrbo));
				if(StringUtils.equalsIgnoreCase("Yes", usrdo.getUavatar_is_uploaded())) {
					usrbo.setUavatar_thumb_profile(usrdo.getUavatar()+"=s100");
				}else {
					usrbo.setUavatar_thumb_profile(usrdo.getUavatar());
				}
				
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
				if(usrdo.getMy_followers()!=null && usrdo.getMy_followers().size()>0) {
					usrbo.setTotal_followers(GlamCmn.intToCoolFormatStr(usrdo.getMy_followers().size()));
				}else {
					usrbo.setTotal_followers("0");
				}
				
				total_lkbk = OfyService.ofy().load().type(UserLookbookDO.class).filter("userid", uid).filter("lookbook_publish_status", "Yes").count();
				usrbo.setTotal_lookbooks(total_lkbk);

				// retrieve all user followers
				followrs_ids = usrdo.getMy_followers();
				
				if(followrs_ids!=null && followrs_ids.size() > 0) {
					for(int j=0; j<followrs_ids.size(); j++) {
						UserDO followerdo = OfyService.ofy().load().type(UserDO.class).id(followrs_ids.get(j).longValue()).get();
						UserBO follower = new UserBO();
						
						String[] ignorefldsUsr = {"account_email"};
						BeanUtils.copyProperties(followerdo, follower, ignorefldsUsr);
						
						follower.setLocation(GlamCmn.getUsrLocn(follower));
						follower.setTotal_glampoints(GlamCmn.intToCoolFormatStr(followerdo.getMy_glam_points()));
						
						followers.add(follower);
					}
				}
			}
			
			uiModel.addAttribute("followers", followers);
			uiModel.addAttribute("usrbo", usrbo);
		}
		return GlamCnst._VIEW_PRFLE_FLLWRS;
		
	
	}
	
}
