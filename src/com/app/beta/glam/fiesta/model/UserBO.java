package com.app.beta.glam.fiesta.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;


public class UserBO implements Serializable {
	
	private static final long serialVersionUID = 4723791931636786554L;

	private Long userid;

	private String account_type;
	private boolean is_account_active;

	// account information
	private String account_email;	
	private String account_username;	
//	private String account_password;

	// personal information
	private String first_name;
	private String last_name;
	private String birth_year;
	private String gender;
	private String aboutme;
	private String citynm;
	private String statenm;	
	private String countrynm;
	private String location;
	private String fcategory;
	
	// user avatar
	private String uavatar;
	private String uavatar_thumb_profile;
	
	//user social settings
	private String twitter_ac;
	private String facebook_ac;
	private String google_plus_ac;
	private String youtube_ac;
	private String linkedin_ac;
	private String pinterest_ac;
	private String instagram_ac;
	private String tumblr_ac;
	private String website_blog_url;
	
	// GlamFiesta information
	private List<LookbookBO> lookbookList = Collections.emptyList();
	private List<UserBO> followerList = Collections.emptyList();
	private List<UsrActivityBO> activities = Collections.emptyList();
	
	private int total_lookbooks;
	private String total_glampoints;
	private String total_followers;
	private String total_me_following;
	
	@Override
	public String toString() {
		
		return "UserBO :- uid [" + userid + "] account_type [" + account_type + "] account_email [" + account_email + "] account_username [" + account_username + "first_name[" + first_name
				+ "] last_name [" + last_name + "] birth_year[" + birth_year + "] gender [" + gender + "] " ;
	}
	
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getAccount_type() {
		return account_type;
	}

	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}

	public boolean isIs_account_active() {
		return is_account_active;
	}

	public void setIs_account_active(boolean is_account_active) {
		this.is_account_active = is_account_active;
	}

	public String getAccount_email() {
		return account_email;
	}

	public void setAccount_email(String account_email) {
		this.account_email = account_email;
	}

	public String getAccount_username() {
		return account_username;
	}

	public void setAccount_username(String account_username) {
		this.account_username = account_username;
	}

/*	public String getAccount_password() {
		return account_password;
	}

	public void setAccount_password(String account_password) {
		this.account_password = account_password;
	}
*/

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

	public String getBirth_year() {
		return birth_year;
	}

	public void setBirth_year(String birth_year) {
		this.birth_year = birth_year;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCitynm() {
		return citynm;
	}

	public void setCitynm(String citynm) {
		this.citynm = citynm;
	}

	public String getCountrynm() {
		return countrynm;
	}

	public void setCountrynm(String countrynm) {
		this.countrynm = countrynm;
	}

	public List<LookbookBO> getLookbookList() {
		return lookbookList;
	}

	public void setLookbookList(List<LookbookBO> lookbookList) {
		this.lookbookList = lookbookList;
	}

	public List<UserBO> getFollowerList() {
		return followerList;
	}

	public void setFollowerList(List<UserBO> followerList) {
		this.followerList = followerList;
	}

	public String getStatenm() {
		return statenm;
	}

	public void setStatenm(String statenm) {
		this.statenm = statenm;
	}

	public String getUavatar() {
		return uavatar;
	}

	public void setUavatar(String uavatar) {
		this.uavatar = uavatar;
	}

	public String getTwitter_ac() {
		return twitter_ac;
	}

	public void setTwitter_ac(String twitter_ac) {
		this.twitter_ac = twitter_ac;
	}

	public String getFacebook_ac() {
		return facebook_ac;
	}

	public void setFacebook_ac(String facebook_ac) {
		this.facebook_ac = facebook_ac;
	}

	public String getGoogle_plus_ac() {
		return google_plus_ac;
	}

	public void setGoogle_plus_ac(String google_plus_ac) {
		this.google_plus_ac = google_plus_ac;
	}

	public String getYoutube_ac() {
		return youtube_ac;
	}

	public void setYoutube_ac(String youtube_ac) {
		this.youtube_ac = youtube_ac;
	}

	public String getLinkedin_ac() {
		return linkedin_ac;
	}

	public void setLinkedin_ac(String linkedin_ac) {
		this.linkedin_ac = linkedin_ac;
	}

	public String getPinterest_ac() {
		return pinterest_ac;
	}

	public void setPinterest_ac(String pinterest_ac) {
		this.pinterest_ac = pinterest_ac;
	}

	public String getInstagram_ac() {
		return instagram_ac;
	}

	public void setInstagram_ac(String instagram_ac) {
		this.instagram_ac = instagram_ac;
	}

	public String getTumblr_ac() {
		return tumblr_ac;
	}

	public void setTumblr_ac(String tumblr_ac) {
		this.tumblr_ac = tumblr_ac;
	}

	public String getWebsite_blog_url() {
		return website_blog_url;
	}

	public void setWebsite_blog_url(String website_blog_url) {
		this.website_blog_url = website_blog_url;
	}

	public String getAboutme() {
		return aboutme;
	}

	public void setAboutme(String aboutme) {
		this.aboutme = aboutme;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getUavatar_thumb_profile() {
		return uavatar_thumb_profile;
	}

	public void setUavatar_thumb_profile(String uavatar_thumb_profile) {
		this.uavatar_thumb_profile = uavatar_thumb_profile;
	}

	public int getTotal_lookbooks() {
		return total_lookbooks;
	}

	public void setTotal_lookbooks(int total_lookbooks) {
		this.total_lookbooks = total_lookbooks;
	}

	public List<UsrActivityBO> getActivities() {
		return activities;
	}

	public void setActivities(List<UsrActivityBO> activities) {
		this.activities = activities;
	}

	public String getTotal_glampoints() {
		return total_glampoints;
	}

	public void setTotal_glampoints(String total_glampoints) {
		this.total_glampoints = total_glampoints;
	}

	public String getTotal_followers() {
		return total_followers;
	}

	public void setTotal_followers(String total_followers) {
		this.total_followers = total_followers;
	}

	public String getTotal_me_following() {
		return total_me_following;
	}

	public void setTotal_me_following(String total_me_following) {
		this.total_me_following = total_me_following;
	}

	public String getFcategory() {
		return fcategory;
	}

	public void setFcategory(String fcategory) {
		this.fcategory = fcategory;
	}

}
