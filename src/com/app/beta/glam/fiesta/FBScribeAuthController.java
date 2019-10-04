package com.app.beta.glam.fiesta;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.FacebookApi;
import org.scribe.exceptions.OAuthException;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import com.app.beta.glam.fiesta.cmn.AuthUtil;
import com.app.beta.glam.fiesta.cmn.GlamCmn;
import com.app.beta.glam.fiesta.cmn.GlamCnst;
import com.app.beta.glam.fiesta.cmn.OfyService;
import com.app.beta.glam.fiesta.db.UserDO;
import com.app.beta.glam.fiesta.form.UserForm;
import com.app.beta.glam.fiesta.model.UsrLoginBO;

@Controller
public class FBScribeAuthController {
	
	private static final Logger logger = Logger.getLogger(FBScribeAuthController.class.getCanonicalName());
	private static final String STATE = "state";
	private OAuthService oAuthService = new ServiceBuilder()
															.provider(FacebookApi.class).apiKey(GlamCmn._API_KEY_FB)
															.apiSecret(GlamCmn._API_SECRET_FB)
															.scope(GlamCmn._SOCIAL_SCOPES_FB)
															.callback(GlamCmn._CALLBACK_URL_FB)
															.build();
	private ObjectMapper mapper = null;

	@RequestMapping("/auth/facebook")
	public RedirectView startAuthentication(HttpSession session) throws OAuthException {
		String state = UUID.randomUUID().toString();
		session.setAttribute(STATE, state);
		String authorizationUrl = oAuthService.getAuthorizationUrl(Token.empty()) + "&" + STATE + "=" + state;
		return new RedirectView(authorizationUrl);
	}

