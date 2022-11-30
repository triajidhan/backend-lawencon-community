package com.lawencon.lawenconcommunity.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_bookmark",uniqueConstraints = {
		@UniqueConstraint(columnNames = {"user_id","post_id"})
})
@Getter
@Setter
public class Bookmark extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4335686179070314947L;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToOne
	@JoinColumn(name = "post_id")
	private Post post;
	
	
	@Transient
	private Integer countOfBookmark;
	
	@Transient
	private String bookmarkId;
}
