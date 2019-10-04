package com.app.beta.glam.fiesta.form;

import java.io.Serializable;

public class MssgBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String mssg;
	private String mssg_type;
	private String total_likes;
	private String total_comments;
	
	public String getMssg() {
		return mssg;
	}
	public void setMssg(String mssg) {
		this.mssg = mssg;
	}
	public String getMssg_type() {
		return mssg_type;
	}
	public void setMssg_type(String mssg_type) {
		this.mssg_type = mssg_type;
	}
	public String getTotal_likes() {
		return total_likes;
	}
	public void setTotal_likes(String total_likes) {
		this.total_likes = total_likes;
	}
	public String getTotal_comments() {
		return total_comments;
	}
	public void setTotal_comments(String total_comments) {
		this.total_comments = total_comments;
	}
}
