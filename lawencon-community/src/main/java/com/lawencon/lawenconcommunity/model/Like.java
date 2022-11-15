package com.lawencon.lawenconcommunity.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

	@ManyToOne
	@JoinColumn(name = "tb_like")
	private User userId;
	
	@ManyToOne
	@JoinColumn(name = "tb_post")
	private Post postId;
	
	
}
