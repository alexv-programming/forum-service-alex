package telran.b7a.forum.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
	@Setter
	String user;
	@Setter
	String message;
	@JsonFormat(pattern = "yyyy-MM-dd_HH:mm:ss")
	LocalDateTime dateCreated = LocalDateTime.now();
	int likes =0;
	
	public Comment(String user, String message) {
		this.user = user;
		this.message = message;
		this.dateCreated = LocalDateTime.now();
		this.likes = 0;
	}
	
	public int addLike() {
		likes++;
		return likes;
	}
	
	
}
