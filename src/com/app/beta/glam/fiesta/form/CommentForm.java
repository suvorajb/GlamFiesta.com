package com.app.beta.glam.fiesta.form;

import java.io.Serializable;

public class CommentForm  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String comment_txt;
	private long lookbookid;
	
	public String getComment_txt() {
		return comment_txt;
	}
	public void setComment_txt(String comment_txt) {
		this.comment_txt = comment_txt;
	}
	public long getLookbookid() {
		return lookbookid;
	}
	public void setLookbookid(long lookbookid) {
		this.lookbookid = lookbookid;
	}
	
	@Override
	public String toString() {
		return "comment_txt: " + comment_txt + ", lookbookid: " + lookbookid;
	}
}
