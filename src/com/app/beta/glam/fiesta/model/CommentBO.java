package com.app.beta.glam.fiesta.model;

import java.io.Serializable;
import java.util.Date;

public class CommentBO implements Serializable {

	private Long commentid;
	private Long lookbookid;
	private String lookbooknm;
	
	private String lookbookurl;
	
	// user data
	private Long userid;
	private String username;
	private String uavatar_thumb;

	private String commenttxt;
	private Date commentdt;
	private String commentdtstr;

	@Override
	public String toString() {

		return "CommentBO :- userid [" + userid + "] username [" + username
				+ "] uavatar_thumb [" + uavatar_thumb + "] commenttxt ["
				+ commenttxt + "commentdt [" + commentdt + "] ";
	}

	public Long getCommentid() {
		return commentid;
	}

	public void setCommentid(Long commentid) {
		this.commentid = commentid;
	}

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUavatar_thumb() {
		return uavatar_thumb;
	}

	public void setUavatar_thumb(String uavatar_thumb) {
		this.uavatar_thumb = uavatar_thumb;
	}

	public String getCommenttxt() {
		return commenttxt;
	}

	public void setCommenttxt(String commenttxt) {
		this.commenttxt = commenttxt;
	}

	public Date getCommentdt() {
		return commentdt;
	}

	public void setCommentdt(Date commentdt) {
		this.commentdt = commentdt;
	}

	public String getLookbooknm() {
		return lookbooknm;
	}

	public void setLookbooknm(String lookbooknm) {
		this.lookbooknm = lookbooknm;
	}

	public String getCommentdtstr() {
		return commentdtstr;
	}

	public void setCommentdtstr(String commentdtstr) {
		this.commentdtstr = commentdtstr;
	}

	public String getLookbookurl() {
		return lookbookurl;
	}

	public void setLookbookurl(String lookbookurl) {
		this.lookbookurl = lookbookurl;
	}
}
