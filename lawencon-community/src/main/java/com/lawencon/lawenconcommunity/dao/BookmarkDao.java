package com.lawencon.lawenconcommunity.dao;

import java.util.List;
import java.util.Optional;

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
	
	public int getTotalByUser(String userId){
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT count(*) ")
		.append("FROM tb_bookmark tb ")
		.append("INNER JOIN tb_user tu ON tu.id = tb.user_id ")
		.append("INNER JOIN tb_post tp ON tp.id = tb.post_id ")
		.append("WHERE tb.user_id  = :userId AND tb.is_active = true");
		
		Object objBookmark = null;
		int total = 0;
		
		try {			
			objBookmark = ConnHandler.getManager().createNativeQuery(sql.toString())
					.setParameter("userId", userId)
					.getSingleResult();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		if(objBookmark != null) {
			Object obj = (Object) objBookmark;
			
			total = Integer.parseInt(obj.toString());
		}
		
		return total;
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
	

	public int getTotalByPost(String postId){
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT count(*) ")
		.append("FROM tb_bookmark tb ")
		.append("INNER JOIN tb_user tu ON tu.id = tb.user_id ")
		.append("INNER JOIN tb_post tp ON tp.id = tb.post_id ")
		.append("WHERE tb.post_id = :postId AND tb.is_active = true");
		
		Object objBookmark = null;
		int total = 0;
		
		try {			
			objBookmark = ConnHandler.getManager().createNativeQuery(sql.toString())
					.setParameter("postId", postId)
					.getSingleResult();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		if(objBookmark != null) {
			Object obj = (Object) objBookmark;
			
			total = Integer.parseInt(obj.toString());
		}
		
		return total;
	}
	
	public Optional<Bookmark> getUserBookmarkPost(String userId,String postId){
		StringBuilder sql = new StringBuilder();
		
		sql.append("Select count(*), ")
		.append("(SELECT count(*) from tb_bookmark where user_id = :userId AND post_id = :postId) as user_id ")
		.append("FROM tb_bookmark WHERE post_id = :postId");
		
		
		Object objBookmark = null;

		Optional<Bookmark> opt = Optional.ofNullable(null);

		
		try {
			objBookmark = ConnHandler.getManager().createNativeQuery(sql.toString())
					.setParameter("userId", userId)
					.setParameter("postId", postId)
					.getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(objBookmark != null) {
			Object[] objArr = (Object[]) objBookmark;
			
			Bookmark bookmark = new Bookmark();
			bookmark.setCountOfBookmark(Integer.parseInt(objArr[0].toString()));
			bookmark.setBookmarkId(objArr[1].toString());
			

			opt = Optional.ofNullable(bookmark);
		}
		return opt;
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
