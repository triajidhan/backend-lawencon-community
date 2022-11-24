package com.lawencon.lawenconcommunity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.lawenconcommunity.constant.PostType;
import com.lawencon.lawenconcommunity.dao.FileDao;
import com.lawencon.lawenconcommunity.dao.PollingDao;
import com.lawencon.lawenconcommunity.dao.PostAttachmentDao;
import com.lawencon.lawenconcommunity.dao.PostDao;
import com.lawencon.lawenconcommunity.dao.PostTypeDao;
import com.lawencon.lawenconcommunity.dto.ResponseMessageDto;
import com.lawencon.lawenconcommunity.model.File;
import com.lawencon.lawenconcommunity.model.Polling;
import com.lawencon.lawenconcommunity.model.Post;
import com.lawencon.lawenconcommunity.model.PostAttachment;

@Service
public class PostService extends BaseCoreService {

	@Autowired
	private PostDao postDao;
	@Autowired
	private PostAttachmentDao postAttachmentDao;
	@Autowired
	private FileDao fileDao;
	@Autowired
	private GenerateService generateService;
	@Autowired
	private PollingDao pollingDao;
	@Autowired
	private PostTypeDao postTypeDao;

	public List<Post> getAll(Integer startPosition, Integer limitPage) {
		final List<Post> posts = postDao.getAll(Post.class, startPosition, limitPage);

		return posts;
	}

	public List<Post> getAll() {
		final List<Post> posts = postDao.getAll(Post.class);

		return posts;
	}
	
	public List<Post> getByUser(String userId) {
		final List<Post> posts = postDao.getByUser(userId);

		return posts;
	}
	
	public List<Post> getByPostType(String postTypeId) {
		final List<Post> posts = postDao.getByPostType(postTypeId);

		return posts;
	}
	
	public Post getTotal() {
		return postDao.getTotalPost();
	}
	
	public Post getTotalByUser(String userId) {
		return postDao.getTotalByUser(userId);
	}

	public Post getByPostCode(String postCode) {
		return postDao.getByPostCode(postCode).get();
	}
	
	public Post getTotalByPostType(String postTypeId) {
		return postDao.getTotalByPostType(postTypeId);
	}
	
	public Post getById(String id) {
		return postDao.getByIdAndDetach(Post.class, id);
	}
	
	public List<Post> getByIsActive(Integer startPosition, Integer limitPage){
		return postDao.getByIsActive(startPosition, limitPage);
	}
	
	public List<Post> getByIsActive(){
		return postDao.getByIsActive();
	}
	
	public List<Post> getByIsActiveAndOrder(int startPosition,int limit,boolean ascending){
		
		return postDao.getByIsActiveAndOrder(startPosition, limit,ascending);
	}

