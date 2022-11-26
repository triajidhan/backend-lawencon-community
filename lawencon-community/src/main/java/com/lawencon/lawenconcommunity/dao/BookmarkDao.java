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
		StringBuilder sql = new StringBuilder();
		
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
	public List<Bookmark> getByUser(String userId,int startPosition,int limit){
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * ")
		.append("FROM tb_bookmark tb ")
		.append("INNER JOIN tb_user tu ON tu.id = tb.user_id ")
		.append("INNER JOIN tb_post tp ON tp.id = tb.post_id ")
		.append("WHERE user_id  = :userId AND tb.is_active = true ")
		.append("LIMIT :limit OFFSET :startPosition");
		
		List<Bookmark> bookmarks = ConnHandler.getManager().createNativeQuery(sql.toString(),Bookmark.class)
				.setParameter("userId", userId)
				.setParameter("startPosition", startPosition)
				.setParameter("limit", limit)
				.getResultList();
		
		return bookmarks;
	}
	
	@SuppressWarnings("unchecked")
	public List<Bookmark> getByUser(String userId,int startPosition,int limit,boolean isAscending){
		StringBuilder sql = new StringBuilder();
		
		String ascending = (isAscending) ? "ASC ":"DESC ";
		
		sql.append("SELECT * ")
		.append("FROM tb_bookmark tb ")
		.append("INNER JOIN tb_user tu ON tu.id = tb.user_id ")
		.append("INNER JOIN tb_post tp ON tp.id = tb.post_id ")
		.append("WHERE user_id  = :userId AND tb.is_active = true ")
		.append("ORDER BY tb.created_at ")
		.append(ascending)
		.append("LIMIT :limit OFFSET :startPosition");
		
		List<Bookmark> bookmarks = ConnHandler.getManager().createNativeQuery(sql.toString(),Bookmark.class)
				.setParameter("userId", userId)
				.setParameter("startPosition", startPosition)
				.setParameter("limit", limit)
				.getResultList();
		
		return bookmarks;
	}
	
	public Bookmark getTotalByUser(String userId){
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT count(*) ")
		.append("FROM tb_bookmark tb ")
		.append("INNER JOIN tb_user tu ON tu.id = tb.user_id ")
		.append("INNER JOIN tb_post tp ON tp.id = tb.post_id ")
		.append("WHERE tb.user_id  = :userId AND tb.is_active = true");
		
		Object objBookmark = null;
		Bookmark bookmark = null;
		
		try {			
			objBookmark = ConnHandler.getManager().createNativeQuery(sql.toString())
					.setParameter("userId", userId)
					.getSingleResult();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		if(objBookmark != null) {
			Object obj = (Object) objBookmark;
			
			bookmark = new Bookmark();
			bookmark.setCountOfBookmark(Integer.parseInt(obj.toString()));
		}
		
		return bookmark;
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
	public List<Bookmark> getByPost(String postId,int startPosition,int limit){
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * ")
		.append("FROM tb_bookmark tb ")
		.append("INNER JOIN tb_user tu ON tu.id = tb.user_id ")
		.append("INNER JOIN tb_post tp ON tp.id = tb.post_id ")
		.append("WHERE post_id  = :postId AND tb.is_active = true ")
		.append("LIMIT :limit OFFSET :startPosition");
		
		List<Bookmark> bookmarks = ConnHandler.getManager().createNativeQuery(sql.toString(),Bookmark.class)
				.setParameter("postId", postId)
				.setParameter("startPosition", startPosition)
				.setParameter("limit", limit)
				.getResultList();
		
		return bookmarks;
	}
	
	public Bookmark getTotalByPost(String postId){
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT count(*) ")
		.append("FROM tb_bookmark tb ")
		.append("INNER JOIN tb_user tu ON tu.id = tb.user_id ")
		.append("INNER JOIN tb_post tp ON tp.id = tb.post_id ")
		.append("WHERE tb.post_id = :postId AND tb.is_active = true");
		
		Object objBookmark = null;
		Bookmark bookmark = new Bookmark();
		
		try {			
			objBookmark = ConnHandler.getManager().createNativeQuery(sql.toString())
					.setParameter("postId", postId)
					.getSingleResult();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		if(objBookmark != null) {
			Object obj = (Object) objBookmark;
			
			bookmark.setCountOfBookmark(Integer.parseInt(obj.toString()));
		}
		
		return bookmark;
	}
	
	public Optional<Bookmark> getUserBookmarkPost(String userId,String postId){
		StringBuilder sql = new StringBuilder();
		
		sql.append("Select count(*), ")
		.append("(SELECT id from tb_bookmark where user_id = :userId AND post_id = :postId) as id ")
		.append("FROM tb_bookmark WHERE post_id = :postId AND is_active = true");
		
		
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
			
			if(objArr[1] != null) {				
				bookmark.setBookmarkId(objArr[1].toString());
				bookmark.setId(objArr[1].toString());
				
			}
			

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
	
	@SuppressWarnings("unchecked")
	public List<Bookmark> getByIsActive(){
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * FROM tb_bookmark tb ")
		.append("INNER JOIN tb_user tu ON tu.id = tb.user_id ")
		.append("INNER JOIN tb_post tp ON tp.id = tb.post_id ")
		.append("WHERE tb.is_active = true ");
		
		final List<Bookmark> objResultBookmarks = ConnHandler.getManager().createNativeQuery(sql.toString(),Bookmark.class)

				.getResultList();
		
		return objResultBookmarks;
	}
}
