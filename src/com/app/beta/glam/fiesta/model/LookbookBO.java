package com.app.beta.glam.fiesta.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class LookbookBO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "**** UserLookbookDO:: lookbookname [" + lookbookname + "] lookbookme [" + lookbookme + "] lookbookstyle [" + lookbookstyle + "] lookbookseason [" + lookbookseason
				+ "] tags [" + tags + "] lookbookstory [" + lookbookstory + "] lookbookid [" + lookbookid;
	}

	private Long lookbookid;

	private Long userid;
	
	private UserBO usrinfo;
	
	private String lookbook_owner;
	
	private String lookbookname;

	
	private boolean lookbookme;

	
	private String[] lookbookstyle;
	
	private String lookbookstylestr;
	
	private String lookbookseason;

	
	private String[] tags;
	private String[] updtdtags;

	
	private String lookbookstory;

	// ********** youtube, vimeo & photos urls ***********
	
	private String lookbook_youtube_videourl;
	
	private String lookbook_vimeo_videourl;

	private int total_no_photos;
	
	
	private String gcs_photo_key1;
	
	private String gcs_photo_url1;
	private String gcs_photo_url_slider_thumb1;
	
	private String web_photo_url1;

	
	private String gcs_photo_key2;
	
	private String gcs_photo_url2;
	private String gcs_photo_url_slider_thumb2;
	
	private String web_photo_url2;

	
	private String gcs_photo_key3;
	
	private String gcs_photo_url3;
	private String gcs_photo_url_slider_thumb3;
	
	private String web_photo_url3;

	
	private String gcs_photo_key4;
	
	private String gcs_photo_url4;
	
	private String web_photo_url4;

	
	private String gcs_photo_key5;
	
	private String gcs_photo_url5;
	
	private String web_photo_url5;

	
	private Date createddt;
	
	private Date publisheddt;
	
	private Date lastupdateddt;

	
	private String lookbook_publish_status;

	
	private String lookbook_display_style;

	
	private String outfit_brand1;
	
	private String outfit_type1;
	
	private String outfit_desc1;

	
	private String outfit_brand2;
	
	private String outfit_type2;
	
	private String outfit_desc2;

	
	private String outfit_brand3;
	
	private String outfit_type3;
	
	private String outfit_desc3;

	
	private String outfit_brand4;
	
	private String outfit_type4;
	
	private String outfit_desc4;

	
	private String outfit_brand5;
	
	private String outfit_type5;
	
	private String outfit_desc5;

	private String total_comments;
	private String total_likes;
	private List<UserBO> likedbyusrs;
	private String lookbook_seo_url;
	private String published_dttm_str;
	private boolean islookbook_liked_by_login_usr;
	private boolean can_edit;
	private boolean isrejected;
	
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

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public String[] getUpdtdtags() {
		return updtdtags;
	}

	public void setUpdtdtags(String[] updtdtags) {
		this.updtdtags = updtdtags;
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

	public int getTotal_no_photos() {
		return total_no_photos;
	}

	public void setTotal_no_photos(int total_no_photos) {
		this.total_no_photos = total_no_photos;
	}

	public String getGcs_photo_url_slider_thumb1() {
		return gcs_photo_url_slider_thumb1;
	}

	public void setGcs_photo_url_slider_thumb1(String gcs_photo_url_slider_thumb1) {
		this.gcs_photo_url_slider_thumb1 = gcs_photo_url_slider_thumb1;
	}

	public String getGcs_photo_url_slider_thumb2() {
		return gcs_photo_url_slider_thumb2;
	}

	public void setGcs_photo_url_slider_thumb2(String gcs_photo_url_slider_thumb2) {
		this.gcs_photo_url_slider_thumb2 = gcs_photo_url_slider_thumb2;
	}

	public String getGcs_photo_url_slider_thumb3() {
		return gcs_photo_url_slider_thumb3;
	}

	public void setGcs_photo_url_slider_thumb3(String gcs_photo_url_slider_thumb3) {
		this.gcs_photo_url_slider_thumb3 = gcs_photo_url_slider_thumb3;
	}

	public String getLookbookstylestr() {
		return lookbookstylestr;
	}

	public void setLookbookstylestr(String lookbookstylestr) {
		this.lookbookstylestr = lookbookstylestr;
	}

	public String getLookbook_owner() {
		return lookbook_owner;
	}

	public void setLookbook_owner(String lookbook_owner) {
		this.lookbook_owner = lookbook_owner;
	}

	public String getTotal_comments() {
		return total_comments;
	}

	public void setTotal_comments(String total_comments) {
		this.total_comments = total_comments;
	}

	public String getTotal_likes() {
		return total_likes;
	}

	public void setTotal_likes(String total_likes) {
		this.total_likes = total_likes;
	}

	public UserBO getUsrinfo() {
		return usrinfo;
	}

	public void setUsrinfo(UserBO usrinfo) {
		this.usrinfo = usrinfo;
	}

	public List<UserBO> getLikedbyusrs() {
		return likedbyusrs;
	}

	public void setLikedbyusrs(List<UserBO> likedbyusrs) {
		this.likedbyusrs = likedbyusrs;
	}

	public String getLookbook_seo_url() {
		return lookbook_seo_url;
	}

	public void setLookbook_seo_url(String lookbook_seo_url) {
		this.lookbook_seo_url = lookbook_seo_url;
	}

	public String getPublished_dttm_str() {
		return published_dttm_str;
	}

	public void setPublished_dttm_str(String published_dttm_str) {
		this.published_dttm_str = published_dttm_str;
	}

	public boolean isIslookbook_liked_by_login_usr() {
		return islookbook_liked_by_login_usr;
	}

	public void setIslookbook_liked_by_login_usr(
			boolean islookbook_liked_by_login_usr) {
		this.islookbook_liked_by_login_usr = islookbook_liked_by_login_usr;
	}

	public boolean isCan_edit() {
		return can_edit;
	}

	public void setCan_edit(boolean can_edit) {
		this.can_edit = can_edit;
	}

	public boolean isIsrejected() {
		return isrejected;
	}

	public void setIsrejected(boolean isrejected) {
		this.isrejected = isrejected;
	}

}