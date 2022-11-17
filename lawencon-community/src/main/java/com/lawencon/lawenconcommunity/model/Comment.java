package com.lawencon.lawenconcommunity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
	
	@OneToOne
	@JoinColumn(name = "post_id")
	private Post post;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
 
}
