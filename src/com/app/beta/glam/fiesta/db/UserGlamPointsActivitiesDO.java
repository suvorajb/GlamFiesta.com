package com.app.beta.glam.fiesta.db;

import java.io.Serializable;
import java.util.Date;

import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Unindex;

@Entity
public class UserGlamPointsActivitiesDO implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	@Id private Long userpointacvtid;
	
	@Index private Long userid;
	
	@Unindex private String pointacvtstr;
	@Unindex private Date actvtdt;
	@Unindex private Long lookbookid;

	public Long getUserpointacvtid() {
		return userpointacvtid;
	}

	public void setUserpointacvtid(Long userpointacvtid) {
		this.userpointacvtid = userpointacvtid;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getPointacvtstr() {
		return pointacvtstr;
	}

	public void setPointacvtstr(String pointacvtstr) {
		this.pointacvtstr = pointacvtstr;
	}

	public Date getActvtdt() {
		return actvtdt;
	}

	public void setActvtdt(Date actvtdt) {
		this.actvtdt = actvtdt;
	}

	public Long getLookbookid() {
		return lookbookid;
	}

	public void setLookbookid(Long lookbookid) {
		this.lookbookid = lookbookid;
	}
	
}
