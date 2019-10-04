package com.app.beta.glam.fiesta.db;

import java.io.Serializable;

import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Unindex;

@Cache
@Entity
public class TopStreetStyleDO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id private Long id;

	@Unindex private Long lkbk_id;

	@Index private String lkbk_seo_url;

	@Unindex private String lkbk_nm;
	@Unindex private String lkbk_imgstr;

	@Unindex private String lkbk_username;
	@Unindex private String lkbk_uavatar;
	@Unindex private String lkbk_user_locn;

	@Unindex private String note_from_editor;

	@Unindex private String lookbookseason;
	
	public Long getLkbk_id() {
		return lkbk_id;
	}

	public void setLkbk_id(Long lkbk_id) {
		this.lkbk_id = lkbk_id;
	}


	public String getLkbk_nm() {
		return lkbk_nm;
	}


	public void setLkbk_nm(String lkbk_nm) {
		this.lkbk_nm = lkbk_nm;
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


	public String getNote_from_editor() {
		return note_from_editor;
	}


	public void setNote_from_editor(String note_from_editor) {
		this.note_from_editor = note_from_editor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLkbk_user_locn() {
		return lkbk_user_locn;
	}

	public void setLkbk_user_locn(String lkbk_user_locn) {
		this.lkbk_user_locn = lkbk_user_locn;
	}

	public String getLookbookseason() {
		return lookbookseason;
	}

	public void setLookbookseason(String lookbookseason) {
		this.lookbookseason = lookbookseason;
	}

}
