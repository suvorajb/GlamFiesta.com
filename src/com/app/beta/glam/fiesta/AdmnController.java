package com.app.beta.glam.fiesta;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.beta.glam.fiesta.cmn.GlamCmn;
import com.app.beta.glam.fiesta.cmn.OfyService;
import com.app.beta.glam.fiesta.db.FeaturedUsersDO;
import com.app.beta.glam.fiesta.db.UserDO;
import com.app.beta.glam.fiesta.db.UserGiveawayWinnerDO;
import com.app.beta.glam.fiesta.form.FtrdFashionistaForm;
import com.app.beta.glam.fiesta.form.UserForm;
import com.app.beta.glam.fiesta.form.UserGiveawayWinnerForm;
import com.app.beta.glam.fiesta.model.UserBO;
import com.app.beta.glam.fiesta.model.UsrContainer;

@Controller
public class AdmnController {
	

	private static final Logger logger = Logger.getLogger(AdmnController.class.getCanonicalName());
	

	@RequestMapping(value = "/mnda/usr/list")
	public void getAllUsersCSV(HttpServletRequest req, HttpServletResponse res) throws IOException{
		List<UserBO> users = new ArrayList<UserBO>();
		UserDO userdo = null;
		StringBuilder csvStrBldr = new StringBuilder();
		
		UserBO usrbo = null;
		UserDO usrdo = null;
		
		if(GlamCmn.chkLoggdInStatus(req)) {
			UsrContainer uc = GlamCmn.getLoggedInUsrDtls(req);
			usrbo = uc.getUsrbo();
			usrdo = uc.getUsrdo();
			
			if(StringUtils.equalsIgnoreCase(usrdo.getAccount_username(), "suvoraj239")) {
				
				res.setContentType("text/csv");
				String reportName = "CSV_Usr_List.csv";
				res.setHeader("Content-disposition", "attachment;filename="+reportName);
				
				csvStrBldr.append("Email,Firstname,Lastname");
				csvStrBldr.append("\n");
				
				List<UserDO> usrdolist = OfyService.ofy().load()
														 .type(UserDO.class)
														 .order("-signup_dttm")
														 .list();
		
				if (usrdolist != null && usrdolist.size() > 0) {
					Iterator<UserDO> usrItr = usrdolist.iterator();
		
					while (usrItr.hasNext()) {
						userdo = usrItr.next();
		
						if (userdo != null) {
	
							if(StringUtils.isNotBlank(userdo.getAccount_email())) {
								csvStrBldr.append(userdo.getAccount_email() + ",");
							}else {
								csvStrBldr.append(",");
							}
							
							if(StringUtils.isNotBlank(userdo.getFirst_name())) {
								csvStrBldr.append(userdo.getFirst_name() + ",");
							}else {
								csvStrBldr.append(",");
							}
							
							if(StringUtils.isNotBlank(userdo.getLast_name())) {
								csvStrBldr.append(userdo.getLast_name() + "\n");
							}else {
								csvStrBldr.append("\n");
							}
							
							
						}
					}
				}
		
				res.getOutputStream().print(csvStrBldr.toString());
			
		}
		}
	}

	/******************************************************************************************************/
	

	@RequestMapping(value = "/mnda/unm/{uid}", method = RequestMethod.GET)
	public String goAdmnUsrMngPage(@PathVariable("uid") long uid,
			HttpServletRequest req, Model uiModel) {

		UserBO usrbo = null;
		UserDO usrdo = null;

		UserForm uform = new UserForm();

		UserDO udo = null;

		if (GlamCmn.chkLoggdInStatus(req)) {
			UsrContainer uc = GlamCmn.getLoggedInUsrDtls(req);
			usrbo = uc.getUsrbo();
			usrdo = uc.getUsrdo();

			if (StringUtils.equalsIgnoreCase(usrdo.getAccount_username(),
					"suvoraj239")) {

				udo = OfyService.ofy().load().type(UserDO.class).id(uid).get();

				if (udo != null) {
					uform.setUid(uid);
					uform.setAccount_username(udo.getAccount_username());
				}

			}
		}

		uiModel.addAttribute("uform", uform);

		uiModel.addAttribute("usrbo", usrbo);
		return "admn_mng_uname";
	}

	
	@RequestMapping(value = "/mnda/unm", method = RequestMethod.POST)
	public String doAdmnUsrMngPage(@ModelAttribute UserForm uform,
			BindingResult result, Model uiModel, HttpServletRequest req) {

		UserBO usrbo = null;
		UserDO usrdo = null;

		UserDO udo = null;
		StringBuilder succsmssg = new StringBuilder();
		
		if (GlamCmn.chkLoggdInStatus(req)) {
			UsrContainer uc = GlamCmn.getLoggedInUsrDtls(req);
			usrbo = uc.getUsrbo();
			usrdo = uc.getUsrdo();


			if (StringUtils.equalsIgnoreCase(usrdo.getAccount_username(),
					"suvoraj239")) {

				long uid = uform.getUid().longValue();
				udo = OfyService.ofy().load().type(UserDO.class).id(uid).get();

				if (udo != null) {
					udo.setAccount_username(StringUtils.strip(uform.getAccount_username()));
					OfyService.ofy().save().entity(udo).now();
					succsmssg.append(" User update is successful ");
				}
			}
		}

		uiModel.addAttribute("usrbo", usrbo);
		uiModel.addAttribute("succsmssg", succsmssg);
		
		return "redirect:/dashboard";
	}
	
	

