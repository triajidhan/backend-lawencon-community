package com.lawencon.lawenconcommunity.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.lawenconcommunity.dto.UserInsertResDto;
import com.lawencon.lawenconcommunity.model.User;
import com.lawencon.lawenconcommunity.service.UserSevice;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("users")
public class UsersController {
	private final UserSevice userSevice;

	public UsersController(UserSevice userSevice) {
		this.userSevice = userSevice;
	}
	
	@GetMapping()
	public ResponseEntity<List<User>> getAll(@RequestParam("startPosition") int startPosition, @RequestParam("limit") int limit){
		final List<User> users = userSevice.getAll(startPosition, limit);
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<User> getById(@PathVariable("id") String id ){
		final User user = userSevice.getById(id);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@GetMapping("role")
	public ResponseEntity<List<User>> getByRole(@RequestParam("roleCode") String roleCode){
		final List<User> users = userSevice.getByRoleCode(roleCode);
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@GetMapping("email")
	public ResponseEntity<List<User>> getByEmail(@RequestParam("email") String email){
		final List<User> users = userSevice.getByRoleCode(email);
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<UserInsertResDto> insert(@RequestBody User data){
		final UserInsertResDto userInsertResDto = userSevice.insert(data);
		return new ResponseEntity<>(userInsertResDto, HttpStatus.OK);
	}
}
