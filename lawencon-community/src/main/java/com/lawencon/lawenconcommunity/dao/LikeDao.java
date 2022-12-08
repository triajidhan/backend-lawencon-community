package com.lawencon.lawenconcommunity.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.lawenconcommunity.model.Like;
import com.lawencon.lawenconcommunity.service.PostService;

@Repository
public class LikeDao extends AbstractJpaDao{
	
	@SuppressWarnings("unchecked")
	public List<Like> getByUser(String userId,int startPosition,int limit,boolean isAscending){
		final StringBuilder sql = new StringBuilder();
		
		String ascending = (isAscending) ? "ASC ":"DESC ";
		
		sql.append("SELECT * ")
		.append("FROM tb_like tl ")
		.append("INNER JOIN tb_user tu ON tu.id = tl.user_id ")
		.append("INNER JOIN tb_post tp ON tp.id = tl.post_id ")
		.append("WHERE user_id  = :userId AND tl.is_active = true AND tp.is_active = true ")
		.append("ORDER BY tl.created_by ")
		.append(ascending)
		.append("LIMIT :limit OFFSET :startPosition ");
		
		List<Like> likes = ConnHandler.getManager().createNativeQuery(sql.toString(),Like.class)
				.setParameter("userId", userId)
				.setParameter("startPosition", startPosition)
				.setParameter("limit", limit)
				.getResultList();
		
		return likes;
	}
	
	@SuppressWarnings("unchecked")
	public List<Like> getByUser(String userId,int startPosition,int limit){
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * ")
		.append("FROM tb_like tl ")
		.append("INNER JOIN tb_user tu ON tu.id = tl.user_id ")
		.append("INNER JOIN tb_post tp ON tp.id = tl.post_id ")
		.append("WHERE user_id  = :userId AND tl.is_active = true ")
		.append("LIMIT :limit OFFSET :startPosition ");
		
		List<Like> likes = ConnHandler.getManager().createNativeQuery(sql.toString(),Like.class)
				.setParameter("userId", userId)
				.setParameter("startPosition", startPosition)
				.setParameter("limit", limit)
				.getResultList();
		
		return likes;
	}
	
	@SuppressWarnings("unchecked")
	public List<Like> getByUser(String userId){
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * ")
		.append("FROM tb_like tl ")
		.append("INNER JOIN tb_user tu ON tu.id = tl.user_id ")
		.append("INNER JOIN tb_post tp ON tp.id = tl.post_id ")
		.append("WHERE user_id  = :userId AND tl.is_active = true");
		
		List<Like> likes = ConnHandler.getManager().createNativeQuery(sql.toString(),Like.class)
				.setParameter("userId", userId)
				.getResultList();
		
		return likes;
	}
	