	@RequestMapping(value = "/mnda/ftrd/{fashionistanm}", method = RequestMethod.GET)
	public String goSveAdmnFtrdFashionista(@PathVariable("fashionistanm") String fashionistanm, HttpServletRequest req, Model uiModel) {
		UserBO usrbo = null;
		UserDO usrdo = null;
		StringBuilder succsmssg = new StringBuilder();
		FtrdFashionistaForm ftrdfashionistaform = new FtrdFashionistaForm();
		
		if(GlamCmn.chkLoggdInStatus(req)) {
			UsrContainer uc = GlamCmn.getLoggedInUsrDtls(req);
			usrbo = uc.getUsrbo();
			usrdo = uc.getUsrdo();
			
			
			if(StringUtils.equalsIgnoreCase(usrdo.getAccount_username(), "suvoraj239")) {
				ftrdfashionistaform.setAccount_username(fashionistanm);
				ftrdfashionistaform.setFtrd_publish_status("Yes");
				
				uiModel.addAttribute("usrbo", usrbo);				
				uiModel.addAttribute("succsmssg", succsmssg);
				uiModel.addAttribute("ftrdfashionistaform", ftrdfashionistaform);
				
				return "admn_mng_ftrd_fashionista";
			}
		}
		
		uiModel.addAttribute("usrbo", usrbo);
		
		return "redirect:/dashboard";
	}
	
	

	@RequestMapping(value = "/mnda/giveaway/", method = RequestMethod.GET)
	public String goSveAdmnGiveawayWinner(HttpServletRequest req, Model uiModel) {
		UserBO usrbo = null;
		UserDO usrdo = null;
		StringBuilder succsmssg = new StringBuilder();
		UserGiveawayWinnerForm giveawaywinnerform = new UserGiveawayWinnerForm();
		
		if(GlamCmn.chkLoggdInStatus(req)) {
			UsrContainer uc = GlamCmn.getLoggedInUsrDtls(req);
			usrbo = uc.getUsrbo();
			usrdo = uc.getUsrdo();
			
			
			if(StringUtils.equalsIgnoreCase(usrdo.getAccount_username(), "suvoraj239")) {
				
				uiModel.addAttribute("usrbo", usrbo);				
				uiModel.addAttribute("succsmssg", succsmssg);
				uiModel.addAttribute("giveawaywinnerform", giveawaywinnerform);
				
				return "admn_mng_giveaway_winner";
			}
		}
		
		uiModel.addAttribute("usrbo", usrbo);
		
		return "redirect:/dashboard";
	}	
	
	
/****************************************************************************************** POST *******************************************************************************/
	
	
	@RequestMapping(value = "/mnda/ftrd", method = RequestMethod.POST)
	public String doMngFtrdFashionista(@ModelAttribute FtrdFashionistaForm ftrdfashionistaform, BindingResult result, Model uiModel, HttpServletRequest req) {
		
		UserBO usrbo = null;
		UserDO usrdo = null;
		
		FeaturedUsersDO ftrdfashionistado = new FeaturedUsersDO();
		StringBuilder succsmssg = new StringBuilder();
		
		
		if(GlamCmn.chkLoggdInStatus(req)) {
			UsrContainer uc = GlamCmn.getLoggedInUsrDtls(req);
			usrbo = uc.getUsrbo();
			usrdo = uc.getUsrdo();
						
			if(StringUtils.equalsIgnoreCase(usrdo.getAccount_username(), "suvoraj239")) {
				// retrieve the lookbook first based on the lookbookid
				List<UserDO> fashionistadolist = OfyService.ofy().load().type(UserDO.class)
																	.filter("account_username", ftrdfashionistaform.getAccount_username())
																	.list();
				
				if(fashionistadolist!=null && fashionistadolist.size()>0) {
					UserDO fashionistado = fashionistadolist.get(0);
					
					ftrdfashionistado.setUserid(fashionistado.getUserid());
					ftrdfashionistado.setNote_from_editor(ftrdfashionistaform.getNote_from_editor());
					ftrdfashionistado.setYoutube_video_url1(ftrdfashionistaform.getYoutube_video_url1());
					ftrdfashionistado.setYoutube_video_url2(ftrdfashionistaform.getYoutube_video_url2());
					ftrdfashionistado.setYoutube_video_url3(ftrdfashionistaform.getYoutube_video_url3());
					ftrdfashionistado.setFashion_blog_url1(ftrdfashionistaform.getFashion_blog_url1());
					
					if(StringUtils.equalsIgnoreCase(ftrdfashionistaform.getFtrd_publish_status(), "Yes")) {
						OfyService.ofy().save().entity(ftrdfashionistado).now();
						
						succsmssg.append(" Featured Fashionista is updated successful ");
					}
				}
				
				uiModel.addAttribute("usrbo", usrbo);
				uiModel.addAttribute("succsmssg", succsmssg);
				uiModel.addAttribute("ftrdfashionistaform", ftrdfashionistaform);
			
				return "admn_mng_ftrd_fashionista";
			}
		}
		
		return "redirect:/dashboard";
	}
	
	
	
