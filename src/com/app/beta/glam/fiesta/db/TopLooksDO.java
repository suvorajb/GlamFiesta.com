package com.app.beta.glam.fiesta.db;

import java.io.Serializable;
import java.util.Date;

import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Unindex;

@Cache
@Entity
public class TopLooksDO implements Serializable {
private static final long serialVersionUID = 1L;
	
	@Id private Long id;
	
	@Index private String glbl_tagname;
	@Unindex private Date lkbk_add_dttm;
	
	@Unindex private Long lkbk_userid;
	@Unindex private String lkbk_username;
	@Unindex private String lkbk_uavatar;
	
	@Unindex private String lkbk_nm;
	@Unindex private Long lkbk_id;
	
	@Unindex private String lkbk_seo_url;
	@Unindex private String lkbk_imgstr;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getGlbl_tagname() {
		return glbl_tagname;
	}
	public void setGlbl_tagname(String glbl_tagname) {
		this.glbl_tagname = glbl_tagname;
	}
	public Date getLkbk_add_dttm() {
		return lkbk_add_dttm;
	}
	public void setLkbk_add_dttm(Date lkbk_add_dttm) {
		this.lkbk_add_dttm = lkbk_add_dttm;
	}
	public Long getLkbk_userid() {
		return lkbk_userid;
	}
	public void setLkbk_userid(Long lkbk_userid) {
		this.lkbk_userid = lkbk_userid;
	}
	public String getLkbk_username() {
		return lkbk_username;
	}
	public void setLkbk_username(String lkbk_username) {
		this.lkbk_username = lkbk_username;
	}
	public String getLkbk_uavatar() {
		return lkbk_uavatar;
	}
	public void setLkbk_uavatar(String lkbk_uavatar) {
		this.lkbk_uavatar = lkbk_uavatar;
	}
	public String getLkbk_nm() {
		return lkbk_nm;
	}
	public void setLkbk_nm(String lkbk_nm) {
		this.lkbk_nm = lkbk_nm;
	}
	public Long getLkbk_id() {
		return lkbk_id;
	}
	public void setLkbk_id(Long lkbk_id) {
		this.lkbk_id = lkbk_id;
	}
	public String getLkbk_seo_url() {
		return lkbk_seo_url;
	}
	public void setLkbk_seo_url(String lkbk_seo_url) {
		this.lkbk_seo_url = lkbk_seo_url;
	}
	public String getLkbk_imgstr() {
		return lkbk_imgstr;
	}
	public void setLkbk_imgstr(String lkbk_imgstr) {
		this.lkbk_imgstr = lkbk_imgstr;
	}
}
