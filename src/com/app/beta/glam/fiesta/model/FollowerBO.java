package com.app.beta.glam.fiesta.model;

import java.util.Date;

public class FollowerBO {
	
	private long userid;
	private String username;
	private String uavatar_thumb;

	private long follower_userid;
	private String follower_username;
	
	private Date followd_dt;

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUavatar_thumb() {
		return uavatar_thumb;
	}

	public void setUavatar_thumb(String uavatar_thumb) {
		this.uavatar_thumb = uavatar_thumb;
	}

	public long getFollower_userid() {
		return follower_userid;
	}

	public void setFollower_userid(long follower_userid) {
		this.follower_userid = follower_userid;
	}

	public String getFollower_username() {
		return follower_username;
	}

	public void setFollower_username(String follower_username) {
		this.follower_username = follower_username;
	}

	public Date getFollowd_dt() {
		return followd_dt;
	}

	public void setFollowd_dt(Date followd_dt) {
		this.followd_dt = followd_dt;
	}
	
	
}
