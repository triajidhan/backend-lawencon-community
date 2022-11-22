package com.lawencon.lawenconcommunity.model;

import java.util.List;

import javax.persistence.Column;
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
@Table(name = "tb_post",uniqueConstraints = {
	@UniqueConstraint(columnNames = "post_code")
})
@Getter
@Setter
public class Post extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2658799131267341300L;

	@Column(name="post_code",length=5)
	private String postCode;
	
	@Column(name="title",length=50)
	private String title;
	
	@Column(name="contents")
	private String contents;
	
	@Column(name="title_poll")
	private String titlePoll;	
	
	@OneToOne
	@JoinColumn(name="post_type_id")
	private PostType postType;
	
	@Transient
	private List<File> file;
	
	@Transient
	private List<String> pollContent;
	
	
	@Transient
	private Integer countOfPost;
}
