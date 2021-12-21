package telran.b7a.security.filter;

import java.io.IOException;
import java.security.Principal;
import java.util.Base64;
import java.util.Optional;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.b7a.accounting.dao.AccountingMongoRepository;
import telran.b7a.accounting.model.UserAccount;

@Service
public class AuthanticationFilter implements Filter {
	
	AccountingMongoRepository repository;
	
	@Autowired
	public AuthanticationFilter(AccountingMongoRepository repository) {
		this.repository = repository;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		//TODO
		if (checkEndPoints(request.getServletPath(), request.getMethod())) {
			String token = request.getHeader("Authorization");
			if (token == null) {
				response.sendError(401, "Header Authorization not found");
				return;
			}
			String[] credentials = getCredentials(token).orElse(null);
			if (credentials == null || credentials.length < 2) {
				response.sendError(401, "Token not valid");
				return;
			}
			UserAccount userAccount = repository.findById(credentials[0]).orElse(null);
			if (userAccount == null) {
				response.sendError(401, "User not found");
				return;
			}
			if (!BCrypt.checkpw(credentials[1], userAccount.getPassword())) {
				response.sendError(401, "User or password not valid");
				return;
			} 
			request = new WrappedRequest(request, credentials[0]);
		}
		chain.doFilter(request, response);
	}

	private boolean checkEndPoints(String path, String method) {
		return !(
					("POST".equalsIgnoreCase(method) && path.matches("[/]account[/]register[/]?"))
					|| (path.matches("[/]forum[/]posts([/]\\w+)+[/]?"))
				);
	}

	private Optional<String[]> getCredentials(String token) {
		String[] res = null;
		try {
			token = token.split(" ")[1];
			byte[] bytesDecode = Base64.getDecoder().decode(token);
			token = new String(bytesDecode);
			res = token.split(":");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Optional.ofNullable(res);
	}

	private class WrappedRequest extends HttpServletRequestWrapper {
		String login;

		public WrappedRequest(HttpServletRequest request, String login) {
			super(request);
			this.login = login;
		}

		@Override
		public Principal getUserPrincipal() {
			return () -> login;
		}
	}
}