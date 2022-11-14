package com.lawencon.lawenconcommunity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_article",uniqueConstraints = {
	@UniqueConstraint(columnNames = "activity_type_code")
})
@Getter
@Setter
public class Article {

	@Column(name="article_code",length=5)
	private String postCode;
	
	@Column(name="title",length=50)
	private String title;
	
	@Column(name="contents")
	private String contents;
	
	@ManyToOne
	@JoinColumn(name = "file_id")
	private File file;
}
