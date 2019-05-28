package simpledemo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import simpledemo.model.Article;

public interface ArticlesRepository extends MongoRepository<Article, String>{
	List<Article> findByAuthorId(String authorId);
	Optional<Article> removeById(String id);
}
