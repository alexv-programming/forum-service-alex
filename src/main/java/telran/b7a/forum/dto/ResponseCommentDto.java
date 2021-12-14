package telran.b7a.forum.dto;

import java.time.LocalDateTime;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseCommentDto {
	String user;
	String message;
	LocalDateTime dateCreated;
	Integer likes;
}
