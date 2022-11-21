package com.lawencon.lawenconcommunity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.lawenconcommunity.model.Bookmark;

@Repository
public class BookmarkDao extends AbstractJpaDao{

	@SuppressWarnings("unchecked")
	public List<Bookmark> getByUser(String userId){
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * ")
		.append("FROM tb_bookmark tb ")
		.append("INNER JOIN tb_user tu ON tu.id = tb.user_id ")
		.append("INNER JOIN tb_post tp ON tp.id = tb.post_id ")
		.append("WHERE user_id  = :userId AND tb.is_active = true");
		
		List<Bookmark> bookmarks = ConnHandler.getManager().createNativeQuery(sql.toString(),Bookmark.class)
				.setParameter("userId", userId)
				.getResultList();
		
		return bookmarks;
	}
	
	@SuppressWarnings("unchecked")
	public List<Bookmark> getByPost(String postId){
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * ")
		.append("FROM tb_bookmark tb ")
		.append("INNER JOIN tb_user tu ON tu.id = tb.user_id ")
		.append("INNER JOIN tb_post tp ON tp.id = tb.post_id ")
		.append("WHERE post_id  = :postId AND tb.is_active = true");
		
		List<Bookmark> bookmarks = ConnHandler.getManager().createNativeQuery(sql.toString(),Bookmark.class)
				.setParameter("postId", postId)
				.getResultList();
		
		return bookmarks;
	}
	
	@SuppressWarnings("unchecked")
	public List<Bookmark> getByIsActive(int startPosition,int limit){
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * FROM tb_bookmark tb ")
		.append("INNER JOIN tb_user tu ON tu.id = tb.user_id ")
		.append("INNER JOIN tb_post tp ON tp.id = tb.post_id ")
		.append("WHERE tb.is_active = true ")
		.append("LIMIT :limit OFFSET :startPosition");
		
		final List<Bookmark> objResultBookmarks = ConnHandler.getManager().createNativeQuery(sql.toString(),Bookmark.class)
				.setParameter("startPosition", startPosition)
				.setParameter("limit", limit)
				.getResultList();
		
		return objResultBookmarks;
	}
}
