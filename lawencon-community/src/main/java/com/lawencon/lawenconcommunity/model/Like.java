package com.lawencon.lawenconcommunity.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

import lombok.Getter;

@Entity
@Table(name = "tb_like")
@Getter
public class Like extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2153507728509756122L;

	@OneToOne
	@JoinColumn(name = "like_id")
	private User user;
	
	@OneToOne
	@JoinColumn(name = "post_id")
	private Post post;
	
	
}
