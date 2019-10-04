package com.app.beta.glam.fiesta.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class FtrdFashionistaBO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String account_username;
	private String from_location;
	private String uavatar;
	
	private String note_from_editor;
	
	private String youtube_video_url1;
	private String youtube_video_url2;
	private String youtube_video_url3;
	private String fashion_blog_url1;
	
	private int total_lookbooks;
	private String total_glampoints;
	
	private List<LookbookBO> lookbookList = Collections.emptyList();

	public String getAccount_username() {
		return account_username;
	}

	public void setAccount_username(String account_username) {
		this.account_username = account_username;
	}

	public String getNote_from_editor() {
		return note_from_editor;
	}

	public void setNote_from_editor(String note_from_editor) {
		this.note_from_editor = note_from_editor;
	}

	public String getYoutube_video_url1() {
		return youtube_video_url1;
	}

	public void setYoutube_video_url1(String youtube_video_url1) {
		this.youtube_video_url1 = youtube_video_url1;
	}

	public String getYoutube_video_url2() {
		return youtube_video_url2;
	}

	public void setYoutube_video_url2(String youtube_video_url2) {
		this.youtube_video_url2 = youtube_video_url2;
	}

	public String getYoutube_video_url3() {
		return youtube_video_url3;
	}

	public void setYoutube_video_url3(String youtube_video_url3) {
		this.youtube_video_url3 = youtube_video_url3;
	}

	public String getFashion_blog_url1() {
		return fashion_blog_url1;
	}

	public void setFashion_blog_url1(String fashion_blog_url1) {
		this.fashion_blog_url1 = fashion_blog_url1;
	}

	public int getTotal_lookbooks() {
		return total_lookbooks;
	}

	public void setTotal_lookbooks(int total_lookbooks) {
		this.total_lookbooks = total_lookbooks;
	}

	public String getTotal_glampoints() {
		return total_glampoints;
	}

	public void setTotal_glampoints(String total_glampoints) {
		this.total_glampoints = total_glampoints;
	}

	public List<LookbookBO> getLookbookList() {
		return lookbookList;
	}

	public void setLookbookList(List<LookbookBO> lookbookList) {
		this.lookbookList = lookbookList;
	}

	public String getFrom_location() {
		return from_location;
	}

	public void setFrom_location(String from_location) {
		this.from_location = from_location;
	}

	public String getUavatar() {
		return uavatar;
	}

	public void setUavatar(String uavatar) {
		this.uavatar = uavatar;
	}

	
}
