package com.lawencon.lawenconcommunity.dao;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.lawenconcommunity.model.Article;
import com.lawencon.lawenconcommunity.model.File;

@Repository
public class ArticleDao extends AbstractJpaDao{

	public Optional<Article> getByArticleCode(final String articleCode){
		final StringBuilder sql = new StringBuilder();
		
		//id, article_code, title, file_id,created_by,created_at,versions
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
			
			// id, article_code, title, file_id, created_by, created_at, versions
			final Article article = new Article();
			final File file = new File();
			
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
}
