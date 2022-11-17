package com.lawencon.lawenconcommunity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.lawenconcommunity.dao.ArticleDao;
import com.lawencon.lawenconcommunity.model.Article;
import com.lawencon.lawenconcommunity.model.Post;

@Service
public class ArticleService extends BaseCoreService{
	
	@Autowired
	private ArticleDao articleDao;
	
	public List<Article> getAll(Integer startPosition, Integer limitPage){
		final List<Article> articles = articleDao.getAll(Article.class, startPosition, limitPage);
		
		return articles;
	}
	
	
	public Article getByArticleCode(String articleCode) {
		return articleDao.getByArticleCode(articleCode).get();
	}
	
}
