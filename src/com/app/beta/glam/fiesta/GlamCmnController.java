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
import com.app.beta.glam.fiesta.cmn.LookbookCmn;
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
public class GlamCmnController {

	private static final Logger logger = Logger.getLogger(GlamCmnController.class.getCanonicalName());


	@RequestMapping(value = "/newlooks", method = RequestMethod.GET)
	public String getFreshLooks(HttpServletRequest req, Model uiModel) {

		List<UserLookbookDO> lookbookdolist = new ArrayList<UserLookbookDO>();
		List<LookbookBO> looks = new ArrayList<LookbookBO>();

		LookbookBOWrapper lookbookWrapper = new LookbookBOWrapper();

		// List<FeaturedUsersDO> ftrdusrdolist = null;
		// List<UserBO> ftrdfashionistas = null;

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
			// logger.info("888888 setting cursor " + next_page);
			queryLookbook = OfyService.ofy().load().type(UserLookbookDO.class).filter("lookbook_publish_status", "Yes")
					.order("-createddt").startAt(Cursor.fromWebSafeString(curr_page)).limit(GlamCmn._HOME_LKBK_LMT);
		} else {
			// logger.info(" 1111 no page so this block executes ");
			queryLookbook = OfyService.ofy().load().type(UserLookbookDO.class).filter("lookbook_publish_status", "Yes")
					.order("-createddt").limit(GlamCmn._HOME_LKBK_LMT);
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
					if (lookbookdo != null && lookbookdo.isDisapproved()) {
						lookbookItr.remove();
					} else {
						LookbookBO lookbook = new LookbookBO();
						UserBO usr = null;
						String[] ignoreFieldsLkbk = { "tags", "lookbookstory" };

						int tot_likes = 0;
						int tot_cmmnts = 0;

						BeanUtils.copyProperties(lookbookdo, lookbook, ignoreFieldsLkbk);

						// update the SEO url
						//lookbook.setLookbook_seo_url(GlamCmn.updtSEOUrl(lookbook));

						// truncate the lookbook story
						lookbook.setLookbookstory(
								GlamCmn.truncateAfterWords(10, lookbookdo.getLookbookstory()) + "...");

						if (lookbookdo.getTags() != null && lookbookdo.getTags().size() > 0) {
							List<String> tagslist = lookbookdo.getTags();
							String[] tags = tagslist.toArray(new String[tagslist.size()]);
							lookbook.setTags(tags);
						}

						if (lookbookdo.getComments_by_users() != null) {
							tot_cmmnts = lookbookdo.getComments_by_users().size();
						}

						if (lookbookdo.getLiked_by_users() != null) {
							tot_likes = lookbookdo.getLiked_by_users().size();
						}

						// update the user info
						usr = GlamCmn.getUsrInfo(lookbookdo.getUserid());
						usr.setLocation(GlamCmn.getUsrLocn(usr));

						lookbook.setUsrinfo(usr);
						lookbook.setTotal_comments(String.valueOf(tot_cmmnts));
						lookbook.setTotal_likes(String.valueOf(tot_likes));
						if (lookbookdo.getPublisheddt() != null) {
							lookbook.setPublished_dttm_str(
									GlamCmn.printDtttmStr(lookbookdo.getPublisheddt().toString()));
						} else if (lookbookdo.getCreateddt() != null) {
							lookbook.setPublished_dttm_str(GlamCmn.printDtttmStr(lookbookdo.getCreateddt().toString()));
						}

						looks.add(lookbook);
					}
				} // end while
			}
		}

		if (looks != null && looks.size() > 0) {
			lookbookWrapper.setLookbooklist(looks);
			lookbookWrapper.setNext_page(next_cursor_str);
			lookbookWrapper.setPrev_page(prev_cursor_str);
		} else {
			mssg_usr.append(
					" You have reached last page... please click here to go back to first page to load the fresh looks ");
			lookbookWrapper.setPrev_page(prev_cursor_str);
		}

		// ftrdfashionistas = GlamCmn.getAllFeaturedFashionistas();

		List<UserDO> alluserslist = OfyService.ofy().load().type(UserDO.class).order("-signup_dttm").list();

		if (alluserslist != null && alluserslist.size() > 0) {
			// populate the new fashionistsas list
			Iterator<UserDO> newusrsItr = alluserslist.iterator();
			while (newusrsItr.hasNext()) {
				UserDO newusrdo = newusrsItr.next();
				UserBO newusr = new UserBO();

				long uid = newusrdo.getUserid().longValue();

				String[] ignorefldsUsr = { "account_email" };
				BeanUtils.copyProperties(newusrdo, newusr, ignorefldsUsr);
				newusr.setUavatar_thumb_profile(GlamCmn.getResizeImg(newusrdo, 2));
				newusr.setLocation(GlamCmn.getUsrLocn(newusr));

				int totlkbk = OfyService.ofy().load().type(UserLookbookDO.class).filter("userid", uid)
						.filter("lookbook_publish_status", "Yes").count();
				if (totlkbk > 0 && totlkbk < 3) {
					newusers.add(newusr);
				}

				if (newusers.size() > 9)
					break;
			}

			// populate the leaders
			Iterator<UserDO> leadersItr = alluserslist.iterator();
			while (leadersItr.hasNext()) {
				UserDO leaderdo = leadersItr.next();
				UserBO leader = new UserBO();

				long uid = leaderdo.getUserid().longValue();

				String[] ignorefldsUsr = { "account_email" };
				BeanUtils.copyProperties(leaderdo, leader, ignorefldsUsr);
				leader.setUavatar_thumb_profile(GlamCmn.getResizeImg(leaderdo, 2));
				leader.setLocation(GlamCmn.getUsrLocn(leader));

				int cntlkbk = OfyService.ofy().load().type(UserLookbookDO.class).filter("userid", uid)
						.filter("lookbook_publish_status", "Yes").count();
				if (cntlkbk > 2) {
					leaders.add(leader);
				}

				if (leaders.size() > 9)
					break;
			}
		}

		// logger.info("**** lookbookWrapper - " + lookbookWrapper);

		uiModel.addAttribute("lookbookwrapper", lookbookWrapper);
		// uiModel.addAttribute("ftrdfashionistas", ftrdfashionistas);
		uiModel.addAttribute("newusers", newusers);
		uiModel.addAttribute("leaders", leaders);
		uiModel.addAttribute("usrmssg", mssg_usr);

		return GlamCnst._VIEW_HOME;
	}

	@RequestMapping(value = "/toplooks", method = RequestMethod.GET)
	public String getTopLooks(HttpServletRequest req, Model uiModel) {

		String[] gtags = GlamCmn.globaltags;
		String currtag = "";

		List<String> winter2015 = new ArrayList<String>();
		winter2015.add("5715902129504256");
		winter2015.add("6613218373926912");
		winter2015.add("5076604837429248");
		winter2015.add("5732344338055168");
		winter2015.add("5709469476454400");
		winter2015.add("5692239946711040");
		winter2015.add("5684997289672704");
		winter2015.add("6026904737939456");
		winter2015.add("5671868648390656");
		winter2015.add("4678106631634944");
		winter2015.add("6240593722212352");
		winter2015.add("6224446524227584");
		winter2015.add("5669752705908736");
		winter2015.add("5699674266664960");
		winter2015.add("5749127996506112");
		winter2015.add("6291743024611328");
		winter2015.add("5665610746822656");
		winter2015.add("5759298613280768");
		winter2015.add("5756471316840448");
		winter2015.add("5716344712462336");
		winter2015.add("5651628682117120");

		List<String> guyfashion = new ArrayList<String>();
		guyfashion.add("6002436711907328");
		guyfashion.add("5632646201737216");
		guyfashion.add("5680572198289408");
		guyfashion.add("4793520925704192");
		guyfashion.add("5200761940082688");
		guyfashion.add("5759522421342208");
		guyfashion.add("4814537274425344");

		List<String> summer2015 = new ArrayList<String>();
		summer2015.add("5197935448948736");
		summer2015.add("6026712471044096");
		summer2015.add("5156171992268800");
		summer2015.add("5425615154970624");
		summer2015.add("5661725076488192");
		summer2015.add("5696101055201280");
		summer2015.add("5085604337418240");
		summer2015.add("5769975163781120");
		summer2015.add("5180730212614144");
		summer2015.add("6494073766543360");
		summer2015.add("5649648836411392");
		summer2015.add("6209964968247296");
		summer2015.add("5653625506365440");
		summer2015.add("5690599873183744");
		summer2015.add("6042968251170816");
		summer2015.add("5142898798493696");

		List<String> spring2015 = new ArrayList<String>();
		spring2015.add("5717244776546304");
		spring2015.add("5721999305342976");
		spring2015.add("5072842915840000");
		spring2015.add("5106257895620608");
		spring2015.add("5634727314718720");
		spring2015.add("5195970098757632");
		spring2015.add("5095284791050240");
		spring2015.add("5146248336113664");
		spring2015.add("5113759995527168");
		spring2015.add("5858753614184448");
		spring2015.add("5752142325350400");
		spring2015.add("5671617594130432");
		spring2015.add("5656619568332800");
		spring2015.add("5747531812175872");
		spring2015.add("5670666258874368");
		spring2015.add("5180730212614144");
		spring2015.add("6043489015955456");
		spring2015.add("6320132959764480");
		spring2015.add("5136611503243264");
		spring2015.add("6305301363949568");
		spring2015.add("5634208160546816");
		spring2015.add("5696758453633024");
		spring2015.add("6462867876347904");
		spring2015.add("5741830645743616");
		spring2015.add("5688555066097664");

		List<String> street2015 = new ArrayList<String>();
		// street2015.add("5129093498535936");
		street2015.add("6520750345289728");
		street2015.add("5187081663938560");
		street2015.add("5669608488960000");
		street2015.add("5687116654706688");
		street2015.add("5757411310698496");
		street2015.add("5967045845843968");
		street2015.add("5750790484393984");
		street2015.add("6234224755474432");
		street2015.add("5635093192245248");
		street2015.add("5129093498535936");
		street2015.add("5683848117485568");
		street2015.add("4823341688946688");
		street2015.add("5718607623356416");
		street2015.add("5744581840732160");
		street2015.add("6249539803545600");
		street2015.add("5687905720729600");
		street2015.add("5695159920492544");
		street2015.add("5945045949612032");
		street2015.add("4880777212854272");
		street2015.add("5162252189564928");
		street2015.add("5875825706532864");
		street2015.add("5109185989574656");
		street2015.add("4835365852545024");
		street2015.add("5714902542974976");
		street2015.add("5227870431477760");
		street2015.add("5461839177580544");
		street2015.add("6478812137127936");

		List<String> offbeat2015 = new ArrayList<String>();

		offbeat2015.add("5215847475838976");
		offbeat2015.add("5971397453021184");
		offbeat2015.add("5959919689793536");
		offbeat2015.add("6327698544656384");
		offbeat2015.add("4652897522417664");
		offbeat2015.add("5283636286849024");
		offbeat2015.add("5780819956203520");
		offbeat2015.add("5968011408179200");
		offbeat2015.add("5175325533143040");
		offbeat2015.add("4880256448069632");
		offbeat2015.add("6249486384889856");
		offbeat2015.add("6011296625459200");

		List<LookbookBO> featuredlooks = new ArrayList<LookbookBO>();

		String tagparam = req.getParameter("tag");
		// System.out.println("tagparam - " + tagparam);

		Iterator<String> lookstaggedItr = null;

		if (StringUtils.isEmpty(tagparam)) {
			// no tagname supplied so select a default one
			Collections.shuffle(street2015);
			lookstaggedItr = street2015.iterator();
			currtag = GlamCmn.globaltags[0];
		} else {
			if (StringUtils.equalsIgnoreCase(GlamCmn.globaltags[0], tagparam)) {
				Collections.shuffle(street2015);
				lookstaggedItr = street2015.iterator();
				currtag = GlamCmn.globaltags[0];
			} else if (StringUtils.equalsIgnoreCase(GlamCmn.globaltags[1], tagparam)) {
				Collections.shuffle(summer2015);
				lookstaggedItr = summer2015.iterator();
				currtag = GlamCmn.globaltags[1];
			} else if (StringUtils.equalsIgnoreCase(GlamCmn.globaltags[2], tagparam)) {
				Collections.shuffle(spring2015);
				lookstaggedItr = spring2015.iterator();
				currtag = GlamCmn.globaltags[2];
			} else if (StringUtils.equalsIgnoreCase(GlamCmn.globaltags[3], tagparam)) {
				Collections.shuffle(winter2015);
				lookstaggedItr = winter2015.iterator();
				currtag = GlamCmn.globaltags[3];
			} else if (StringUtils.equalsIgnoreCase(GlamCmn.globaltags[4], tagparam)) {
				Collections.shuffle(offbeat2015);
				lookstaggedItr = offbeat2015.iterator();
				currtag = GlamCmn.globaltags[3];
			} else if (StringUtils.equalsIgnoreCase(GlamCmn.globaltags[5], tagparam)) {
				Collections.shuffle(guyfashion);
				lookstaggedItr = guyfashion.iterator();
				currtag = GlamCmn.globaltags[3];
			} else {
				Collections.shuffle(street2015);
				lookstaggedItr = street2015.iterator();
				currtag = GlamCmn.globaltags[0];
			}
		}

		while (lookstaggedItr.hasNext()) {
			String lkbkidstr = lookstaggedItr.next();
			UserLookbookDO templookdo = OfyService.ofy().load().type(UserLookbookDO.class)
					.id(Long.valueOf(lkbkidstr).longValue()).get();
			// System.out.println("templookdo - " + templookdo);
			LookbookBO lookbook = new LookbookBO();
			UserBO usr = null;
			String[] ignoreFieldsLkbk = { "tags", "lookbookstory" };

			BeanUtils.copyProperties(templookdo, lookbook, ignoreFieldsLkbk);

			// update the SEO url
			//lookbook.setLookbook_seo_url(GlamCmn.updtSEOUrl(lookbook));

			// update the user info
			usr = GlamCmn.getUsrInfo(templookdo.getUserid());
			usr.setLocation(GlamCmn.getUsrLocn(usr));

			lookbook.setUsrinfo(usr);
			lookbook.setLookbookname(GlamCmn.truncateAfterWords(10, lookbook.getLookbookname()) + "...");

			featuredlooks.add(lookbook);

		}

		uiModel.addAttribute("featuredlooks", featuredlooks);
		uiModel.addAttribute("globaltags", GlamCmn.globaltags);
		uiModel.addAttribute("currtag", currtag);

		return GlamCnst._VIEW_TOPLOOKS;
	}

	@RequestMapping(value = "/comments/lookbook/list/{lookbookid}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getLookBookComments(@PathVariable("lookbookid") final long lookbookid) {
		List<CommentBO> comments = new ArrayList<CommentBO>();
		Gson gson = new Gson();
		// LookbookCommentsDO commentdo = null;
		LookbookCommentsDO cmmntdo = null;

		CommentBO comment = null;
		String avatrUrl = "";

		// retrieve all lookbook that user has created

		// List<LookbookCommentsDO> commentdos =
		// OfyService.ofy().load().type(LookbookCommentsDO.class).filter("lookbookid",
		// lookbookid).order("-commentdt").list();
		List<LookbookCommentsDO> commentlist = OfyService.ofy().load().type(LookbookCommentsDO.class)
				.filter("lookbookid", lookbookid).order("-commentdt").list();

		if (commentlist != null && commentlist.size() > 0) {
			Iterator<LookbookCommentsDO> cmmntItr = commentlist.iterator();

			while (cmmntItr.hasNext()) {
				cmmntdo = cmmntItr.next();

				if (cmmntdo != null) {
					comment = new CommentBO();
					// BeanUtils.copyProperties(cmmntdo, comment);
					comment.setCommentdt(cmmntdo.getCommentdt());
					comment.setCommentid(cmmntdo.getCommentid());
					comment.setCommenttxt(cmmntdo.getCommenttxt());
					comment.setLookbookid(cmmntdo.getLookbookid());
					comment.setUserid(cmmntdo.getUserid());

					UserDO usr = GlamCmn.getUsrFrmDB(cmmntdo.getUserid());
					if (StringUtils.isNotBlank(usr.getUavatar_is_uploaded())) {
						avatrUrl = usr.getUavatar() + GlamCmn._UAVTR_PARAM_S65;
					} else {
						avatrUrl = usr.getUavatar();
					}
					comment.setUavatar_thumb(avatrUrl);
					comment.setUsername(usr.getAccount_username());

					comments.add(comment);
				}
			}
		}

		logger.info("**** comments - " + comments);

		return gson.toJson(comments);
	}

	@RequestMapping(value = "/comments/recent/", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getRecentCommentsNew10() {
		List<CommentBO> comments = new ArrayList<CommentBO>();
		Gson gson = new Gson();
		LookbookCommentsDO cmmntdo = null;

		CommentBO comment = null;
		// String avatrUrl = "";

		List<LookbookCommentsDO> commentlist = OfyService.ofy().load().type(LookbookCommentsDO.class)
				.order("-commentdt").limit(10).list();

		if (commentlist != null && commentlist.size() > 0) {
			Iterator<LookbookCommentsDO> cmmntItr = commentlist.iterator();

			while (cmmntItr.hasNext()) {
				cmmntdo = cmmntItr.next();

				if (cmmntdo != null) {
					comment = new CommentBO();
					// BeanUtils.copyProperties(cmmntdo, comment);
					comment.setCommentdt(cmmntdo.getCommentdt());
					comment.setCommentid(cmmntdo.getCommentid());
					comment.setCommenttxt(cmmntdo.getCommenttxt());
					comment.setLookbookid(cmmntdo.getLookbookid());
					comment.setUserid(cmmntdo.getUserid());

					UserDO usr = GlamCmn.getUsrFrmDB(cmmntdo.getUserid());
					/*
					 * if(StringUtils.isNotBlank(usr.getUavatar_is_uploaded()))
					 * { avatrUrl = usr.getUavatar() + GlamCmn._UAVTR_PARAM_S65;
					 * }else { avatrUrl = usr.getUavatar(); }
					 */
					comment.setUavatar_thumb(usr.getUavatar());
					comment.setUsername(usr.getAccount_username());

					if (cmmntdo.getCommentdt() != null) {
						comment.setCommentdtstr(GlamCmn.printDtttmStr(cmmntdo.getCommentdt().toString()));
					}

					comment.setLookbookurl("/u/" + GlamCmn.getUsrnmStr(cmmntdo.getUserid()) + "/lookbook/view/"
							+ cmmntdo.getLookbookid() + "/" + GlamCmn.updtSEOUrl(cmmntdo.getLookbookid()));

					comments.add(comment);
				}
			}
		}

		logger.info("**** comments - " + comments);

		return gson.toJson(comments);
	}

	@RequestMapping(value = "/comments/u/list/{uid}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getAllCommentsInUsrLookbook(@PathVariable("uid") final long uid) {
		List<CommentBO> comments = new ArrayList<CommentBO>();
		Gson gson = new Gson();
		// LookbookCommentsDO commentdo = null;
		LookbookCommentsDO cmmntdo = null;

		CommentBO comment = null;
		String avatrUrl = "";

		// retrieve all lookbook that user has created
		List<UserLookbookDO> lookbookdolist = OfyService.ofy().load().type(UserLookbookDO.class).filter("userid", uid)
				.order("-createddt").list();
		if (lookbookdolist != null && lookbookdolist.size() > 0) {

			Iterator<UserLookbookDO> lookbookdoItr = lookbookdolist.iterator();
			if (lookbookdoItr != null) {
				while (lookbookdoItr.hasNext()) {
					UserLookbookDO templkbk = (UserLookbookDO) lookbookdoItr.next();

					List<LookbookCommentsDO> commentlist = OfyService.ofy().load().type(LookbookCommentsDO.class)
							.filter("lookbookid", templkbk.getLookbookid()).order("-commentdt").limit(10).list();

					if (commentlist != null && commentlist.size() > 0) {
						Iterator<LookbookCommentsDO> cmmntItr = commentlist.iterator();

						while (cmmntItr.hasNext()) {
							cmmntdo = cmmntItr.next();

							if (cmmntdo != null) {
								comment = new CommentBO();

								comment.setCommentdt(cmmntdo.getCommentdt());
								comment.setCommentid(cmmntdo.getCommentid());
								comment.setCommenttxt(cmmntdo.getCommenttxt());
								comment.setLookbookid(cmmntdo.getLookbookid());
								comment.setUserid(cmmntdo.getUserid());

								UserDO usr = GlamCmn.getUsrFrmDB(cmmntdo.getUserid());
								if (StringUtils.isNotBlank(usr.getUavatar_is_uploaded())) {
									avatrUrl = usr.getUavatar() + GlamCmn._UAVTR_PARAM_S65;
								} else {
									avatrUrl = usr.getUavatar();
								}
								comment.setUavatar_thumb(avatrUrl);
								comment.setUsername(usr.getAccount_username());

								comment.setLookbookurl("/u/" + GlamCmn.getUsrnmStr(cmmntdo.getUserid())
										+ "/lookbook/view/" + cmmntdo.getLookbookid() + "/"
										+ GlamCmn.updtSEOUrl(cmmntdo.getLookbookid()));

								comments.add(comment);
							}
						}
					}

					logger.info("**** comments - " + comments);
				}
			}
		}

		return gson.toJson(comments);
	}

	/************************************************************* saving *******************************************************/

	@RequestMapping(value = "/comments/lookbook/save", method = RequestMethod.POST, headers = "Content-Type=application/json", produces = "application/json")
	public @ResponseBody String saveLookBookComment(@RequestBody String cmmntjson, HttpServletRequest req) {
		// UserBO usrbo = new UserBO();
		UserDO usrdo = null;
		UserBO usrbo = null;

		LookbookCommentsDO cmmntdo = new LookbookCommentsDO();

		MssgBean mssgbn = new MssgBean();
		Gson gson = GlamCmn.getGson(new Gson());
		CommentForm commentform = null;

		// long gpoints = 0;

		// logger.info("**** searchparam populated-" + cmmntjson);
		// logger.setLevel(Level.INFO);

		try {
			commentform = GlamCmn.parseSearchJsonData(cmmntjson, gson);

		} catch (Exception ex) {
			logger.severe("**** Error while parsing the search params:: " + ex.getMessage());
		}

		// logger.info("**** Search Param Object populated-" + commentform);

		if (GlamCmn.chkLoggdInStatus(req)) {
			UsrContainer uc = GlamCmn.getLoggedInUsrDtls(req);
			usrbo = uc.getUsrbo();
			usrdo = uc.getUsrdo();

			cmmntdo.setCommentdt(new Date());
			cmmntdo.setCommenttxt(commentform.getComment_txt());
			cmmntdo.setLookbookid(commentform.getLookbookid());
			cmmntdo.setUserid(usrdo.getUserid());

			long cmntid = OfyService.ofy().save().entity(cmmntdo).now().getId();

			// update lookbook with comments
			UserLookbookDO lookbookdo = OfyService.ofy().load().type(UserLookbookDO.class)
					.id(commentform.getLookbookid()).get();
			List<Long> comments_ids = lookbookdo.getComments_by_users();
			if (comments_ids == null) {
				comments_ids = new ArrayList<Long>();
			}
			comments_ids.add(cmntid);
			lookbookdo.setComments_by_users(comments_ids);

			List<Long> comments_id_list_usrs = usrdo.getMy_lookbook_comments();
			if (comments_id_list_usrs == null) {
				comments_id_list_usrs = new ArrayList<Long>();
			}
			comments_id_list_usrs.add(cmntid);
			usrdo.setMy_lookbook_comments(comments_id_list_usrs);

			usrdo.setMy_glam_points(GlamCnst._GLAM_PTS_LKBK_CMNT_RWRD + usrdo.getMy_glam_points());

			OfyService.ofy().save().entity(lookbookdo).now();
			OfyService.ofy().save().entity(usrdo).now();

			// update the user activity
			GlamCmn.saveUsrActivity(usrdo.getUserid(), usrdo.getAccount_username(), GlamCnst._ATVY_LKBK_CMTADD,
					commentform.getLookbookid(), lookbookdo.getLookbook_seo_url(), "");

			GlamCmn.saveGlamPointsActivity(usrdo.getUserid(), usrdo.getAccount_username(), GlamCnst._ATVY_PTS_ADDED,
					GlamCnst._GLAM_PTS_LKBK_CMNT_RWRD,
					"Commenting on the look <b>" + lookbookdo.getLookbookname() + "</b>");

			// send email to user who posted this lookbook
			UserDO lookbook_posted_userdo = OfyService.ofy().load().type(UserDO.class).id(lookbookdo.getUserid()).get();
			// logger.info(" lookbook found - " +
			// lookbook_posted_userdo.getAccount_email());
			if (lookbook_posted_userdo != null && StringUtils.isNotBlank(lookbook_posted_userdo.getAccount_email())) {
				String email_txt = "Dear Fashionista, <br/>" + "<p><b>" + usrdo.getAccount_username()
						+ "</b> posted a comment in your look."
						+ " To see the comment or reply visit your look at glamfiesta.com  "
						+ "<b><a href='http://www.glamfiesta.com/fashion/" + lookbookdo.getLookbook_seo_url() 
						+ "'>" + lookbookdo.getLookbookname()
						+ "</a></b> </p>"
						+ "<p> Collaborate with us and post more looks and inspire other Fashionistas</p>"
						+ "<p> Cheers!! <br/><br/> - Editor (glamfiesta.com) <br/><br/> "
						+ "( <a href=www.glamfiesta.com/aboutus.htm>www.glamfiesta.com/aboutus.htm</a> )</b> </p>";
				logger.info("**** comment email_txt - " + email_txt);
				GlamCmn.sendEmailToUsr(lookbook_posted_userdo.getAccount_email(),
						lookbook_posted_userdo.getAccount_username(), "A new comment posted in your look !!", email_txt,
						true);
			}

			mssgbn.setMssg("Comment posted successfully...");
			mssgbn.setMssg_type("SCS");
		}

		return gson.toJson(mssgbn);
	}

	@RequestMapping(value = "/likes/lookbook/save", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String saveLookBookLikes(HttpServletRequest req) {
		UserBO likedusrbo = null;
		UserDO likedusrdo = null;

		MssgBean mssgbn = new MssgBean();
		Gson gson = new Gson();

		boolean doSaveLike = false;

		String lookbbookidstr = (String) req.getParameter("lookbookid");
		long lookbookid = Long.valueOf(lookbbookidstr).longValue();

		// logger.info("**** lookbookid populated-" + lookbbookidstr);

		if (GlamCmn.chkLoggdInStatus(req)) {
			// GlamCmn.getLoggedInUsrDtls(req, usrbo);
			UsrContainer uc = GlamCmn.getLoggedInUsrDtls(req);
			likedusrbo = uc.getUsrbo();
			likedusrdo = uc.getUsrdo();

			UserLookbookDO lookbookdo = OfyService.ofy().load().type(UserLookbookDO.class).id(lookbookid).get();

			if (lookbookdo != null) {
				// liked_lookbook_list = likedusr.getMy_liked_lookbooks();
				if (likedusrdo.getMy_liked_lookbooks() == null) {
					// user has not liked any lookbook so add this lookbook and
					// save
					doSaveLike = true;
					likedusrdo.setMy_liked_lookbooks(new ArrayList<Long>());
					likedusrdo.getMy_liked_lookbooks().add(lookbookid);
				} else {
					// user has previous liked lookbook list
					// so check if the lookbookid already exists
					if (likedusrdo.getMy_liked_lookbooks().contains(lookbookid) == false) {
						// user has not liked this lookbook yet so add it to the
						// list and mark for save
						doSaveLike = true;
						likedusrdo.getMy_liked_lookbooks().add(lookbookid);
					} else {
						mssgbn.setMssg("You already have liked this lookbook");
						mssgbn.setMssg_type("ERR");
						// logger.info("**** user has liked the lookbook
						// before");
					}
				}

				if (doSaveLike == true) {
					// user has not liked this lookbook before so save it
					// logger.info("**** user has not liked this lookbook before
					// so save it ...");

					if (lookbookdo.getLiked_by_users() == null) {
						lookbookdo.setLiked_by_users(new ArrayList<Long>());
					}

					likedusrdo.setMy_glam_points(GlamCnst._GLAM_PTS_LKBK_LIKE_RWRD + likedusrdo.getMy_glam_points());

					logger.info(" ******  saving User liked data to datastore  ****** ");
					OfyService.ofy().save().entity(likedusrdo).now();

					logger.info(" ******  saving lookbook to datastore  ****** ");
					lookbookdo.getLiked_by_users().add(likedusrdo.getUserid());
					OfyService.ofy().save().entity(lookbookdo).now();

					// update the user activity
					GlamCmn.saveUsrActivity(likedusrdo.getUserid(), likedusrdo.getAccount_username(),
							GlamCnst._ATVY_LKBK_LIKE, lookbookid, LookbookCmn.getLookBookSEOUrl(lookbookid), "");

					GlamCmn.saveGlamPointsActivity(likedusrdo.getUserid(), likedusrdo.getAccount_username(),
							GlamCnst._ATVY_PTS_ADDED, GlamCnst._GLAM_PTS_LKBK_LIKE_RWRD,
							"Liking the Look <b>" + lookbookdo.getLookbookname() + "</b>");
					// notify user with email
					UserDO lookbookowner = OfyService.ofy().load().type(UserDO.class).id(lookbookdo.getUserid()).get();
					String email_txt = "Dear " + lookbookowner.getAccount_username()
							+ ", <br/><p><b> <a href='http://www.glamfiesta.com/u/" + likedusrdo.getAccount_username()
							+ "'>" + likedusrdo.getAccount_username() + "</a></b> liked your lookbook. Connect with "
							+ likedusrdo.getAccount_username() + " and say Hi !!";
					logger.info("**** email_txt - " + email_txt);
					GlamCmn.sendEmailToUsr(lookbookowner.getAccount_email(), lookbookowner.getAccount_username(),
							lookbookowner.getAccount_username() + ", you have new notification", email_txt, true);

					mssgbn.setMssg("SUCCESS");
					mssgbn.setMssg_type("SCS");
					mssgbn.setTotal_likes(String.valueOf(lookbookdo.getLiked_by_users().size()));
				}
			}
		} else {
			mssgbn.setMssg("You need to <a href='/login'>login</a> before you follow the user...");
			mssgbn.setMssg_type("ERR");
			logger.info("**** user has not logged in ****");

		}
		return gson.toJson(mssgbn);
	}

	@RequestMapping(value = "/followers/save", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String saveLookBookFollowers(HttpServletRequest req) {
		UserDO following = null;
		UserBO followingbo = null;

		// UserFollowerDO usrfollwrdo = new UserFollowerDO();
		MssgBean mssgbn = new MssgBean();
		Gson gson = new Gson();
		List<Long> followrs_list = null;
		boolean doSaveFllw = false;

		long usr2followid = Long.valueOf(req.getParameter("u2fllw")).longValue();
		UserDO usr2follow = GlamCmn.getUsrFrmDB(usr2followid);

		// long userid = GlamCmn.getUsrId(uname);

		//logger.info("**** usr2followid populated-" + usr2followid);

		if (GlamCmn.chkLoggdInStatus(req)) {
			UsrContainer uc = GlamCmn.getLoggedInUsrDtls(req);
			followingbo = uc.getUsrbo();
			following = uc.getUsrdo();

			if (usr2follow.getUserid().longValue() == following.getUserid().longValue()) {
				// same user so skip it.
				//logger.info(" ******  You can not follow yourself  ****** ");
				mssgbn.setMssg("You can not follow yourself");
				mssgbn.setMssg_type("ERR");
			} else {
				followrs_list = usr2follow.getMy_followers();

				// check if the follower list is empty then we need to save
				if (followrs_list == null) {
					//logger.info(" ******  User has no follower so save it  ****** ");
					doSaveFllw = true;
				} else {
					// check if the logged in user already following the user
					// then do nothing
					if (usr2follow.getMy_followers().contains(following.getUserid()) == false) {
						doSaveFllw = true;
						logger.info(" ****** User has followers but not the user who sends the request ****** ");
					} else {
						logger.info("**** user is already following ");
						mssgbn.setMssg("You are already following the user");
						mssgbn.setMssg_type("ERR");
					}
				}

				if (doSaveFllw == true) {
					// user has not followed this user before so save it
					//logger.info("**** user has not followed this user before so save it ");

					if (usr2follow.getMy_followers() == null) {
						usr2follow.setMy_followers(new ArrayList<Long>());
					}

					if (following.getMe_following() == null) {
						following.setMe_following(new ArrayList<Long>());
					}

					logger.info(" ******  saving usr2follow to datastore  ****** ");
					usr2follow.getMy_followers().add(following.getUserid());
					OfyService.ofy().save().entity(usr2follow).now();

					logger.info(" ******  saving following user to datastore  ****** ");
					following.setMy_glam_points(GlamCnst._GLAM_PTS_USR_FLLW_RWRD + following.getMy_glam_points());

					following.getMe_following().add(usr2follow.getUserid());
					OfyService.ofy().save().entity(following).now();

					// update the user activity
					GlamCmn.saveUsrActivity(following.getUserid(), following.getAccount_username(),
							GlamCnst._ATVY_PRFL_FLLW, 0, "", usr2follow.getAccount_username());

					GlamCmn.saveGlamPointsActivity(following.getUserid(), following.getAccount_username(),
							GlamCnst._ATVY_PTS_ADDED, GlamCnst._GLAM_PTS_USR_FLLW_RWRD,
							" Following the user <a href=\"/" + usr2follow.getAccount_username() + "/\">"
									+ usr2follow.getAccount_username() + "</a>");
					
					// notify user with email
					String email_txt = "Dear " + usr2follow.getAccount_username()
							+ ", <br/><p><b> <a href='http://www.glamfiesta.com/u/" + following.getAccount_username()
							+ "'>" + following.getAccount_username() + "</a></b> has started following you. Connect with "
							+ following.getAccount_username() + " and say Hi !!";
					//logger.info("**** email_txt - " + email_txt);
					GlamCmn.sendEmailToUsr(usr2follow.getAccount_email(), usr2follow.getAccount_username(),
							usr2follow.getAccount_username() + ", you have new notification", email_txt, true);

					
					mssgbn.setMssg("SUCCESS");
					mssgbn.setMssg_type("SCS");
				}
			}
		} else {
			mssgbn.setMssg("You need to <a href='/login'>login</a> before you follow the user...");
			mssgbn.setMssg_type("ERR");
			logger.info("**** user has not logged in ****");

		}

		return gson.toJson(mssgbn);
	}

	/*
	 * @RequestMapping(value = "/looks/new", method = RequestMethod.GET,
	 * produces = "application/json") public @ResponseBody String
	 * getNewLookbooks(HttpServletRequest req) {
	 * 
	 * List<LookbookBO> lookbooks = new ArrayList<LookbookBO>();
	 * List<UserLookbookDO> lookbookdolist = new ArrayList<UserLookbookDO>();
	 * Gson gson = new Gson();
	 * 
	 * String next_cursor_str = ""; String next_page =
	 * req.getParameter("next_page");
	 * 
	 * LookbookBOWrapper lookbookWrapper = new LookbookBOWrapper();
	 * 
	 * Query<UserLookbookDO> queryLookbook =
	 * OfyService.ofy().load().type(UserLookbookDO.class)
	 * .filter("lookbook_publish_status", "Yes") .order("-publisheddt");
	 * if(StringUtils.isNotBlank(next_page)) {
	 * queryLookbook.startAt(Cursor.fromWebSafeString(next_page)); }
	 * queryLookbook.limit(GlamCmn._HOME_LKBK_LMT);
	 * 
	 * QueryResultIterator<UserLookbookDO> lookbookDoItr =
	 * queryLookbook.iterator(); next_cursor_str =
	 * lookbookDoItr.getCursor().toWebSafeString();
	 * 
	 * while (lookbookDoItr.hasNext()) { lookbookdolist.add((UserLookbookDO)
	 * lookbookDoItr.next()); }
	 */
	// String offsetStr = (String)req.getParameter("offset");
	// int offset = 0;
	// int tot_reslts = 0;

	// if(StringUtils.isNotBlank(offsetStr)) {
	// offset = Integer.valueOf(offsetStr).intValue();
	// }

	// tot_reslts =
	// OfyService.ofy().load().type(UserLookbookDO.class).filter("lookbook_publish_status",
	// "Yes").count();

	/*
	 * lookbookdolist =
	 * OfyService.ofy().load().type(UserLookbookDO.class).filter(
	 * "lookbook_publish_status", "Yes") .order("-publisheddt")
	 * .limit(GlamCmn._HOME_LKBK_LMT) .list();
	 */

	/*
	 * if(lookbookdolist != null && lookbookdolist.size()>0) {
	 * 
	 * for(int i=0; i<lookbookdolist.size(); i++) { UserLookbookDO lookbookdo =
	 * null; LookbookBO lookbook = new LookbookBO(); UserBO usr = null; String[]
	 * ignoreFieldsLkbk = {""}; int tot_likes = 0; int tot_cmmnts = 0;
	 * 
	 * lookbookdo = (UserLookbookDO)lookbookdolist.get(i);
	 * BeanUtils.copyProperties(lookbookdo, lookbook, ignoreFieldsLkbk);
	 * 
	 * // update the SEO url
	 * lookbook.setLookbook_seo_url(GlamCmn.updtSEOUrl(lookbook));
	 * 
	 * // update total no of comments and likings for each lookbook int
	 * tot_cmmnts = OfyService.ofy() .load() .type(LookbookCommentsDO.class)
	 * .filter("lookbookid", lookbookdo.getLookbookid()) .count();
	 * 
	 * if(lookbookdo.getComments_by_users()!=null) { tot_cmmnts =
	 * lookbookdo.getComments_by_users().size(); }
	 * 
	 * if(lookbookdo.getLiked_by_users()!=null) { tot_likes =
	 * lookbookdo.getLiked_by_users().size(); }
	 * 
	 * // update the user info usr = GlamCmn.getUsrInfo(lookbookdo.getUserid());
	 * usr.setLocation(GlamCmn.getUsrLocn(usr));
	 * 
	 * lookbook.setUsrinfo(usr);
	 * lookbook.setTotal_comments(String.valueOf(tot_cmmnts));
	 * lookbook.setTotal_likes(String.valueOf(tot_likes));
	 * if(lookbookdo.getPublisheddt() != null) {
	 * lookbook.setPublished_dttm_str(GlamCmn.printDtttmStr(lookbookdo.
	 * getPublisheddt().toString())); }else if(lookbookdo.getCreateddt()!=null)
	 * { lookbook.setPublished_dttm_str(GlamCmn.printDtttmStr(lookbookdo.
	 * getCreateddt().toString())); }
	 * 
	 * lookbooks.add(lookbook); } }
	 * 
	 * if(lookbooks!=null && lookbooks.size()>0) {
	 * lookbookWrapper.setLookbooklist(lookbooks);
	 * lookbookWrapper.setNext_page(next_cursor_str); }
	 * 
	 * logger.info("**** lookbookWrapper - " + lookbookWrapper);
	 * 
	 * return gson.toJson(lookbooks); }
	 */

}
