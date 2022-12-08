package com.lawencon.lawenconcommunity.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseCoreService;
import com.lawencon.lawenconcommunity.constant.PostType;
import com.lawencon.lawenconcommunity.dao.BookmarkDao;
import com.lawencon.lawenconcommunity.dao.CommentDao;
import com.lawencon.lawenconcommunity.dao.FileDao;
import com.lawencon.lawenconcommunity.dao.LikeDao;
import com.lawencon.lawenconcommunity.dao.PollingDao;
import com.lawencon.lawenconcommunity.dao.PollingStatusDao;
import com.lawencon.lawenconcommunity.dao.PostAttachmentDao;
import com.lawencon.lawenconcommunity.dao.PostDao;
import com.lawencon.lawenconcommunity.dao.PostTypeDao;
import com.lawencon.lawenconcommunity.dao.UserDao;
import com.lawencon.lawenconcommunity.dto.ResponseMessageDto;
import com.lawencon.lawenconcommunity.model.Comment;
import com.lawencon.lawenconcommunity.model.File;
import com.lawencon.lawenconcommunity.model.Polling;
import com.lawencon.lawenconcommunity.model.Post;
import com.lawencon.lawenconcommunity.model.PostAttachment;
import com.lawencon.lawenconcommunity.model.User;
import com.lawencon.security.principal.PrincipalService;

@Service
public class PostService extends BaseCoreService {

	@Autowired
	PollingStatusDao pollingStatusDao;
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
	@Autowired
	private UserDao userDao;
	@Autowired
	private CommentDao commentDao;
	@Autowired
	private LikeDao likeDao;
	@Autowired
	private BookmarkDao bookmarkDao;
	@Autowired
	private PrincipalService principalService;


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
	
