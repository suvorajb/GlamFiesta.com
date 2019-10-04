package com.app.beta.glam.fiesta.db;

import java.io.Serializable;
import java.util.Date;

import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Unindex;

@Entity
public class UserFollowerDO  implements Serializable {

	private static final long serialVersionUID = 4331676605357275209L;

	@Id private Long followid;
	
	@Unindex private Long userid;
	@Unindex private String username;
	
	@Unindex private Long following_userid;
	@Unindex private String following_username;
	
	@Unindex private Date followd_dt;

	public Long getFollowid() {
		return followid;
	}

	public void setFollowid(Long followid) {
		this.followid = followid;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getFollowing_userid() {
		return following_userid;
	}

	public void setFollowing_userid(Long following_userid) {
		this.following_userid = following_userid;
	}

	public String getFollowing_username() {
		return following_username;
	}

	public void setFollowing_username(String following_username) {
		this.following_username = following_username;
	}

	public Date getFollowd_dt() {
		return followd_dt;
	}

	public void setFollowd_dt(Date followd_dt) {
		this.followd_dt = followd_dt;
	}
	
}
