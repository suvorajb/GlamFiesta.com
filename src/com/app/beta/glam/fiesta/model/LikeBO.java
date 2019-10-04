package com.app.beta.glam.fiesta.model;

import java.util.Date;

public class LikeBO {
	
	private Long likeid;
	private Long lookbookid;
	private Long likedby_userid;
	private String likedby_username;
	private Date likeddt;
	
	public Long getLikeid() {
		return likeid;
	}
	public void setLikeid(Long likeid) {
		this.likeid = likeid;
	}
	public Long getLookbookid() {
		return lookbookid;
	}
	public void setLookbookid(Long lookbookid) {
		this.lookbookid = lookbookid;
	}
	public Long getLikedby_userid() {
		return likedby_userid;
	}
	public void setLikedby_userid(Long likedby_userid) {
		this.likedby_userid = likedby_userid;
	}
	public String getLikedby_username() {
		return likedby_username;
	}
	public void setLikedby_username(String likedby_username) {
		this.likedby_username = likedby_username;
	}
	public Date getLikeddt() {
		return likeddt;
	}
	public void setLikeddt(Date likeddt) {
		this.likeddt = likeddt;
	}

}
