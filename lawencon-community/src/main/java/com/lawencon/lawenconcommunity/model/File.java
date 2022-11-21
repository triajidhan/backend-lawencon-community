package com.lawencon.lawenconcommunity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_file")
@Getter
@Setter
public class File extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2193244827181488175L;
	
	@Column(name = "file")
	private String files;
	
	@Column(name = "ext", length = 5)
	private String ext;
}