	public Like getTotalByUser(String userId){
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT count(*) ")
		.append("FROM tb_like tl ")
		.append("INNER JOIN tb_user tu ON tu.id = tl.user_id ")
		.append("INNER JOIN tb_post tp ON tp.id = tl.post_id ")
		.append("WHERE tl.user_id  = :userId AND tl.is_active = true");
		
		Object objLike = null;
		Like like = new Like();
		
		try {			
			objLike = ConnHandler.getManager().createNativeQuery(sql.toString())
					.setParameter("userId", userId)
					.getSingleResult();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		if(objLike != null) {
			Object obj = (Object) objLike;
			
			like.setCountOfLike(Integer.parseInt(obj.toString()));
		}
		
		return like;
	}
	
	@SuppressWarnings("unchecked")
	public List<Like> getByPost(String postId,int startPosition,int limit,boolean isAscending){
		final StringBuilder sql = new StringBuilder();
		
		String ascending = (isAscending)? "ASC ":"DESC ";
		
		sql.append("SELECT * ")
		.append("FROM tb_like tl ")
		.append("INNER JOIN tb_user tu ON tu.id = tl.user_id ")
		.append("INNER JOIN tb_post tp ON tp.id = tl.post_id ")
		.append("WHERE tl.post_id  = :postId AND tl.is_active = true ")
		.append("ORDER BY tl.created_at ")
		.append(ascending)
		.append("LIMIT :limit OFFSET :startPosition");
		
		List<Like> likes = ConnHandler.getManager().createNativeQuery(sql.toString(),Like.class)
				.setParameter("postId", postId)
				.setParameter("startPosition", startPosition)
				.setParameter("limit", limit)
				.getResultList();
		
		return likes;
	}
	
	@SuppressWarnings("unchecked")
	public List<Like> getByPost(String postId){
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * ")
		.append("FROM tb_like tl ")
		.append("INNER JOIN tb_user tu ON tu.id = tl.user_id ")
		.append("INNER JOIN tb_post tp ON tp.id = tl.post_id ")
		.append("WHERE tl.post_id  = :postId AND tl.is_active = true");
		
		List<Like> likes = ConnHandler.getManager().createNativeQuery(sql.toString(),Like.class)
				.setParameter("postId", postId)
				.getResultList();
		
		return likes;
	}
	
	public Like getTotalByPost(String postId){
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT count(*) ")
		.append("FROM tb_like tl ")
		.append("INNER JOIN tb_user tu ON tu.id = tl.user_id ")
		.append("INNER JOIN tb_post tp ON tp.id = tl.post_id ")
		.append("WHERE post_id = :postId AND tl.is_active = true");
		
		Object objLike = null;
		Like like = new Like();
		
		try {			
			objLike = ConnHandler.getManager().createNativeQuery(sql.toString())
					.setParameter("postId", postId)
					.getSingleResult();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		if(objLike != null) {
			Object obj = (Object) objLike;
			
			like.setCountOfLike(Integer.parseInt(obj.toString()));
		}
		
		return like;
	}
	
	
	public Optional<Like> userLikePost(String userId,String postId){
		StringBuilder sql = new StringBuilder();
		
		sql.append("Select count(*),")
		.append(" (SELECT id  from tb_like where user_id = :userId AND post_id = :postId) as id, ")
		.append(" (SELECT is_active  from tb_like where user_id = :userId AND post_id = :postId) as is_active ")
		.append("FROM tb_like WHERE post_id = :postId AND is_active = true");
		
		Object objLike = null;
		Optional<Like> optLike = Optional.ofNullable(null);
		
		try {
			objLike = ConnHandler.getManager().createNativeQuery(sql.toString())
					.setParameter("userId", userId)
					.setParameter("postId", postId)
					.getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(objLike != null) {
			Object[] objArr = (Object[]) objLike;
			
			Like like = new Like();
			like.setCountOfLike(Integer.parseInt(objArr[0].toString()));
			
			if(objArr[1] != null) {				
				like.setLikeId(objArr[1].toString());
				like.setId(objArr[1].toString());
			}
			
			if(objArr[2] != null) {				
				like.setIsActive(Boolean.parseBoolean(objArr[2].toString()));
			}
			
			optLike = Optional.ofNullable(like);
		}
		return optLike;
	}
	
	@SuppressWarnings("unchecked")
	public List<Like> getByIsActive(int startPosition,int limit){
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * FROM tb_like tl ")
		.append("INNER JOIN tb_user tu ON tu.id = tl.user_id ")
		.append("INNER JOIN tb_post tp ON tp.id = tl.post_id ")
		.append("WHERE tl.is_active = true ")
		.append("LIMIT :limit OFFSET :startPosition");
		
		List<Like> objResultLikes = ConnHandler.getManager().createNativeQuery(sql.toString(),Like.class)
				.setParameter("startPosition", startPosition)
				.setParameter("limit", limit)
				.getResultList();
		
		return objResultLikes;
	}
	
	@SuppressWarnings("unchecked")
	public List<Like> getByIsActive(){
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * FROM tb_like tl ")
		.append("INNER JOIN tb_user tu ON tu.id = tl.user_id ")
		.append("INNER JOIN tb_post tp ON tp.id = tl.post_id ")
		.append("WHERE tl.is_active = true ");
		
		List<Like> objResultLikes = ConnHandler.getManager().createNativeQuery(sql.toString(),Like.class)
				.getResultList();
		
		return objResultLikes;
	}
}
