package com.app.beta.glam.fiesta.db;

import java.io.Serializable;

import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Unindex;

@Entity
public class UserLookbookTagsDO  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id private Long tid;
	
	@Unindex private Long lookbookid;
	@Index private String tagstr;
	
	public Long getTid() {
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}
	public Long getLookbookid() {
		return lookbookid;
	}
	public void setLookbookid(Long lookbookid) {
		this.lookbookid = lookbookid;
	}
	public String getTagstr() {
		return tagstr;
	}
	public void setTagstr(String tagstr) {
		this.tagstr = tagstr;
	}
}
