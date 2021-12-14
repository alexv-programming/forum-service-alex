package telran.b7a.forum.service;

import telran.b7a.forum.dto.AddPostDto;
import telran.b7a.forum.dto.ResponseDto;

public interface ForumService {
	ResponseDto addDto(AddPostDto addPostDto, String author);
	
	ResponseDto findPostById(Integer id); 

}
