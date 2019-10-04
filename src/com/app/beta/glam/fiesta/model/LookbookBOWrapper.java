package com.app.beta.glam.fiesta.model;

import java.util.List;

public class LookbookBOWrapper {
	
	private List<LookbookBO> lookbooklist = null;
	private String next_page = null;
	private String prev_page = null;
	
	@Override
	public String toString() {

		return "LookbookBOWrapper :- lookbooklist [" + lookbooklist + "] next_page [" + next_page + "] ";
	}
	
	public List<LookbookBO> getLookbooklist() {
		return lookbooklist;
	}
	
	public void setLookbooklist(List<LookbookBO> lookbooklist) {
		this.lookbooklist = lookbooklist;
	}
	
	public String getNext_page() {
		return next_page;
	}
	
	public void setNext_page(String next_page) {
		this.next_page = next_page;
	}
	
	public String getPrev_page() {
		return prev_page;
	}

	public void setPrev_page(String prev_page) {
		this.prev_page = prev_page;
	}
}
