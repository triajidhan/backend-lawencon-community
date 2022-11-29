package com.lawencon.lawenconcommunity.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.lawenconcommunity.model.Post;
import com.lawencon.lawenconcommunity.model.PostType;
import com.lawencon.lawenconcommunity.model.User;

@Repository
public class PostDao extends AbstractJpaDao{
	
	public Optional<Post> getByPostCode(final String postCode){
		final StringBuilder sql = new StringBuilder();
		sql.append("SELECT ")
		.append("tp.id as tp_id, post_code, title , contents , title_poll, ")
		.append("tpt.id as tpt_id,tpt.post_type_code, tpt.post_type_name, ")
		.append("tu.id as tu_id, tu.full_name, tu.company, ")
		.append("tp.created_by,tp.created_at, tp.versions ")
		.append("FROM tb_post tp ")
		.append("INNER JOIN tb_post_type tpt  ON tp.post_type_id = tpt.id ")
		.append("INNER JOIN tb_user tu ON tp.created_by = tu.id ")
		.append("WHERE post_code = :postCode AND tp.is_active = true");
		
		Object objPost = null;
		Optional<Post> optPost = Optional.ofNullable(null);
		
		try {
			objPost = ConnHandler.getManager().createNativeQuery(sql.toString())
					.setParameter("postCode", postCode)
					.getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(objPost != null) {
			final Object[] objArr = (Object[]) objPost;
			
			final Post post = new Post();
			final PostType postType = new PostType();
			final User user = new User();
			
			post.setId(objArr[0].toString());
			post.setPostCode(objArr[1].toString());
			post.setTitle(objArr[2].toString());
			post.setContents(objArr[3].toString());
			post.setTitlePoll(objArr[4].toString());
			
			postType.setId(objArr[5].toString());
			postType.setPostTypeCode(objArr[6].toString());
			postType.setPostTypeName(objArr[7].toString());
			
			user.setId(objArr[8].toString());
			user.setFullName(objArr[9].toString());
			user.setCompany(objArr[10].toString());
			
			post.setCreatedBy(objArr[11].toString());
			post.setCreatedAt(Timestamp.valueOf(objArr[12].toString()).toLocalDateTime());
			post.setVersion(Integer.parseInt(objArr[13].toString()));
			
			post.setPostType(postType);
			post.setUser(user);
			
			optPost = Optional.ofNullable(post);
		}
		
		return optPost;
	}
	
	@SuppressWarnings("unchecked")
	public List<Post> getByUser(final String userId){
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * ")
		.append("FROM tb_post tp ")
		.append("INNER JOIN tb_post_type tpt ON tp.post_type_id = tpt.id ")
		.append("WHERE tp.created_by = :userId AND tp.is_active = true");
		
		final List<Post> objResultPosts = ConnHandler.getManager().createNativeQuery(sql.toString(),Post.class)
				.setParameter("userId", userId)
				.getResultList();
		
		return objResultPosts;
	}
	
	@SuppressWarnings("unchecked")
	public List<Post> getByUser(final String userId,int startPosition, int limit,boolean isAscending){
		final StringBuilder sql = new StringBuilder();
		
		String ascending = (isAscending) ? "ASC ": "DESC ";
		
		sql.append("SELECT * ")
		.append("FROM tb_post tp ")
		.append("INNER JOIN tb_post_type tpt ON tp.post_type_id = tpt.id ")
		.append("WHERE tp.created_by = :userId AND tp.is_active = true ")
		.append("ORDER BY tp.created_at ")
		.append(ascending)
		.append("LIMIT :limit OFFSET :startPosition");;
		
		final List<Post> objResultPosts = ConnHandler.getManager().createNativeQuery(sql.toString(),Post.class)
				.setParameter("userId", userId)
				.setParameter("startPosition",startPosition)
				.setParameter("limit",limit)
				.getResultList();
		
		return objResultPosts;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Post> getByPostType(final String postTypeId){
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * ")
		.append("FROM tb_post tp ")
		.append("INNER JOIN tb_post_type tpt ON tp.post_type_id = tpt.id ")
		.append("WHERE tpt.id = :postTypeId AND tp.is_active = true");
		
		final List<Post> objResultPosts = ConnHandler.getManager().createNativeQuery(sql.toString(),Post.class)
				.setParameter("postTypeId", postTypeId)
				.getResultList();
		
		return objResultPosts;
	}
	
	public Post getTotalByUser(final String userId) {
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT count(tp.id) ")
		.append("FROM tb_post tp ")
		.append("INNER JOIN tb_post_type tpt ON tp.post_type_id = tpt.id ")
		.append("WHERE tp.created_by = :userId AND tp.is_active = true");
		
		Object objUser = null;
		Post post = new Post();
		
		try {
			objUser = ConnHandler.getManager().createNativeQuery(sql.toString())
					.setParameter("userId", userId)
					.getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(objUser != null) {
			Object obj = (Object) objUser;
			
			post.setCountOfPost(Integer.parseInt(obj.toString()));
		}
		
		return post;
	}
	
	
	public Post getTotalByPostType(final String postTypeId) {
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT count(tp.id) ")
		.append("FROM tb_post tp ")
		.append("INNER JOIN tb_post_type tpt ON tp.post_type_id = tpt.id ")
		.append("WHERE post_type_id = :postTypeId AND tp.is_active = true");
		
		Object objUser = null;
		
		Post post = new Post();
		
		try {
			objUser = ConnHandler.getManager().createNativeQuery(sql.toString())
					.setParameter("postTypeId", postTypeId)
					.getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(objUser != null) {
			Object obj = (Object) objUser;
			
			post.setCountOfPost(Integer.parseInt(obj.toString()));
		}
		
		return post;
	}
	
	public Post getTotalPost() {
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT count(tp.id) ")
		.append("FROM tb_post tp ")
		.append("INNER JOIN tb_post_type tpt ON tp.post_type_id = tpt.id ")
		.append("WHERE tp.is_active = true");
		
		Object objUser = null;
		
		Post post = new Post();
		
		try {
			objUser = ConnHandler.getManager().createNativeQuery(sql.toString())
					.getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(objUser != null) {
			Object obj = (Object) objUser;
			
			post.setCountOfPost(Integer.parseInt(obj.toString()));
		}
		
		return post;
	}
	
	@SuppressWarnings("unchecked")
	public List<Post> getByIsActive(int startPosition,int limit){
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * ")
		.append("FROM tb_post tp ")
		.append("INNER JOIN tb_post_type tpt ON tp.post_type_id = tpt.id ")
		.append("WHERE tp.is_active = true ")
		.append("LIMIT :limit OFFSET :startPosition");
		
		final List<Post> objResultPosts = ConnHandler.getManager().createNativeQuery(sql.toString(),Post.class)
				.setParameter("startPosition", startPosition)
				.setParameter("limit", limit)
				.getResultList();
		
		return objResultPosts;
	}
	
	@SuppressWarnings("unchecked")
	public List<Post> getByIsActive(){
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * ")
		.append("FROM tb_post tp ")
		.append("INNER JOIN tb_post_type tpt ON tp.post_type_id = tpt.id ")
		.append("WHERE tp.is_active = true ");
		
		final List<Post> objResultPosts = ConnHandler.getManager().createNativeQuery(sql.toString(),Post.class)
				.getResultList();
		
		return objResultPosts;
	}
	
	@SuppressWarnings("unchecked")
	public List<Post> getByIsActiveAndOrder(int startPosition,int limit,boolean isAscending){
		final StringBuilder sql = new StringBuilder();
		
		String ascending = (isAscending) ? "ASC ":"DESC ";
		
		sql.append("SELECT * ")
		.append("FROM tb_post tp ")
		.append("INNER JOIN tb_post_type tpt ON tp.post_type_id = tpt.id ")
		.append("WHERE tp.is_active = true ")
		.append("ORDER BY tp.created_at ")
		.append(ascending)
		.append("LIMIT :limit OFFSET :startPosition");
		
		final List<Post> objResult = ConnHandler.getManager().createNativeQuery(sql.toString(),Post.class)
				.setParameter("startPosition", startPosition)
				.setParameter("limit", limit)
				.getResultList();
		
		return objResult;
	}
}
