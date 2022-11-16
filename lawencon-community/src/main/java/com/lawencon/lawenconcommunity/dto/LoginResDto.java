package com.lawencon.lawenconcommunity.dto;

import com.lawencon.lawenconcommunity.model.Balance;
import com.lawencon.lawenconcommunity.model.File;
import com.lawencon.lawenconcommunity.model.Industry;
import com.lawencon.lawenconcommunity.model.Position;
import com.lawencon.lawenconcommunity.model.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResDto {
	private String id;
	private String fullName;
	private String email;
	private Role role;
	private File file;
	private Position position;
	private Industry industry;
	private Balance balance;
	private String token;
}
