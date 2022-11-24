package com.lawencon.lawenconcommunity.dao;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.lawenconcommunity.model.Post;
import com.lawencon.lawenconcommunity.model.PostType;

@Repository
public class PostTypeDao extends AbstractJpaDao{

	public PostType getByPostTypeCode(String postTypeCode) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT ")
		.append("tp.id as tp_id, post_code, title ,contents , title_poll, ")
		.append("tpt.id as tpt_id,tpt.post_type_code, tpt.post_type_name, ")
		.append("tp.created_by,tp.created_at, tp.versions ")
		.append("FROM tb_post tp ")
		.append("INNER JOIN tb_post_type tpt  ON tp.post_type_id = tpt.id ")
		.append("WHERE post_code = :postCode AND tp.is_active = true");
		
		Object objPost = null;
		Optional<Post> optPost = Optional.ofNullable(null);
		
		try {
			objPost = ConnHandler.getManager().createNativeQuery(sql.toString())
					.setParameter("postCode", postTypeCode)
					.getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(objPost != null) {
			final Object[] objArr = (Object[]) objPost;
			
			final Post post = new Post();
			final PostType postType = new PostType();
			
			post.setId(objArr[0].toString());
			post.setPostCode(objArr[1].toString());
			post.setTitle(objArr[2].toString());
			post.setContents(objArr[3].toString());
			post.setTitlePoll(objArr[4].toString());
			
			postType.setId(objArr[5].toString());
			postType.setPostTypeCode(objArr[6].toString());
			postType.setPostTypeName(objArr[7].toString());
			
			post.setCreatedBy(objArr[8].toString());
			post.setCreatedAt(Timestamp.valueOf(objArr[9].toString()).toLocalDateTime());
			post.setVersion(Integer.parseInt(objArr[10].toString()));
			
			optPost = Optional.ofNullable(post);
		}
		
		return null;
	}
}
