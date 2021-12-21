package telran.b7a.security.filter;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
@Order(30)
public class OwnerFilter implements Filter {
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		Principal principal = request.getUserPrincipal();
		String servletPath = request.getServletPath();
		if (checkEndPoints(servletPath, request.getMethod())) {
			String[] uriStrings = servletPath.split("[/]");
			String userChanging = uriStrings[uriStrings.length-1];
			if (!principal.getName().equals(userChanging)) {
				response.sendError(403, "filter 3 error Owner filter. principal :" 
			+ principal.getName() + " userChanging : " + userChanging);
				return;
			}
		}
		
		chain.doFilter(request, response);
	}
		
	private boolean checkEndPoints(String path, String method) {
		return 
			(
				(path.matches("[/]account[/]user[/]\\w+[/]?"))
				|| ("POST".equalsIgnoreCase(method) && path.matches("[/]forum[/]post[/]\\w+[/]?"))
				|| (path.matches("/forum/post/\\w+/comment/\\w+/?"))
			);
	}

}

	
