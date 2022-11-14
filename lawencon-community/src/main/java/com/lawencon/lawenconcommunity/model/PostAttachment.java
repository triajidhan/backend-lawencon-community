package com.lawencon.lawenconcommunity.model;

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
public class PostAttachment extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8354098971607019627L;

	@ManyToOne
	@JoinColumn(name = "tb_post")
	private Post post;
	
	@ManyToOne
	@JoinColumn(name = "tb_file")
	private File file;
}
