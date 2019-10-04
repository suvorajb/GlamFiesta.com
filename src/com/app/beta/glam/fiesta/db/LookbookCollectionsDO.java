package com.app.beta.glam.fiesta.db;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Unindex;

@Cache
@Entity
public class LookbookCollectionsDO  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id private Long id;
	
	@Index private String collection_title;
	@Unindex private String collection_desc;
	@Unindex private List<Long> featuredlooks_list;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCollection_title() {
		return collection_title;
	}
	public void setCollection_title(String collection_title) {
		this.collection_title = collection_title;
	}
	public String getCollection_desc() {
		return collection_desc;
	}
	public void setCollection_desc(String collection_desc) {
		this.collection_desc = collection_desc;
	}
	public List<Long> getFeaturedlooks_list() {
		return featuredlooks_list;
	}
	public void setFeaturedlooks_list(List<Long> featuredlooks_list) {
		this.featuredlooks_list = featuredlooks_list;
	}
}
