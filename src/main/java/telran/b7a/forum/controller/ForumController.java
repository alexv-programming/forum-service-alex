package telran.b7a.forum.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import telran.b7a.forum.dto.AddPostDto;
import telran.b7a.forum.dto.ResponseDto;
import telran.b7a.forum.service.ForumService;

@RestController
public class ForumController {
	ForumService forumService;

	@Autowired
	public ForumController(ForumService forumService) {
		this.forumService = forumService;
	}

	@PostMapping("/forum/post/{/forum/post/{author}")
	public ResponseDto postPost(@RequestBody AddPostDto addPostDto, @PathVariable String author) {
		return forumService.addDto(addPostDto, author);
	}

	@GetMapping("/forum/post/{id}")
	public ResponseDto findPostById(@PathVariable Integer id) {
		return forumService.findPostById(id);
	}
}
