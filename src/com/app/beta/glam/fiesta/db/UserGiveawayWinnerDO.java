package com.app.beta.glam.fiesta.db;

import java.io.Serializable;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Unindex;

@Entity
public class UserGiveawayWinnerDO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id private Long giveawayid;
	
	@Unindex private Long userid;
	@Unindex private String username;
	@Unindex private String uavatar;
	
	@Unindex private String date_of_winning;
	@Unindex private String giveaway_rewards_amnt;
	
	@Unindex private String lookbook_winning_url1;
	@Unindex private String lookbook_winning_url2;
	@Unindex private String lookbook_winning_url3;
	
	
	public Long getGiveawayid() {
		return giveawayid;
	}
	public void setGiveawayid(Long giveawayid) {
		this.giveawayid = giveawayid;
	}
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public String getDate_of_winning() {
		return date_of_winning;
	}
	public void setDate_of_winning(String date_of_winning) {
		this.date_of_winning = date_of_winning;
	}
	public String getGiveaway_rewards_amnt() {
		return giveaway_rewards_amnt;
	}
	public void setGiveaway_rewards_amnt(String giveaway_rewards_amnt) {
		this.giveaway_rewards_amnt = giveaway_rewards_amnt;
	}
	public String getLookbook_winning_url1() {
		return lookbook_winning_url1;
	}
	public void setLookbook_winning_url1(String lookbook_winning_url1) {
		this.lookbook_winning_url1 = lookbook_winning_url1;
	}
	public String getLookbook_winning_url2() {
		return lookbook_winning_url2;
	}
	public void setLookbook_winning_url2(String lookbook_winning_url2) {
		this.lookbook_winning_url2 = lookbook_winning_url2;
	}
	public String getLookbook_winning_url3() {
		return lookbook_winning_url3;
	}
	public void setLookbook_winning_url3(String lookbook_winning_url3) {
		this.lookbook_winning_url3 = lookbook_winning_url3;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUavatar() {
		return uavatar;
	}
	public void setUavatar(String uavatar) {
		this.uavatar = uavatar;
	}
}
