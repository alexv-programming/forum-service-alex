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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import telran.b7a.forum.dao.ForumMongoRepository;
import telran.b7a.forum.dto.exception.PostNotFoundException;
import telran.b7a.forum.model.Post;

@Service
@Order(40)
public class OwnerOfPostFilter implements Filter {
	ForumMongoRepository repository;
	
	@Autowired
	public OwnerOfPostFilter(ForumMongoRepository repository) {
		this.repository = repository;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		String path = request.getServletPath();
		if (checkEndPoints(path , request.getMethod())) {
			Principal user = request.getUserPrincipal();
			String[] arrPath = path.split("/");
			String id = arrPath[arrPath.length-1];
			Post post = repository.findById(id).orElseThrow(() -> new PostNotFoundException());
			if (post == null) {
				response.sendError(404);
				return;
			}
			if(!user.getName().equalsIgnoreCase(post.getAuthor())) {
				response.sendError(401, "This is not your post to edit");
				return;
			}
		}
		chain.doFilter(request, response);
	}

	private boolean checkEndPoints(String path, String method) {
		return (
				("PUT".equalsIgnoreCase(method) && path.matches("/forum/post/\\w+/?"))
//				|| ("DELETE".equalsIgnoreCase(method) && path.matches("/forum/post/\\w+/?"))
			);
	}
}