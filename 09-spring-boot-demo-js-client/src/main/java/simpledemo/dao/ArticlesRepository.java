package simpledemo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import simpledemo.model.Article;

public interface ArticlesRepository extends MongoRepository<Article, String>{
	List<Article> findByAuthorId(String authorId);
//	@Query(value="{id : ?0}", delete = true, fields="{title : 1, content: 1}")
//	Optional<Article> removeById(String id);
}
