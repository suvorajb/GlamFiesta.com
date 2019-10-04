package com.app.beta.glam.fiesta.db;

import java.io.Serializable;
import java.util.Date;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Unindex;

@Entity
public class LookbookCommentsDO  implements Serializable {

private static final long serialVersionUID = 1L;
	
	@Id private Long commentid;
	
	@Index private Long lookbookid;
	
	@Unindex private Long userid;
	@Unindex private String commenttxt;
	@Index private Date commentdt;
	@Unindex private String comment_type;
	
	
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
	public String getComment_type() {
		return comment_type;
	}
	public void setComment_type(String comment_type) {
		this.comment_type = comment_type;
	}
}
