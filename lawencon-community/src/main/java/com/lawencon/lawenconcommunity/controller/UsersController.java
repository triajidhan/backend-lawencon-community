package com.lawencon.lawenconcommunity.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.lawenconcommunity.dto.ResponseMessageDto;
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
	
	@GetMapping("id/{id}")
	public ResponseEntity<User> getById(@PathVariable("id") String id ){
		final User user = userSevice.getById(id);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@GetMapping("role")
	public ResponseEntity<List<User>> getByRole(@RequestParam("roleCode") String roleCode,@RequestParam int startPosition,@RequestParam int limit){
		final List<User> users = userSevice.getByRoleCode(roleCode,startPosition,limit);
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@GetMapping("email")
	public ResponseEntity<User> getByEmail(@RequestParam("email") String email){
		final User users = userSevice.getByEmail(email);
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@GetMapping("total-user")
	public ResponseEntity<User> getTotalUser(){
		User user = userSevice.getTotalUser();
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@GetMapping("total-user/role-code")
	public ResponseEntity<User> getTotalUserByRole(@RequestParam("roleCode") String roleCode){
		final User user = userSevice.getTotalUserByRole(roleCode);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@GetMapping("total-user-subscribe")
	public ResponseEntity<User> getTotalUserSubscribe(){
		User user = userSevice.getTotalUserSubscribe();
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@GetMapping("is-active")
	public ResponseEntity<List<User>> getByIsActive(@RequestParam("startPosition") int startPosition, @RequestParam("limit") int limit){
		final List<User> users = userSevice.getByIsActive(startPosition, limit);
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<ResponseMessageDto> registerAdmin(@RequestBody User data){
		final ResponseMessageDto userInsertResDto = userSevice.insertWithLogin(data);
		return new ResponseEntity<>(userInsertResDto, HttpStatus.CREATED);
	}
	
	@PostMapping("register")
	public ResponseEntity<ResponseMessageDto> registerUser(@RequestBody User data){
		final ResponseMessageDto userInsertResDto = userSevice.insertWithoutLogin(data);
		return new ResponseEntity<>(userInsertResDto, HttpStatus.CREATED);
	}
	
	@PutMapping()
	public ResponseEntity<ResponseMessageDto> update(@RequestBody User data){
		final ResponseMessageDto message = userSevice.update(data);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
}
