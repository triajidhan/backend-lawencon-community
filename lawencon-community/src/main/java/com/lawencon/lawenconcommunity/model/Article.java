package com.lawencon.lawenconcommunity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_article",uniqueConstraints = {
	@UniqueConstraint(columnNames = "article_code")
})
@Getter
@Setter
public class Article extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3262674835335265266L;

	@Column(name="article_code",length=5)
	private String articleCode;
	
	@Column(name="title",length=50)
	private String title;
	
	@Column(name="contents")
	private String contents;
	
	@ManyToOne
	@JoinColumn(name = "file_id")
	private File file;
}
