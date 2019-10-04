package com.app.beta.glam.fiesta;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.beta.glam.fiesta.cmn.GlamCmn;
import com.app.beta.glam.fiesta.cmn.GlamCnst;
import com.app.beta.glam.fiesta.cmn.OfyService;
import com.app.beta.glam.fiesta.db.LookbookCommentsDO;
import com.app.beta.glam.fiesta.db.UserDO;
import com.app.beta.glam.fiesta.model.CommentBO;
import com.app.beta.glam.fiesta.model.UserBO;
import com.app.beta.glam.fiesta.model.UsrContainer;

@Controller
public class DshBrdCommentsController {
	
	private static final Logger logger = Logger.getLogger(DshBrdCommentsController.class.getCanonicalName());

	@RequestMapping(value = "/dashboard/comments/manage", method = RequestMethod.GET)
	public String goMngCommentsPage(HttpServletRequest req, Model uiModel) {
		UserBO usrbo = null;
		UserDO usrdo = null;
		String avatrUrl = "";
		List<CommentBO> comments = new ArrayList<CommentBO>();
		
		if (GlamCmn.chkLoggdInStatus(req)) {
			UsrContainer uc = GlamCmn.getLoggedInUsrDtls(req);
			usrbo = uc.getUsrbo();
			usrdo = uc.getUsrdo();

			uiModel.addAttribute("usrbo", usrbo);
			
			List<Long> my_cmmnts_id_list = usrdo.getMy_lookbook_comments();
			logger.info("**** my_cmmnts_id_list - " + my_cmmnts_id_list);
			
			if (my_cmmnts_id_list != null && my_cmmnts_id_list.size() > 0) {
				Iterator<Long> cmmntIdItr = my_cmmnts_id_list.iterator();

				while (cmmntIdItr.hasNext()) {
					Long cmmntid = cmmntIdItr.next();
					LookbookCommentsDO cmmntdo = OfyService.ofy().load().type(LookbookCommentsDO.class).id(cmmntid.longValue()).get();

					if (cmmntdo != null) {
						CommentBO comment = new CommentBO();
						//BeanUtils.copyProperties(cmmntdo, comment);
						comment.setCommentdt(cmmntdo.getCommentdt());
						comment.setCommentid(cmmntdo.getCommentid());
						comment.setCommenttxt(cmmntdo.getCommenttxt());
						if(cmmntdo.getLookbookid()!=null) {
							//UserLookbookDO lkbkdo = OfyService.ofy().load().type(UserLookbookDO.class).id(cmmntdo.getLookbookid()).get();
							comment.setLookbookid(cmmntdo.getLookbookid());
							//comment.setLookbooknm(GlamCmn.updtSEOUrl(GlamCmn.getLookBookTitle(cmmntdo.getLookbookid())));
						}
												
						comments.add(comment);
					}
				}
			}
			
			logger.info("**** comments - " + comments);
			
			uiModel.addAttribute("usrbo", usrbo);
			uiModel.addAttribute("comments", comments);
			
			return GlamCnst._VIEW_DSHBRD_CMMNTS_MNG;
		} else {
			return "redirect:/login";
		}
	}
	
}
