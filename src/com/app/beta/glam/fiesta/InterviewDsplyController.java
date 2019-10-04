package com.app.beta.glam.fiesta;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.beta.glam.fiesta.cmn.GlamCnst;
import com.app.beta.glam.fiesta.cmn.LookbookCmn;
import com.app.beta.glam.fiesta.cmn.OfyService;
import com.app.beta.glam.fiesta.db.InterviewDO;
import com.app.beta.glam.fiesta.model.LookbookBO;

@Controller
public class InterviewDsplyController {

	@RequestMapping(value = "/interviews", method = RequestMethod.GET)
	public String intrvwListPg(HttpServletRequest req, Model uiModel) {

		List<InterviewDO> intervwlist = OfyService.ofy().load().type(InterviewDO.class).list();
		List<LookbookBO> rndmList = LookbookCmn.genRandomLooks();

		uiModel.addAttribute("intervwlist", intervwlist);
		uiModel.addAttribute("randomlooks", rndmList);

		return GlamCnst._VIEW_INTRVWLIST_PG;
	}

	@RequestMapping(value = "/interviews/{intrvw_seo_url}", method = RequestMethod.GET)
	public String showIntrvw(@PathVariable("intrvw_seo_url") String intrvw_seo_url,
			HttpServletRequest req, Model uiModel) {
		//System.out.println("intrvw_seo_url-" + intrvw_seo_url);
		InterviewDO intrvw = null;
		
		List<InterviewDO> srchIntrvwList = OfyService.ofy().load().type(InterviewDO.class)
													 .filter("intrvw_seo_title", intrvw_seo_url+".htm").list();
		
		List<InterviewDO> intervwlist = OfyService.ofy().load().type(InterviewDO.class).list();
		List<LookbookBO> rndmList = LookbookCmn.genRandomLooks();
		
		if(srchIntrvwList!=null && srchIntrvwList.size()>0) {
			intrvw = (InterviewDO)srchIntrvwList.get(0);
			//System.out.println("interview found-" + intrvw.getIntrvwtitle());
		}
		uiModel.addAttribute("intrvw", intrvw);
		uiModel.addAttribute("intervwlist", intervwlist);
		uiModel.addAttribute("randomlooks", rndmList);
		
		return GlamCnst._VIEW_INTRVW;
	}
}
