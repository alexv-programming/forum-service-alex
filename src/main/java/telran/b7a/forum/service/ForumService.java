package telran.b7a.forum.service;

import java.util.List;

import telran.b7a.forum.dto.AddCommentDto;
import telran.b7a.forum.dto.AddPostDto;
import telran.b7a.forum.dto.FindPostByPeriodDto;
import telran.b7a.forum.dto.ResponseDto;

public interface ForumService {
	ResponseDto addPost(AddPostDto addPostDto, String author);
	
	ResponseDto findPostById(String id); 
	
	ResponseDto deletePost(String id);
	
	ResponseDto updatePost(String id, AddPostDto addPostDto);
	
	Integer addLikeToPost(String id);
	
	ResponseDto addCommentToPost(String id, String author, AddCommentDto addCommentDto);
	
	Iterable<ResponseDto> findPostsByAuthor(String author);

	Iterable<ResponseDto> findPostByPeriod(FindPostByPeriodDto findPostByPeriodDto);

	Iterable<ResponseDto> findPostsByTags(List<String> tags);
}
