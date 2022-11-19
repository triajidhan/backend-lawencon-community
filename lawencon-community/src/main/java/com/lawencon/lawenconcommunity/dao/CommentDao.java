package com.lawencon.lawenconcommunity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.lawenconcommunity.model.Comment;

@Repository
public class CommentDao extends AbstractJpaDao{

	@SuppressWarnings("unchecked")
	public List<Comment> getByUser(String userId){
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * ")
		.append("FROM tb_comment tc ")
		.append("INNER JOIN tb_user tu ON tu.id = tc.user_id ")
		.append("INNER JOIN tb_post tp ON tp.id = tc.post_id ")
		.append("WHERE user_id  = :userId AND is_active = true");
		
		List<Comment> comments = ConnHandler.getManager().createNativeQuery(sql.toString(),Comment.class)
				.setParameter("userId", userId)
				.getResultList();
		
		return comments;
	}
	
	@SuppressWarnings("unchecked")
	public List<Comment> getByPost(String postId){
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * ")
		.append("FROM tb_comment tc ")
		.append("INNER JOIN tb_user tu ON tu.id = tc.user_id ")
		.append("INNER JOIN tb_post tp ON tp.id = tc.post_id ")
		.append("WHERE post_id  = :postId AND is_active = true");
		
		List<Comment> comments = ConnHandler.getManager().createNativeQuery(sql.toString(),Comment.class)
				.setParameter("postId", postId)
				.getResultList();
		
		return comments;
	}
}
