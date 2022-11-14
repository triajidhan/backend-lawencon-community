package com.lawencon.lawenconcommunity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

import lombok.Getter;
import lombok.Setter;
@Table(name = "tb_post_type")
@Entity
@Getter
@Setter
public class PostType extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1371887644871388057L;
	@Column(name = "post_type_code", unique = true)
	private String postTypeCode;
	
	@Column(name ="post_type_name")
	private String postTypeName;
	
}