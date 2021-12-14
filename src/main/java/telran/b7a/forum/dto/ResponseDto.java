package telran.b7a.forum.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDto {
	@Id
	String id;
	@Setter
	String title;
	@Setter
	String content;
	String author;
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	LocalDateTime dateCreated;
	@Singular
	Set<String> tags;
	Integer likes;
	@Singular
	List<ResponseCommentDto> comments;
}
