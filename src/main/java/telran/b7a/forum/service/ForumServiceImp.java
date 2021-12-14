package telran.b7a.forum.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.b7a.forum.dao.ForumMongoRepository;
import telran.b7a.forum.dto.AddCommentDto;
import telran.b7a.forum.dto.AddPostDto;
import telran.b7a.forum.dto.ResponseDto;
import telran.b7a.forum.dto.exception.PostNotFoundException;
import telran.b7a.forum.model.Post;

@Service
public class ForumServiceImp implements ForumService {
	
	ForumMongoRepository forumRepository;
	
	ModelMapper modelMapper;
	
	@Autowired
	public ForumServiceImp(ForumMongoRepository forumMongoRepository, ModelMapper modelMapper) {
		this.forumRepository = forumMongoRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public ResponseDto addPost(AddPostDto addPostDto, String author) {
		Post post = modelMapper.map(addPostDto, Post.class);
		post.setAuthor(author);
		post.setDateCreated();
		forumRepository.save(post);
		return modelMapper.map(post, ResponseDto.class);
	}

	@Override
	public ResponseDto findPostById(String id) {
		Post post = forumRepository.findById(id)
				.orElseThrow(() -> new PostNotFoundException(id));
		return modelMapper.map(post, ResponseDto.class);
	}

	@Override
	public ResponseDto deletePost(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseDto updatePost(String id, AddPostDto addPostDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addLikeToPost(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ResponseDto addCommentToPost(String id, String author, AddCommentDto addCommentDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseDto findPostsByAuthor(String author) {
		// TODO Auto-generated method stub
		return null;
	}

}
