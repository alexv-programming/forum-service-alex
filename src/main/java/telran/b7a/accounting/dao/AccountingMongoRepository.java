package telran.b7a.accounting.dao;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.b7a.accounting.model.User;

public interface AccountingMongoRepository extends MongoRepository<User, Integer>{
	
	Optional<User> findByLogin(String id);
	
	void deleteByLogin(String id);
	
}
