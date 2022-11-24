package com.lawencon.lawenconcommunity.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.lawenconcommunity.model.Article;
import com.lawencon.lawenconcommunity.model.File;

@Repository
public class ArticleDao extends AbstractJpaDao{

	public Optional<Article> getByArticleCode(String articleCode){
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT ")
		.append("id, article_code, title, file_id, created_by, created_at, versions, is_active ")
		.append("FROM tb_article ")
		.append("WHERE article_code = :articleCode AND is_active = true");
		
		Object articleObjs = null;
		
		Optional<Article> articleOpt = Optional.ofNullable(null);
		
		try {
			articleObjs = ConnHandler.getManager().createNativeQuery(sql.toString())
					.setParameter("articleCode",articleCode).getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(articleObjs != null) {
			Object[] objArr = (Object[]) articleObjs;
			
			
			Article article = new Article();
			File file = new File();
			
			article.setId(objArr[0].toString());
			article.setArticleCode(objArr[1].toString());
			article.setTitle(objArr[2].toString());
			
			file.setId(objArr[3].toString());
			
			article.setFile(file);
			
			article.setCreatedBy(objArr[4].toString());
			article.setCreatedAt(Timestamp.valueOf(objArr[5].toString()).toLocalDateTime());
			article.setVersion(Integer.parseInt(objArr[6].toString()));
			article.setIsActive(Boolean.parseBoolean(objArr[7].toString()));
			
			articleOpt = Optional.ofNullable(article);
		}
		
		return articleOpt;
	}
	
	public Article getTotalArticle() {
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT count(*) from tb_article ")
		.append("WHERE is_active = true");
		
		
		Object objArticle = null;
		Article article = null;
		try {
			objArticle = ConnHandler.getManager().createNativeQuery(sql.toString())
					.getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(objArticle != null) {
			Object obj = (Object) objArticle;
			
			article = new Article();
			
			article.setCountOfArticle(Integer.parseInt(obj.toString()));
		}
		
		return article;
	}
	
	@SuppressWarnings("unchecked")
	public List<Article> getByIsActive(int startPosition,int limit){
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * ")
		.append("FROM tb_article ")
		.append("WHERE is_active = true ")
		.append("LIMIT :limit OFFSET :startPosition");
		
		final List<Article> objResultArticles = ConnHandler.getManager().createNativeQuery(sql.toString(),Article.class)
				.setParameter("startPosition", startPosition)
				.setParameter("limit", limit)
				.getResultList();
		
		return objResultArticles;
	}
	
	@SuppressWarnings("unchecked")
	public List<Article> getByIsActive(){
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * ")
		.append("FROM tb_article ")
		.append("WHERE is_active = true ");
		
		final List<Article> objResultArticles = ConnHandler.getManager().createNativeQuery(sql.toString(),Article.class)
				.getResultList();
		
		return objResultArticles;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Article> getByIsActiveAndOrder(int startPosition,int limit,boolean isAscending){
		final StringBuilder sql = new StringBuilder();
		
		String ascending = (isAscending) ? "ASC ":"DESC ";
		
		sql.append("SELECT * ")
		.append("FROM tb_article ")
		.append("WHERE is_active = true ")
		.append("ORDER BY created_at ")
		.append(ascending)
		.append("LIMIT :limit OFFSET :startPosition");
		
		final List<Article> objResult = ConnHandler.getManager().createNativeQuery(sql.toString(),Article.class)
				.setParameter("startPosition", startPosition)
				.setParameter("limit", limit)
				.getResultList();
		
		return objResult;
	}
}
