package telran.b7a.forum.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import telran.b7a.forum.dto.AddCommentDto;
import telran.b7a.forum.dto.AddPostDto;
import telran.b7a.forum.dto.FindPostByPeriodDto;
import telran.b7a.forum.dto.ResponseDto;
import telran.b7a.forum.service.ForumService;

@RestController
@RequestMapping("/forum")
public class ForumController {
	ForumService forumService;

	@Autowired
	public ForumController(ForumService forumService) {
		this.forumService = forumService;
	}

	@PostMapping("/post/{author}")
	public ResponseDto postPost(@RequestBody AddPostDto addPostDto, @PathVariable String author) {
		return forumService.addPost(addPostDto, author);
	}

	@GetMapping("/post/{id}")
	public ResponseDto findPostById(@PathVariable String id) {
		return forumService.findPostById(id);
	}

	@DeleteMapping("/post/{id}")
	public ResponseDto deletePost(@PathVariable String id) {
		return forumService.deletePost(id);
	}

	@PutMapping("/post/{id}")
	public ResponseDto updatePost(@PathVariable String id, @RequestBody AddPostDto addPostDto) {
		return forumService.updatePost(id, addPostDto);
	}

	@PutMapping("/post/{id}/like")
	public void addLikeToPost(@PathVariable String id) {
		forumService.addLikeToPost(id);
	}

	@PutMapping("/post/{id}/comment/{author}")
	public ResponseDto addCommentToPost(@PathVariable String id, @PathVariable String author,
			@RequestBody AddCommentDto addCommentDto) {
		return forumService.addCommentToPost(id, author, addCommentDto);
	}

	@GetMapping("posts/author/{author}")
	public Iterable<ResponseDto> findPostsByAuthor(@PathVariable String author) {
		return forumService.findPostsByAuthor(author);
	}

	@PostMapping("/posts/tags")
	public Iterable<ResponseDto> findPostsByTags(@RequestBody List<String> tags) {
		return forumService.findPostsByTags(tags);
	}

	@PostMapping("/posts/period")
	public Iterable<ResponseDto> findPostsByPeriod(@RequestBody FindPostByPeriodDto findPostByPeriodDto) {
		return forumService.findPostByPeriod(findPostByPeriodDto);
	}
}