	public List<Post> getByUser(String userId,int startPosition,int limit, boolean ascending) {
		final List<Post> posts = postDao.getByUser(userId,startPosition,limit,ascending);
		for(int i = 0 ; i <posts.size();i++) {
			posts.get(i).setUser(userDao.getById(User.class, posts.get(i).getCreatedBy()));
			
			posts.get(i).setCountOfLike(likeDao.getTotalByPost(posts.get(i).getId()).getCountOfLike());

			try {
				if(likeDao.userLikePost(principalService.getAuthPrincipal(), posts.get(i).getId())!= null) {
					posts.get(i).setStatusLike(likeDao.userLikePost(principalService.getAuthPrincipal(), posts.get(i).getId()).get().getIsActive());
					posts.get(i).setLikeId(likeDao.userLikePost(principalService.getAuthPrincipal(), posts.get(i).getId()).get().getLikeId());
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			try {
				if(bookmarkDao.getUserBookmarkPost(principalService.getAuthPrincipal(), posts.get(i).getId())!= null) {
					posts.get(i).setStatusBookmark(bookmarkDao.getUserBookmarkPost(principalService.getAuthPrincipal(), posts.get(i).getId()).get().getIsActive());
					posts.get(i).setBookmarkId(bookmarkDao.getUserBookmarkPost(principalService.getAuthPrincipal(), posts.get(i).getId()).get().getBookmarkId());
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			List<PostAttachment>attachments = postAttachmentDao.getByPost(posts.get(i).getId());
			List<String>fileId = new ArrayList<>();
			for(int j = 0 ; j<attachments.size();j++) {
				fileId.add(attachments.get(j).getFile().getId());
			}
			posts.get(i).setFileId(fileId);
			
			List<Polling>pollings = pollingDao.getByPost(posts.get(i).getId());
			List<String>pollContents = new ArrayList<>();
			List<String>pollId = new ArrayList<>();
			List<Integer>totalPoll = new ArrayList<>();
			Boolean statusPolling = false;
			Integer totalVote = 0;
			for(int k = 0; k<pollings.size();k++) {
				pollContents.add(pollings.get(k).getPollContent());
				pollId.add(pollings.get(k).getId());
				totalPoll.add(pollings.get(k).getTotalPoll());	
				totalVote += pollings.get(k).getTotalPoll();
			}
			posts.get(i).setPollContents(pollContents);
			posts.get(i).setPollId(pollId);
			posts.get(i).setTotalPoll(totalPoll);
			posts.get(i).setTotalVote(totalVote);
			
			String choosenPolling = null;
			try {
				if(pollingStatusDao.getByUserAndPosting(principalService.getAuthPrincipal(),posts.get(i).getId())!= null) {
					statusPolling = true;
					choosenPolling = pollingStatusDao.getByUserAndPosting(principalService.getAuthPrincipal(),posts.get(i).getId()).getPolling().getId();
				}else {
					statusPolling = false;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			posts.get(i).setStatusPolling(statusPolling);
			posts.get(i).setChoosenPolling(choosenPolling);
			
			List<User> userComment= new ArrayList<>();
			List<String>commentId =  new ArrayList<>();
			List<String>commentBody =  new ArrayList<>();
			List<LocalDateTime>createdAtComment=  new ArrayList<>();
			List<Comment>comments = commentDao.getByPostAndOrder(posts.get(i).getId(), 0, 2, false);
			for(int l = comments.size()-1 ; l>=0; l--) {
				userComment.add(comments.get(l).getUser());
				commentBody.add(comments.get(l).getCommentBody());
				commentId.add(comments.get(l).getId());
				createdAtComment.add(comments.get(l).getCreatedAt());
			}
			posts.get(i).setCommentBody(commentBody);
			posts.get(i).setCommentId(commentId);
			posts.get(i).setCreatedAtComment(createdAtComment);
			posts.get(i).setUserComment(userComment);
			
			Comment countOfComment = commentDao.getTotalByPost(posts.get(i).getId());
			posts.get(i).setCountOfComment(countOfComment.getCountOfComment());
		}
		
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
		Post post = postDao.getByIdAndDetach(Post.class, id);
		post.setUser(userDao.getById(User.class, post.getCreatedBy()));
		
			post.setCountOfLike(likeDao.getTotalByPost(post.getId()).getCountOfLike());

			try {
				if(likeDao.userLikePost(principalService.getAuthPrincipal(), post.getId())!= null) {
					post.setStatusLike(likeDao.userLikePost(principalService.getAuthPrincipal(), post.getId()).get().getIsActive());
					post.setLikeId(likeDao.userLikePost(principalService.getAuthPrincipal(), post.getId()).get().getLikeId());
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			try {
				if(bookmarkDao.getUserBookmarkPost(principalService.getAuthPrincipal(), post.getId())!= null) {
					post.setStatusBookmark(bookmarkDao.getUserBookmarkPost(principalService.getAuthPrincipal(), post.getId()).get().getIsActive());
					post.setBookmarkId(bookmarkDao.getUserBookmarkPost(principalService.getAuthPrincipal(), post.getId()).get().getBookmarkId());
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		
		
		List<PostAttachment>attachments = postAttachmentDao.getByPost(post.getId());
		List<String>fileId = new ArrayList<>();
		for(int j = 0 ; j<attachments.size();j++) {
			fileId.add(attachments.get(j).getFile().getId());
		}
		post.setFileId(fileId);
		
		List<Polling>pollings = pollingDao.getByPost(post.getId());
		List<String>pollContents = new ArrayList<>();
		List<String>pollId = new ArrayList<>();
		List<Integer>totalPoll = new ArrayList<>();
		Boolean statusPolling = false;
		Integer totalVote = 0;
		for(int k = 0; k<pollings.size();k++) {
			pollContents.add(pollings.get(k).getPollContent());
			pollId.add(pollings.get(k).getId());
			totalPoll.add(pollings.get(k).getTotalPoll());	
			totalVote += pollings.get(k).getTotalPoll();
		}
		post.setPollContents(pollContents);
		post.setPollId(pollId);
		post.setTotalPoll(totalPoll);
		post.setTotalVote(totalVote);
		
		String choosenPolling = null;
		try {
			if(pollingStatusDao.getByUserAndPosting(principalService.getAuthPrincipal(),post.getId())!= null) {
				statusPolling = true;
				choosenPolling = pollingStatusDao.getByUserAndPosting(principalService.getAuthPrincipal(),post.getId()).getPolling().getId();
			}else {
				statusPolling = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		post.setStatusPolling(statusPolling);
		post.setChoosenPolling(choosenPolling);
		
		List<User> userComment= new ArrayList<>();
		List<String>commentId =  new ArrayList<>();
		List<String>commentBody =  new ArrayList<>();
		List<LocalDateTime>createdAtComment=  new ArrayList<>();
		List<Comment>comments = commentDao.getByPostAndOrder(post.getId(),true);
		for(int l = comments.size()-1 ; l>=0; l--) {
			userComment.add(comments.get(l).getUser());
			commentBody.add(comments.get(l).getCommentBody());
			commentId.add(comments.get(l).getId());
			createdAtComment.add(comments.get(l).getCreatedAt());
		}
		post.setCommentBody(commentBody);
		post.setCommentId(commentId);
		post.setCreatedAtComment(createdAtComment);
		post.setUserComment(userComment);
		Comment countOfComment = commentDao.getTotalByPost(post.getId());
		post.setCountOfComment(countOfComment.getCountOfComment());
		return post;
	}

	public List<Post> getByIsActive(Integer startPosition, Integer limitPage) {
		List<Post> posts = postDao.getByIsActive(startPosition, limitPage);
		for(int i = 0 ; i <posts.size();i++) {
			posts.get(i).setUser(userDao.getById(User.class, posts.get(i).getCreatedBy()));
			
			posts.get(i).setCountOfLike(likeDao.getTotalByPost(posts.get(i).getId()).getCountOfLike());

			try {
				if(likeDao.userLikePost(principalService.getAuthPrincipal(), posts.get(i).getId())!= null) {
					posts.get(i).setStatusLike(likeDao.userLikePost(principalService.getAuthPrincipal(), posts.get(i).getId()).get().getIsActive());
					posts.get(i).setLikeId(likeDao.userLikePost(principalService.getAuthPrincipal(), posts.get(i).getId()).get().getLikeId());
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			try {
				if(bookmarkDao.getUserBookmarkPost(principalService.getAuthPrincipal(), posts.get(i).getId())!= null) {
					posts.get(i).setStatusBookmark(bookmarkDao.getUserBookmarkPost(principalService.getAuthPrincipal(), posts.get(i).getId()).get().getIsActive());
					posts.get(i).setBookmarkId(bookmarkDao.getUserBookmarkPost(principalService.getAuthPrincipal(), posts.get(i).getId()).get().getBookmarkId());
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			List<PostAttachment>attachments = postAttachmentDao.getByPost(posts.get(i).getId());
			List<String>fileId = new ArrayList<>();
			for(int j = 0 ; j<attachments.size();j++) {
				fileId.add(attachments.get(j).getFile().getId());
			}
			posts.get(i).setFileId(fileId);
			
			List<Polling>pollings = pollingDao.getByPost(posts.get(i).getId());
			List<String>pollContents = new ArrayList<>();
			List<String>pollId = new ArrayList<>();
			List<Integer>totalPoll = new ArrayList<>();
			Boolean statusPolling = false;
			Integer totalVote = 0;
			for(int k = 0; k<pollings.size();k++) {
				pollContents.add(pollings.get(k).getPollContent());
				pollId.add(pollings.get(k).getId());
				totalPoll.add(pollings.get(k).getTotalPoll());	
				totalVote += pollings.get(k).getTotalPoll();
			}
			posts.get(i).setPollContents(pollContents);
			posts.get(i).setPollId(pollId);
			posts.get(i).setTotalPoll(totalPoll);
			posts.get(i).setTotalVote(totalVote);
			
			String choosenPolling = null;
			try {
				if(pollingStatusDao.getByUserAndPosting(principalService.getAuthPrincipal(),posts.get(i).getId())!= null) {
					statusPolling = true;
					choosenPolling = pollingStatusDao.getByUserAndPosting(principalService.getAuthPrincipal(),posts.get(i).getId()).getPolling().getId();
				}else {
					statusPolling = false;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			posts.get(i).setStatusPolling(statusPolling);
			posts.get(i).setChoosenPolling(choosenPolling);
			
			posts.get(i).setStatusPolling(statusPolling);
			List<User> userComment= new ArrayList<>();
			List<String>commentId =  new ArrayList<>();
			List<String>commentBody =  new ArrayList<>();
			List<LocalDateTime>createdAtComment=  new ArrayList<>();
			List<Comment>comments = commentDao.getByPostAndOrder(posts.get(i).getId(), 0, 2, false);
			for(int l = comments.size()-1 ; l>=0; l--) {
				userComment.add(comments.get(l).getUser());
				commentBody.add(comments.get(l).getCommentBody());
				commentId.add(comments.get(l).getId());
				createdAtComment.add(comments.get(l).getCreatedAt());
			}
			posts.get(i).setCommentBody(commentBody);
			posts.get(i).setCommentId(commentId);
			posts.get(i).setCreatedAtComment(createdAtComment);
			posts.get(i).setUserComment(userComment);
			Comment countOfComment = commentDao.getTotalByPost(posts.get(i).getId());
			posts.get(i).setCountOfComment(countOfComment.getCountOfComment());
		}
		return posts;
	}

	public List<Post> getByIsActive() {
		return postDao.getByIsActive();
	}

	public List<Post> getByIsActiveAndOrder(int startPosition, int limit, boolean ascending) {
		List<Post> posts = postDao.getByIsActiveAndOrder(startPosition, limit,ascending);
		for(int i = 0 ; i <posts.size();i++) {
			posts.get(i).setUser(userDao.getById(User.class, posts.get(i).getCreatedBy()));
			
			posts.get(i).setCountOfLike(likeDao.getTotalByPost(posts.get(i).getId()).getCountOfLike());

			try {
				if(likeDao.userLikePost(principalService.getAuthPrincipal(), posts.get(i).getId())!= null) {
					posts.get(i).setStatusLike(likeDao.userLikePost(principalService.getAuthPrincipal(), posts.get(i).getId()).get().getIsActive());
					posts.get(i).setLikeId(likeDao.userLikePost(principalService.getAuthPrincipal(), posts.get(i).getId()).get().getLikeId());
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			try {
				if(bookmarkDao.getUserBookmarkPost(principalService.getAuthPrincipal(), posts.get(i).getId())!= null) {
					posts.get(i).setStatusBookmark(bookmarkDao.getUserBookmarkPost(principalService.getAuthPrincipal(), posts.get(i).getId()).get().getIsActive());
					posts.get(i).setBookmarkId(bookmarkDao.getUserBookmarkPost(principalService.getAuthPrincipal(), posts.get(i).getId()).get().getBookmarkId());
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			List<PostAttachment>attachments = postAttachmentDao.getByPost(posts.get(i).getId());
			List<String>fileId = new ArrayList<>();
			for(int j = 0 ; j<attachments.size();j++) {
				fileId.add(attachments.get(j).getFile().getId());
			}
			posts.get(i).setFileId(fileId);
			
			List<Polling>pollings = pollingDao.getByPost(posts.get(i).getId());
			List<String>pollContents = new ArrayList<>();
			List<String>pollId = new ArrayList<>();
			List<Integer>totalPoll = new ArrayList<>();
			Boolean statusPolling = false;
			Integer totalVote = 0;
			for(int k = 0; k<pollings.size();k++) {
				pollContents.add(pollings.get(k).getPollContent());
				pollId.add(pollings.get(k).getId());
				totalPoll.add(pollings.get(k).getTotalPoll());	
				totalVote += pollings.get(k).getTotalPoll();
			}
			posts.get(i).setPollContents(pollContents);
			posts.get(i).setPollId(pollId);
			posts.get(i).setTotalPoll(totalPoll);
			posts.get(i).setTotalVote(totalVote);
			
			String choosenPolling = null;
			try {
				if(pollingStatusDao.getByUserAndPosting(principalService.getAuthPrincipal(),posts.get(i).getId())!= null) {
					statusPolling = true;
					choosenPolling = pollingStatusDao.getByUserAndPosting(principalService.getAuthPrincipal(),posts.get(i).getId()).getPolling().getId();
				}else {
					statusPolling = false;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			posts.get(i).setStatusPolling(statusPolling);
			posts.get(i).setChoosenPolling(choosenPolling);
			
			List<User> userComment= new ArrayList<>();
			List<String>commentId =  new ArrayList<>();
			List<String>commentBody =  new ArrayList<>();
			List<LocalDateTime>createdAtComment=  new ArrayList<>();
			List<Comment>comments = commentDao.getByPostAndOrder(posts.get(i).getId(), 0, 2, false);
			for(int l = comments.size()-1 ; l>=0; l--) {
				userComment.add(comments.get(l).getUser());
				commentBody.add(comments.get(l).getCommentBody());
				commentId.add(comments.get(l).getId());
				createdAtComment.add(comments.get(l).getCreatedAt());
			}
			posts.get(i).setCommentBody(commentBody);
			posts.get(i).setCommentId(commentId);
			posts.get(i).setCreatedAtComment(createdAtComment);
			posts.get(i).setUserComment(userComment);
			Comment countOfComment = commentDao.getTotalByPost(posts.get(i).getId());
			posts.get(i).setCountOfComment(countOfComment.getCountOfComment());
		}
		return posts;
	}

	public ResponseMessageDto insert(Post data) {
		Post postInsert = new Post();

		ResponseMessageDto responseMessageDto = new ResponseMessageDto();
		com.lawencon.lawenconcommunity.model.PostType postType = postTypeDao
				.getByIdAndDetach(com.lawencon.lawenconcommunity.model.PostType.class, data.getPostTypeId());
		begin();
		try {

			if (PostType.POL.toString().equals(postType.getPostTypeCode())) {
				postInsert.setContents(data.getContents());

				postInsert.setPostCode(generateService.generate(5));
				postInsert.setPostType(postType);
				postInsert.setTitle(data.getTitle());
				postInsert.setTitlePoll(data.getTitlePoll());
				postInsert.setFile(data.getFile());
				valInsert(postInsert);

				postInsert = postDao.save(postInsert);
				for (int i = 0; i < data.getPollContents().size(); i++) {
					Polling pollingInsert = new Polling();
					pollingInsert.setPost(postInsert);
					pollingInsert.setPollContent(data.getPollContents().get(i));
					pollingInsert.setTotalPoll(0);
					pollingInsert = pollingDao.save(pollingInsert);
				}
				if (data.getFile() != null) {
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
				if (data.getFile() != null) {
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
				if (data.getFile() != null) {
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
			}
		} catch (Exception e) {
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
