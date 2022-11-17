package com.lawencon.lawenconcommunity.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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

	@OneToOne
	@JoinColumn(name = "post_id")
	private Post post;
	
	@OneToOne
	@JoinColumn(name = "file_id")
	private File file;
}
