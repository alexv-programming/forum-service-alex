package telran.b7a.accounting.dao;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.b7a.accounting.model.UserAccount;

public interface AccountingMongoRepository extends MongoRepository<UserAccount, String>{
	

	
}
