package telran.b7a.forum.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mongodb.Tag;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import telran.b7a.forum.dto.AddPostDto;

@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = { "id" })
public class Post {
	@Id
	String id;
	@Setter
	String title;
	@Setter
	String content;
	@Setter
	String author;
	@JsonFormat //(pattern = "yyyy-MM-dd_HH:mm:ss")
	LocalDateTime dateCreated;
	@Setter
	Set<String> tags = new HashSet<String>();
	int likes = 0;
	List<Comment> comments = new ArrayList<Comment>();

	public Post(String title, String content, String author, Set<String> tags) {
		this.title = title;
		this.content = content;
		this.author = author;
		dateCreated = LocalDateTime.now();
		this.tags = tags;
	}
	
	public void addLike() {
		likes++;
	}
	
	public boolean addTag(String tag) {
		return tags.add(tag);
	}
	
//	public boolean removeTag(String tag) {
//		return tags.remove(tag);
//	}
//
	public boolean addComment(Comment comment) {
		return comments.add(comment);
	}
	
	public void setDateCreated() {
		this.dateCreated = LocalDateTime.now();
	}
}
