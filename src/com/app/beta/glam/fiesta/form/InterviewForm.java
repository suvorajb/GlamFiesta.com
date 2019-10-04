package com.app.beta.glam.fiesta.form;

import java.io.Serializable;

public class InterviewForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String usernm;
	private String intrvwtitle;

	private String intrvwtxt1;
	private String fshnphoto1;

	private String intrvwtxt2;
	private String fshnphoto2;

	private String intrvwtxt3;
	private String fshnphoto3;

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

	@Override
	public String toString() {
		return "InterviewForm [usernm=" + usernm + ", intrvwtitle=" + intrvwtitle + ", intrvwtxt1=" + intrvwtxt1
				+ ", fshnphoto1=" + fshnphoto1 + ", intrvwtxt2=" + intrvwtxt2 + ", fshnphoto2=" + fshnphoto2
				+ ", intrvwtxt3=" + intrvwtxt3 + ", fshnphoto3=" + fshnphoto3 + "]";
	}
	
	
}