	@RequestMapping("/auth/facebook/callback")
	public String callback(HttpServletRequest req, Model uiModel) throws IOException {

		String code = "";
		String error = "";
		String state = "";

		code = req.getParameter("code");
		error = req.getParameter("error");
		state = req.getParameter("state");
		
		StringBuilder errmssg = new StringBuilder();
		UsrLoginBO ul = new UsrLoginBO();
		
		logger.setLevel(Level.INFO);
		
		logger.info("code: " + code + " error: " + error + " state: " + state);
		
		mapper = new ObjectMapper();

		HttpSession httpsessn = req.getSession();
		
		boolean loginredir 			= false;
		boolean unmupdtreqdredir 	= false;
		
		String FB_userId = "";
		String FB_email = "";
		String first_name = "";
		String last_name = "";
		
		long gpoints = 0;
		
		if (StringUtils.isBlank(error)) {
			// Check the state parameter
			String stateFromSession = (String) httpsessn.getAttribute(STATE);
			httpsessn.removeAttribute(STATE);
			if (!state.equals(stateFromSession)) {
				logger.info(" **** The state returned by facebook doesn't match with state stores in session ****");
				loginredir = true;
			}

			// Exchange the code for an AccessToken and retrieve the profile
			Token accessToken = getAccessToken(code);
			Response response = getResponseForProfile(accessToken);
			if (!response.isSuccessful()) {
				logger.info(" **** Facebook token and request token doesn't match **** ");
				loginredir = true;
			}

			// Store the Facebook user id in the session and redirect the user
			// to the page that needs the profile.
			String responseBody = response.getBody();
			JsonNode jsonNode = mapper.readTree(responseBody);
			
			logger.info("**** jackson responseBody : " + response.getBody());
			
			JsonNode idNode = jsonNode.get("id");
			FB_userId = idNode.asText();
			
			JsonNode emailNode = jsonNode.get("email");
			FB_email = emailNode.asText();
			
			JsonNode fnmNode = jsonNode.get("first_name");
			first_name = fnmNode.asText();
					
			JsonNode lnmNode = jsonNode.get("last_name");
			last_name = lnmNode.asText();
			
			// check if user already exists or not
			List<UserDO> usrlist = OfyService.ofy().load().type(UserDO.class).filter("account_email", FB_email).list();
			if (usrlist != null && usrlist.size() > 0) {
				logger.info("**** Userlist size ****" + usrlist.size());
				UserDO usrdo = usrlist.get(0);
				
				// now check if we have the updated facebook id else save it
				if(!StringUtils.equals(FB_userId, String.valueOf(usrdo.getFacebook_usrid()))) {
					logger.info("**** user facebook id differs, so save with updated data ****");
					usrdo.setFacebook_usrid(Long.valueOf(FB_userId));
					//logger.info("*** usr values before saving " + usrdo);
					OfyService.ofy().save().entity(usrdo).now();
				}
				
				if(StringUtils.isBlank(usrdo.getAccount_username())) {
					unmupdtreqdredir = true;
				}else {
					ul.setUserid(usrdo.getUserid());
					ul.setAccount_username(usrdo.getAccount_username());
					ul.setFirst_name(usrdo.getFirst_name());
					ul.setLast_name(usrdo.getLast_name());
					ul.setIs_account_active(usrdo.isIs_account_active());
					ul.setLoginstatus(true);
					ul.setAccount_type(usrdo.getAccount_type());
					ul.setUavatar(usrdo.getUavatar());
				}
			}else {
				try {
					logger.info("**** user doesn't exist so save it ****");
					// user doesn't exist so save it
					String ipAddress = req.getHeader("X-FORWARDED-FOR");
					if (ipAddress == null) {
						ipAddress = req.getRemoteAddr();
					}
					
					UserDO userdo = new UserDO();
					String saltkey = AuthUtil.getGenrtdRndmAuthSalt();
					String hexed_passwd = AuthUtil.generateAuthPsswd("BH943JLW323JODJDSBYE", saltkey);
					
					userdo.setAccount_email(FB_email);
					userdo.setAccount_password(hexed_passwd);
					userdo.setAccount_pwd_salt(saltkey);
					userdo.setFirst_name(first_name);
					userdo.setLast_name(last_name);
					userdo.setIs_account_active(false);
					userdo.setSignup_ip_addr(ipAddress);
					userdo.setSignup_datetime(GlamCmn.getDateTmStr());
		
					// assign a default random profile image for the user
					userdo.setUavatar("http://graph.facebook.com/" + FB_userId + "/picture?type=large");
					userdo.setFacebook_ac("https://www.facebook.com/" + FB_userId);
					
					if(userdo.getMy_glam_points()>0) {
						gpoints = GlamCnst._GLAM_PTS_SIGNUP_BONUS + userdo.getMy_glam_points();
					} else {
						gpoints = GlamCnst._GLAM_PTS_SIGNUP_BONUS;
					}
					userdo.setMy_glam_points(gpoints);

					logger.info("*** usr values before saving " + userdo);
					
					OfyService.ofy().save().entity(userdo).now();
					
					GlamCmn.saveUsrActivity(userdo.getUserid(), userdo.getAccount_username(), GlamCnst._ATVY_PRFL_CREATE, 0, "", "");
					GlamCmn.saveGlamPointsActivity(userdo.getUserid(), 
												   userdo.getFirst_name(), 
												   GlamCnst._ATVY_PTS_ADDED, 
												   GlamCnst._GLAM_PTS_SIGNUP_BONUS, 
												   "Joining glamfiesta.com as SignUp bonus");
					unmupdtreqdredir = true;
					
					String email_txt = "Dear Fashionista, <br/>"
							+ "<p>Welcome to Glam Fiesta, where we celebrate fashion everyday! We're really glad you joined our growing community."
							+ "We recently launched Glam Fiesta, a startup fashion community platform for fashionistas like you to share and "
							+ "discuss new outfit ideas or outfit tips or outfit of the day (OOTD) or outfit of the night (OOTN) or look book "
							+ "photos and contribute to the most happening fashion trends.</p>"
							+ "<p>If you have YouTube fashion channel, you can embed your YouTube video URL in your look post and get good organic "
							+ "traffic from various search engines or traffic from our community. Our platform is growing and we are also growing "
							+ "with fashion ideas from you.</p>"
							+ "<p>If you are a fashion blogger, post your look photo and add link to your blog and get traffic linkback from our growing platform</p>"
							+ "<p>Should you have question or feedback or just want to say <i>Hi</i>, drop an email to <b>editor.glamfiesta@gmail.com</b>.<br/>"
							+ "So what are you waiting for? Go ahead and start posting your new look photo right away! "
							+ "Visit <b><a href='http://www.glamfiesta.com/dashboard/lookbook/postnew'>Post New Look</a></b> "
							+ "and start connecting with other fashionistas...</p>"
							+ "<p> Cheers!! <br/><br/> - Raj & Mita (Founders, Glam Fiesta) <br/><br/> "
							+ "<b>Proudly running on Google Cloud Platform ( <a href='www.glamfiesta.com/aboutus.htm'>About Us</a> )</b> </p>";
					
					//String email_txt2 = "A new user-" + userdo.getFirst_name() + " has joined glamfiesta.com ";
					
					GlamCmn.sendEmailToUsr(userdo.getAccount_email(), userdo.getAccount_username(), "Welcome to GlamFiesta !!", email_txt, true);
					//GlamCmn.sendEmailToUsr("suvoraj.biswas@gmail.com", "GlamFiesta", "", email_txt2, true);
					
				}catch (NoSuchAlgorithmException ne) {
					errmssg.append("Generic system exception has occurred... pls try again later...");
				}catch (InvalidKeySpecException ie) {
					errmssg.append("Generic system exception has occurred... pls try again later...");
				}
			}
			
		} else {
			uiModel.addAttribute("errmssg", "Error occurred while authenticating through Facebook... ");
			
			loginredir = true;
		}
		
		// redirect user to login page
		if(loginredir) {
			UserForm loginform = new UserForm();
			/*String _REFFR_URL = req.getHeader("Referer");

			if (StringUtils.contains(_REFFR_URL, "http://localhost:8888")) {
				_REFFR_URL = StringUtils.remove(_REFFR_URL, "http://");
			}else if (StringUtils.contains(_REFFR_URL, "http://www.glamfiesta.com")) {
				_REFFR_URL = StringUtils.remove(_REFFR_URL, "http://");
			}else if (StringUtils.contains(_REFFR_URL, "http://glamfiesta.com")) {
				_REFFR_URL = StringUtils.remove(_REFFR_URL, "http://");
			}else if (StringUtils.contains(_REFFR_URL, "http://localhost:8888/signup")) {
				_REFFR_URL = "http://localhost:8888/dashboard";
			}else if (StringUtils.contains(_REFFR_URL, "signup")) {
				_REFFR_URL = "http://www.glamfiesta.com/dashboard";
			}*/

			//logger.info("**** _REFFR_URL - " + _REFFR_URL);
		
			//httpsessn.setAttribute("_REFFR_URL", _REFFR_URL);

			uiModel.addAttribute("loginform", loginform);

			return GlamCnst._VIEW_LOGIN;
			
		} else if(unmupdtreqdredir) {
			UserForm accform = new UserForm();
			accform.setAccount_email(FB_email);
			uiModel.addAttribute("accform", accform);
			
			return GlamCnst._VIEW_UNMUPDT;
			
		} else {
			httpsessn.setAttribute("ul", ul);
			
			//if (StringUtils.isBlank((String) httpsessn.getAttribute("_REFFR_URL"))) {
				return "redirect:/dashboard";
			//} else {
				//return "redirect://" + (String) httpsessn.getAttribute("_REFFR_URL");
			//}
		} 
		
		
	}
	
	
	@RequestMapping(value = "/unmupdt", method = RequestMethod.POST)
	public String doUpdtUsrnm(@ModelAttribute UserForm accform, BindingResult result, Model uiModel, HttpServletRequest req) {
		StringBuilder errmssg = new StringBuilder();
		boolean updtunmsuccess = false;
		boolean reqd_fields_empty = false;
		HttpSession httpsessn = req.getSession();
		//String acc_usrnmstr = "";
		//String acc_emailstr = "";
		
		if (accform != null) {
			if (StringUtils.isBlank(accform.getAccount_email()) || 
				StringUtils.isBlank(accform.getAccount_username()) || 
				StringUtils.isBlank(accform.getCitynm())) {
				logger.info("**** any of the required field is missing ... ");
				reqd_fields_empty = true;
			}
			
			if(reqd_fields_empty) {
				errmssg.append(" One of the required field is empty, pls correct...");
			}else {
				// convert to lowercase
				accform.setAccount_username(StringUtils.lowerCase(accform.getAccount_username()));
				accform.setAccount_email(StringUtils.lowerCase(accform.getAccount_email()));

				// check if the username is already taken
				int cnt_usrnm = OfyService.ofy().load().type(UserDO.class).filter("account_username", accform.getAccount_username()).count();
				if(cnt_usrnm > 0) {
					errmssg.append(" The account username is already taken, pls use different username");
				} else {
					// save the user name
					List<UserDO> usrlist = OfyService.ofy().load().type(UserDO.class).filter("account_email", accform.getAccount_email()).list();
					UserDO usrdo = usrlist.get(0);
					if(StringUtils.equals(usrdo.getAccount_email(), accform.getAccount_email())) {
						usrdo.setAccount_username(accform.getAccount_username());
						usrdo.setCitynm(accform.getCitynm());
						usrdo.setIs_account_active(true);
						
						logger.info("*** usr values before saving " + usrdo);
						OfyService.ofy().save().entity(usrdo).now();
						GlamCmn.saveUsrActivity(usrdo.getUserid(), usrdo.getAccount_username(), GlamCnst._ATVY_PRFL_CREATE, 0, "", "");
						updtunmsuccess = true;
						
						UsrLoginBO ul = new UsrLoginBO();
						ul.setUserid(usrdo.getUserid());
						ul.setAccount_username(usrdo.getAccount_username());
						ul.setFirst_name(usrdo.getFirst_name());
						ul.setLast_name(usrdo.getLast_name());
						ul.setIs_account_active(usrdo.isIs_account_active());
						ul.setLoginstatus(true);
						ul.setAccount_type(usrdo.getAccount_type());
						ul.setUavatar(usrdo.getUavatar());
						
						httpsessn.setAttribute("ul", ul);
					}
				}
				
			}
		} else {
			errmssg.append(" Generic system exception occurred... pls try again later...");
		}
		
		if(updtunmsuccess) {
			return "redirect:/dashboard";
		}else {
			uiModel.addAttribute("errmssg", errmssg.toString());
			uiModel.addAttribute("accform", accform);
			
			return GlamCnst._VIEW_UNMUPDT;
		}
	}
	

	private Token getAccessToken(String code) {
		Verifier verifier = new Verifier(code);
		return oAuthService.getAccessToken(Token.empty(), verifier);
	}

	private Response getResponseForProfile(Token accessToken) {
		logger.info("**** accessToken : " + accessToken.toString());
		OAuthRequest oauthRequest = new OAuthRequest(Verb.GET, "https://graph.facebook.com/me");
		oAuthService.signRequest(accessToken, oauthRequest);
		return oauthRequest.send();
	}
	
}
