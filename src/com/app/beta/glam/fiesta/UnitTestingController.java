package com.app.beta.glam.fiesta;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.beta.glam.fiesta.cmn.GlamCmn;

@Controller
public class UnitTestingController {
	private static final Logger logger = Logger.getLogger(UnitTestingController.class.getCanonicalName());
	
	@RequestMapping(value = "/test/welcome", method = RequestMethod.GET)
	public void tstWelComeEmail(HttpServletRequest req, Model uiModel) {
		
		String email_txt = "<html> <head>    <meta http-equiv='Content-Type' content='text/html; charset=utf-8'>"
				+ "<meta name='viewport' content='width=device-width, initial-scale=1.0'><title>Welcome to Glam Fiesta</title>"
				+ "<style type='text/css'>         /* Client-specific Styles */         div, p, a, li, td { -webkit-text-size-adjust:none; }         #outlook a {padding:0;} /* Force Outlook to provide a 'view in browser' menu link. */         html{width: 100%; }         body{width:100% !important; -webkit-text-size-adjust:100%; -ms-text-size-adjust:100%; margin:0; padding:0;}         /* Prevent Webkit and Windows Mobile platforms from changing default font sizes, while not breaking desktop design. */         .ExternalClass {width:100%;} /* Force Hotmail to display emails at full width */         .ExternalClass, .ExternalClass p, .ExternalClass span, .ExternalClass font, .ExternalClass td, .ExternalClass div {line-height: 100%;} /* Force Hotmail to display normal line spacing. */         #backgroundTable {margin:0; padding:0; width:100% !important; line-height: 100% !important;}         img {outline:none; text-decoration:none;border:none; -ms-interpolation-mode: bicubic;}         a img {border:none;}         .image_fix {display:block;}         p {margin: 0px 0px !important;}         table td {border-collapse: collapse;}         table { border-collapse:collapse; mso-table-lspace:0pt; mso-table-rspace:0pt; }         a {color: #33b9ff;text-decoration: none;text-decoration:none!important;}         /*STYLES*/         table[class=full] { width: 100%; clear: both; }         /*IPAD STYLES*/         @media only screen and (max-width: 640px) {             a[href^='tel'], a[href^='sms'] {                 text-decoration: none;                 color: #33b9ff; /* or whatever your want */                 pointer-events: none;                 cursor: default;             }             .mobile_link a[href^='tel'], .mobile_link a[href^='sms'] {                 text-decoration: default;                 color: #33b9ff !important;                 pointer-events: auto;                 cursor: default;             }             table[class=devicewidth] {width: 440px!important;text-align:center!important;}             table[class=devicewidthinner] {width: 420px!important;text-align:center!important;}             img[class=banner] {width: 440px!important;height:220px!important;}             img[class=col2img] {width: 440px!important;height:220px!important;}           }         /*IPHONE STYLES*/         @media only screen and (max-width: 480px) {             a[href^='tel'], a[href^='sms'] {                 text-decoration: none;                 color: #33b9ff; /* or whatever your want */                 pointer-events: none;                 cursor: default;             }             .mobile_link a[href^='tel'], .mobile_link a[href^='sms'] {                 text-decoration: default;                 color: #33b9ff !important;                 pointer-events: auto;                 cursor: default;             }             table[class=devicewidth] {width: 280px!important;text-align:center!important;}             table[class=devicewidthinner] {width: 260px!important;text-align:center!important;}             img[class=banner] {width: 280px!important;height:140px!important;}             img[class=col2img] {width: 280px!important;height:140px!important;}           }     </style> </head><body><!-- Start of preheader -->  <!-- End of preheader -->  <!-- Start of header -->  <!-- End of Header -->  <!-- Start of seperator -->  <!-- End of seperator -->  <!-- Start of main-banner -->  <!-- End of main-banner -->  <!-- Start of seperator -->  <!-- End of seperator -->  <!-- Start of Left Image -->  <!-- End of Left Image -->  <!-- Start of seperator -->  <!-- End of seperator -->  <!-- start of Full text -->  <!-- End of Full Text -->  <!-- Start of seperator -->  <!-- End of seperator -->  <!-- Start of Right Image -->  <!-- End of Right Image -->  <!-- Start of seperator -->  <!-- End of seperator -->  <!-- Start of footer -->  <!-- End of footer -->  <!-- Start of Postfooter -->  <!-- End of postfooter -->  <table width='100%' bgcolor='#fcfcfc' cellpadding='0' cellspacing='0' border='0'> 	<tbody> 		<tr> 			<td> 				<div class='innerbg'> 				</div> 				<table width='600' cellpadding='0' cellspacing='0' border='0' align='center' class='devicewidth'> 					<tbody> 						<tr> 							<td width='100%'> 								<table width='600' align='center' cellspacing='0' cellpadding='0' border='0' class='devicewidth'> 									<tbody> 										<tr> 											<!-- start of image -->  											<td align='center'> 												<div class='imgpop'> 													<div class='uploader_wrap' style='width: 600px; margin-top: 91px; opacity: 0;'> 														<div class='upload_buttons'> 															<div class='img_link'> 															</div> 															<div class='img_upload'> 															</div> 															<div class='img_edit' style='visibility: visible;'> 															</div> 														</div> 													</div> <a href='#'><img width='600' border='0' alt='' style='display:block; border:none; outline:none; text-decoration:none;' src='https://storage.googleapis.com/rsrc/welcome_email.jpg' class='banner' /></a> 												</div> 											</td> 										</tr> 									</tbody> 								</table> 								<!-- end of image -->  							</td> 						</tr> 					</tbody> 				</table> 			</td> 		</tr> 	</tbody> </table> <table width='100%' bgcolor='#fcfcfc' cellpadding='0' cellspacing='0' border='0'> 	<tbody> 		<tr> 			<td> 				<div class='innerbg'> 				</div> 				<table width='600' cellpadding='0' cellspacing='0' border='0' align='center' class='devicewidth' bgcolor='#ffffff'> 					<tbody> 						<tr> 							<td width='100%'> 								<table width='600' cellpadding='0' cellspacing='0' border='0' align='center' class='devicewidth' bgcolor='#ffffff'> 									<tbody> 										<!-- Spacing -->  										<tr> 											<td height='20' style='font-size:1px; line-height:1px; mso-line-height-rule: exactly;'> 											</td> 										</tr> 										<!-- Spacing -->  										<tr> 											<td> 												<table width='560' align='center' cellpadding='0' cellspacing='0' border='0' class='devicewidthinner'> 													<tbody> 														<!-- Title -->  														<tr> 															<td style='font-family: Helvetica, arial, sans-serif; font-size: 18px; color: #282828; text-align:center; line-height: 24px;'> 																<p> 																	Welcome 'Fashion Lovers' 																</p> 															</td> 														</tr> 														<!-- End of Title -->  														<!-- spacing -->  														<tr> 															<td width='100%' height='15' style='font-size:1px; line-height:1px; mso-line-height-rule: exactly;'> 															</td> 														</tr> 														<!-- End of spacing -->  														<!-- content -->  														<tr> 															<td style='font-family: Helvetica, arial, sans-serif; font-size: 14px; color: #889098; text-align:center; line-height: 24px;'> 																<p> 																	We are really glad you have joined our fastest growing community of fashion lovers. Glam Fiesta is a social network platform to connect with fashion lovers, fashionistas, YouTube vloggers and fashion bloggers around the world. Share your lookbook photo and join the latest and inspiring fashion trends. Create lookbook portfolios, like, comment and follow other members. Explore some of the inspiring posts from our members below: 																</p> 															</td> 														</tr> 														<!-- End of content -->  														<!-- Spacing -->  														<tr> 															<td width='100%' height='15' style='font-size:1px; line-height:1px; mso-line-height-rule: exactly;'> 															</td> 														</tr> 														<!-- Spacing -->  													</tbody> 												</table> 											</td> 										</tr> 										<!-- Spacing -->  										<tr> 											<td height='20' style='font-size:1px; line-height:1px; mso-line-height-rule: exactly;'> 											</td> 										</tr> 										<!-- Spacing -->  									</tbody> 								</table> 							</td> 						</tr> 					</tbody> 				</table> 			</td> 		</tr> 	</tbody> </table> <table width='100%' bgcolor='#fcfcfc' cellpadding='0' cellspacing='0' border='0'> 	<tbody> 		<tr> 			<td> 				<div class='innerbg'> 				</div> 				<table width='600' cellpadding='0' cellspacing='0' border='0' align='center' class='devicewidth' bgcolor='#edf2f5'> 					<tbody> 						<tr> 							<td width='100%'> 								<table width='600' cellpadding='0' cellspacing='0' border='0' align='center' class='devicewidth' bgcolor='#edf2f5'> 									<tbody> 										<tr> 											<td> 												<!-- Start of left column -->  												<table width='280' align='left' border='0' cellpadding='0' cellspacing='0' class='devicewidth' bgcolor='#edf2f5'> 													<tbody> 														<!-- image -->  														<tr> 															<td width='280' height='140' align='center' class='devicewidth' bgcolor='#edf2f5'> 																<div class='imgpop'> 																	<div class='uploader_wrap' style='width: 280px; margin-top: 75.5px; opacity: 0;'> 																		<div class='upload_buttons'> 																			<div class='img_link'> 																			</div> 																			<div class='img_upload'> 																			</div> 																			<div class='img_edit' style='visibility: visible;'> 																			</div> 																		</div> 																	</div> <a href='http://www.glamfiesta.com/u/susipb/lookbook/view/6478812137127936/week-inspirational-outfits.htm'><img src='https://storage.googleapis.com/rsrc/welcome-email1.jpg' alt='' border='0' width='280' style='display: block; border: none; outline: none; text-decoration: none;' class='col2img' /></a> 																</div> 															</td> 														</tr> 														<!-- /image -->  													</tbody> 												</table> 												<!-- end of left column -->  												<!-- spacing for mobile devices-->  												<table align='left' border='0' cellpadding='0' cellspacing='0' class='mobilespacing'> 													<tbody> 														<tr> 															<td width='100%' height='15' style='font-size:1px; line-height:1px; mso-line-height-rule: exactly;'> 															</td> 														</tr> 													</tbody> 												</table> 												<!-- end of for mobile devices-->  												<!-- start of right column -->  												<table width='280' align='right' border='0' cellpadding='0' cellspacing='0' class='devicewidth' bgcolor='#edf2f5'> 													<tbody> 														<tr> 															<td> 																<table width='280' align='center' border='0' cellpadding='0' cellspacing='0' class='devicewidth' bgcolor='#edf2f5'> 																	<tbody> 																		<!-- title -->  																		<tr> 																			<td style='font-family: Helvetica, arial, sans-serif; font-size: 18px; color: #282828; text-align:left; line-height: 24px;'> 																				<p> 																					<a href='http://www.glamfiesta.com/u/susipb/lookbook/view/6478812137127936/week-inspirational-outfits.htm'><span style='font-size: 12pt;'>WEEK INSPIRATIONAL OUTFITS</span></a> 																				</p> 																			</td> 																		</tr> 																		<!-- end of title -->  																		<!-- Spacing -->  																		<tr> 																			<td width='100%' height='15' style='font-size:1px; line-height:1px; mso-line-height-rule: exactly;'> 																			</td> 																		</tr> 																		<!-- /Spacing -->  																		<!-- content -->  																		<tr> 																			<td style='font-family: Helvetica, arial, sans-serif; font-size: 14px; color: #889098; text-align:left; line-height: 24px;'> 																				<p> 																					A collection of street styke lookbook 																				</p> 																			</td> 																		</tr> 																		<!-- end of content -->  																		<!-- Spacing -->  																		<tr> 																			<td width='100%' height='15' style='font-size:1px; line-height:1px; mso-line-height-rule: exactly;'> 																			</td> 																		</tr> 																		<!-- /Spacing -->  																		<!-- read more -->  																		<tr> 																			<td> 																				<div class='buttonbg'> 																				</div> 																				<table width='120' height='32' bgcolor='#6da7d1' align='left' valign='middle' border='0' cellpadding='0' cellspacing='0' style='border-radius: 3px; background-color: rgb(109, 167, 209);'> 																					<tbody> 																						<tr> 																							<td height='9' align='center' style='font-size:1px; line-height:1px;'> 																							</td> 																						</tr> 																						<tr> 																							<td height='14' align='center' valign='middle' style='font-family: Helvetica, Arial, sans-serif; font-size: 13px; font-weight:bold;color: #ffffff; text-align:center; line-height: 14px; ; -webkit-text-size-adjust:none;'> 																								<p> 																									<a href='http://www.glamfiesta.com/u/susipb/lookbook/view/6478812137127936/week-inspirational-outfits.htm'>View Lookbook</a> 																								</p> 																							</td> 																						</tr> 																						<tr> 																							<td height='9' align='center' style='font-size:1px; line-height:1px;'> 																							</td> 																						</tr> 																					</tbody> 																				</table> 																			</td> 																		</tr> 																		<!-- end of read more -->  																	</tbody> 																</table> 															</td> 														</tr> 													</tbody> 												</table> 												<!-- end of right column -->  											</td> 										</tr> 									</tbody> 								</table> 							</td> 						</tr> 					</tbody> 				</table> 			</td> 		</tr> 	</tbody> </table> <table width='100%' bgcolor='#fcfcfc' cellpadding='0' cellspacing='0' border='0'> 	<tbody> 		<tr> 			<td> 				<div class='innerbg'> 				</div> 				<table width='600' align='center' cellspacing='0' cellpadding='0' border='0' class='devicewidth'> 					<tbody> 						<tr> 							<td align='center' height='30' style='font-size:1px; line-height:1px;'> 								<p> 								</p> 							</td> 						</tr> 					</tbody> 				</table> 			</td> 		</tr> 	</tbody> </table> <table width='100%' bgcolor='#fcfcfc' cellpadding='0' cellspacing='0' border='0'> 	<tbody> 		<tr> 			<td> 				<div class='innerbg'> 				</div> 				<table width='600' cellpadding='0' cellspacing='0' border='0' align='center' class='devicewidth' bgcolor='#edf2f5'> 					<tbody> 						<tr> 							<td width='100%'> 								<table width='600' cellpadding='0' cellspacing='0' border='0' align='center' class='devicewidth' bgcolor='#edf2f5'> 									<tbody> 										<tr> 											<td> 												<!-- Start of left column -->  												<table width='280' align='right' border='0' cellpadding='0' cellspacing='0' class='devicewidth' bgcolor='#edf2f5'> 													<tbody> 														<!-- image -->  														<tr> 															<td width='280' height='140' align='center' class='devicewidth' bgcolor='#edf2f5'> 																<div class='imgpop'> 																	<div class='uploader_wrap' style='width: 280px; margin-top: 82.5px; opacity: 0;'> 																		<div class='upload_buttons'> 																			<div class='img_link'> 																			</div> 																			<div class='img_upload'> 																			</div> 																			<div class='img_edit' style='visibility: visible;'> 																			</div> 																		</div> 																	</div> <a href='http://www.glamfiesta.com/u/reesewest/lookbook/view/5109185989574656/business-casual-lookbook-.htm'><img src='https://storage.googleapis.com/rsrc/welcome-email2.jpg' alt='' border='0' width='280' style='display:block; border:none; outline:none; text-decoration:none;' class='col2img' /></a> 																</div> 															</td> 														</tr> 														<!-- /image -->  													</tbody> 												</table> 												<!-- end of left column -->  												<!-- spacing for mobile devices-->  												<table align='left' border='0' cellpadding='0' cellspacing='0' class='mobilespacing'> 													<tbody> 														<tr> 															<td width='100%' height='15' style='font-size:1px; line-height:1px; mso-line-height-rule: exactly;'> 															</td> 														</tr> 													</tbody> 												</table> 												<!-- end of for mobile devices-->  												<!-- start of right column -->  												<table width='280' align='left' border='0' cellpadding='0' cellspacing='0' class='devicewidth' bgcolor='#edf2f5'> 													<tbody> 														<tr> 															<td> 																<table width='280' align='center' border='0' cellpadding='0' cellspacing='0' class='devicewidth' bgcolor='#edf2f5'> 																	<tbody> 																		<!-- title -->  																		<tr> 																			<td style='font-family: Helvetica, arial, sans-serif; font-size: 18px; color: #282828; text-align:left; line-height: 24px;'> 																				<p> 																					<a href='http://www.glamfiesta.com/u/reesewest/lookbook/view/5109185989574656/business-casual-lookbook-.htm'>BUSINESS CASUAL LOOKBOOK</a> 																				</p> 																			</td> 																		</tr> 																		<!-- end of title -->  																		<!-- Spacing -->  																		<tr> 																			<td width='100%' height='15' style='font-size:1px; line-height:1px; mso-line-height-rule: exactly;'> 																			</td> 																		</tr> 																		<!-- /Spacing -->  																		<!-- content -->  																		<tr> 																			<td style='font-family: Helvetica, arial, sans-serif; font-size: 14px; color: #889098; text-align:left; line-height: 24px;'> 																				<p> 																					Business casual lookbook collection 																				</p> 																			</td> 																		</tr> 																		<!-- end of content -->  																		<!-- Spacing -->  																		<tr> 																			<td width='100%' height='15' style='font-size:1px; line-height:1px; mso-line-height-rule: exactly;'> 																			</td> 																		</tr> 																		<!-- /Spacing -->  																		<!-- read more -->  																		<tr> 																			<td> 																				<div class='buttonbg'> 																				</div> 																				<table width='120' height='32' bgcolor='#4c8fbf' align='left' valign='middle' border='0' cellpadding='0' cellspacing='0' style='border-radius: 3px; background-color: rgb(76, 143, 191);'> 																					<tbody> 																						<tr> 																							<td height='9' align='center' style='font-size:1px; line-height:1px;'> 																							</td> 																						</tr> 																						<tr> 																							<td height='14' align='center' valign='middle' style='font-family: Helvetica, Arial, sans-serif; font-size: 13px; font-weight:bold;color: #ffffff; text-align:center; line-height: 14px; ; -webkit-text-size-adjust:none;'> 																								<p> 																									<a href='http://www.glamfiesta.com/u/reesewest/lookbook/view/5109185989574656/business-casual-lookbook-.htm'>View Lookbook</a> 																								</p> 																							</td> 																						</tr> 																						<tr> 																							<td height='9' align='center' style='font-size:1px; line-height:1px;'> 																							</td> 																						</tr> 																					</tbody> 																				</table> 																			</td> 																		</tr> 																		<!-- end of read more -->  																	</tbody> 																</table> 															</td> 														</tr> 													</tbody> 												</table> 												<!-- end of right column -->  											</td> 										</tr> 									</tbody> 								</table> 							</td> 						</tr> 					</tbody> 				</table> 			</td> 		</tr> 	</tbody> </table> <table width='100%' bgcolor='#fcfcfc' cellpadding='0' cellspacing='0' border='0'> 	<tbody> 		<tr> 			<td> 				<div class='innerbg'> 				</div> 				<table width='600' align='center' cellspacing='0' cellpadding='0' border='0' class='devicewidth'> 					<tbody> 						<tr> 							<td align='center' height='30' style='font-size:1px; line-height:1px;'> 								<p> 								</p> 							</td> 						</tr> 					</tbody> 				</table> 			</td> 		</tr> 	</tbody> </table> <table width='100%' bgcolor='#fcfcfc' cellpadding='0' cellspacing='0' border='0'> 	<tbody> 		<tr> 			<td> 				<div class='innerbg'> 				</div> 				<table width='600' cellpadding='0' cellspacing='0' border='0' align='center' class='devicewidth' bgcolor='#e8edf0'> 					<tbody> 						<tr> 							<td width='100%'> 								<table width='600' cellpadding='0' cellspacing='0' border='0' align='center' class='devicewidth' bgcolor='#e8edf0'> 									<tbody> 										<tr> 											<td> 												<!-- Start of left column -->  												<table width='280' align='left' border='0' cellpadding='0' cellspacing='0' class='devicewidth' bgcolor='#e8edf0'> 													<tbody> 														<!-- image -->  														<tr> 															<td width='280' height='140' align='center' class='devicewidth' bgcolor='#e8edf0'> 																<div class='imgpop'> 																	<div class='uploader_wrap' style='width: 280px; margin-top: 59.5px; opacity: 0;'> 																		<div class='upload_buttons'> 																			<div class='img_link'> 																			</div> 																			<div class='img_upload'> 																			</div> 																			<div class='img_edit' style='visibility: visible;'> 																			</div> 																		</div> 																	</div> <a href='http://www.glamfiesta.com/u/losangelesdesire/lookbook/view/5687905720729600/monochrome-lookbook.htm'><img src='https://storage.googleapis.com/rsrc/welcome-email3.jpg' alt='' border='0' width='280' style='display: block; border: none; outline: none; text-decoration: none;' class='col2img' /></a> 																</div> 															</td> 														</tr> 														<!-- /image -->  													</tbody> 												</table> 												<!-- end of left column -->  												<!-- spacing for mobile devices-->  												<table align='left' border='0' cellpadding='0' cellspacing='0' class='mobilespacing'> 													<tbody> 														<tr> 															<td width='100%' height='15' style='font-size:1px; line-height:1px; mso-line-height-rule: exactly;'> 															</td> 														</tr> 													</tbody> 												</table> 												<!-- end of for mobile devices-->  												<!-- start of right column -->  												<table width='280' align='right' border='0' cellpadding='0' cellspacing='0' class='devicewidth' bgcolor='#e8edf0'> 													<tbody> 														<tr> 															<td> 																<table width='280' align='center' border='0' cellpadding='0' cellspacing='0' class='devicewidth' bgcolor='#e8edf0'> 																	<tbody> 																		<!-- title -->  																		<tr> 																			<td style='font-family: Helvetica, arial, sans-serif; font-size: 18px; color: #282828; text-align:left; line-height: 24px;'> 																				<p> 																					<a href='http://www.glamfiesta.com/u/losangelesdesire/lookbook/view/5687905720729600/monochrome-lookbook.htm'>MONOCHROME LOOKBOOK ...</a> 																				</p> 																			</td> 																		</tr> 																		<!-- end of title -->  																		<!-- Spacing -->  																		<tr> 																			<td width='100%' height='15' style='font-size:1px; line-height:1px; mso-line-height-rule: exactly;'> 																			</td> 																		</tr> 																		<!-- /Spacing -->  																		<!-- content -->  																		<tr> 																			<td style='font-family: Helvetica, arial, sans-serif; font-size: 14px; color: #889098; text-align:left; line-height: 24px;'> 																				<p> 																					Explore monochrome lookbook collection 																				</p> 																			</td> 																		</tr> 																		<!-- end of content -->  																		<!-- Spacing -->  																		<tr> 																			<td width='100%' height='15' style='font-size:1px; line-height:1px; mso-line-height-rule: exactly;'> 																			</td> 																		</tr> 																		<!-- /Spacing -->  																		<!-- read more -->  																		<tr> 																			<td> 																				<div class='buttonbg'> 																				</div> 																				<table width='120' height='32' bgcolor='#5194c4' align='left' valign='middle' border='0' cellpadding='0' cellspacing='0' style='border-radius: 3px; background-color: rgb(81, 148, 196);'> 																					<tbody> 																						<tr> 																							<td height='9' align='center' style='font-size:1px; line-height:1px;'> 																							</td> 																						</tr> 																						<tr> 																							<td height='14' align='center' valign='middle' style='font-family: Helvetica, Arial, sans-serif; font-size: 13px; font-weight:bold;color: #ffffff; text-align:center; line-height: 14px; ; -webkit-text-size-adjust:none;'> 																								<p> 																									<a href='http://www.glamfiesta.com/u/losangelesdesire/lookbook/view/5687905720729600/monochrome-lookbook.htm'>View Lookbook</a> 																								</p> 																							</td> 																						</tr> 																						<tr> 																							<td height='9' align='center' style='font-size:1px; line-height:1px;'> 																							</td> 																						</tr> 																					</tbody> 																				</table> 																			</td> 																		</tr> 																		<!-- end of read more -->  																	</tbody> 																</table> 															</td> 														</tr> 													</tbody> 												</table> 												<!-- end of right column -->  											</td> 										</tr> 									</tbody> 								</table> 							</td> 						</tr> 					</tbody> 				</table> 			</td> 		</tr> 	</tbody> </table> <table width='100%' bgcolor='#fcfcfc' cellpadding='0' cellspacing='0' border='0'> 	<tbody> 		<tr> 			<td> 				<div class='innerbg'> 				</div> 				<table width='600' align='center' cellspacing='0' cellpadding='0' border='0' class='devicewidth'> 					<tbody> 						<tr> 							<td align='center' height='30' style='font-size:1px; line-height:1px;'> 								<p> 								</p> 							</td> 						</tr> 					</tbody> 				</table> 			</td> 		</tr> 	</tbody> </table></body> </html>";
		
		GlamCmn.sendEmailToUsr("editor.glamfiesta@gmail.com", "test editor", "Welcome to GlamFiesta !!", email_txt, true);
	}
}