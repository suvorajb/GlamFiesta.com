package com.app.beta.glam.fiesta.db;

import java.io.Serializable;
import java.util.Date;

import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Unindex;

@Entity
public class UserLoginResetDO  implements Serializable {

	private static final long serialVersionUID = 5834539649563788833L;
	
	@Id	private Long loginresetid;
	@Index private Long userid;
	@Unindex private String resetkey;
	@Unindex private Date resetdt;
	
	public Long getLoginresetid() {
		return loginresetid;
	}
	public void setLoginresetid(Long loginresetid) {
		this.loginresetid = loginresetid;
	}
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public String getResetkey() {
		return resetkey;
	}
	public void setResetkey(String resetkey) {
		this.resetkey = resetkey;
	}
	public Date getResetdt() {
		return resetdt;
	}
	public void setResetdt(Date resetdt) {
		this.resetdt = resetdt;
	}
}
