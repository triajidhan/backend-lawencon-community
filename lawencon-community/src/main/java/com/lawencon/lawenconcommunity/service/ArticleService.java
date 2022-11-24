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
	@Autowired
	private GenerateService generateService;
	
	public List<Article> getAll() {
		final List<Article> articles = articleDao.getAll(Article.class);

		return articles;
	}
	
	public List<Article> getAll(Integer startPosition, Integer limitPage) {
		final List<Article> articles = articleDao.getAll(Article.class, startPosition, limitPage);

		return articles;
	}

	public Article getById(String id) {
		return articleDao.getByIdAndDetach(Article.class, id);	
	}
	
	public Article getByArticleCode(String articleCode) {
		return articleDao.getByArticleCode(articleCode).get();
	}
	
	
	public Article getTotalArticle() {
		return articleDao.getTotalArticle();
	}
	
	public List<Article> getByIsActive(int startPosition,int limit){
		final List<Article> articles = articleDao.getByIsActive(startPosition, limit);
		
		return articles;
	}
	
	public List<Article> getByIsActive(){
		final List<Article> articles = articleDao.getByIsActive();
		
		return articles;
	}
	
	public List<Article> getByIsActiveAndOrder(int startPosition,int limit,boolean ascending){
		final List<Article> articles = articleDao.getByIsActiveAndOrder(startPosition, limit,ascending);
		
		return articles;
	}

	public ResponseMessageDto insert(Article data) {

		File fileInsert = new File();
		ResponseMessageDto responseMessageDto = new ResponseMessageDto();
		responseMessageDto.setMessage("Failed to create the article!");
		valInsert(data);
		try {
			begin();
			if(fileInsert != null) {
				fileInsert = fileDao.save(data.getFile());				
				data.setFile(fileInsert);
			}
			data.setArticleCode(generateService.generate(5));
			articleDao.save(data);
			responseMessageDto.setMessage("Article created successfully!");
			commit();
		} catch (Exception e) {
			responseMessageDto.setMessage("Failed to create the article!");
			e.printStackTrace();
		}
		return responseMessageDto;

	}

	public void valInsert(Article data) {
		valDuplicateBk(data);
		valNotNullInsert(data);
		valIdNullInsert(data);
	}

	public void valIdNullInsert(Article data) {
		if (data.getId() != null) {
			throw new RuntimeException("Id Must Be Empty!");
		}

		if (data.getFile() != null) {
			if(data.getFile().getId() != null) {
				throw new RuntimeException("Id Must Be Empty!");				
			}
		}
	}

	public void valNotNullInsert(Article data) {
		if (data.getTitle() == null) {
			throw new RuntimeException("Title Required!");
		}
	}

	public void valDuplicateBk(Article data) {
		if (articleDao.getByArticleCode(data.getArticleCode()).isPresent()) {
			throw new RuntimeException("Duplicate article code detected!");
		}
	}

	public ResponseMessageDto update(Article data) {
		File fileInsert = new File();
		ResponseMessageDto responseMessageDto = new ResponseMessageDto();
		responseMessageDto.setMessage("Failed!");
		Article articleUpdate = new Article();
		Article article = articleDao.getByIdAndDetach(Article.class, data.getId());
		valUpdate(data);
		begin();
		try {
			articleUpdate = article;
			if (article != null) {
				if(data.getTitle() != null) {
					articleUpdate.setTitle(data.getTitle());
				}
				
				if(data.getContents() != null) {
					articleUpdate.setContents(data.getContents());
				}
				
				if(data.getIsActive() !=null) {
					articleUpdate.setIsActive(data.getIsActive());
				}
				
				if(data.getFile() != null) {
					fileInsert = fileDao.save(data.getFile());
					data.setFile(fileInsert);
					data.setArticleCode(null);
					articleUpdate.setFile(fileInsert);
				}
				articleDao.saveAndFlush(articleUpdate);
				responseMessageDto.setMessage("Success");
			}else {
				throw new RuntimeException("User Not Found!");
			}
		} catch (Exception e) {
			responseMessageDto.setMessage("Failed!");
			e.printStackTrace();
		}
		commit();
		return responseMessageDto;
	}

	public void valUpdate(Article data) {
		valFoundId(data);
	}

	public void valFoundId(Article data) {
		if (articleDao.getByIdAndDetach(Article.class, data.getId()) == null) {
			throw new RuntimeException("Account Not Found");
		}
	}

}
