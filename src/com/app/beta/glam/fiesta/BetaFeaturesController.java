package com.app.beta.glam.fiesta;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.beta.glam.fiesta.cmn.GlamCmn;
import com.app.beta.glam.fiesta.cmn.GlamCnst;
import com.app.beta.glam.fiesta.cmn.OfyService;
import com.app.beta.glam.fiesta.db.TopLooksDO;
import com.app.beta.glam.fiesta.model.LookbookBO;

@Controller
public class BetaFeaturesController {

	@RequestMapping(value = "/beta/toplooks", method = RequestMethod.GET)
	public String getTopLooks(HttpServletRequest req, Model uiModel) {

		String[] gtags = GlamCmn.globaltags;
		String currtag = "";

		List<LookbookBO> toplooks = new ArrayList<LookbookBO>();
		List<TopLooksDO> toplooksdo = null;

		String tagparam = req.getParameter("tag");

		if (StringUtils.isNotEmpty(tagparam)) {
			toplooksdo = OfyService.ofy().load().type(TopLooksDO.class).filter("glbl_tagname", tagparam).list();
		}

		uiModel.addAttribute("toplooks", toplooksdo);
		uiModel.addAttribute("globaltags", GlamCmn.globaltags);
		uiModel.addAttribute("currtag", currtag);

		return GlamCnst._VIEW_TOPLOOKS;
	}
	
	

}
