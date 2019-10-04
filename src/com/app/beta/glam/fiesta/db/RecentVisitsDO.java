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
public class RecentVisitsDO  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id private Long visitid;
	
	@Index private Long visiting_userid;
	@Unindex private String visiting_username;
	@Unindex private String uavatar;	
	@Index private String visiting_ip_addr;
	
	@Index private Long lookbookid;
	@Unindex private String lookbookname;
	@Unindex private String lookbook_seo_url;
	@Unindex private String lookbookimgstr;
	
	@Unindex private Date visiting_datetime;
	
	public Long getVisitid() {
		return visitid;
	}
	public void setVisitid(Long visitid) {
		this.visitid = visitid;
	}
	public Long getVisiting_userid() {
		return visiting_userid;
	}
	public void setVisiting_userid(Long visiting_userid) {
		this.visiting_userid = visiting_userid;
	}
	public String getVisiting_username() {
		return visiting_username;
	}
	public void setVisiting_username(String visiting_username) {
		this.visiting_username = visiting_username;
	}
	public String getVisiting_ip_addr() {
		return visiting_ip_addr;
	}
	public void setVisiting_ip_addr(String visiting_ip_addr) {
		this.visiting_ip_addr = visiting_ip_addr;
	}
	public Long getLookbookid() {
		return lookbookid;
	}
	public void setLookbookid(Long lookbookid) {
		this.lookbookid = lookbookid;
	}
	public Date getVisiting_datetime() {
		return visiting_datetime;
	}
	public void setVisiting_datetime(Date visiting_datetime) {
		this.visiting_datetime = visiting_datetime;
	}
	public String getUavatar() {
		return uavatar;
	}
	public void setUavatar(String uavatar) {
		this.uavatar = uavatar;
	}
	public String getLookbook_seo_url() {
		return lookbook_seo_url;
	}
	public void setLookbook_seo_url(String lookbook_seo_url) {
		this.lookbook_seo_url = lookbook_seo_url;
	}
	public String getLookbookimgstr() {
		return lookbookimgstr;
	}
	public void setLookbookimgstr(String lookbookimgstr) {
		this.lookbookimgstr = lookbookimgstr;
	}
	public String getLookbookname() {
		return lookbookname;
	}
	public void setLookbookname(String lookbookname) {
		this.lookbookname = lookbookname;
	}
	
}
