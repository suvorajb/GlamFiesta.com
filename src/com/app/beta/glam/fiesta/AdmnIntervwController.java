package com.app.beta.glam.fiesta;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.beta.glam.fiesta.cmn.GlamCmn;
import com.app.beta.glam.fiesta.cmn.OfyService;
import com.app.beta.glam.fiesta.db.InterviewDO;
import com.app.beta.glam.fiesta.db.UserDO;
import com.app.beta.glam.fiesta.form.InterviewForm;
import com.app.beta.glam.fiesta.model.UserBO;
import com.app.beta.glam.fiesta.model.UsrContainer;

@Controller
public class AdmnIntervwController {

	private static final Logger logger = Logger.getLogger(FashionistasController.class.getCanonicalName());

	@RequestMapping(value = "/mnda/intrvw", method = RequestMethod.GET)
	public String goAdmnIntrvw(HttpServletRequest req, Model uiModel) {
		UserBO usrbo = null;
		UserDO usrdo = null;
		InterviewForm interviewform = new InterviewForm();

		if (GlamCmn.chkLoggdInStatus(req)) {
			UsrContainer uc = GlamCmn.getLoggedInUsrDtls(req);
			usrbo = uc.getUsrbo();
			usrdo = uc.getUsrdo();

			uiModel.addAttribute("usrbo", usrbo);
			uiModel.addAttribute("interviewform", interviewform);

			if (StringUtils.equalsIgnoreCase(usrdo.getAccount_username(), "suvoraj239")) {
				return "admn_mng_intrvw";
			}
		}

		return "redirect:/dashboard";
	}

	@RequestMapping(value = "/mnda/intrvw", method = RequestMethod.POST)
	public String doMngIntrvw(@ModelAttribute InterviewForm interviewform, BindingResult result, Model uiModel,
			HttpServletRequest req) {

		UserBO usrbo = null;
		UserDO usrdo = null;

		InterviewDO intrvwdo = new InterviewDO();
		StringBuilder succsmssg = new StringBuilder();
		UserDO udo = null;

		if (GlamCmn.chkLoggdInStatus(req)) {
			UsrContainer uc = GlamCmn.getLoggedInUsrDtls(req);
			usrbo = uc.getUsrbo();
			usrdo = uc.getUsrdo();

			if (StringUtils.equalsIgnoreCase(usrdo.getAccount_username(), "suvoraj239")) {

				BeanUtils.copyProperties(interviewform, intrvwdo);
				String seoUrl = GlamCmn.genSeoUrlByStrTitle(interviewform.getIntrvwtitle());
				intrvwdo.setIntrvw_seo_title(seoUrl);
				OfyService.ofy().save().entity(intrvwdo).now();

				succsmssg.append("Interview saved successfully");
			}
		}

		uiModel.addAttribute("succsmssg", succsmssg);
		uiModel.addAttribute("usrbo", usrbo);

		return "redirect:/dashboard";
	}
}
