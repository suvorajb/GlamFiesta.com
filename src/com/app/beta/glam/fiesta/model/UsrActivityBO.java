package com.app.beta.glam.fiesta.model;

import java.io.Serializable;

public class UsrActivityBO implements Serializable {
		
	private Long userid;
	private String username;
	private String useravatar;
	
	private String actvtystr;
	private String actvtyurl;
	private String imgurl1;
	private String imgurl2;
	private String imgurl3;
	
	private String actvtydttm; 
	
	private String actvtytype;
	
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public String getActvtystr() {
		return actvtystr;
	}
	public void setActvtystr(String actvtystr) {
		this.actvtystr = actvtystr;
	}
	public String getActvtyurl() {
		return actvtyurl;
	}
	public void setActvtyurl(String actvtyurl) {
		this.actvtyurl = actvtyurl;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getActvtydttm() {
		return actvtydttm;
	}
	public void setActvtydttm(String actvtydttm) {
		this.actvtydttm = actvtydttm;
	}
	public String getActvtytype() {
		return actvtytype;
	}
	public void setActvtytype(String actvtytype) {
		this.actvtytype = actvtytype;
	}
	public String getUseravatar() {
		return useravatar;
	}
	public void setUseravatar(String useravatar) {
		this.useravatar = useravatar;
	}
	public String getImgurl1() {
		return imgurl1;
	}
	public void setImgurl1(String imgurl1) {
		this.imgurl1 = imgurl1;
	}
	public String getImgurl2() {
		return imgurl2;
	}
	public void setImgurl2(String imgurl2) {
		this.imgurl2 = imgurl2;
	}
	public String getImgurl3() {
		return imgurl3;
	}
	public void setImgurl3(String imgurl3) {
		this.imgurl3 = imgurl3;
	}

}
