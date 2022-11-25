package com.lawencon.lawenconcommunity.dao;

import java.sql.Timestamp;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.lawenconcommunity.model.PostType;

@Repository
public class PostTypeDao extends AbstractJpaDao{

	public PostType getByPostTypeCode(String postTypeCode) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT ")
		.append("id, post_type_code, post_type_name, ")
		.append("created_by,created_at, versions, is_active ")
		.append("FROM tb_post_type ")
		.append("WHERE post_type_code = :postTypeCode AND is_active = true");
		
		Object objPost = null;
		PostType postType = new PostType();
		
		try {
			objPost = ConnHandler.getManager().createNativeQuery(sql.toString())
					.setParameter("postTypeCode", postTypeCode)
					.getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(objPost != null) {
			final Object[] objArr = (Object[]) objPost;
			
			postType.setId(objArr[0].toString());
			postType.setPostTypeCode(objArr[1].toString());
			postType.setPostTypeName(objArr[2].toString());
			
			postType.setCreatedBy(objArr[3].toString());
			postType.setCreatedAt(Timestamp.valueOf(objArr[4].toString()).toLocalDateTime());
			postType.setVersion(Integer.parseInt(objArr[5].toString()));
			postType.setIsActive(Boolean.valueOf(objArr[6].toString()));
		}
		
		return postType;
	}
	
	
}
