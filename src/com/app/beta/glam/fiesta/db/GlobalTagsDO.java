package com.app.beta.glam.fiesta.db;

import java.io.Serializable;
import java.util.Date;

import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Unindex;

@Cache
@Entity
public class GlobalTagsDO  implements Serializable {
private static final long serialVersionUID = 1L;
	
	@Id private Long id;
	
	@Index private String glbl_tagname;
	@Unindex private int tag_display_order;
	@Unindex private String glbl_tagdesc;
	@Unindex private boolean istagactivated;
	@Unindex private Date tagadddatetm;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}	
	public int getTag_display_order() {
		return tag_display_order;
	}
	public void setTag_display_order(int tag_display_order) {
		this.tag_display_order = tag_display_order;
	}
	public String getGlbl_tagname() {
		return glbl_tagname;
	}
	public void setGlbl_tagname(String glbl_tagname) {
		this.glbl_tagname = glbl_tagname;
	}
	public String getGlbl_tagdesc() {
		return glbl_tagdesc;
	}
	public void setGlbl_tagdesc(String glbl_tagdesc) {
		this.glbl_tagdesc = glbl_tagdesc;
	}
	public boolean isIstagactivated() {
		return istagactivated;
	}
	public void setIstagactivated(boolean istagactivated) {
		this.istagactivated = istagactivated;
	}
	public Date getTagadddatetm() {
		return tagadddatetm;
	}
	public void setTagadddatetm(Date tagadddatetm) {
		this.tagadddatetm = tagadddatetm;
	}
	
	
}
