package com.app.beta.glam.fiesta.db;

import java.io.Serializable;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Unindex;

@Entity
public class FeaturedUsersDO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id private Long id;
	
	@Unindex private Long userid;
	@Unindex private String note_from_editor;
	@Unindex private String youtube_video_url1;
	@Unindex private String youtube_video_url2;
	@Unindex private String youtube_video_url3;
	@Unindex private String youtube_video_url4;
	@Unindex private String youtube_video_url5;
	@Unindex private String youtube_video_url6;
	@Unindex private String fashion_blog_url1;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
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
	public String getYoutube_video_url4() {
		return youtube_video_url4;
	}
	public void setYoutube_video_url4(String youtube_video_url4) {
		this.youtube_video_url4 = youtube_video_url4;
	}
	public String getYoutube_video_url5() {
		return youtube_video_url5;
	}
	public void setYoutube_video_url5(String youtube_video_url5) {
		this.youtube_video_url5 = youtube_video_url5;
	}
	public String getYoutube_video_url6() {
		return youtube_video_url6;
	}
	public void setYoutube_video_url6(String youtube_video_url6) {
		this.youtube_video_url6 = youtube_video_url6;
	}
	public String getFashion_blog_url1() {
		return fashion_blog_url1;
	}
	public void setFashion_blog_url1(String fashion_blog_url1) {
		this.fashion_blog_url1 = fashion_blog_url1;
	}
	
}
