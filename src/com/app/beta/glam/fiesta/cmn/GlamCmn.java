package com.app.beta.glam.fiesta.cmn;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.BreakIterator;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Months;
import org.joda.time.Years;
import org.springframework.beans.BeanUtils;

import com.app.beta.glam.fiesta.db.FeaturedUsersDO;
import com.app.beta.glam.fiesta.db.UserActivitiesDO;
import com.app.beta.glam.fiesta.db.UserDO;
import com.app.beta.glam.fiesta.db.UserGlamPointsActivitiesDO;
import com.app.beta.glam.fiesta.form.CommentForm;
import com.app.beta.glam.fiesta.model.LookbookBO;
import com.app.beta.glam.fiesta.model.UserBO;
import com.app.beta.glam.fiesta.model.UsrContainer;
import com.app.beta.glam.fiesta.model.UsrLoginBO;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.ImagesServiceFailureException;
import com.google.appengine.api.images.ServingUrlOptions;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GlamCmn {
	private static final Logger logger = Logger.getLogger(GlamCmn.class.getCanonicalName());
	private static char[] c = new char[] { 'K', 'M', 'B', 'T' };

	public static final int _HOME_LKBK_LMT = 50;

	public static final String _EML_FRM_EDTR = "editor.glamfiesta@gmail.com";

	public static final String _UAVTR_PARAM_S65 = "=s65";

	public static final String _IMG_RSZ_PARAM_S50 = "=s50";
	public static final String _IMG_RSZ_PARAM_S65 = "=s65";
	public static final String _IMG_RSZ_PARAM_S75 = "=s75";
	public static final String _IMG_RSZ_PARAM_S150 = "=s150";

	public static final String _SOCIAL_SCOPES_FB = "public_profile,email";

	public static final String _API_KEY_FB = "1544059335851477";

	public static final String _API_SECRET_FB = "9e839b0fc425d4857f08b0510414c343";

	public static final String _CALLBACK_URL_FB = "http://www.glamfiesta.com/auth/facebook/callback";

	public static final String[] globaltags = { "StreetStyle", "SummerStyle", "SpringStyle", "WinterStyle",
			"OffBeatStyle", "MaleFashion" };
	
	public static final String[] spotlights = { "StreetStyle", "SummerStyle", "SpringStyle", "WinterStyle",
			"OffBeatStyle", "MaleFashion" };
	
	/*
	 * public static final String[] globaltags = { "streetstyle",
	 * "coolsummer2015", "springspiration2015", "winter2015", "offbeatstyle",
	 * "malefashion"};
	 */

	public final static String getDateTmStr() {
		Calendar cal = new GregorianCalendar();
		Date creationDate = cal.getTime();
		SimpleDateFormat date_format = new SimpleDateFormat(GlamCnst._FRMT_DTTM);

		return date_format.format(creationDate);
	}

	public final static boolean chkLoggdInStatus(HttpServletRequest req) {
		boolean isLoggedIn = false;

		UsrLoginBO ul = (UsrLoginBO) req.getSession().getAttribute("ul");

		if (ul != null && ul.isLoginstatus() == true) {
			isLoggedIn = true;
		}

		return isLoggedIn;
	}

	public final static UserDO getUsrFrmDB(long usrid) {
		UserDO usrdo = null;
		usrdo = OfyService.ofy().load().type(UserDO.class).id(usrid).get();
		return usrdo;
	}

	public final static UserDO getUsrFrmDB(String unm) {

		List<UserDO> usrdolist = OfyService.ofy().load().type(UserDO.class).filter("account_username", unm).list();
		UserDO usrdo = null;

		if (usrdolist != null && usrdolist.size() > 0) {
			usrdo = (UserDO) usrdolist.get(0);
		}

		return usrdo;

	}
	
	public final static UserBO getUsrInfo(long usrid) {
		UserDO usrdo = OfyService.ofy().load().type(UserDO.class).id(usrid).get();
		UserBO usrbo = new UserBO();
		BeanUtils.copyProperties(usrdo, usrbo);
		return usrbo;
	}

	public final static UserBO getUsrInfo(String unm) {

		List<UserDO> usrdolist = OfyService.ofy().load().type(UserDO.class).filter("account_username", unm).list();
		UserDO usrdo = null;
		UserBO usrbo = new UserBO();
		
		if (usrdolist != null && usrdolist.size() > 0) {
			usrdo = (UserDO) usrdolist.get(0);
		}
		
		BeanUtils.copyProperties(usrdo, usrbo);
		
		return usrbo;

	}
	
	public final static String getUsrnmStr(long usrid) {
		UserDO usrdo = OfyService.ofy().load().type(UserDO.class).id(usrid).get();
		if (usrdo != null) {
			return usrdo.getAccount_username();
		} else {
			return "";
		}
	}

	public final static long getUsrId(String unm) {
		List<UserDO> usrdolist = OfyService.ofy().load().type(UserDO.class).filter("account_username", unm).list();
		UserDO usrdo = null;
		long uid = 0L;

		if (usrdolist != null && usrdolist.size() > 0) {
			usrdo = (UserDO) usrdolist.get(0);
			uid = usrdo.getUserid().longValue();
		}

		return uid;
	}

	public final static String getAvatarImg(long usrid, String imgParam) {
		UserDO usrdo = OfyService.ofy().load().type(UserDO.class).id(usrid).get();
		if (usrdo != null) {
			return usrdo.getUavatar() + imgParam;
		} else {
			return "";
		}
	}

	
	public final static String getRndAvtrImgStr() {
		int maxval = 5;
		int minval = 1;
		int n = 0;

		Random rand = new Random();
		n = rand.nextInt(maxval) + minval;

		return "/resources/img/profile/u/dflt/avatar-icon-" + n + ".png";
	}

	public final static String getUsrLocn(UserBO usrbo) {
		StringBuilder locn = new StringBuilder("");
		boolean cityflag = false;
		boolean stateflag = false;

		boolean locnflag = false;

		if (StringUtils.isNotBlank(usrbo.getCitynm())) {
			locn.append(usrbo.getCitynm());
			cityflag = true;
			locnflag = true;
		}

		if (StringUtils.isNotBlank(usrbo.getStatenm())) {
			if (cityflag == true) {
				locn.append(", " + usrbo.getStatenm());
			} else {
				locn.append(usrbo.getStatenm());
			}
			stateflag = true;
			locnflag = true;
		}

		if (StringUtils.isNotBlank(usrbo.getCountrynm())) {
			if (stateflag == true || cityflag == true) {
				locn.append(", " + usrbo.getCountrynm());
			} else {
				locn.append(usrbo.getCountrynm());
			}

			locnflag = true;
		}

		if (locnflag == false) {
			locn.append("Somewhere in the world...");
		}

		return locn.toString();
	}

	public final static String getUsrLocn(UserDO usrdo) {
		StringBuilder locn = new StringBuilder("");
		boolean cityflag = false;
		boolean stateflag = false;

		boolean locnflag = false;

		if (StringUtils.isNotBlank(usrdo.getCitynm())) {
			locn.append(usrdo.getCitynm());
			cityflag = true;
			locnflag = true;
		}

		if (StringUtils.isNotBlank(usrdo.getStatenm())) {
			if (cityflag == true) {
				locn.append(", " + usrdo.getStatenm());
			} else {
				locn.append(usrdo.getStatenm());
			}
			stateflag = true;
			locnflag = true;
		}

		if (StringUtils.isNotBlank(usrdo.getCountrynm())) {
			if (stateflag == true || cityflag == true) {
				locn.append(", " + usrdo.getCountrynm());
			} else {
				locn.append(usrdo.getCountrynm());
			}

			locnflag = true;
		}

		if (locnflag == false) {
			locn.append("Somewhere in the world...");
		}

		return locn.toString();
	}

	/*
	 * public final static UserBO getLoggedInUsrDtls(HttpServletRequest req,
	 * UserBO usrbo, boolean ignr) { UsrLoginBO ulobj =
	 * (UsrLoginBO)req.getSession().getAttribute("ul"); UserDO usrdo =
	 * OfyService.ofy().load().type(UserDO.class).id(ulobj.getUserid()).get();
	 * if(ignr) { BeanUtils.copyProperties(usrdo, usrbo); }else { String[]
	 * ignoreflds = {"account_email"}; BeanUtils.copyProperties(usrdo, usrbo,
	 * ignoreflds); }
	 * 
	 * return usrbo; }
	 */

	public final static UsrContainer getLoggedInUsrDtls(HttpServletRequest req) {
		UsrLoginBO ulobj = (UsrLoginBO) req.getSession().getAttribute("ul");
		UsrContainer uc = new UsrContainer();
		UserBO usrbo = new UserBO();
		UserDO usrdo = null;

		// logger.info(" - - - - - GlamCmn-> getLoggedInUsrDtls:: " + ulobj);

		if (ulobj != null) {
			usrdo = OfyService.ofy().load().type(UserDO.class).id(ulobj.getUserid()).get();
			// logger.info(" - - - - - GlamCmn-> getLoggedInUsrDtls:: " +
			// usrdo);
			String[] ignoreflds = { "account_password" };

			BeanUtils.copyProperties(usrdo, usrbo, ignoreflds);

			// logger.info(" - - - - - GlamCmn-> getLoggedInUsrDtls:: " +
			// usrbo);
		}

		uc.setUsrbo(usrbo);
		uc.setUsrdo(usrdo);

		return uc;
	}

	public final static String getUpldPhotoUrl(BlobKey blobKey) {
		String ftrdPhotoUrl = "";

		ServingUrlOptions servingUrlOptions = ServingUrlOptions.Builder.withBlobKey(blobKey);
		try {
			ImagesService imagesService = ImagesServiceFactory.getImagesService();
			ftrdPhotoUrl = imagesService.getServingUrl(servingUrlOptions);
		} catch (ImagesServiceFailureException e) {
			logger.severe("############### Failed to get image serving url: " + e.getMessage());
			return "";
		}

		return ftrdPhotoUrl;
	}

	public final static int getDaysDiffWithToday(String nxtDateStr) {
		Date nxtDate = new Date();
		int daysDiff = 0;
		try {
			SimpleDateFormat format = new SimpleDateFormat(GlamCnst._FRMT_DTTM_PRNT);
			nxtDate = format.parse(nxtDateStr);

			DateTime todayDttm = new DateTime();// current date
			DateTime nextDttm = new DateTime(nxtDate); // next date to find the
														// difference

			daysDiff = Days.daysBetween(nextDttm, todayDttm).getDays();
		} catch (ParseException pe) {
			daysDiff = 0;
		}

		return daysDiff;
	}

	public final static String printDtttmStr(String nxtDateStr) {

		StringBuilder dtdiffStr = new StringBuilder("");
		boolean flagDtFormatted = false;
		int daysDiff = 0;
		int monthDiff = 0;
		int yearDiff = 0;
		Date nxtDate = new Date();
		// String appndstr = "<button class=\"btn btn-primary\"
		// type=\"button\">";

		try {
			SimpleDateFormat format = new SimpleDateFormat(GlamCnst._FRMT_DTTM_PRNT);
			nxtDate = format.parse(nxtDateStr);

			DateTime todayDttm = new DateTime();// current date
			DateTime nextDttm = new DateTime(nxtDate); // next date to find the
														// difference

			daysDiff = Days.daysBetween(nextDttm, todayDttm).getDays();
			monthDiff = Months.monthsBetween(nextDttm, todayDttm).getMonths();
			yearDiff = Years.yearsBetween(nextDttm, todayDttm).getYears();

			if (yearDiff > 0) {
				dtdiffStr.append(yearDiff).append(yearDiff == 1 ? " Year " : " Years ");
				flagDtFormatted = true;
			}

			if (!flagDtFormatted) {
				if (monthDiff > 0) {
					dtdiffStr.append(monthDiff).append(monthDiff == 1 ? " Month " : " Months ");
					flagDtFormatted = true;
				}
			}

			if (!flagDtFormatted) {
				if (daysDiff > 0) {
					dtdiffStr.append(daysDiff).append(daysDiff == 1 ? " Day " : " Days ");
					flagDtFormatted = true;
				}
			}

			if (!flagDtFormatted && daysDiff == 0) {
				dtdiffStr.append("Today ");
			} else {
				dtdiffStr.append("Ago ");
			}
		} catch (ParseException pe) {
			dtdiffStr.append(nxtDateStr);
		}

		return dtdiffStr.toString() + "";
	}

	public final static void saveUsrActivity(long userid, String uname, String actvtytype, long paramid,
			String paramstr1, String paramstr2) {

		UserActivitiesDO usractvtydo = new UserActivitiesDO();

		if (StringUtils.isBlank(uname)) {
			uname = GlamCmn.getUsrnmStr(userid);
		}

		String actvtystr = "";
		String actvtyurl = "";
		String[] imgUrl = {};
		Long lookbookid = 1L;

		// lookbook create activity
		if (StringUtils.endsWithIgnoreCase(GlamCnst._ATVY_LKBK_CREATE, actvtytype)) {
			actvtystr = " Created lookbook ";
			actvtyurl = "/fashion/" + paramstr1;
			lookbookid = paramid;
		}

		// lookbook update activity
		if (StringUtils.endsWithIgnoreCase(GlamCnst._ATVY_LKBK_UPDTE, actvtytype)) {
			actvtystr = " Updated lookbook ";
			actvtyurl = "/fashion/" + paramstr1;
			lookbookid = paramid;
		}

		// lookbook photo add activity
		if (StringUtils.endsWithIgnoreCase(GlamCnst._ATVY_LKBK_PH_ADD, actvtytype)) {
			actvtystr = " Updated lookbook photo ";
			imgUrl = StringUtils.split(paramstr2, "|");
			actvtyurl = "/fashion/" + paramstr1;
			lookbookid = paramid;
		}

		// lookbook like activity
		if (StringUtils.endsWithIgnoreCase(GlamCnst._ATVY_LKBK_LIKE, actvtytype)) {
			actvtystr = " Liked lookbook ";
			actvtyurl = "/fashion/" + paramstr1;
			lookbookid = paramid;
		}

		// lookbook comment posted
		if (StringUtils.endsWithIgnoreCase(GlamCnst._ATVY_LKBK_CMTADD, actvtytype)) {
			actvtystr = " Posted a comment on the lookbook ";
			actvtyurl = "/fashion/" + paramstr1;
			lookbookid = paramid;
		}

		// user profile created activity
		if (StringUtils.endsWithIgnoreCase(GlamCnst._ATVY_PRFL_CREATE, actvtytype)) {
			actvtystr = " Joined Glam Fiesta ";
			actvtyurl = "/fashionistas/" + uname;
		}

		// profile update
		if (StringUtils.endsWithIgnoreCase(GlamCnst._ATVY_PRFL_UPDTE, actvtytype)) {
			actvtystr = " Updated profile ";
			actvtyurl = "/fashionistas/" + uname;
		}

		// user avatar uploaded/changed
		if (StringUtils.endsWithIgnoreCase(GlamCnst._ATVY_PRFL_AVTR_UPDT, actvtytype)) {
			actvtystr = " Updated avatar ";
			actvtyurl = "/fashionistas/" + uname;
		}

		// user is following another user activity
		if (StringUtils.endsWithIgnoreCase(GlamCnst._ATVY_PRFL_FLLW, actvtytype)) {
			actvtystr = " Following " + paramstr2;
			actvtyurl = "/fashionistas/" + uname;
		}

		usractvtydo.setActvtdt(new Date());
		usractvtydo.setActvtystr(actvtystr);
		usractvtydo.setActvtytype(actvtytype);
		usractvtydo.setActvtyurl(StringUtils.lowerCase(actvtyurl));

		if (imgUrl != null && imgUrl.length > 0) {

			if (StringUtils.isNotBlank(imgUrl[0])) {
				usractvtydo.setImgurl1(imgUrl[0]);
			}
			/*
			 * if(StringUtils.isNotBlank(imgUrl[1])) {
			 * usractvtydo.setImgurl2(imgUrl[1]); }
			 * if(StringUtils.isNotBlank(imgUrl[2])) {
			 * usractvtydo.setImgurl3(imgUrl[2]); }
			 */
		}

		usractvtydo.setUserid(userid);
		usractvtydo.setUsername(uname);
		usractvtydo.setLookbookid(lookbookid);

		// save the updated lookbook
		logger.info("********** saving the activity ******************");
		OfyService.ofy().save().entity(usractvtydo).now();
	}

	public final static void saveGlamPointsActivity(long userid, String uname, String actvtytype, int points,
			String actvt) {
		UserGlamPointsActivitiesDO usrglamptsactvtydo = new UserGlamPointsActivitiesDO();

		if (StringUtils.isBlank(uname)) {
			uname = GlamCmn.getUsrnmStr(userid);
		}

		String actvtystr = "";

		if (StringUtils.endsWithIgnoreCase(GlamCnst._ATVY_PTS_ADDED, actvtytype)) {
			actvtystr = uname + " has been awarded + " + points + " glam points for " + actvt;
		} else if (StringUtils.endsWithIgnoreCase(GlamCnst._ATVY_PTS_DEDUCTD, actvtytype)) {
			actvtystr = uname + " has been deducted + " + points + " glam points for " + actvt;
		}

		usrglamptsactvtydo.setUserid(userid);
		usrglamptsactvtydo.setPointacvtstr(actvtystr);
		usrglamptsactvtydo.setActvtdt(new Date());

		// save the updated lookbook
		logger.info("********** saving the points history ******************");
		OfyService.ofy().save().entity(usrglamptsactvtydo).now();
	}

	public static CommentForm parseSearchJsonData(String jsonStr, Gson gson) throws IOException {
		return gson.fromJson(jsonStr, CommentForm.class);
	}

	public static Gson getGson(Gson gson) {
		GsonBuilder gb = new GsonBuilder();
		gb.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY);
		gson = gb.create();

		return gson;
	}

	/**
	 * Recursive implementation, invokes itself for each factor of a thousand,
	 * increasing the class on each invokation.
	 * 
	 * @param n
	 *            the number to format
	 * @param iteration
	 *            in fact this is the class from the array c
	 * @return a String representing the number n formatted in a cool looking
	 *         way.
	 */
	public static String coolFormat(double n, int iteration) {
		double d = ((long) n / 100) / 10.0;
		boolean isRound = (d * 10) % 10 == 0;// true if the decimal part is
												// equal to 0 (then it's trimmed
												// anyway)
		return (d < 1000 ? // this determines the class, i.e. 'k', 'm' etc
				((d > 99.9 || isRound || (!isRound && d > 9.99) ? // this
																	// decides
																	// whether
																	// to trim
																	// the
																	// decimals
						(int) d * 10 / 10 : d + "" // (int) d * 10 / 10 drops
													// the decimal
				) + "" + c[iteration]) : coolFormat(d, iteration + 1));
	}

	public static String intToCoolFormatStr(int val) {
		if (val == 0) {
			return "0";
		} else {
			return NumberFormat.getNumberInstance(Locale.US).format(val);
		}
	}

	public static String intToCoolFormatStr(long val) {
		return NumberFormat.getNumberInstance(Locale.US).format(val);
	}

	public static void sendEmailToUsr(String to_emailid, String fname, String subj_txt, String body_txt,
			boolean isHtml) {
		logger.info("sending email..." + to_emailid);
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(GlamCmn._EML_FRM_EDTR, "GlamFiesta Editor"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to_emailid, fname));
			msg.setSubject(subj_txt);
			msg.setText(body_txt);

			if (isHtml == true) {
				// Create Multipart to add content to
				Multipart mp = new MimeMultipart();

				// Create a Part for the html content
				MimeBodyPart htmlPart = new MimeBodyPart();
				htmlPart.setContent(body_txt, "text/html");

				// Add html part to your Multipart
				mp.addBodyPart(htmlPart);
				msg.setContent(mp);
			}
			Transport.send(msg);

		} catch (AddressException ea) {
			logger.severe("****** Exception occurred while sending email **** " + ea.getMessage() + " **** fname - "
					+ fname + " **** email id- " + to_emailid);
		} catch (MessagingException em) {
			logger.severe("****** Exception occurred while sending email **** " + em.getMessage() + " **** fname - "
					+ fname + " **** email id- " + to_emailid);
		} catch (Exception e) {
			logger.severe("****** Exception occurred while sending email **** " + e.getMessage() + " **** fname - "
					+ fname + " **** email id- " + to_emailid);
		}
	}

	/*public static String updtSEOUrl(LookbookBO lookbook) {
		if (lookbook != null) {
			return StringUtils.remove(StringUtils.lowerCase(StringUtils.replace(
					StringUtils.replace(lookbook.getLookbookname().replaceAll("[^a-zA-Z0-9 ]", ""), " ", "-"), "--",
					"-") + ".htm"), "/");
		} else {
			return "404.htm";
		}
	}*/

	public static String genSeoUrlByStrTitle(String strtitle) {
		return StringUtils.remove(StringUtils.lowerCase(StringUtils.replace(
				StringUtils.replace(strtitle.replaceAll("[^a-zA-Z0-9 ]", ""), " ", "-"), "--", "-") + ".htm"), "/");
	}

	public static String updtSEOUrl(long lookbookid) {
		/*return StringUtils.remove(StringUtils.lowerCase(StringUtils.replace(
				StringUtils.replace(LookbookCmn.getLookBookTitle(lookbookid).replaceAll("[^a-zA-Z0-9 ]", ""), " ", "-"),
				"--", "-") + ".htm"), "/");*/
		return LookbookCmn.getLookBookSEOUrl(lookbookid);
	}

	public static String genUnqueLookbookSEOUrl(String lookbooknm, String usrnm) {
		DateFormat dateFormat = new SimpleDateFormat("MMddyyyyHHmmss");
		Date date = new Date();

		return dateFormat.format(date) + "-"
				+ StringUtils.remove(StringUtils.lowerCase(
						StringUtils.replace(StringUtils.replace(lookbooknm.trim().replaceAll("[^a-zA-Z0-9 ]", ""), " ", "-"),
								"--", "-") + "-by-" + usrnm + ".htm"),
						"/");
	}
	
	public static List<Long> getRandmList(List<Long> sourceList, int totItms) {
		List<Long> rndmList = new ArrayList<Long>();
		Random random = new Random();
		int totIndx = sourceList.size();

		for (int j = 0; j < totItms; j++) {
			int rndIndx = random.nextInt(totIndx);
			Long rndNo = sourceList.get(rndIndx);
			if (!rndmList.contains(rndNo)) {
				rndmList.add(rndNo);
			}
		}

		return rndmList;
	}

	public static boolean checkIfInputStringIsEmailAddrs(String emailId) {
		String regex = "^(.+)@(.+)$";

		Pattern pattern = Pattern.compile(regex);

		if (StringUtils.isNotBlank(emailId)) {
			Matcher matcher = pattern.matcher(emailId);
			return matcher.matches();
		} else {
			return false;
		}
	}

	public static String truncateAfterWords(int n, String s) {
		if (StringUtils.isBlank(s))
			return "";

		BreakIterator wb = BreakIterator.getWordInstance();
		wb.setText(s);
		int pos = 0;
		for (int i = 0; i < n && pos != BreakIterator.DONE && pos < s.length();) {
			if (Character.isLetter(s.codePointAt(pos)))
				i++;
			pos = wb.next();
		}
		if (pos == BreakIterator.DONE || pos >= s.length())
			return s;
		return s.substring(0, pos);
	}

	public static String getResizeImg(UserDO usr, int sizepaaram) {
		if (StringUtils.isNotBlank(usr.getUavatar_is_uploaded())) {
			if (sizepaaram == 1) {
				return usr.getUavatar() + GlamCmn._IMG_RSZ_PARAM_S50;
			} else if (sizepaaram == 2) {
				return usr.getUavatar() + GlamCmn._IMG_RSZ_PARAM_S65;
			} else if (sizepaaram == 3) {
				return usr.getUavatar() + GlamCmn._IMG_RSZ_PARAM_S75;
			} else {
				return usr.getUavatar() + GlamCmn._IMG_RSZ_PARAM_S150;
			}
		} else {
			return usr.getUavatar();
		}
	}

	public static List<Date> genRandomDate() {
		List<Date> dtRngLst = new ArrayList<Date>();
		dtRngLst.add(new Date());
		long beginTime = new Date().getTime();
		long endTime = Timestamp.valueOf("2015-03-01 19:33:30").getTime();
		long diff = endTime - beginTime + 1;

		dtRngLst.add(new Date(beginTime + (long) (Math.random() * diff)));

		//logger.info("dtRngLst.get(0)-" + dtRngLst.get(0));
		//logger.info("dtRngLst.get(1)-" + dtRngLst.get(1));

		return dtRngLst;
	}

	public static List<UserBO> getAllFeaturedFashionistas() {

		List<FeaturedUsersDO> ftrdusrdolist = null;
		List<UserBO> ftrdfashionistas = new ArrayList<UserBO>();

		ftrdusrdolist = OfyService.ofy().load().type(FeaturedUsersDO.class).list();

		if (ftrdusrdolist != null && ftrdusrdolist.size() > 0) {

			Iterator<FeaturedUsersDO> ftrdusrItr = ftrdusrdolist.iterator();

			while (ftrdusrItr.hasNext()) {

				FeaturedUsersDO ftrdfashionistado = ftrdusrItr.next();

				if (ftrdfashionistado != null) {
					UserDO usrdo = OfyService.ofy().load().type(UserDO.class).id(ftrdfashionistado.getUserid()).get();
					UserBO ftrdfashionista = new UserBO();

					String[] ignorefldsUsr = { "account_email" };
					BeanUtils.copyProperties(usrdo, ftrdfashionista, ignorefldsUsr);
					ftrdfashionista.setUavatar_thumb_profile(GlamCmn.getResizeImg(usrdo, 2));

					ftrdfashionista.setLocation(GlamCmn.getUsrLocn(ftrdfashionista));

					ftrdfashionistas.add(ftrdfashionista);
				}
			}
		}

		return ftrdfashionistas;
	}

	public static String convrtAllWebAddrs(String txt) {
		StringBuilder rtrnStr = new StringBuilder("");
		String[] parts = txt.split("\\s+");
		// Attempt to convert each item into an URL.
		for (String item : parts) {
			try {
				if (StringUtils.startsWithIgnoreCase(item, "www.")) {
					item = "http://" + item;
				}
				URL url = new URL(item);
				// If possible then replace with anchor...
				rtrnStr.append("<a href=\"" + url + "\" target='_blank' rel='no-follow'>" + url + "</a> ");
			} catch (MalformedURLException e) {
				// If there was an URL that was not it!...
				rtrnStr.append(item + " ");
			}
		}

		return rtrnStr.toString();
	}

	public static String checkForHttp(String urltochk) {
		boolean http_found = false;

		if (StringUtils.isNotBlank(urltochk)) {
			if (StringUtils.startsWithIgnoreCase(urltochk, "www.")) {
				urltochk = "http://" + urltochk;
			} else {
				if (StringUtils.startsWithIgnoreCase(urltochk, "http://")) {
					// urltochk = "http://www." + urltochk;
					http_found = true;
				} else if (StringUtils.startsWithIgnoreCase(urltochk, "https://")) {
					// urltochk = "http://www." + urltochk;
					http_found = true;
				}

				if (!http_found) {
					urltochk = "http://www." + urltochk;
				}
			}
		}

		return urltochk;
	}

	public static Date getDtFrmStr(String dtstr) {
		SimpleDateFormat sf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss z");
		Date dt = null;
		try {
			dt = sf.parse(dtstr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dt;
	}
}
