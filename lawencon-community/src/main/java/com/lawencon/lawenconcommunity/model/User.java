package com.lawencon.lawenconcommunity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_user",uniqueConstraints = {
		@UniqueConstraint(columnNames = "email"),
		@UniqueConstraint(columnNames = {"full_name","email"})
})
@Getter
@Setter
public class User extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3790180587767255458L;

	@Column(name="full_name",length=30)
	private String fullname;
	
	@Column(name="email",length=50)
	private String email;
	
	@Column(name="pass")
	private String pass;
	
	@Column(name="company", length=100)
	private String company;
	
	@OneToOne
	@JoinColumn(name = "industry_id")
	private Industry industry;
	
	@OneToOne
	@JoinColumn(name = "position_id")
	private Position position;
	
	@OneToOne
	@JoinColumn(name = "balance_id")
	private Balance balance;
	
	@OneToOne
	@JoinColumn(name = "role_id")
	private Role role;
	
	@OneToOne
	@JoinColumn(name = "file_id")
	private File file;
	
}
