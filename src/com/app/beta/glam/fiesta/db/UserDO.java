package com.app.beta.glam.fiesta.db;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Unindex;

@Entity
public class UserDO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Long userid;
	
	@Index
	private Long facebook_usrid;

	@Index
	private String account_type;

	// account information
	@Index
	private String account_email;	
	@Index
	private String account_username;	
	@Index
	private String account_password;
	@Unindex
	private String account_pwd_salt;
	@Index
	private boolean is_account_active;
	@Index
	private String signup_datetime;
	@Index
	private Date signup_dttm;
	@Unindex
	private String signup_ip_addr;

	// personal information
	@Unindex
	private String first_name;
	@Unindex
	private String last_name;
	@Unindex
	private String birth_year;
	@Index
	private String gender;
	@Index
	private String fcategory;
	@Unindex
	private String aboutme;
	@Index
	private String citynm;
	@Unindex
	private String statenm;
	@Unindex
	private String countrynm;
	
	// user avatar
	@Unindex
	private String uavatar;
	@Unindex
	private String uavatar_is_uploaded;
	
	//user social settings
	@Unindex
	private String twitter_ac;
	@Unindex
	private String facebook_ac;
	@Unindex
	private String google_plus_ac;
	@Index
	private String youtube_ac;
	@Unindex
	private String linkedin_ac;
	@Unindex
	private String pinterest_ac;
	@Unindex
	private String instagram_ac;
	@Unindex
	private String tumblr_ac;
	@Unindex
	private String website_blog_url;
	
	@Unindex
	private List<Long> my_followers;
	@Unindex
	private List<Long> me_following;
	@Unindex
	private List<Long> my_liked_lookbooks;
	@Unindex
	private List<Long> my_lookbook_comments;
	@Index
	private long my_glam_points;
	
	
	@Override
	public String toString() {
		
		return "USERDO :- userid [ " + userid + "] account_type [" + account_type + "] account_email [" + account_email + "] account_username [" + account_username + "first_name[" + first_name
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

	public String getAccount_password() {
		return account_password;
	}

	public void setAccount_password(String account_password) {
		this.account_password = account_password;
	}

	public boolean isIs_account_active() {
		return is_account_active;
	}

	public void setIs_account_active(boolean is_account_active) {
		this.is_account_active = is_account_active;
	}

	public String getSignup_datetime() {
		return signup_datetime;
	}

	public void setSignup_datetime(String signup_datetime) {
		this.signup_datetime = signup_datetime;
	}

	public String getSignup_ip_addr() {
		return signup_ip_addr;
	}

	public void setSignup_ip_addr(String signup_ip_addr) {
		this.signup_ip_addr = signup_ip_addr;
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


	public String getAboutme() {
		return aboutme;
	}


	public void setAboutme(String aboutme) {
		this.aboutme = aboutme;
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
	
	
	public String getAccount_pwd_salt() {
		return account_pwd_salt;
	}


	public void setAccount_pwd_salt(String account_pwd_salt) {
		this.account_pwd_salt = account_pwd_salt;
	}


	public List<Long> getMy_followers() {
		return my_followers;
	}


	public void setMy_followers(List<Long> my_followers) {
		this.my_followers = my_followers;
	}


	public List<Long> getMe_following() {
		return me_following;
	}


	public void setMe_following(List<Long> me_following) {
		this.me_following = me_following;
	}


	public List<Long> getMy_liked_lookbooks() {
		return my_liked_lookbooks;
	}


	public void setMy_liked_lookbooks(List<Long> my_liked_lookbooks) {
		this.my_liked_lookbooks = my_liked_lookbooks;
	}


	public List<Long> getMy_lookbook_comments() {
		return my_lookbook_comments;
	}


	public void setMy_lookbook_comments(List<Long> my_lookbook_comments) {
		this.my_lookbook_comments = my_lookbook_comments;
	}


	public Long getFacebook_usrid() {
		return facebook_usrid;
	}


	public void setFacebook_usrid(Long facebook_usrid) {
		this.facebook_usrid = facebook_usrid;
	}


	public long getMy_glam_points() {
		return my_glam_points;
	}


	public void setMy_glam_points(long my_glam_points) {
		this.my_glam_points = my_glam_points;
	}


	public String getUavatar_is_uploaded() {
		return uavatar_is_uploaded;
	}


	public void setUavatar_is_uploaded(String uavatar_is_uploaded) {
		this.uavatar_is_uploaded = uavatar_is_uploaded;
	}


	public Date getSignup_dttm() {
		return signup_dttm;
	}


	public void setSignup_dttm(Date signup_dttm) {
		this.signup_dttm = signup_dttm;
	}


	public String getFcategory() {
		return fcategory;
	}


	public void setFcategory(String fcategory) {
		this.fcategory = fcategory;
	}
	
}
