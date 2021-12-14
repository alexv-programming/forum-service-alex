package telran.b7a.forum.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
public class AddPostDto {
	String title;
	String content;
	Set<String> tags;
}
