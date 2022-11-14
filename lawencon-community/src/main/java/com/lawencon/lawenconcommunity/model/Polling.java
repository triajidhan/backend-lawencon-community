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
@Table(name = "tb_post_attachment")
@Getter
@Setter
public class Polling extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5710340391615076988L;

	@Column(name = "poll_content",length = 50)
	private String pollContent;
	
	@Column(name = "total_poll")
	private Integer totalPoll;
	
	@ManyToOne
	@JoinColumn(name = "tb_post")
	private Post post;
	
}
