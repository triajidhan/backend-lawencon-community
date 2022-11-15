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
	
	@ManyToOne
	@JoinColumn(name = "industry_id")
	private Industry industry;
	
	@ManyToOne
	@JoinColumn(name = "position_id")
	private Position position;
	
	@ManyToOne
	@JoinColumn(name = "balance_id")
	private Balance balance;
	
	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;
	
	@ManyToOne
	@JoinColumn(name = "file_id")
	private File file;
	
	@ManyToOne
	@JoinColumn(name = "verification_id")
	private Verification verification;
}
