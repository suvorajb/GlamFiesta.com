package com.app.beta.glam.fiesta.db;

import java.io.Serializable;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Unindex;

@Entity
public class InterviewDO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long intrvwid;
	
	@Index
	private String usernm;
	
	@Unindex
	private String intrvwtitle;	
	
	@Index
	private String intrvw_seo_title;		
	
	@Unindex
	private String intrvwtxt1;
	@Unindex
	private String fshnphoto1;	
	
	@Unindex
	private String intrvwtxt2;
	@Unindex
	private String fshnphoto2;	
	
	@Unindex
	private String intrvwtxt3;
	@Unindex
	private String fshnphoto3;
	
	
	public Long getIntrvwid() {
		return intrvwid;
	}
	public void setIntrvwid(Long intrvwid) {
		this.intrvwid = intrvwid;
	}
	public String getUsernm() {
		return usernm;
	}
	public void setUsernm(String usernm) {
		this.usernm = usernm;
	}
	public String getIntrvwtitle() {
		return intrvwtitle;
	}
	public void setIntrvwtitle(String intrvwtitle) {
		this.intrvwtitle = intrvwtitle;
	}
	public String getIntrvwtxt1() {
		return intrvwtxt1;
	}
	public void setIntrvwtxt1(String intrvwtxt1) {
		this.intrvwtxt1 = intrvwtxt1;
	}
	public String getFshnphoto1() {
		return fshnphoto1;
	}
	public void setFshnphoto1(String fshnphoto1) {
		this.fshnphoto1 = fshnphoto1;
	}
	public String getIntrvwtxt2() {
		return intrvwtxt2;
	}
	public void setIntrvwtxt2(String intrvwtxt2) {
		this.intrvwtxt2 = intrvwtxt2;
	}
	public String getFshnphoto2() {
		return fshnphoto2;
	}
	public void setFshnphoto2(String fshnphoto2) {
		this.fshnphoto2 = fshnphoto2;
	}
	public String getIntrvwtxt3() {
		return intrvwtxt3;
	}
	public void setIntrvwtxt3(String intrvwtxt3) {
		this.intrvwtxt3 = intrvwtxt3;
	}
	public String getFshnphoto3() {
		return fshnphoto3;
	}
	public void setFshnphoto3(String fshnphoto3) {
		this.fshnphoto3 = fshnphoto3;
	}
	public String getIntrvw_seo_title() {
		return intrvw_seo_title;
	}
	public void setIntrvw_seo_title(String intrvw_seo_title) {
		this.intrvw_seo_title = intrvw_seo_title;
	}
}
