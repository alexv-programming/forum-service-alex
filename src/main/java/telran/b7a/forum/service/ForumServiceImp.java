package telran.b7a.forum.service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.asm.Advice.Return;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import telran.b7a.forum.dao.ForumMongoRepository;
import telran.b7a.forum.dto.AddCommentDto;
import telran.b7a.forum.dto.AddPostDto;
import telran.b7a.forum.dto.FindPostByPeriodDto;
import telran.b7a.forum.dto.ResponseDto;
import telran.b7a.forum.dto.exception.PostNotFoundException;
import telran.b7a.forum.model.Comment;
import telran.b7a.forum.model.Post;
import telran.b7a.forum.service.logging.PostLogger;

@Service
@Slf4j
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
		Post post = forumRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
		return modelMapper.map(post, ResponseDto.class);
	}

	@Override
	public ResponseDto deletePost(String id) {
		Post post = forumRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
		forumRepository.delete(post);
		return modelMapper.map(post, ResponseDto.class);
	}

	@Override
	@PostLogger
	public ResponseDto updatePost(String id, AddPostDto addPostDto) {
		Post post = forumRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
		updateWhatNeeded(post, addPostDto);
		forumRepository.save(post);		
		return modelMapper.map(post, ResponseDto.class);
	}

	private void updateWhatNeeded(Post post, AddPostDto addPostDto) {
		String content = addPostDto.getContent();
		if (content != null && content != "") {
			post.setContent(content);
		}
		String title = addPostDto.getTitle();
		if (title != null && title != "") {
			post.setTitle(title);
		}
		Set<String> tags = addPostDto.getTags();
		if (tags != null) {
			tags.forEach(post::addTag);
		}
	}

	@Override
	public Integer addLikeToPost(String id) {
		Post post = forumRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
		post.addLike();
		forumRepository.save(post);
		return post.getLikes();
	}

	@Override
	public ResponseDto addCommentToPost(String id, String author, AddCommentDto addCommentDto) {
		Post post = forumRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
		Comment comment = new Comment(author, addCommentDto.getMessage());
		post.addComment(comment);
		forumRepository.save(post);
		return modelMapper.map(post, ResponseDto.class);
	}

	@Override
	public Iterable<ResponseDto> findPostsByAuthor(String author) {
		return forumRepository.findByAuthorIgnoreCase(author)
				.map(p -> modelMapper.map(p, ResponseDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public Iterable<ResponseDto> findPostByPeriod(FindPostByPeriodDto dateRequestDto) {
		LocalDate from = dateRequestDto.getDateFrom();
		LocalDate to = dateRequestDto.getDateTo().plusDays(1);
		return forumRepository.findByDateCreatedBetween(from, to)
						.map(p -> modelMapper.map(p, ResponseDto.class))
						.collect(Collectors.toList());		
	}

	@Override
	public Iterable<ResponseDto> findPostsByTags(List<String> tags) {
		return forumRepository.findByTagsContaining(tags)	//findByTagsIn
				.map(p -> modelMapper.map(p, ResponseDto.class))
				.collect(Collectors.toList());
	}
}
