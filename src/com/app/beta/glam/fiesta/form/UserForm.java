package com.app.beta.glam.fiesta.form;


public class UserForm {
	
	// account information
	private String account_username;	
	private String account_password;
	private String account_email;
	private String reset_key;
	private Long uid;

	// personal information
	private String first_name;
	private String last_name;
	private String birth_year;
	private String gender;
	private String aboutme;
	private String statenm;
	private String citynm;
	private String countrynm;

	// social information
	private String twitter_ac;
	private String facebook_ac;
	private String google_plus_ac;
	private String youtube_ac;
	private String linkedin_ac;
	private String pinterest_ac;
	private String instagram_ac;
	private String tumblr_ac;
	private String website_blog_url;
	
	private String prfile_actn = "";
	
	
	@Override
	public String toString() {
		
		return " account_email [" + account_email + "] account_username [" + account_username + "first_name[" + first_name	+ 
			   "] last_name [" + last_name + "] birth_year[" + birth_year + "] gender [" + gender + "] " ;
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
	public String getAccount_email() {
		return account_email;
	}
	public void setAccount_email(String account_email) {
		this.account_email = account_email;
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

	public String getPrfile_actn() {
		return prfile_actn;
	}

	public void setPrfile_actn(String prfile_actn) {
		this.prfile_actn = prfile_actn;
	}

	public String getReset_key() {
		return reset_key;
	}

	public void setReset_key(String reset_key) {
		this.reset_key = reset_key;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

}
