package telran.b7a.forum.model;

import java.time.LocalDateTime;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import telran.b7a.forum.dto.AddPostDto;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {
	String user;
	String message;
	LocalDateTime dateCreated;
	Integer likes;
}