	public ResponseMessageDto insert(Post data) {
		Post postInsert = new Post();

		ResponseMessageDto responseMessageDto = new ResponseMessageDto();
		responseMessageDto.setMessage("Failed to create the Post!");
		com.lawencon.lawenconcommunity.model.PostType postType = postTypeDao
				.getByIdAndDetach(com.lawencon.lawenconcommunity.model.PostType.class, data.getPostTypeId());
		begin();
		try {

			if (PostType.PO.toString().equals(postType.getPostTypeCode())) {
				postInsert.setContents(data.getContents());

				postInsert.setPostCode(generateService.generate(5));
				postInsert.setPostType(postType);
				postInsert.setTitle(data.getTitle());
				postInsert.setTitlePoll(data.getTitlePoll());
				postInsert.setFile(data.getFile());
				valInsert(postInsert);

				postInsert = postDao.save(postInsert);
				responseMessageDto.setMessage("Success to create the Post!");
				for (int i = 0; i < data.getPollContent().size(); i++) {
					Polling pollingInsert = new Polling();
					pollingInsert.setPost(postInsert);
					pollingInsert.setPollContent(data.getPollContent().get(i));
					pollingInsert.setTotalPoll(0);
					pollingInsert = pollingDao.save(pollingInsert);
				}
				if(data.getFile() != null) {
					if (data.getFile().size() >= 0) {
						for (int i = 0; i < data.getFile().size(); i++) {
							File fileInsert = new File();
							fileInsert.setFiles(data.getFile().get(i).getFiles());
							fileInsert.setExt(data.getFile().get(i).getExt());
							fileInsert = fileDao.save(fileInsert);
							
							PostAttachment postAttachmentInsert = new PostAttachment();
							postAttachmentInsert.setFile(fileInsert);
							postAttachmentInsert.setPost(postInsert);
							postAttachmentInsert = postAttachmentDao.save(postAttachmentInsert);
						}
					}					
				}

			} else if (PostType.PRE.toString().equals(postType.getPostTypeCode())) {
				postInsert.setContents(data.getContents());
				postInsert.setPostCode(generateService.generate(5));
				postInsert.setPostType(postType);
				postInsert.setTitle(data.getTitle());
				postInsert.setTitlePoll(data.getTitlePoll());
				postInsert = postDao.save(postInsert);
				responseMessageDto.setMessage("Success to create the Post!");
				if(data.getFile() != null) {
					if (data.getFile().size() >= 0) {
						for (int i = 0; i < data.getFile().size(); i++) {
							File fileInsert = new File();
							fileInsert.setFiles(data.getFile().get(i).getFiles());
							fileInsert.setExt(data.getFile().get(i).getExt());
							fileInsert = fileDao.save(fileInsert);
							
							PostAttachment postAttachmentInsert = new PostAttachment();
							postAttachmentInsert.setFile(fileInsert);
							postAttachmentInsert.setPost(postInsert);
							postAttachmentInsert = postAttachmentDao.save(postAttachmentInsert);
						}
					}					
				}
			} else {
				postInsert.setContents(data.getContents());
				postInsert.setPostCode(generateService.generate(5));
				postInsert.setPostType(postType);
				postInsert.setTitle(data.getTitle());
				postInsert.setTitlePoll(data.getTitlePoll());
				postInsert = postDao.save(postInsert);
				responseMessageDto.setMessage("Success to create the Post!");
				if(data.getFile() != null) {
					if (data.getFile().size() >= 0) {
						for (int i = 0; i < data.getFile().size(); i++) {
							File fileInsert = new File();
							fileInsert.setFiles(data.getFile().get(i).getFiles());
							fileInsert.setExt(data.getFile().get(i).getExt());
							fileInsert = fileDao.save(fileInsert);
							
							PostAttachment postAttachmentInsert = new PostAttachment();
							postAttachmentInsert.setFile(fileInsert);
							postAttachmentInsert.setPost(postInsert);
							postAttachmentInsert = postAttachmentDao.save(postAttachmentInsert);
						}
					}					
				}
			}
		} catch (Exception e) {
			responseMessageDto.setMessage("Failed to create the Post!");
			e.printStackTrace();
		}
		commit();
		return responseMessageDto;
	}

	public void valIdNullInsert(Post data) {
		if (data.getId() != null) {
			throw new RuntimeException("Id Must Be Empty!");
		}

		if (data.getFile() != null) {
			if (data.getFile().size() != 0) {
				for (int i = 0; i < data.getFile().size(); i++) {
					if (data.getFile().get(i).getId() != null) {
						throw new RuntimeException("Id Must Be Empty!");
					}
				}
			}
		}
	}

	public void valNotNullInsert(Post data) {
		if (data.getTitle() == null) {
			throw new RuntimeException("Title Required!");
		}

		if (data.getPostType() == null) {
			throw new RuntimeException("Type Post Required!");
		}
	}

	public void valDuplicateBk(Post data) {
		if (postDao.getByPostCode(data.getPostCode()).isPresent()) {
			throw new RuntimeException("Duplicate post code detected!");
		}
	}

	public void valInsert(Post data) {
		valDuplicateBk(data);
		valNotNullInsert(data);
		valIdNullInsert(data);
	}

	public ResponseMessageDto update(Post data) {
		Post postUpdate = new Post();
		ResponseMessageDto responseMessageDto = new ResponseMessageDto();
		responseMessageDto.setMessage("Failed!");
		Post post = postDao.getByIdAndDetach(Post.class, data.getId());
		valUpdate(data);
		begin();
		try {
			postUpdate = post;
			if (post != null) {
				if (data.getTitle() != null) {
					postUpdate.setTitle(data.getTitle());
				}

				if (data.getContents() != null) {
					postUpdate.setContents(data.getContents());
				}

				if (data.getIsActive() != null) {
					postUpdate.setIsActive(data.getIsActive());
				}
				postDao.saveAndFlush(postUpdate);
				responseMessageDto.setMessage("Success");
			}
		} catch (Exception e) {
			responseMessageDto.setMessage("Failed");
			e.printStackTrace();
		}

		return responseMessageDto;
	}

	public void valUpdate(Post data) {
		valFoundId(data);
	}

	public void valFoundId(Post data) {
		if (postDao.getByIdAndDetach(Post.class, data.getId()) == null) {
			throw new RuntimeException("Post Not Found");
		}
	}
}
