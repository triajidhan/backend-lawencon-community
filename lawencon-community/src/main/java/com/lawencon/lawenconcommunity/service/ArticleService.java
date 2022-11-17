package com.lawencon.lawenconcommunity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.lawenconcommunity.dao.ArticleDao;
import com.lawencon.lawenconcommunity.dao.FileDao;
import com.lawencon.lawenconcommunity.dto.ResponseMessageDto;
import com.lawencon.lawenconcommunity.model.Article;
import com.lawencon.lawenconcommunity.model.File;

@Service
public class ArticleService extends BaseCoreService {

	@Autowired
	private ArticleDao articleDao;
	@Autowired
	private FileDao fileDao;

	public List<Article> getAll(Integer startPosition, Integer limitPage) {
		final List<Article> articles = articleDao.getAll(Article.class, startPosition, limitPage);

		return articles;
	}

	public Article getByArticleCode(String articleCode) {
		return articleDao.getByArticleCode(articleCode).get();
	}

	public ResponseMessageDto insert(Article data) {
		
		File fileInsert = new File();
		ResponseMessageDto responseMessageDto = new ResponseMessageDto();
		responseMessageDto.setMessage("Failed to create the article!");
		valInsert(data);
		try {
			begin();
			fileInsert = fileDao.save(data.getFile());
			data.setFile(fileInsert);
			articleDao.save(data);	
			responseMessageDto.setMessage("Article created successfully!");
			commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseMessageDto;
		
	}
	
	public void valInsert(Article data) {
//		valDuplicateBk(data);
		valNotNullInsert(data);
		valIdNullInsert(data);
	}
	
	public void valIdNullInsert(Article data) {
		if (data.getId() != null) {
			throw new RuntimeException("Id Must Be Empty!");
		}

		if (data.getFile().getId() != null) {
			throw new RuntimeException("Id Must Be Empty!");
		}
	}
	
	public void valNotNullInsert(Article data) {
		if (data.getTitle()==null) {
			throw new RuntimeException("Title Required!");
		}
	}
	
	public void valDuplicateBk(Article data) {
		if(articleDao.getByArticleCode(data.getArticleCode()).get() != null) {
			throw new RuntimeException("Duplicate article code detected!");
		}
	}

}
