package com.lawencon.lawenconcommunity.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_bookmark")
@Getter
@Setter
public class Bookmark extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4335686179070314947L;

	@OneToOne
	@JoinColumn(name = "tb_user")
	private User userId;
	
	@OneToOne
	@JoinColumn(name = "tb_post")
	private Post postId;
}
