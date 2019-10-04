package com.app.beta.glam.fiesta.db;

import java.io.Serializable;
import java.util.Date;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Unindex;

@Entity
public class UserActivitiesDO  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id private Long useractvtyid;
	
	@Index private Long userid;
	
	/**
	 * activity type:
	 * 	 _ATVY_LKBK_CREATE 				= "LKCRTN"
	 * 	 _ATVY_LKBK_UPDTE 				= "LKUPDT"
	 * 	 _ATVY_LKBK_CMTADD 				= "LKCMNT"
	 * 	 _ATVY_LKBK_LIKE 				= "LKLIKE";	 
	 */
	
	@Index private String actvtytype;
	@Index private Date actvtdt;
	
	@Unindex private String actvtystr;
	@Unindex private String actvtyurl;

	@Unindex private String imgurl1;
	@Unindex private String imgurl2;
	@Unindex private String imgurl3;
	
	@Unindex private String username;
	
	@Unindex private Long lookbookid;	
	
	public Long getUseractvtyid() {
		return useractvtyid;
	}
	public void setUseractvtyid(Long useractvtyid) {
		this.useractvtyid = useractvtyid;
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
	public String getActvtytype() {
		return actvtytype;
	}
	public void setActvtytype(String actvtytype) {
		this.actvtytype = actvtytype;
	}
	public Date getActvtdt() {
		return actvtdt;
	}
	public void setActvtdt(Date actvtdt) {
		this.actvtdt = actvtdt;
	}
	public String getActvtystr() {
		return actvtystr;
	}
	public void setActvtystr(String actvtystr) {
		this.actvtystr = actvtystr;
	}
	public String getActvtyurl() {
		return actvtyurl;
	}
	public void setActvtyurl(String actvtyurl) {
		this.actvtyurl = actvtyurl;
	}
	public String getImgurl1() {
		return imgurl1;
	}
	public void setImgurl1(String imgurl1) {
		this.imgurl1 = imgurl1;
	}
	public String getImgurl2() {
		return imgurl2;
	}
	public void setImgurl2(String imgurl2) {
		this.imgurl2 = imgurl2;
	}
	public String getImgurl3() {
		return imgurl3;
	}
	public void setImgurl3(String imgurl3) {
		this.imgurl3 = imgurl3;
	}
	public Long getLookbookid() {
		return lookbookid;
	}
	public void setLookbookid(Long lookbookid) {
		this.lookbookid = lookbookid;
	}
	
}
