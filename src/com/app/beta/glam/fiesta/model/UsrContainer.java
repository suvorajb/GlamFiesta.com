package com.app.beta.glam.fiesta.model;

import java.io.Serializable;

import com.app.beta.glam.fiesta.db.UserDO;

public class UsrContainer  implements Serializable {
	
	UserDO usrdo = null;
	UserBO usrbo = null;
	
	public UserDO getUsrdo() {
		return usrdo;
	}
	public void setUsrdo(UserDO usrdo) {
		this.usrdo = usrdo;
	}
	public UserBO getUsrbo() {
		return usrbo;
	}
	public void setUsrbo(UserBO usrbo) {
		this.usrbo = usrbo;
	}
	
	

}
