package com.app.beta.glam.fiesta.db;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Unindex;

@Entity
public class LoginHistoryDO implements Serializable {

	private static final long serialVersionUID = -6927130675811294261L;

	@Id
	private Long loginhistoryid;
	
	@Index
	private Long userid;
	
	@Unindex
	private List<String> login_histry_list;
	
	

	public Long getLoginhistoryid() {
		return loginhistoryid;
	}

	public void setLoginhistoryid(Long loginhistoryid) {
		this.loginhistoryid = loginhistoryid;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public List<String> getLogin_histry_list() {
		return login_histry_list;
	}

	public void setLogin_histry_list(List<String> login_histry_list) {
		this.login_histry_list = login_histry_list;
	}

}
