package telran.b7a.forum.dto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class PostNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8675216042467271591L;
	public PostNotFoundException(Integer id) {
		super("post with id: " + id + " not found");
	}
}