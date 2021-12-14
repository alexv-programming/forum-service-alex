package telran.b7a.forum.service;

import telran.b7a.forum.dto.AddCommentDto;
import telran.b7a.forum.dto.AddPostDto;
import telran.b7a.forum.dto.ResponseDto;

public interface ForumService {
	ResponseDto addPost(AddPostDto addPostDto, String author);
	
	ResponseDto findPostById(Integer id); 
	
	ResponseDto deletePost(Integer id);
	
	ResponseDto updatePost(Integer id, AddPostDto addPostDto);
	
	void addLikeToPost(Integer id);
	
	ResponseDto addCommentToPost(Integer id, String author, AddCommentDto addCommentDto);
	
	ResponseDto findPostsByAuthor(String author);
}