	@RequestMapping(value = "/mnda/giveaway/",  method = RequestMethod.POST)
	public String doSveAdmnGiveawayWinner(UserGiveawayWinnerForm giveawaywinnerform, BindingResult result, Model uiModel, HttpServletRequest req) {
		
		UserBO usrbo = null;
		UserDO usrdo = null;
		
		UserGiveawayWinnerDO giveawaywinnerdo = new UserGiveawayWinnerDO();
		StringBuilder succsmssg = new StringBuilder();
		
		
		if(GlamCmn.chkLoggdInStatus(req)) {
			UsrContainer uc = GlamCmn.getLoggedInUsrDtls(req);
			usrbo = uc.getUsrbo();
			usrdo = uc.getUsrdo();
						
			if(StringUtils.equalsIgnoreCase(usrdo.getAccount_username(), "suvoraj239")) {
				// retrieve the lookbook first based on the lookbookid
				List<UserDO> usrdolist = OfyService.ofy().load()
														 .type(UserDO.class)
														 .filter("account_username", giveawaywinnerform.getUsername())
														 .list();
				
				if(usrdolist!=null && usrdolist.size()>0) {
					UserDO winnerusrdo = usrdolist.get(0);
					
					giveawaywinnerdo.setUserid(winnerusrdo.getUserid());
					giveawaywinnerdo.setUsername(winnerusrdo.getAccount_username());
					giveawaywinnerdo.setUavatar(winnerusrdo.getUavatar());
					
					giveawaywinnerdo.setDate_of_winning(giveawaywinnerform.getDate_of_winning());
					giveawaywinnerdo.setGiveaway_rewards_amnt(giveawaywinnerform.getGiveaway_rewards_amnt());
					
					giveawaywinnerdo.setLookbook_winning_url1(giveawaywinnerform.getLookbook_winning_url1());
/*					giveawaywinnerdo.setLookbook_winning_url2(giveawaywinnerform.getLookbook_winning_url2());
					giveawaywinnerdo.setLookbook_winning_url3(giveawaywinnerform.getLookbook_winning_url3());
*/					
					
					OfyService.ofy().save().entity(giveawaywinnerdo).now();
					
					succsmssg.append(" Giveaway winner is added successfully ");
				}
				
				uiModel.addAttribute("usrbo", usrbo);
				uiModel.addAttribute("succsmssg", succsmssg);
				uiModel.addAttribute("giveawaywinnerform", giveawaywinnerform);
			
				return "admn_mng_giveaway_winner";
			}
		}
		
		return "redirect:/dashboard";
	}
	
}
