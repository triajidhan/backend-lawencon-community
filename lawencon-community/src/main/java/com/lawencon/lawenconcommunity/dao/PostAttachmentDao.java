package com.lawencon.lawenconcommunity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.lawenconcommunity.model.PostAttachment;

@Repository
public class PostAttachmentDao extends AbstractJpaDao{

	@SuppressWarnings("unchecked")
	public List<PostAttachment> getByPost(String postId){
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * ")
		.append("FROM tb_post_attachment tpa ")
		.append("INNER JOIN tb_post tp ON tp.id = tpa.post_id ")
		.append("WHERE tpa.post_id = :postId AND tpa.is_active = true");
		
		List<PostAttachment> postAttachment = ConnHandler.getManager().createNativeQuery(sql.toString(),PostAttachment.class)
				.setParameter("postId", postId)
				.getResultList();
		
		return postAttachment;
	}
}
