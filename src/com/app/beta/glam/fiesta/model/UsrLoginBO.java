package com.app.beta.glam.fiesta.model;

import java.io.Serializable;

public class UsrLoginBO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long userid;
	private String account_username;
	private String first_name;
	private String last_name;
	private String uavatar;
	private String account_type;
	private boolean is_account_active;
	
	private boolean loginstatus;
	
	
	@Override
	public String toString() {
		
		return "UsrLoginBO :- userid [ " + userid + "] account_type [" + account_type + "] account_username [" + account_username + "first_name[" + first_name
				+ "] last_name [" + last_name + "] uavatar [" + uavatar + "] loginstatus [" + loginstatus + "] " ;
	}
	
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public String getAccount_username() {
		return account_username;
	}
	public void setAccount_username(String account_username) {
		this.account_username = account_username;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getUavatar() {
		return uavatar;
	}
	public void setUavatar(String uavatar) {
		this.uavatar = uavatar;
	}
	public String getAccount_type() {
		return account_type;
	}
	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}
	public boolean isLoginstatus() {
		return loginstatus;
	}
	public void setLoginstatus(boolean loginstatus) {
		this.loginstatus = loginstatus;
	}
	public boolean isIs_account_active() {
		return is_account_active;
	}
	public void setIs_account_active(boolean is_account_active) {
		this.is_account_active = is_account_active;
	}
}
