package telran.b7a.forum.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import telran.b7a.forum.model.Comment;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDto {
	String id;
	String title;
	String content;
	String author;
	LocalDateTime dateCreated;
	Set<String> tags;
	Integer likes;
	List<Comment> comments;
}
