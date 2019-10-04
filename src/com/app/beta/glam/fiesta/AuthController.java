package com.app.beta.glam.fiesta;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.beta.glam.fiesta.cmn.AuthUtil;
import com.app.beta.glam.fiesta.cmn.GlamCmn;
import com.app.beta.glam.fiesta.cmn.GlamCnst;
import com.app.beta.glam.fiesta.cmn.OfyService;
import com.app.beta.glam.fiesta.db.UserDO;
import com.app.beta.glam.fiesta.db.UserLoginResetDO;
import com.app.beta.glam.fiesta.form.UserForm;
import com.app.beta.glam.fiesta.model.UsrLoginBO;

@Controller
public class AuthController {

	private static final Logger logger = Logger.getLogger(AuthController.class
			.getCanonicalName());

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String goSignupPage(HttpServletRequest req, Model uiModel) {
		
		if(GlamCmn.chkLoggdInStatus(req)) {
			return "redirect:/dashboard";
		}else {
			UserForm signupform = new UserForm();
			signupform.setGender("Female");
			uiModel.addAttribute("signupform", signupform);
			return GlamCnst._VIEW_SIGNUP;
		}
	}

	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String goLoginPage(HttpServletRequest req, Model uiModel) {
		
		if(GlamCmn.chkLoggdInStatus(req)) {
			return "redirect:/dashboard";
		}else {
			UserForm loginform = new UserForm();
			String _REFFR_URL = req.getHeader("Referer");
			String succsmssg = req.getParameter("succsmssg");
			
			//remove http://
			if (StringUtils.contains(_REFFR_URL, "http://www.glamfiesta.com")) {
				_REFFR_URL = StringUtils.remove(_REFFR_URL, "http://");
			}else if (StringUtils.contains(_REFFR_URL, "http://glamfiesta.com")) {
				_REFFR_URL = StringUtils.remove(_REFFR_URL, "http://");
			}
			
			if (StringUtils.contains(_REFFR_URL, "signup")) {
				_REFFR_URL = "www.glamfiesta.com/dashboard";
			}
	
			//logger.info("**** _REFFR_URL - " + _REFFR_URL);
	
			HttpSession httpsessn = req.getSession();
			httpsessn.setAttribute("_REFFR_URL", _REFFR_URL);
	
			uiModel.addAttribute("loginform", loginform);
			uiModel.addAttribute("succsmssg", succsmssg);
			
			return GlamCnst._VIEW_LOGIN;
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String doLogout(HttpServletRequest req, Model uiModel) {
		UsrLoginBO ul = (UsrLoginBO) req.getSession().getAttribute("ul");
		if (ul != null) {
			ul = null;
		}
		req.getSession().setAttribute("ul", ul);
		req.getSession().setAttribute("_REFFR_URL", null);
		req.getSession().invalidate();

		UserForm loginform = new UserForm();
		uiModel.addAttribute("loginform", loginform);

		return GlamCnst._VIEW_LOGIN;
	}

	@RequestMapping(value = "/forgotpwd", method = RequestMethod.GET)
	public String goForgotPwdPage(HttpServletRequest req, Model uiModel) {

		UserForm forgotpform = new UserForm();

		uiModel.addAttribute("forgotpform", forgotpform);

		return GlamCnst._VIEW_FRGTPWD;
	}

	

	@RequestMapping(value = "/resetpwd", method = RequestMethod.GET)
	public String goResetPwdPage(HttpServletRequest req, Model uiModel) {
		String uname 	= req.getParameter("account_username");
		String uemail 	= req.getParameter("account_email");
		String rkey 	= req.getParameter("reset_key");
		
		UserForm resetform = new UserForm();
		resetform.setAccount_username(uname);
		resetform.setAccount_email(uemail);
		resetform.setReset_key(rkey);

		uiModel.addAttribute("resetform", resetform);

		return GlamCnst._VIEW_RSTPWD;
	}
	
	
	/*************************************** Request Type - POST **************************************/

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String doSignup(@ModelAttribute UserForm signupform,
			BindingResult result, Model uiModel, HttpServletRequest req) {
		UserDO userdo = new UserDO();
		long usrid = 0;
		StringBuilder errmssg = new StringBuilder();
		String hexed_passwd = "";
		String saltkey = "";
		boolean signupsuccess = false;
		boolean reqd_fields_empty = false;
		long gpoints = 0;
		HttpSession httpsessn = req.getSession();
		
		if (signupform != null) {
			if (StringUtils.isBlank(signupform.getAccount_email()) || 
				StringUtils.isBlank(signupform.getAccount_username()) || 
				StringUtils.isBlank(signupform.getAccount_password()) || 
				StringUtils.isBlank(signupform.getCitynm())) {
					logger.info("**** any of the required field is missing ... ");
					reqd_fields_empty = true;
			}
			
			if(reqd_fields_empty) {
				errmssg.append(" One of the required field is empty, pls correct...");
			}else {
				// convert emailid and username to lower case
				signupform.setAccount_username(StringUtils.lowerCase(signupform.getAccount_username()));
				signupform.setAccount_email(StringUtils.lowerCase(signupform.getAccount_email()));
								
				// check if the emailid is already registered or not
				int cnt_emlid = OfyService.ofy().load().type(UserDO.class)
													   .filter("account_email", signupform.getAccount_email())
													   .count();
				logger.info("**** is signup email existing? cnt_emlid- " + cnt_emlid);
				if(cnt_emlid > 0) {
					errmssg.append(" The email id is already being used, pls use different emailid");
				}else {
					// check if the username is already taken
					//if there is space in username then different flow
					int cnt_usrnm = 0;
					/*
					if(StringUtils.contains(signupform.getAccount_username(), " ")) {
						
					}*/
					cnt_usrnm = OfyService.ofy().load().type(UserDO.class)
														   .filter("account_username", signupform.getAccount_username())
														   .count();
					//logger.info("**** is signup username existing? cnt_usrnm- " + cnt_usrnm);					
					if(cnt_usrnm > 0) {
						errmssg.append(" The account username is already taken, pls use different username");
					}else {
						try {
							String[] ignoreflds = { "first_name", "last_name", "birth_year", "account_password" };
							BeanUtils.copyProperties(signupform, userdo, ignoreflds);
							String ipAddress = req.getHeader("X-FORWARDED-FOR");
							if (ipAddress == null) {
								ipAddress = req.getRemoteAddr();
							}
							saltkey = AuthUtil.getGenrtdRndmAuthSalt();
							hexed_passwd = AuthUtil.generateAuthPsswd(signupform.getAccount_password(), saltkey);
							userdo.setAccount_password(hexed_passwd);
							userdo.setAccount_pwd_salt(saltkey);
							// userdo.setAccount_password(GlamCmn.getEncrptdPwd(signupform));
							userdo.setIs_account_active(true);
							userdo.setSignup_ip_addr(ipAddress);
							userdo.setSignup_datetime(GlamCmn.getDateTmStr());
							userdo.setSignup_dttm(new Date());
				
							// assign a default random profile image for the user
							userdo.setUavatar(GlamCmn.getRndAvtrImgStr());
							
							if(userdo.getMy_glam_points()>0) {
								gpoints = GlamCnst._GLAM_PTS_SIGNUP_BONUS + userdo.getMy_glam_points();
							}else {
								gpoints = GlamCnst._GLAM_PTS_SIGNUP_BONUS;
							}
							userdo.setMy_glam_points(gpoints);
							
							usrid = OfyService.ofy().save().entity(userdo).now().getId();
							
							GlamCmn.saveUsrActivity(userdo.getUserid(), userdo.getAccount_username(), GlamCnst._ATVY_PRFL_CREATE, 0, "", "");
							GlamCmn.saveGlamPointsActivity(userdo.getUserid(), userdo.getAccount_username(), GlamCnst._ATVY_PTS_ADDED,  
									GlamCnst._GLAM_PTS_SIGNUP_BONUS, "Joining glamfiesta.com as SignUp bonus");
							signupsuccess = true;
							
							// send user email with login details
							String email_txt = "<html> <head>    <meta http-equiv='Content-Type' content='text/html; charset=utf-8'>"
									+ "<meta name='viewport' content='width=device-width, initial-scale=1.0'><title>Welcome to Glam Fiesta</title>"
									+ "<style type='text/css'>         "
									+ "/* Client-specific Styles */         "
									+ "div, p, a, li, td { -webkit-text-size-adjust:none; }        "
									+ " #outlook a {padding:0;} /* Force Outlook to provide a 'view in browser' menu link. */         "
									+ " href^='sms'] {                 text-decoration: default;                 color: #33b9ff !important;                "
									+ " pointer-events: auto;                 cursor: default;             }             table[class=devicewidth] {width: 280px!important;"
									+ "text-align:center!important;}             table[class=devicewidthinner] {width: 260px!important;text-align:center!important;}             "
									+ "img[class=banner] {width: 280px!important;height:140px!important;}             img[class=col2img] {width: 280px!important;height:140px!important;}  "
									+ "         }     </style> </head><body><!-- Start of preheader -->  <!-- End of preheader -->  <!-- Start of header -->  "
									+ "<!-- End of Header -->  <!-- Start of seperator -->  <!-- End of seperator -->  <!-- Start of main-banner --> "
									+ " <!-- End of main-banner -->  <!-- Start of seperator -->  <!-- End of seperator -->  <!-- Start of Left Image -->  "
									+ "<!-- End of Left Image -->  <!-- Start of seperator -->  <!-- End of seperator -->  <!-- start of Full text -->  "
									+ "<!-- End of Full Text -->  <!-- Start of seperator -->  <!-- End of seperator -->  <!-- Start of Right Image --> "
									+ " <!-- End of Right Image -->  <!-- Start of seperator -->  <!-- End of seperator -->  <!-- Start of footer -->  "
									+ "<!-- End of footer -->  <!-- Start of Postfooter -->  <!-- End of postfooter -->  <table width='100%' bgcolor='#fcfcfc' "
									+ "cellpadding='0' cellspacing='0' border='0'> 	<tbody> 		<tr> 			<td> 				<div class='innerbg'> "
									+ "				</div> 				<table width='600' cellpadding='0' cellspacing='0' border='0' align='center' class='devicewidth'> "
									+ "					<tbody> 						<tr> 							<td width='100%'> "
									+ "								<table width='600' align='center' cellspacing='0' cellpadding='0' border='0' class='devicewidth'> "
									+ "									<tbody> 										<tr> 	"
									+ "										<!-- start of image -->  											<td align='center'> "
									+ "												<div class='imgpop'> 													"
									+ "<div class='uploader_wrap' style='width: 600px; margin-top: 91px; opacity: 0;'> 	"
									+ "													<div class='upload_buttons'> 	"
									+ "														<div class='img_link'> 	"
									+ "														</div> 	"
									+ "														<div class='img_upload'> 	"
									+ "														</div> 	"
									+ "														<div class='img_edit' style='visibility: visible;'> "
									+ "															</div> 	"
									+ "													</div> 	"
									+ "												</div> <a href='#'><img width='600' border='0' alt='' style='display:block; "
									+ "border:none; outline:none; text-decoration:none;' src='https://storage.googleapis.com/rsrc/welcome_email.jpg' class='banner' /></a> "
									+ "												</div> 											</td> 	"
									+ "									</tr> 									</tbody> 								</table> "
									+ "								<!-- end of image -->  							</td> 						</tr> 					</tbody> "
									+ "				</table> 			</td> 		</tr> 	</tbody> </table> <table width='100%' bgcolor='#fcfcfc' cellpadding='0' cellspacing='0' "
									+ "border='0'> 	<tbody> 		<tr> 			<td> 				<div class='innerbg'> 				</div> 	"
									+ "			<table width='600' cellpadding='0' cellspacing='0' border='0' align='center' class='devicewidth' bgcolor='#ffffff'> "
									+ "					<tbody> 						<tr> 							<td width='100%'> 	"
									+ "							<table width='600' cellpadding='0' cellspacing='0' border='0' align='center' class='devicewidth' bgcolor='#ffffff'> "
									+ "									<tbody> 										<!-- Spacing -->  	"
									+ "									<tr> 											<td height='20' style='font-size:1px; line-height:1px; "
									+ "mso-line-height-rule: exactly;'> 											</td> 										</tr> "
									+ "										<!-- Spacing -->  										<tr> 	"
									+ "										<td> 												<table width='560' align='center' cellpadding='0' "
									+ "cellspacing='0' border='0' class='devicewidthinner'> 													<tbody> "
									+ "														<!-- Title -->  														<tr> "
									+ "															<td style='font-family: Helvetica, arial, sans-serif; font-size: 18px; color: #282828; "
									+ "text-align:center; line-height: 24px;'> 																<p> 	"
									+ "																Welcome 'Fashion Lovers' 	"
									+ "															</p> 															</td> "
									+ "														</tr> 														<!-- End of Title -->  "
									+ "														<!-- spacing -->  														<tr> "
									+ "															<td width='100%' height='15' style='font-size:1px; line-height:1px; mso-line-height-rule: "
									+ "exactly;'> 															</td> 														</tr> "
									+ "														<!-- End of spacing -->  	"
									+ "													<!-- content -->  														<tr> "
									+ "															<td style='font-family: Helvetica, arial, sans-serif; font-size: 14px; color: "
									+ "#889098; text-align:center; line-height: 24px;'> 																<p> "
									+ "																	We are really glad you have joined our fastest growing community of fashion lovers. Glam Fiesta is a social network platform to connect with fashion lovers, fashionistas, YouTube vloggers and fashion bloggers around the world. Share your lookbook photo and join the latest and inspiring fashion trends. Create lookbook portfolios, like, comment and follow other members. Explore some of the inspiring posts from our members below: 																</p> 															</td> 														</tr> 														<!-- End of content -->  														<!-- Spacing -->  														<tr> 															<td width='100%' height='15' style='font-size:1px; line-height:1px; mso-line-height-rule: exactly;'> 															</td> 														</tr> 														<!-- Spacing -->  													</tbody> 												</table> 											</td> 										</tr> 										<!-- Spacing -->  										<tr> 											<td height='20' style='font-size:1px; line-height:1px; mso-line-height-rule: exactly;'> 											</td> 										</tr> 										<!-- Spacing -->  									</tbody> 								</table> 							</td> 						</tr> 					</tbody> 				</table> 			</td> 		</tr> 	</tbody> </table> <table width='100%' bgcolor='#fcfcfc' cellpadding='0' cellspacing='0' border='0'> 	<tbody> 		<tr> 			<td> 				<div class='innerbg'> 				</div> 				<table width='600' cellpadding='0' cellspacing='0' border='0' align='center' class='devicewidth' bgcolor='#edf2f5'> 					<tbody> 						<tr> 							<td width='100%'> 								<table width='600' cellpadding='0' cellspacing='0' border='0' align='center' class='devicewidth' bgcolor='#edf2f5'> 									<tbody> 										<tr> 											<td> 												<!-- Start of left column -->  												<table width='280' align='left' border='0' cellpadding='0' cellspacing='0' class='devicewidth' bgcolor='#edf2f5'> 													<tbody> 														<!-- image -->  														<tr> 															<td width='280' height='140' align='center' class='devicewidth' bgcolor='#edf2f5'> 																<div class='imgpop'> 																	<div class='uploader_wrap' style='width: 280px; margin-top: 75.5px; opacity: 0;'> 																		<div class='upload_buttons'> 																			<div class='img_link'> 																			</div> 																			<div class='img_upload'> 																			</div> 																			<div class='img_edit' style='visibility: visible;'> 																			</div> 																		</div> 																	</div> "
									+ "<a href='http://www.glamfiesta.com/fashion/07092017022133-week-inspirational-outfits-by-susipb.htm'>"
									+ "<img src='https://storage.googleapis.com/rsrc/welcome-email1.jpg' alt='' border='0' width='280' style='display: block; border: none; outline: none; text-decoration: none;' class='col2img' /></a> 																</div> 															</td> 														</tr> 														<!-- /image -->  													</tbody> 												</table> 												<!-- end of left column -->  												<!-- spacing for mobile devices-->  												<table align='left' border='0' cellpadding='0' cellspacing='0' class='mobilespacing'> 													<tbody> 														<tr> 															<td width='100%' height='15' style='font-size:1px; line-height:1px; mso-line-height-rule: exactly;'> 															</td> 														</tr> 													</tbody> 												</table> 												<!-- end of for mobile devices-->  												<!-- start of right column -->  												<table width='280' align='right' border='0' cellpadding='0' cellspacing='0' class='devicewidth' bgcolor='#edf2f5'> 													<tbody> 														<tr> 															<td> 																<table width='280' align='center' border='0' cellpadding='0' cellspacing='0' class='devicewidth' bgcolor='#edf2f5'> 																	<tbody> 																		<!-- title -->  																		<tr> 																			<td style='font-family: Helvetica, arial, sans-serif; font-size: 18px; color: #282828; text-align:left; line-height: 24px;'> 																				<p> 	"
									+ "<a href='http://www.glamfiesta.com/fashion/07092017022133-week-inspirational-outfits-by-susipb.htm'><span style='font-size: 12pt;'>WEEK INSPIRATIONAL OUTFITS</span></a> 																				</p> 																			</td> 																		</tr> 																		<!-- end of title -->  																		<!-- Spacing -->  																		<tr> 																			<td width='100%' height='15' style='font-size:1px; line-height:1px; mso-line-height-rule: exactly;'> 																			</td> 																		</tr> 																		<!-- /Spacing -->  																		<!-- content -->  																		<tr> 																			<td style='font-family: Helvetica, arial, sans-serif; font-size: 14px; color: #889098; text-align:left; line-height: 24px;'> 																				<p> 																					A collection of street styke lookbook 																				</p> 																			</td> 																		</tr> 																		<!-- end of content -->  																		<!-- Spacing -->  																		<tr> 																			<td width='100%' height='15' style='font-size:1px; line-height:1px; mso-line-height-rule: exactly;'> 																			</td> 																		</tr> 																		<!-- /Spacing -->  																		<!-- read more -->  																		<tr> 																			<td> 																				<div class='buttonbg'> 																				</div> 																				<table width='120' height='32' bgcolor='#6da7d1' align='left' valign='middle' border='0' cellpadding='0' cellspacing='0' style='border-radius: 3px; background-color: rgb(109, 167, 209);'> 																					<tbody> 																						<tr> 																							<td height='9' align='center' style='font-size:1px; line-height:1px;'> 																							</td> 																						</tr> 																						<tr> 																							<td height='14' align='center' valign='middle' style='font-family: Helvetica, Arial, sans-serif; font-size: 13px; font-weight:bold;color: #ffffff; text-align:center; line-height: 14px; ; -webkit-text-size-adjust:none;'> 																								<p>"
									+ "<a href='http://www.glamfiesta.com/fashion/07092017022133-week-inspirational-outfits-by-susipb.htm'>View Lookbook</a> 																								</p> 																							</td> 																						</tr> 																						<tr> 																							<td height='9' align='center' style='font-size:1px; line-height:1px;'> 																							</td> 																						</tr> 																					</tbody> 																				</table> 																			</td> 																		</tr> 																		<!-- end of read more -->  																	</tbody> 																</table> 															</td> 														</tr> 													</tbody> 												</table> 												<!-- end of right column -->  											</td> 										</tr> 									</tbody> 								</table> 							</td> 						</tr> 					</tbody> 				</table> 			</td> 		</tr> 	</tbody> </table> <table width='100%' bgcolor='#fcfcfc' cellpadding='0' cellspacing='0' border='0'> 	<tbody> 		<tr> 			<td> 				<div class='innerbg'> 				</div> 				<table width='600' align='center' cellspacing='0' cellpadding='0' border='0' class='devicewidth'> 					<tbody> 						<tr> 							<td align='center' height='30' style='font-size:1px; line-height:1px;'> 								<p> 								</p> 							</td> 						</tr> 					</tbody> 				</table> 			</td> 		</tr> 	</tbody> </table> <table width='100%' bgcolor='#fcfcfc' cellpadding='0' cellspacing='0' border='0'> 	<tbody> 		<tr> 			<td> 				<div class='innerbg'> 				</div> 				<table width='600' cellpadding='0' cellspacing='0' border='0' align='center' class='devicewidth' bgcolor='#edf2f5'> 					<tbody> 						<tr> 							<td width='100%'> 								<table width='600' cellpadding='0' cellspacing='0' border='0' align='center' class='devicewidth' bgcolor='#edf2f5'> 									<tbody> 										<tr> 											<td> 												<!-- Start of left column -->  												<table width='280' align='right' border='0' cellpadding='0' cellspacing='0' class='devicewidth' bgcolor='#edf2f5'> 													<tbody> 														<!-- image -->  														<tr> 															<td width='280' height='140' align='center' class='devicewidth' bgcolor='#edf2f5'> 																<div class='imgpop'> 																	<div class='uploader_wrap' style='width: 280px; margin-top: 82.5px; opacity: 0;'> 																		<div class='upload_buttons'> 																			<div class='img_link'> 																			</div> 																			<div class='img_upload'> 																			</div> 																			<div class='img_edit' style='visibility: visible;'> 																			</div> 																		</div> 																	</div> "
									+ "<a href='http://www.glamfiesta.com/fashion/07092017022105-summer-casual-lookbook-by-reesewest.htm'>"
									+ "<img src='https://storage.googleapis.com/rsrc/welcome-email2.jpg' alt='' border='0' width='280' style='display:block; border:none; outline:none; text-decoration:none;' class='col2img' /></a> </div> 															</td> 														</tr> 														<!-- /image -->  													</tbody> 												</table> 												<!-- end of left column -->  												<!-- spacing for mobile devices-->  												<table align='left' border='0' cellpadding='0' cellspacing='0' class='mobilespacing'> 													<tbody> 														<tr> 															<td width='100%' height='15' style='font-size:1px; line-height:1px; mso-line-height-rule: exactly;'> 															</td> 														</tr> 													</tbody> 												</table> 												<!-- end of for mobile devices-->  												<!-- start of right column -->  												<table width='280' align='left' border='0' cellpadding='0' cellspacing='0' class='devicewidth' bgcolor='#edf2f5'> 													<tbody> 														<tr> 															<td> 																<table width='280' align='center' border='0' cellpadding='0' cellspacing='0' class='devicewidth' bgcolor='#edf2f5'> 																	<tbody> 																		<!-- title -->  																		<tr> 																			<td style='font-family: Helvetica, arial, sans-serif; font-size: 18px; color: #282828; text-align:left; line-height: 24px;'> 																				<p> 																					"
									+ "<a href='http://www.glamfiesta.com/fashion/07092017022105-summer-casual-lookbook-by-reesewest.htm'>BUSINESS CASUAL LOOKBOOK</a> 																				</p> 																			</td> 																		</tr> 																		<!-- end of title -->  																		<!-- Spacing -->  																		<tr> 																			<td width='100%' height='15' style='font-size:1px; line-height:1px; mso-line-height-rule: exactly;'> 																			</td> 																		</tr> 																		<!-- /Spacing -->  																		<!-- content -->  																		<tr> 																			<td style='font-family: Helvetica, arial, sans-serif; font-size: 14px; color: #889098; text-align:left; line-height: 24px;'> 																				<p> 																					Business casual lookbook collection 																				</p> 																			</td> 																		</tr> 																		<!-- end of content -->  																		<!-- Spacing -->  																		<tr> 																			<td width='100%' height='15' style='font-size:1px; line-height:1px; mso-line-height-rule: exactly;'> 																			</td> 																		</tr> 																		<!-- /Spacing -->  																		<!-- read more -->  																		<tr> 																			<td> 																				<div class='buttonbg'> 																				</div> 																				<table width='120' height='32' bgcolor='#4c8fbf' align='left' valign='middle' border='0' cellpadding='0' cellspacing='0' style='border-radius: 3px; background-color: rgb(76, 143, 191);'> 																					<tbody> 																						<tr> 																							<td height='9' align='center' style='font-size:1px; line-height:1px;'> 																							</td> 																						</tr> 																						<tr> 																							<td height='14' align='center' valign='middle' style='font-family: Helvetica, Arial, sans-serif; font-size: 13px; font-weight:bold;color: #ffffff; text-align:center; line-height: 14px; ; -webkit-text-size-adjust:none;'> 																								<p> 																									"
									+ "<a href='http://www.glamfiesta.com/fashion/07092017022105-summer-casual-lookbook-by-reesewest.htm'>View Lookbook</a> 																								</p> 																							</td> 																						</tr> 																						<tr> 																							<td height='9' align='center' style='font-size:1px; line-height:1px;'> 																							</td> 																						</tr> 																					</tbody> 																				</table> 																			</td> 																		</tr> 																		<!-- end of read more -->  																	</tbody> 																</table> 															</td> 														</tr> 													</tbody> 												</table> 												<!-- end of right column -->  											</td> 										</tr> 									</tbody> 								</table> 							</td> 						</tr> 					</tbody> 				</table> 			</td> 		</tr> 	</tbody> </table> <table width='100%' bgcolor='#fcfcfc' cellpadding='0' cellspacing='0' border='0'> 	<tbody> 		<tr> 			<td> 				<div class='innerbg'> 				</div> 				<table width='600' align='center' cellspacing='0' cellpadding='0' border='0' class='devicewidth'> 					<tbody> 						<tr> 							<td align='center' height='30' style='font-size:1px; line-height:1px;'> 								<p> 								</p> 							</td> 						</tr> 					</tbody> 				</table> 			</td> 		</tr> 	</tbody> </table> <table width='100%' bgcolor='#fcfcfc' cellpadding='0' cellspacing='0' border='0'> 	<tbody> 		<tr> 			<td> 				<div class='innerbg'> 				</div> 				<table width='600' cellpadding='0' cellspacing='0' border='0' align='center' class='devicewidth' bgcolor='#e8edf0'> 					<tbody> 						<tr> 							<td width='100%'> 								<table width='600' cellpadding='0' cellspacing='0' border='0' align='center' class='devicewidth' bgcolor='#e8edf0'> 									<tbody> 										<tr> 											<td> 												<!-- Start of left column -->  												<table width='280' align='left' border='0' cellpadding='0' cellspacing='0' class='devicewidth' bgcolor='#e8edf0'> 													<tbody> 														<!-- image -->  														<tr> 															<td width='280' height='140' align='center' class='devicewidth' bgcolor='#e8edf0'> 																<div class='imgpop'> 																	<div class='uploader_wrap' style='width: 280px; margin-top: 59.5px; opacity: 0;'> 																		<div class='upload_buttons'> 																			<div class='img_link'> 																			</div> 																			<div class='img_upload'> 																			</div> 																			<div class='img_edit' style='visibility: visible;'> 																			</div> 																		</div> 																	</div> "
									+ "<a href='http://www.glamfiesta.com/fashion/07092017022112-monochrome-lookbook-by-losangelesdesire.htm'><img src='https://storage.googleapis.com/rsrc/welcome-email3.jpg' alt='' border='0' width='280' style='display: block; border: none; outline: none; text-decoration: none;' class='col2img' /></a> 																</div> 															</td> 														</tr> 														<!-- /image -->  													</tbody> 												</table> 												<!-- end of left column -->  												<!-- spacing for mobile devices-->  												<table align='left' border='0' cellpadding='0' cellspacing='0' class='mobilespacing'> 													<tbody> 														<tr> 															<td width='100%' height='15' style='font-size:1px; line-height:1px; mso-line-height-rule: exactly;'> 															</td> 														</tr> 													</tbody> 												</table> 												<!-- end of for mobile devices-->  												<!-- start of right column -->  												<table width='280' align='right' border='0' cellpadding='0' cellspacing='0' class='devicewidth' bgcolor='#e8edf0'> 													<tbody> 														<tr> 															<td> 																<table width='280' align='center' border='0' cellpadding='0' cellspacing='0' class='devicewidth' bgcolor='#e8edf0'> 																	<tbody> 																		<!-- title -->  																		<tr> 																			<td style='font-family: Helvetica, arial, sans-serif; font-size: 18px; color: #282828; text-align:left; line-height: 24px;'> 																				<p>"
									+ "<a href='http://www.glamfiesta.com/fashion/07092017022112-monochrome-lookbook-by-losangelesdesire.htm'>MONOCHROME LOOKBOOK ...</a> 																				</p> 																			</td> 																		</tr> 																		<!-- end of title -->  																		<!-- Spacing -->  																		<tr> 																			<td width='100%' height='15' style='font-size:1px; line-height:1px; mso-line-height-rule: exactly;'> 																			</td> 																		</tr> 																		<!-- /Spacing -->  																		<!-- content -->  																		<tr> 																			<td style='font-family: Helvetica, arial, sans-serif; font-size: 14px; color: #889098; text-align:left; line-height: 24px;'> 																				<p> 																					Explore monochrome lookbook collection 																				</p> 																			</td> 																		</tr> 																		<!-- end of content -->  																		<!-- Spacing -->  																		<tr> 																			<td width='100%' height='15' style='font-size:1px; line-height:1px; mso-line-height-rule: exactly;'> 																			</td> 																		</tr> 																		<!-- /Spacing -->  																		<!-- read more -->  																		<tr> 																			<td> 																				<div class='buttonbg'> 																				</div> 																				<table width='120' height='32' bgcolor='#5194c4' align='left' valign='middle' border='0' cellpadding='0' cellspacing='0' style='border-radius: 3px; background-color: rgb(81, 148, 196);'> 																					<tbody> 																						<tr> 																							<td height='9' align='center' style='font-size:1px; line-height:1px;'> 																							</td> 																						</tr> 																						<tr> 																							<td height='14' align='center' valign='middle' style='font-family: Helvetica, Arial, sans-serif; font-size: 13px; font-weight:bold;color: #ffffff; text-align:center; line-height: 14px; ; -webkit-text-size-adjust:none;'> 																								<p> "
									+ "<a href='http://www.glamfiesta.com/fashion/07092017022112-monochrome-lookbook-by-losangelesdesire.htm'>View Lookbook</a> 																								</p> 																							</td> 																						</tr> 																						<tr> 																							<td height='9' align='center' style='font-size:1px; line-height:1px;'> 																							</td> 																						</tr> 																					</tbody> 																				</table> 																			</td> 																		</tr> 																		<!-- end of read more -->  																	</tbody> 																</table> 															</td> 														</tr> 													</tbody> 												</table> 												<!-- end of right column -->  											</td> 										</tr> 									</tbody> 								</table> 							</td> 						</tr> 					</tbody> 				</table> 			</td> 		</tr> 	</tbody> </table> <table width='100%' bgcolor='#fcfcfc' cellpadding='0' cellspacing='0' border='0'> 	<tbody> 		<tr> 			<td> 				<div class='innerbg'> 				</div> 				<table width='600' align='center' cellspacing='0' cellpadding='0' border='0' class='devicewidth'> 					<tbody> 						<tr> 							<td align='center' height='30' style='font-size:1px; line-height:1px;'> 								<p> 								</p> 							</td> 						</tr> 					</tbody> 				</table> 			</td> 		</tr> 	</tbody> </table></body> </html>";
									
									/*"Dear Fashionista, <br/>"
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
									+ "<p> Cheers!! <br/><br/> - Raj & Mita (Founders, glamfiesta.com) <br/><br/> "
									+ "<b>Proudly running on Google Cloud Platform ( <a href=www.glamfiesta.com/aboutus.htm>www.glamfiesta.com/aboutus.htm</a> )</b> </p>";
*/							
							//String email_txt2 = "A new user-" + userdo.getAccount_username() + " has joined glamfiesta.com ";
							
							GlamCmn.sendEmailToUsr(userdo.getAccount_email(), userdo.getAccount_username(), "Welcome to GlamFiesta - the Fashion Social Network !!", email_txt, true);
							//GlamCmn.sendEmailToUsr("suvoraj.biswas@gmail.com", "Glam Fiesta", "", email_txt2, true);
						
						}catch (NoSuchAlgorithmException ne) {
							errmssg.append("Generic system exception has occurred... pls try again later...");
						}catch (InvalidKeySpecException ie) {
							errmssg.append("Generic system exception has occurred... pls try again later...");
						}
					}
				}
			}
		} else {
			errmssg.append(" Generic system exception occurred... pls try again later...");
		}

		if (!signupsuccess) {

			uiModel.addAttribute("errmssg", errmssg.toString());
			uiModel.addAttribute("signupform", signupform);

			return GlamCnst._VIEW_SIGNUP;

		} else {
			uiModel.addAttribute("succsmssg", "Signup Successful... We've sent all the account details in email...");
			
			UsrLoginBO ul = new UsrLoginBO();
			ul.setUserid(usrid);
			ul.setAccount_username(userdo.getAccount_username());
			ul.setFirst_name(userdo.getFirst_name());
			ul.setLast_name(userdo.getLast_name());
			ul.setIs_account_active(userdo.isIs_account_active());
			ul.setLoginstatus(true);
			ul.setAccount_type(userdo.getAccount_type());
			ul.setUavatar(userdo.getUavatar());
			
			httpsessn.setAttribute("ul", ul);
			
			return "redirect:/dashboard";
		}
	}

	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String doLogin(@ModelAttribute UserForm loginform,
			BindingResult result, Model uiModel, HttpServletRequest req) {
		UserDO usrdo = null;
		UsrLoginBO ul = new UsrLoginBO();
		boolean loginsuccss = false;
		String u_hexdpwd = "";
		String u_salt = "";
		String inpt_hexdpwd = "";
		HttpSession httpsessn = req.getSession();
		StringBuilder errmssg = new StringBuilder();
		List<UserDO> usrlist = null;
		
		if(loginform!=null) {
			if(StringUtils.isEmpty(loginform.getAccount_username()) || StringUtils.isEmpty(loginform.getAccount_password())) {
				errmssg.append("Required field is missing, pls fill up...");
			}else {
					// check if user has entered email id or username
					if(GlamCmn.checkIfInputStringIsEmailAddrs(loginform.getAccount_username())) {
						usrlist = OfyService.ofy().load().type(UserDO.class)
														 .filter("account_email", StringUtils.lowerCase(loginform.getAccount_username()))
														 .list();
					}else {
						usrlist = OfyService.ofy().load().type(UserDO.class)
														  .filter("account_username", loginform.getAccount_username())
														  .list();
					}
					if (usrlist != null && usrlist.size() > 0) {
						//logger.info("**** Userlist size ****" + usrlist.size());
						try {
							usrdo = usrlist.get(0);
							u_hexdpwd = usrdo.getAccount_password();
							u_salt = usrdo.getAccount_pwd_salt();
							inpt_hexdpwd = AuthUtil.generateAuthPsswd(loginform.getAccount_password(), u_salt);
							//logger.info("**** uerdo retrieved in AuthController - " + usrdo);
							
							if (StringUtils.equals(u_hexdpwd, inpt_hexdpwd)) {
								loginsuccss = true;
			
								ul.setUserid(usrdo.getUserid());
								ul.setAccount_username(usrdo.getAccount_username());
								ul.setFirst_name(usrdo.getFirst_name());
								ul.setLast_name(usrdo.getLast_name());
								ul.setIs_account_active(usrdo.isIs_account_active());
								ul.setLoginstatus(loginsuccss);
								ul.setAccount_type(usrdo.getAccount_type());
								ul.setUavatar(usrdo.getUavatar());
			
							} else {
								errmssg.append("Login Failed !! Username or Password not matching, please try again...");
							}
						} catch (Exception ex) {
							errmssg.append("Generic system exception occurred, please try again later...");
						}
					} else {
						errmssg.append("Login Failed !! Username or Password not matching, please try again...");
					}
			  }
		}else {
			errmssg.append("Generic system exception occurred, please try again later...");
		}
		
		uiModel.addAttribute("errmssg", errmssg.toString());

		if (loginsuccss) {
			httpsessn.setAttribute("ul", ul);

			//if (StringUtils.isBlank((String) httpsessn.getAttribute("_REFFR_URL"))) {
				return "redirect:/dashboard";
			//} else {
				//return "redirect:/" + (String) httpsessn.getAttribute("_REFFR_URL");
			//}
		} else {
			loginform.setAccount_username("");
			loginform.setAccount_password("");
			uiModel.addAttribute("loginform", loginform);

			return GlamCnst._VIEW_LOGIN;
		}
	}
	
	
	
	
	@RequestMapping(value = "/forgotpwd", method = RequestMethod.POST)
	public String goForgotPwdPage(@ModelAttribute UserForm forgotpform,	BindingResult result, Model uiModel, HttpServletRequest req) {
		String unm = forgotpform.getAccount_username();
		String uemail = forgotpform.getAccount_email();
		UserDO usrdo = null;
		String succsmssg = "";
		String errmssg = "";
		
		if(StringUtils.isNotEmpty(unm) && StringUtils.isNotEmpty(uemail)) {
			List<UserDO> usrlist = OfyService.ofy().load().type(UserDO.class)
					.filter("account_username", unm)
					.filter("account_email", uemail)
					.list();
			if (usrlist != null && usrlist.size() > 0) {
				usrdo = usrlist.get(0);
				
				//generate reset key for the forgot password request and save and email user
				String reset_key = AuthUtil.generateResetPwdKey();
				UserLoginResetDO loginresetdo = new UserLoginResetDO();
				loginresetdo.setResetdt(new Date());
				loginresetdo.setResetkey(reset_key);
				loginresetdo.setUserid(usrdo.getUserid());
				
				// check if any earlier reset request exist or not then delete it
				List<UserLoginResetDO> loginresetlist = OfyService.ofy().load().type(UserLoginResetDO.class)
																			   .filter("userid", usrdo.getUserid())
																			   .list();
				if(loginresetlist!=null && loginresetlist.size()>0) {
					UserLoginResetDO lgnresetdo = loginresetlist.get(0);
					// already reset key info exists so delete it
					OfyService.ofy().delete().type(UserLoginResetDO.class).id(lgnresetdo.getLoginresetid()).now();
				}
				
				logger.info("*** saving loginresetdo with key" + loginresetdo.getResetkey());
				OfyService.ofy().save().entity(loginresetdo).now();
				
				// send user email with login details
				String email_txt = "<b>Dear <i>"+usrdo.getFirst_name()+ "</i></b>, <br/>"
						+ "<p> You've recently requested to reset your account password.<br/>"
						+ "We've generated a new auth key <b>"+ loginresetdo.getResetkey() + "</b> "
						+ "for your username "+usrdo.getAccount_username() + ".</p> "
						+ "<p>Please visit <a href='http://www.glamfiesta.com/resetpwd?account_username=" 
						+ usrdo.getAccount_username() 
						+ "&account_email=" 
						+ usrdo.getAccount_email() 
						+ "&reset_key=" 
						+ loginresetdo.getResetkey() 
						+ "'>Reset My Password</a> "
						+ "page and change your password.</p>"
						+ "<p> <b>Thanks, Glam Fiesta Support Team</b> </p>";
				
				GlamCmn.sendEmailToUsr(uemail, usrdo.getFirst_name(), "Reset Account Password at GlamFiest.com !!", email_txt, true);
				succsmssg = "An email has been sent to " + uemail + " with account details. Pls use this information to reset and login.";
			}else {
				errmssg = "The username and emailid you have provided doesn't match with anything in our system. Please check and try again.";
			}
		}else {
			errmssg = "Username field and the emailid field can not be empty...";
		}
		
		uiModel.addAttribute("succsmssg", succsmssg);
		uiModel.addAttribute("errmssg", errmssg);
		uiModel.addAttribute("forgotpform", forgotpform);
		
		return GlamCnst._VIEW_FRGTPWD;
	}
	
	
	@RequestMapping(value = "/resetpwd", method = RequestMethod.POST)
	public String doReesetPwdPage1(@ModelAttribute UserForm resetform, BindingResult result, Model uiModel, HttpServletRequest req) {
		boolean reqd_fields_empty = false;
		boolean usr_no_exists = false;
		boolean reset_key_invalid = false;
		boolean redir_login_pg = true;
		String succsmssg = "";
		String errmssg = "";
		
		UserDO usrdo = null;
		
		// check if the auth reset key is valid then redirect user to 
		if (resetform != null) {
			if (StringUtils.isBlank(resetform.getAccount_email()) || StringUtils.isBlank(resetform.getAccount_username()) 
																  || StringUtils.isBlank(resetform.getReset_key())
																  || StringUtils.isBlank(resetform.getAccount_password())) {
				logger.info("**** any of the required field is missing in reset password page ... ");
				reqd_fields_empty = true;
			}
			
			if(!reqd_fields_empty) {
				// check if the user exists
				List<UserDO> usrlist = OfyService.ofy().load().type(UserDO.class)
															  .filter("account_username", resetform.getAccount_username())
															  .filter("account_email", resetform.getAccount_email())
															  .list();
				if (usrlist != null && usrlist.size() > 0) {
					usrdo = usrlist.get(0);
				}else {
					usr_no_exists = true;
				}
				
				// user exists... now check reset key in system
				if(!usr_no_exists) {
					List<UserLoginResetDO> loginresetlist = OfyService.ofy().load().type(UserLoginResetDO.class)
																				   .filter("userid", usrdo.getUserid())
																				   .list();
					if(loginresetlist!=null && loginresetlist.size()>0) {
						UserLoginResetDO lgnresetdo = loginresetlist.get(0);
						if(StringUtils.equals(lgnresetdo.getResetkey(), resetform.getReset_key())) {
							// reset key is matched... now update the password
							try {
								String saltkey = AuthUtil.getGenrtdRndmAuthSalt();
								String hexed_passwd = AuthUtil.generateAuthPsswd(resetform.getAccount_password(), saltkey);
								usrdo.setAccount_password(hexed_passwd);
								usrdo.setAccount_pwd_salt(saltkey);
								
								// save the userdo object
								//logger.info("*** updated usr values before saving " + usrdo);
								OfyService.ofy().save().entity(usrdo).now();
							}catch(Exception ex) {
								errmssg = "Generic system exception occurred... pls try again later...";
							}
						}else {
							reset_key_invalid = true;
						}
					}
				}
			}
			
			if(reqd_fields_empty) {
				errmssg = " Required fields are missing... pls fill up";
				redir_login_pg = false;
			}
			if(usr_no_exists) {
				errmssg = "Either Username or email id is invalid as we couldn't find you in our system...";
				redir_login_pg = false;
			}
			if(reset_key_invalid) {
				errmssg = " the Auth reset key is invalid...";
				redir_login_pg = false;
			}
			
		}else {
			errmssg = "Generic system exception occurred... pls try again later...";
		}
		
		uiModel.addAttribute("succsmssg", succsmssg);
		uiModel.addAttribute("errmssg", errmssg.toString());
		
		if(redir_login_pg) {
			// everything is successful... redirect user to login page
			UserForm loginform = new UserForm();
			uiModel.addAttribute("loginform", loginform);
			return GlamCnst._VIEW_LOGIN;
		}else {
			uiModel.addAttribute("resetform", resetform);
			return GlamCnst._VIEW_RSTPWD;
		}
	}

}
