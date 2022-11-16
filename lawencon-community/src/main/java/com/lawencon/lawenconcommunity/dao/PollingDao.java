package com.lawencon.lawenconcommunity.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.lawenconcommunity.model.Polling;
import com.lawencon.lawenconcommunity.model.Post;

@Repository
public class PollingDao extends AbstractJpaDao{

	public List<Polling> getByPost(final String postId){
		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT ")
		.append("tpol.id as tpol_id, poll_content,total_poll, ")
		.append("tpos.id as tpos_id, tpos.post_code, tpos.title, tpos.title_poll, ")
		.append("tpol.created_by, tpol.created_at,tpol.versions ")
		.append("FROM tb_polling tpol ")
		.append("INNER JOIN tb_post tpos ON  tpos.id  = tpol.post_id ")
		.append("WHERE tpos.id = :postId ");
		
		Object objPolling = null;
		Optional<Polling> pollingOpt = Optional.ofNullable(null);
		
		try {
			objPolling = ConnHandler.getManager().createNativeQuery(sql.toString())
					.setParameter("postId", postId)
					.getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(objPolling != null) {
			Object[] objArr = (Object[]) objPolling;
			
			final Polling polling = new Polling();
			final Post post = new Post();
			
			// tpol.id as tpol_id, poll_content,total_poll,
			polling.setId(objArr[0].toString());
			polling.setPollContent(objArr[1].toString());
			polling.setTotalPoll(Integer.parseInt(objArr[2].toString()));
			
			// tpos.id as tpos_id, tpos.post_code, tpos.title, tpos.title_poll,
			post.setId(objArr[4].toString());
			post.setPostCode(objArr[5].toString());
			post.setTitle(objArr[6].toString());
			post.setTitlePoll(objArr[7].toString());
			
			// tpol.created_by, tpol.created_at,tpol.versions
			polling.setCreatedBy(objArr[8].toString());
			polling.setCreatedAt(LocalDateTime.parse(objArr[9].toString()));
			polling.setVersion(Integer.parseInt(objArr[10].toString()));
			
			pollingOpt = Optional.ofNullable(polling);
		}
		
		return null;
	}
}
