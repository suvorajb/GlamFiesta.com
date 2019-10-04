package com.app.beta.glam.fiesta.db;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Unindex;

@Entity
public class UserLookbookDO  implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id private Long lookbookid;
	
	@Index private Long userid;
	@Index private String username;
	@Index private String lookbookname;
	@Index private String lookbook_seo_url;	
	@Index private String[] lookbookstyle;
	@Index private String lookbookseason;
	@Index private List<String> tags;
	@Index private Date createddt;
	@Index private Date publisheddt;	
	@Index private String lookbook_publish_status;
	@Index private boolean disapproved;
	
	@Unindex private String lookbookstory;

	@Unindex private boolean lookbookme;

	// ********** youtube, vimeo & photos urls ***********
	@Unindex private String lookbook_youtube_videourl;
	@Unindex private String lookbook_vimeo_videourl;
	
	@Unindex private String gcs_photo_key1;
	@Unindex private String gcs_photo_url1;
	@Unindex private String web_photo_url1;
	
	@Unindex private String gcs_photo_key2;
	@Unindex private String gcs_photo_url2;
	@Unindex private String web_photo_url2;
	
	@Unindex private String gcs_photo_key3;
	@Unindex private String gcs_photo_url3;
	@Unindex private String web_photo_url3;
	
	@Unindex private String gcs_photo_key4;
	@Unindex private String gcs_photo_url4;
	@Unindex private String web_photo_url4;
	
	@Unindex private String gcs_photo_key5;
	@Unindex private String gcs_photo_url5;
	@Unindex private String web_photo_url5;
	
	
	@Unindex private Date lastupdateddt;
	
	@Unindex private String lookbook_display_style;


	@Unindex private String outfit_brand1;
	@Unindex private String outfit_type1;
	@Unindex private String outfit_desc1;
	
	@Unindex private String outfit_brand2;
	@Unindex private String outfit_type2;
	@Unindex private String outfit_desc2;
	
	@Unindex private String outfit_brand3;
	@Unindex private String outfit_type3;
	@Unindex private String outfit_desc3;
	
	@Unindex private String outfit_brand4;
	@Unindex private String outfit_type4;
	@Unindex private String outfit_desc4;

	@Unindex private String outfit_brand5;
	@Unindex private String outfit_type5;
	@Unindex private String outfit_desc5;
	
	@Unindex private List<Long> liked_by_users;
	@Unindex private List<Long> comments_by_users;
	
	@Unindex private String disapproved_cmmt;
	
	public Long getLookbookid() {
		return lookbookid;
	}
	public void setLookbookid(Long lookbookid) {
		this.lookbookid = lookbookid;
	}
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public String getLookbookname() {
		return lookbookname;
	}
	public void setLookbookname(String lookbookname) {
		this.lookbookname = lookbookname;
	}
	public boolean isLookbookme() {
		return lookbookme;
	}
	public void setLookbookme(boolean lookbookme) {
		this.lookbookme = lookbookme;
	}
	public String getLookbook_youtube_videourl() {
		return lookbook_youtube_videourl;
	}
	public void setLookbook_youtube_videourl(String lookbook_youtube_videourl) {
		this.lookbook_youtube_videourl = lookbook_youtube_videourl;
	}
	public String getLookbook_publish_status() {
		return lookbook_publish_status;
	}
	public void setLookbook_publish_status(String lookbook_publish_status) {
		this.lookbook_publish_status = lookbook_publish_status;
	}
	public String[] getLookbookstyle() {
		return lookbookstyle;
	}
	public void setLookbookstyle(String[] lookbookstyle) {
		this.lookbookstyle = lookbookstyle;
	}
	public String getLookbookseason() {
		return lookbookseason;
	}
	public void setLookbookseason(String lookbookseason) {
		this.lookbookseason = lookbookseason;
	}
	public  List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public String getLookbookstory() {
		return lookbookstory;
	}
	public void setLookbookstory(String lookbookstory) {
		this.lookbookstory = lookbookstory;
	}
	public Date getPublisheddt() {
		return publisheddt;
	}
	public void setPublisheddt(Date publisheddt) {
		this.publisheddt = publisheddt;
	}
	public Date getLastupdateddt() {
		return lastupdateddt;
	}
	public void setLastupdateddt(Date lastupdateddt) {
		this.lastupdateddt = lastupdateddt;
	}
	public String getLookbook_vimeo_videourl() {
		return lookbook_vimeo_videourl;
	}
	public void setLookbook_vimeo_videourl(String lookbook_vimeo_videourl) {
		this.lookbook_vimeo_videourl = lookbook_vimeo_videourl;
	}
	public String getGcs_photo_url1() {
		return gcs_photo_url1;
	}
	public void setGcs_photo_url1(String gcs_photo_url1) {
		this.gcs_photo_url1 = gcs_photo_url1;
	}
	public String getWeb_photo_url1() {
		return web_photo_url1;
	}
	public void setWeb_photo_url1(String web_photo_url1) {
		this.web_photo_url1 = web_photo_url1;
	}
	public String getGcs_photo_url2() {
		return gcs_photo_url2;
	}
	public void setGcs_photo_url2(String gcs_photo_url2) {
		this.gcs_photo_url2 = gcs_photo_url2;
	}
	public String getWeb_photo_url2() {
		return web_photo_url2;
	}
	public void setWeb_photo_url2(String web_photo_url2) {
		this.web_photo_url2 = web_photo_url2;
	}
	public String getGcs_photo_url3() {
		return gcs_photo_url3;
	}
	public void setGcs_photo_url3(String gcs_photo_url3) {
		this.gcs_photo_url3 = gcs_photo_url3;
	}
	public String getWeb_photo_url3() {
		return web_photo_url3;
	}
	public void setWeb_photo_url3(String web_photo_url3) {
		this.web_photo_url3 = web_photo_url3;
	}
	public String getGcs_photo_url4() {
		return gcs_photo_url4;
	}
	public void setGcs_photo_url4(String gcs_photo_url4) {
		this.gcs_photo_url4 = gcs_photo_url4;
	}
	public String getWeb_photo_url4() {
		return web_photo_url4;
	}
	public void setWeb_photo_url4(String web_photo_url4) {
		this.web_photo_url4 = web_photo_url4;
	}
	public String getGcs_photo_url5() {
		return gcs_photo_url5;
	}
	public void setGcs_photo_url5(String gcs_photo_url5) {
		this.gcs_photo_url5 = gcs_photo_url5;
	}
	public String getWeb_photo_url5() {
		return web_photo_url5;
	}
	public void setWeb_photo_url5(String web_photo_url5) {
		this.web_photo_url5 = web_photo_url5;
	}
	public Date getCreateddt() {
		return createddt;
	}
	public void setCreateddt(Date createddt) {
		this.createddt = createddt;
	}
	public String getLookbook_display_style() {
		return lookbook_display_style;
	}
	public void setLookbook_display_style(String lookbook_display_style) {
		this.lookbook_display_style = lookbook_display_style;
	}
	public String getOutfit_brand1() {
		return outfit_brand1;
	}
	public void setOutfit_brand1(String outfit_brand1) {
		this.outfit_brand1 = outfit_brand1;
	}
	public String getOutfit_type1() {
		return outfit_type1;
	}
	public void setOutfit_type1(String outfit_type1) {
		this.outfit_type1 = outfit_type1;
	}
	public String getOutfit_desc1() {
		return outfit_desc1;
	}
	public void setOutfit_desc1(String outfit_desc1) {
		this.outfit_desc1 = outfit_desc1;
	}
	public String getOutfit_brand2() {
		return outfit_brand2;
	}
	public void setOutfit_brand2(String outfit_brand2) {
		this.outfit_brand2 = outfit_brand2;
	}
	public String getOutfit_type2() {
		return outfit_type2;
	}
	public void setOutfit_type2(String outfit_type2) {
		this.outfit_type2 = outfit_type2;
	}
	public String getOutfit_desc2() {
		return outfit_desc2;
	}
	public void setOutfit_desc2(String outfit_desc2) {
		this.outfit_desc2 = outfit_desc2;
	}
	public String getOutfit_brand3() {
		return outfit_brand3;
	}
	public void setOutfit_brand3(String outfit_brand3) {
		this.outfit_brand3 = outfit_brand3;
	}
	public String getOutfit_type3() {
		return outfit_type3;
	}
	public void setOutfit_type3(String outfit_type3) {
		this.outfit_type3 = outfit_type3;
	}
	public String getOutfit_desc3() {
		return outfit_desc3;
	}
	public void setOutfit_desc3(String outfit_desc3) {
		this.outfit_desc3 = outfit_desc3;
	}
	public String getOutfit_brand4() {
		return outfit_brand4;
	}
	public void setOutfit_brand4(String outfit_brand4) {
		this.outfit_brand4 = outfit_brand4;
	}
	public String getOutfit_type4() {
		return outfit_type4;
	}
	public void setOutfit_type4(String outfit_type4) {
		this.outfit_type4 = outfit_type4;
	}
	public String getOutfit_desc4() {
		return outfit_desc4;
	}
	public void setOutfit_desc4(String outfit_desc4) {
		this.outfit_desc4 = outfit_desc4;
	}
	public String getOutfit_brand5() {
		return outfit_brand5;
	}
	public void setOutfit_brand5(String outfit_brand5) {
		this.outfit_brand5 = outfit_brand5;
	}
	public String getOutfit_type5() {
		return outfit_type5;
	}
	public void setOutfit_type5(String outfit_type5) {
		this.outfit_type5 = outfit_type5;
	}
	public String getOutfit_desc5() {
		return outfit_desc5;
	}
	public void setOutfit_desc5(String outfit_desc5) {
		this.outfit_desc5 = outfit_desc5;
	}
	public String getGcs_photo_key1() {
		return gcs_photo_key1;
	}
	public void setGcs_photo_key1(String gcs_photo_key1) {
		this.gcs_photo_key1 = gcs_photo_key1;
	}
	public String getGcs_photo_key2() {
		return gcs_photo_key2;
	}
	public void setGcs_photo_key2(String gcs_photo_key2) {
		this.gcs_photo_key2 = gcs_photo_key2;
	}
	public String getGcs_photo_key3() {
		return gcs_photo_key3;
	}
	public void setGcs_photo_key3(String gcs_photo_key3) {
		this.gcs_photo_key3 = gcs_photo_key3;
	}
	public String getGcs_photo_key4() {
		return gcs_photo_key4;
	}
	public void setGcs_photo_key4(String gcs_photo_key4) {
		this.gcs_photo_key4 = gcs_photo_key4;
	}
	public String getGcs_photo_key5() {
		return gcs_photo_key5;
	}
	public void setGcs_photo_key5(String gcs_photo_key5) {
		this.gcs_photo_key5 = gcs_photo_key5;
	}
	public List<Long> getLiked_by_users() {
		return liked_by_users;
	}
	public void setLiked_by_users(List<Long> liked_by_users) {
		this.liked_by_users = liked_by_users;
	}
	public List<Long> getComments_by_users() {
		return comments_by_users;
	}
	public void setComments_by_users(List<Long> comments_by_users) {
		this.comments_by_users = comments_by_users;
	}
	public boolean isDisapproved() {
		return disapproved;
	}
	public void setDisapproved(boolean disapproved) {
		this.disapproved = disapproved;
	}
	public String getDisapproved_cmmt() {
		return disapproved_cmmt;
	}
	public void setDisapproved_cmmt(String disapproved_cmmt) {
		this.disapproved_cmmt = disapproved_cmmt;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getLookbook_seo_url() {
		return lookbook_seo_url;
	}
	public void setLookbook_seo_url(String lookbook_seo_url) {
		this.lookbook_seo_url = lookbook_seo_url;
	}
	@Override
	public String toString() {
		return "UserLookbookDO [lookbookid=" + lookbookid + ", userid=" + userid + ", username=" + username
				+ ", lookbookname=" + lookbookname + ", lookbook_seo_url=" + lookbook_seo_url + ", lookbookstyle="
				+ Arrays.toString(lookbookstyle) + ", lookbookseason=" + lookbookseason + ", tags=" + tags
				+ ", createddt=" + createddt + ", publisheddt=" + publisheddt + ", lookbook_publish_status="
				+ lookbook_publish_status + ", disapproved=" + disapproved + ", lookbookstory=" + lookbookstory
				+ ", lookbookme=" + lookbookme + ", lookbook_youtube_videourl=" + lookbook_youtube_videourl
				+ ", lookbook_vimeo_videourl=" + lookbook_vimeo_videourl + ", gcs_photo_key1=" + gcs_photo_key1
				+ ", gcs_photo_url1=" + gcs_photo_url1 + ", web_photo_url1=" + web_photo_url1 + ", gcs_photo_key2="
				+ gcs_photo_key2 + ", gcs_photo_url2=" + gcs_photo_url2 + ", web_photo_url2=" + web_photo_url2
				+ ", gcs_photo_key3=" + gcs_photo_key3 + ", gcs_photo_url3=" + gcs_photo_url3 + ", web_photo_url3="
				+ web_photo_url3 + ", gcs_photo_key4=" + gcs_photo_key4 + ", gcs_photo_url4=" + gcs_photo_url4
				+ ", web_photo_url4=" + web_photo_url4 + ", gcs_photo_key5=" + gcs_photo_key5 + ", gcs_photo_url5="
				+ gcs_photo_url5 + ", web_photo_url5=" + web_photo_url5 + ", lastupdateddt=" + lastupdateddt
				+ ", lookbook_display_style=" + lookbook_display_style + ", outfit_brand1=" + outfit_brand1
				+ ", outfit_type1=" + outfit_type1 + ", outfit_desc1=" + outfit_desc1 + ", outfit_brand2="
				+ outfit_brand2 + ", outfit_type2=" + outfit_type2 + ", outfit_desc2=" + outfit_desc2
				+ ", outfit_brand3=" + outfit_brand3 + ", outfit_type3=" + outfit_type3 + ", outfit_desc3="
				+ outfit_desc3 + ", outfit_brand4=" + outfit_brand4 + ", outfit_type4=" + outfit_type4
				+ ", outfit_desc4=" + outfit_desc4 + ", outfit_brand5=" + outfit_brand5 + ", outfit_type5="
				+ outfit_type5 + ", outfit_desc5=" + outfit_desc5 + ", liked_by_users=" + liked_by_users
				+ ", comments_by_users=" + comments_by_users + ", disapproved_cmmt=" + disapproved_cmmt + "]";
	}
	
	
}
