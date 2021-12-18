package telran.b7a.accounting.dto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class WrongPasswordException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4704474285695081880L;
	public WrongPasswordException() {
		super("Wrong password or login! Try again. Or not...");
	}
}
