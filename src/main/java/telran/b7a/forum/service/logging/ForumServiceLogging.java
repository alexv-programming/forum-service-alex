package telran.b7a.forum.service.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "Post service")
@Service
@Aspect
public class ForumServiceLogging {
	
	//@Pointcut("execution(* telran.b7a.forum.serviceImpl.*(..))")
	
	@Pointcut("execution(public * telran.b7a.forum.service.ForumServiceImp.find*(..))")
	public void bulkingFind() {}

	@Pointcut("execution(public * "
			+ "telran.b7a.forum.service.ForumServiceImp.findPostById(..)) && args(id)")
	public void findById(String id) {}
	
	@Pointcut("@annotation(PostLogger) && args(id)")
	public void annotated(String id) {}
	
	@Around("bulkingFind()")
	public Object bulkingFindLogging(ProceedingJoinPoint pjp) throws Throwable {
		//Object[] args = pjp.getArgs();
		long t1 = System.currentTimeMillis();
		Object retVal =  pjp.proceed();
		long t2 = System.currentTimeMillis();
		log.info("method - {}, duration = {}", pjp.getSignature().toLongString(), (t2-t1));
		return retVal;
	}
	
	@Before("findById(id)")
	public void signInLogging(String id) {
		log.info("user {} sign in", id);
	}
	
	@AfterReturning("annotated(id)")
	public void updatePostLoggin(String id) {
		log.info("post with id {} update", id);
	}
}
