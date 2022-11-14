package com.lawencon.lawenconcommunity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Table(name = "tb_role",uniqueConstraints = {
		@UniqueConstraint(columnNames = "role_code")
})
@Entity
@Getter
@Setter
public class Role extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2815172980955681636L;

	@Column(name ="role_code",length=5)
	private String roleCode;
	
	@Column(name ="role_name",length=50)
	private String roleName;
}
