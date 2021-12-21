package telran.b7a.security.filter;

import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

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

import telran.b7a.accounting.dao.AccountingMongoRepository;
import telran.b7a.accounting.dto.exception.UserNotFoundException;
import telran.b7a.accounting.model.UserAccount;
import telran.b7a.forum.dao.ForumMongoRepository;
import telran.b7a.forum.dto.exception.PostNotFoundException;
import telran.b7a.forum.model.Post;

@Service
@Order(50)
public class DeletePostFilter implements Filter {
	AccountingMongoRepository aRepository;
	ForumMongoRepository fRepository;
	
	@Autowired
	public DeletePostFilter(AccountingMongoRepository aRepository, ForumMongoRepository fRepository) {
		this.aRepository = aRepository;
		this.fRepository = fRepository;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		String path = request.getServletPath();
		if (checkEndPoints(path , request.getMethod())) {
			Principal user = request.getUserPrincipal();
			UserAccount userAccount = aRepository.findById(user.getName()).orElseThrow(() -> new UserNotFoundException());
			
			String[] arrPath = path.split("/");
			String id = arrPath[arrPath.length-1];
			Post post = fRepository.findById(id).orElseThrow(() -> new PostNotFoundException());
			if (
					(
					!(userAccount.getRoles().contains("moderator".toUpperCase())))
					&& 
					!(user.getName().equalsIgnoreCase(post.getAuthor()))
					) {
				response.sendError(401, "You don't have rights to delete this post" + 
					userAccount.getRoles().stream().reduce(" ", (a,b) -> a.concat(b + " ")));
				return;
			}
			
		}
		chain.doFilter(request, response);
	}
	
	private boolean checkEndPoints(String path, String method) {
		return (
				("DELETE".equalsIgnoreCase(method) && path.matches("/forum/post/\\w+/?"))
			);
	}
}
