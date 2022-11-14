package com.lawencon.lawenconcommunity.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_comment_attachment")
@Getter
@Setter
public class CommentAttachment extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2203761108088123714L;

	@ManyToOne
	@JoinColumn(name = "tb_comment")
	private String commentId;
	
	@ManyToOne
	@JoinColumn(name = "tb_file")
	private File file;
	

	
}
