package com.lawencon.lawenconcommunity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_comment")
@Getter
@Setter
public class Comment extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5656380880437262410L;

	@Column(name ="comment_body")
	private String commentBody;
	
	@ManyToOne
	@JoinColumn(name = "tb_post")
	private Post postId;
	
	@ManyToOne
	@JoinColumn(name = "tb_user")
	private User user;
 
}
