package com.app.beta.glam.fiesta.cmn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import com.app.beta.glam.fiesta.db.UserDO;
import com.app.beta.glam.fiesta.model.UserBO;

public class FashionistasCmn {

	private static final Logger logger = Logger.getLogger(FashionistasCmn.class.getCanonicalName());

	public static List<UserBO> getRandomFashionistasBO(int totFashonistas, UserBO ubo) {

		List<UserBO> fashionistas = new ArrayList<UserBO>();

		List<Date> dtList = GlamCmn.genRandomDate();

		if (dtList != null && dtList.size() > 1) {
			List<UserDO> usrdolist = OfyService.ofy().load().type(UserDO.class).filter("signup_dttm <", dtList.get(0))
					.filter("signup_dttm >", dtList.get(1)).limit(100).list();

			if (usrdolist != null && usrdolist.size() > 0) {
				Collections.shuffle(usrdolist);
				int i = 1;
				for (UserDO usrdo : usrdolist) {
					UserBO fashionista = new UserBO();

					String[] ignorefldsUsr = { "account_email" };
					BeanUtils.copyProperties(usrdo, fashionista, ignorefldsUsr);

					fashionista.setLocation(GlamCmn.getUsrLocn(fashionista));
					fashionista.setUavatar_thumb_profile(GlamCmn.getResizeImg(usrdo, 2));
					if (ubo != null
							&& StringUtils.equalsIgnoreCase(fashionista.getAccount_email(), ubo.getAccount_email())) {
						logger.info("**** match found so not adding..." + ubo.getAccount_email());
					} else {
						fashionistas.add(fashionista);
					}
					
					if (i == totFashonistas)
						break;
					i++;
				}
			}
		}

		return fashionistas;

	}

	public static List<UserDO> getRandomFashionistasDO() {
		List<UserDO> usrdolist = null;

		List<Date> dtList = GlamCmn.genRandomDate();

		if (dtList != null && dtList.size() > 2) {

			usrdolist = OfyService.ofy().load().type(UserDO.class).filter("signup_dttm <", dtList.get(0))
					.filter("signup_dttm >", dtList.get(1)).limit(50).list();

			Collections.shuffle(usrdolist);
		}
		return usrdolist;
	}

}
