package com.lawencon.lawenconcommunity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.lawencon.lawenconcommunity.model.Article;
import com.lawencon.lawenconcommunity.service.ArticleService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("articles")
public class ArticleController {

	@Autowired
	private ArticleService articleService;
	
	@GetMapping()
	public ResponseEntity<List<Article>> getAll(@RequestParam("startPosition") int startPosition, @RequestParam("limit") int limit){
		final List<Article> articles = articleService.getAll(startPosition, limit);
		
		return new ResponseEntity<>(articles,HttpStatus.OK);
	}
	
	@GetMapping("{id}/get")
	public ResponseEntity<Article> getById(@PathVariable("id") String id){
		final Article article = articleService.getById(id);
		return new ResponseEntity<>(article,HttpStatus.OK);
	}
	
	@GetMapping("article-code")
	public ResponseEntity<Article> getByArticleCode(@RequestParam("articleCode") String articleCode){
		final Article article = articleService.getByArticleCode(articleCode);
		
		return new ResponseEntity<>(article,HttpStatus.OK);
	}
	
	@GetMapping("total-article")
	public ResponseEntity<Article> getTotalArticle(){
		Article article = articleService.getTotalArticle();
		
		return new ResponseEntity<>(article,HttpStatus.OK);
	}
	
	@GetMapping("is-active")
	public ResponseEntity<List<Article>> getByIsActive(@RequestParam("startPosition") int startPosition, @RequestParam("limit") int limit){
		final List<Article> articles = articleService.getByIsActive(startPosition, limit);
		
		return new ResponseEntity<>(articles,HttpStatus.OK);
	}
	
	@GetMapping("is-active-all")
	public ResponseEntity<List<Article>> getByIsActive(){
		final List<Article> articles = articleService.getByIsActive();
		
		return new ResponseEntity<>(articles,HttpStatus.OK);
	}
	
	@GetMapping("is-active-order")
	public ResponseEntity<List<Article>> getByIsActiveAndOrder(@RequestParam("startPosition") int startPosition,@RequestParam("limit") int limitPage,@RequestParam("asc") boolean ascending){
		final List<Article> articles = articleService.getByIsActiveAndOrder(startPosition, limitPage,ascending);
		
		return new ResponseEntity<>(articles,HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<ResponseMessageDto> insert(@RequestBody Article data){
		final ResponseMessageDto responseMessageDto = articleService.insert(data);
		return new ResponseEntity<>(responseMessageDto,HttpStatus.CREATED);
	}
	
	@PutMapping()
	public ResponseEntity<ResponseMessageDto> update(@RequestBody Article data){
		final ResponseMessageDto responseMessageDto = articleService.update(data);
		return new ResponseEntity<>(responseMessageDto,HttpStatus.OK);
	}
	
	
}
