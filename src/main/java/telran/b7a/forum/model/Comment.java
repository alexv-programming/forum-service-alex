package telran.b7a.forum.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
	String user;
	String message;
	@JsonFormat(pattern = "yyyy-MM-dd_HH:mm:ss")
	LocalDateTime dateCreated;
	int likes;
}
