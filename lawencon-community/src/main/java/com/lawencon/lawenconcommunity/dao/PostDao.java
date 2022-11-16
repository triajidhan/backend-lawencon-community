package com.lawencon.lawenconcommunity.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.lawenconcommunity.model.Post;

@Repository
public class PostDao extends AbstractJpaDao{
	
	
	/**
	 * 
	 * select 
tp.id, tp.post_code, tp.title , tp.contents , tp.title_poll,
tpt.id,tpt.post_type_code, tpt.post_type_name,
tpoll.id, tpoll.poll_content, tpoll.total_poll,
tp.created_by, tp.versions 
from tb_post tp 
INNER JOIN tb_post_type tpt  ON tp.post_type_id = tpt.id
LEFT JOIN tb_polling tpoll ON tpoll.post_id = tp.id;
	 */
	public Optional<Post> getByPostCode(final String postCode){
		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT ")
		.append("tp.id as tp_id, tp.post_code, tp.title , tp.contents , tp.title_poll, ")
		.append("tpt.id as tpt_id,tpt.post_type_code, tpt.post_type_name, ")
		.append("tpoll.id as tpoll.id, tpoll.poll_content, tpoll.total_poll, ")
		.append("tp.created_by, tp.versions ")
		.append("from tb_post tp ");
		
		
		return null;
	}
	
	public List<Post> getByUser(final String userId){
		return null;
	}
}
