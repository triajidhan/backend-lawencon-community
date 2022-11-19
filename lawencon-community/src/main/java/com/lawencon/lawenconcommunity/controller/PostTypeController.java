package com.lawencon.lawenconcommunity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.lawenconcommunity.model.PostType;
import com.lawencon.lawenconcommunity.service.PostTypeService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name="bearerAuth")
@RestController
@RequestMapping("post-types")
public class PostTypeController {

	@Autowired
	private PostTypeService postTypeService;
	
	@GetMapping()
	public ResponseEntity<List<PostType>> getAll(){
		List<PostType> postTypes = postTypeService.getAll();
		
		return new ResponseEntity<>(postTypes,HttpStatus.OK);
	}
}
