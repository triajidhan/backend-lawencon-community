package com.lawencon.lawenconcommunity.controller;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.lawenconcommunity.dto.LoginResDto;
import com.lawencon.lawenconcommunity.model.User;
import com.lawencon.lawenconcommunity.service.UserSevice;
import com.lawencon.util.JwtUtil;
import com.lawencon.util.JwtUtil.ClaimKey;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("login")
public class LoginController {
	private final JwtUtil jwtUtil;
	private final UserSevice userService;
	private final AuthenticationManager authenticationManager;
	public LoginController(JwtUtil jwtUtil, UserSevice userService, AuthenticationManager authenticationManager){
		this.jwtUtil = jwtUtil;
		this.userService = userService;
		this.authenticationManager = authenticationManager;
	}
	
	@PostMapping
    public ResponseEntity<LoginResDto> login(@RequestBody User data) {
		Authentication auth = new UsernamePasswordAuthenticationToken(data.getEmail(), data.getPass());
        authenticationManager.authenticate(auth);
        final User user = userService.getByEmail(data.getEmail());

        Map<String, Object> claims = new HashMap<>();
        claims.put(ClaimKey.ID.name(), user.getId());
        claims.put(ClaimKey.ROLE.name(), user.getRole().getRoleCode());

        LoginResDto res = new LoginResDto();
        res.setId(user.getId());
        res.setFullName(user.getFullname());
        res.setRole(user.getRole());
        res.setToken(jwtUtil.generateToken(claims, Duration.ofHours(1)));
        return new ResponseEntity<LoginResDto>(res, HttpStatus.OK);
    }
	
}
