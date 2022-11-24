package com.lawencon.lawenconcommunity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.lawencon.base.BaseEntity;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "tb_post_type")
@Getter
@Setter
public class PostType extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1371887644871388057L;
	@Column(name = "post_type_code",length = 5)
	private String postTypeCode;
	
	@Column(name ="post_type_name",length=50)
	private String postTypeName;
	
	@Transient
	private Integer countOfPostType;
	
}
