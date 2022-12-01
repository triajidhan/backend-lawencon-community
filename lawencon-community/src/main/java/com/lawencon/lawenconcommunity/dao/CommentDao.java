package com.lawencon.lawenconcommunity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.lawenconcommunity.model.Bookmark;
import com.lawencon.lawenconcommunity.model.Comment;

@Repository
public class CommentDao extends AbstractJpaDao{

	public Comment getTotalComment() {
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT count(*) from tb_comment ")
		.append("WHERE is_active = true");
		
		
		Object objComment = null;
		Comment comment = null;
		try {
			objComment = ConnHandler.getManager().createNativeQuery(sql.toString())
					.getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(objComment != null) {
			Object obj = (Object) objComment;
			
			comment = new Comment();
			
			comment.setCountOfComment(Integer.parseInt(obj.toString()));
		}
		
		return comment;
	}
	
	@SuppressWarnings("unchecked")
	public List<Comment> getByUser(String userId){
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * ")
		.append("FROM tb_comment tc ")
		.append("INNER JOIN tb_user tu ON tu.id = tc.user_id ")
		.append("INNER JOIN tb_post tp ON tp.id = tc.post_id ")
		.append("WHERE user_id  = :userId AND tc.is_active = true");
		
		List<Comment> comments = ConnHandler.getManager().createNativeQuery(sql.toString(),Comment.class)
				.setParameter("userId", userId)
				.getResultList();
		
		return comments;
	}
	
	@SuppressWarnings("unchecked")
	public List<Comment> getByUserAndOrder(String userId,int startPosition,int limit,boolean isAscending){
		final StringBuilder sql = new StringBuilder();
		
		String ascending = (isAscending) ? "ASC ":"DESC ";
		
		sql.append("SELECT * ")
		.append("FROM tb_comment tc ")
		.append("INNER JOIN tb_user tu ON tu.id = tc.user_id ")
		.append("INNER JOIN tb_post tp ON tp.id = tc.post_id ")
		.append("WHERE user_id  = :userId AND tc.is_active = true ")
		.append("ORDER BY tc.created_at ")
		.append(ascending)
		.append("LIMIT :limit OFFSET :startPosition");
		
		List<Comment> comments = ConnHandler.getManager().createNativeQuery(sql.toString(),Comment.class)
				.setParameter("userId", userId)
				.setParameter("startPosition", startPosition)
				.setParameter("limit", limit)
				.getResultList();
		
		return comments;
	}
	
	@SuppressWarnings("unchecked")
	public List<Comment> getByUserAndOrder(String userId,boolean isAscending){
		final StringBuilder sql = new StringBuilder();
		
		String ascending = (isAscending) ? "ASC ":"DESC ";
		
		sql.append("SELECT * ")
		.append("FROM tb_comment tc ")
		.append("INNER JOIN tb_user tu ON tu.id = tc.user_id ")
		.append("INNER JOIN tb_post tp ON tp.id = tc.post_id ")
		.append("WHERE user_id  = :userId AND tc.is_active = true ")
		.append("ORDER BY tc.created_at ")
		.append(ascending);
		
		List<Comment> comments = ConnHandler.getManager().createNativeQuery(sql.toString(),Comment.class)
				.setParameter("userId", userId)
				.getResultList();
		
		return comments;
	}
	
	public Comment getTotalByUser(String userId) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT count(*) from tb_comment ")
		.append("WHERE user_id  = :userId AND is_active = true");
		
		
		Object objComment = null;
		Comment comment = null;
		
		try {
			objComment = ConnHandler.getManager().createNativeQuery(sql.toString())
					.setParameter("userId", userId)
					.getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(objComment != null) {
			Object obj = (Object) objComment;
			
			comment = new Comment();
			
			comment.setCountOfComment(Integer.parseInt(obj.toString()));
		}
		
		return comment;
	}
	
	@SuppressWarnings("unchecked")
	public List<Comment> getByPost(String postId){
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * ")
		.append("FROM tb_comment tc ")
		.append("INNER JOIN tb_user tu ON tu.id = tc.user_id ")
		.append("INNER JOIN tb_post tp ON tp.id = tc.post_id ")
		.append("WHERE post_id  = :postId AND tc.is_active = true");
		
		List<Comment> comments = ConnHandler.getManager().createNativeQuery(sql.toString(),Comment.class)
				.setParameter("postId", postId)
				.getResultList();
		
		return comments;
	}
	
	@SuppressWarnings("unchecked")
	public List<Comment> getByPostAndOrder(String postId,boolean isAscending){
		final StringBuilder sql = new StringBuilder();
		
		String ascending = (isAscending) ? "ASC ":"DESC ";
		
		sql.append("SELECT * ")
		.append("FROM tb_comment tc ")
		.append("INNER JOIN tb_user tu ON tu.id = tc.user_id ")
		.append("INNER JOIN tb_post tp ON tp.id = tc.post_id ")
		.append("WHERE post_id = :postId AND tc.is_active = true ")
		.append("ORDER BY tc.created_at ")
		.append(ascending);
		
		List<Comment> comments = ConnHandler.getManager().createNativeQuery(sql.toString(),Comment.class)
				.setParameter("postId", postId)
				.getResultList();
		
		return comments;
	}
	
	@SuppressWarnings("unchecked")
	public List<Comment> getByPostAndOrder(String postId,int startPosition,int limit,boolean isAscending){
		final StringBuilder sql = new StringBuilder();
		
		String ascending = (isAscending) ? "ASC ":"DESC ";
		
		sql.append("SELECT * ")
		.append("FROM tb_comment tc ")
		.append("INNER JOIN tb_user tu ON tu.id = tc.user_id ")
		.append("INNER JOIN tb_post tp ON tp.id = tc.post_id ")
		.append("WHERE post_id = :postId AND tc.is_active = true ")
		.append("ORDER BY tc.created_at ")
		.append(ascending)
		.append("LIMIT :limit OFFSET :startPosition");
		
		List<Comment> comments = ConnHandler.getManager().createNativeQuery(sql.toString(),Comment.class)
				.setParameter("postId", postId)
				.getResultList();
		
		return comments;
	}
	
	public Comment getTotalByPost(String postId) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT count(*) from tb_comment ")
		.append("WHERE post_id  = :postId AND is_active = true");
		
		
		Object objComment = null;
		Comment comment = null;
		
		try {
			objComment = ConnHandler.getManager().createNativeQuery(sql.toString())
					.setParameter("postId", postId)
					.getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(objComment != null) {
			Object obj = (Object) objComment;
			
			comment = new Comment();
			
			comment.setCountOfComment(Integer.parseInt(obj.toString()));
		}
		
		return comment;
	}
	
	@SuppressWarnings("unchecked")
	public List<Comment> getByIsActive(int startPosition,int limit){
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * ")
		.append("FROM tb_comment tc ")
		.append("INNER JOIN tb_user tu ON tu.id = tc.user_id ")
		.append("INNER JOIN tb_post tp ON tp.id = tc.post_id ")
		.append("LIMIT :limit OFFSET :startPosition");
		
		final List<Comment> objResult = ConnHandler.getManager().createNativeQuery(sql.toString(),Bookmark.class)
				.setParameter("startPosition", startPosition)
				.setParameter("limit", limit)
				.getResultList();
		
		return objResult;
	}
	
	@SuppressWarnings("unchecked")
	public List<Comment> getByIsActive(){
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * ")
		.append("FROM tb_comment tc ")
		.append("INNER JOIN tb_user tu ON tu.id = tc.user_id ")
		.append("INNER JOIN tb_post tp ON tp.id = tc.post_id ")
		.append("LIMIT :limit OFFSET :startPosition");
		
		final List<Comment> objResult = ConnHandler.getManager().createNativeQuery(sql.toString(),Bookmark.class)
				.getResultList();
		
		return objResult;
	}
}
