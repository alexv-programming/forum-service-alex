package telran.b7a.forum.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.b7a.forum.model.Post;

public interface ForumMongoRepository extends MongoRepository<Post, Integer> {
	
	Optional<Post> findById(String id);	

	Stream<Post> findByAuthorIgnoreCase(String author);

	Stream<Post> findByDateCreatedBetween(LocalDate from, LocalDate to);
	
	Stream<Post> findByTagsContaining(List<String> tags);
}
