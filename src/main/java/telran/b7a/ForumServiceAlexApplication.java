package telran.b7a;

import org.apache.catalina.startup.UserConfig;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import telran.b7a.accounting.dao.AccountingMongoRepository;
import telran.b7a.accounting.model.UserAccount;

@SpringBootApplication
public class ForumServiceAlexApplication implements CommandLineRunner {
	
	@Autowired
	AccountingMongoRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(ForumServiceAlexApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if(!repository.existsById("admin")) {
			String password = BCrypt.hashpw("admin", BCrypt.gensalt());
			UserAccount userAccount = new UserAccount("admin", password, "", "");
			userAccount.addRole("USER".toUpperCase());
			userAccount.addRole("Moderator".toUpperCase());
			userAccount.addRole("administrator".toUpperCase());;
			repository.save(userAccount);
		}
		
	}

}
