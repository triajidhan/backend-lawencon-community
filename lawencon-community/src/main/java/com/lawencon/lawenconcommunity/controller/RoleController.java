package com.lawencon.lawenconcommunity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.lawenconcommunity.model.Role;
import com.lawencon.lawenconcommunity.service.RoleService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name="bearerAuth")
@RestController
@RequestMapping("roles")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@GetMapping
	public ResponseEntity<List<Role>> getAll(){
		List<Role> roles = roleService.getAll();
		
		return new ResponseEntity<>(roles,HttpStatus.OK);
	}
	
	@GetMapping("{roleCode}/get")
	public ResponseEntity<Role> getByRoleCode(@PathVariable("roleCode") String roleCode){
		Role role = roleService.getByRoleCode(roleCode);
		
		return new ResponseEntity<>(role,HttpStatus.OK);
	}
}
