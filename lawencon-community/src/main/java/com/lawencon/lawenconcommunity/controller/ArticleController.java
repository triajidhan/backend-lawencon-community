package com.lawencon.lawenconcommunity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<List<Article>> getAll(@RequestParam("startPostion") int startPosition, @RequestParam("limit") int limit){
		final List<Article> articles = articleService.getAll(startPosition, limit);
		
		return new ResponseEntity<>(articles,HttpStatus.OK);
	}
	
	
	@GetMapping("article-code")
	public ResponseEntity<Article> getByArticleCode(@RequestParam("articleCode") String articleCode){
		final Article article = articleService.getByArticleCode(articleCode);
		
		return new ResponseEntity<>(article,HttpStatus.OK);
	}
}
