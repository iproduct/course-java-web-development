package simpledemo.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.json.async.NonBlockingJsonParser;

import simpledemo.dao.ArticleProvider;
import simpledemo.dao.ArticlesRepository;
import simpledemo.exception.EntityExistsException;
import simpledemo.exception.NonexistingEntityException;
import simpledemo.model.Article;

@Service
public class MockArticleService implements ArticleService {
	private ArticlesRepository repo;

	@Autowired
	public MockArticleService(ArticlesRepository repository) {
		this.repo = repository;
	}

	@Override
	public Article addArticle(Article article) {
		return repo.save(article);
	}

	@Override
	public Collection<Article> getArticles() {
		return repo.findAll();
	}

	@Override
	public Article getArticleById(String id) throws NonexistingEntityException {
		return repo.findById(id)
			.orElseThrow(() -> new NonexistingEntityException("Entity with ID=" + id + " does not exist."));
	}

	@Override
	public Article deleteArticleById(String id) throws NonexistingEntityException {
		Optional<Article> old = repo.findById(id);
		old.ifPresent(article -> repo.deleteById(article.getId()));
		return old
			.orElseThrow(() -> new NonexistingEntityException("Entity with ID=" + id + " does not exist."));
	}

	@Override
	public Article updateArticle(Article article) throws NonexistingEntityException {
		getArticleById(article.getId());
		return repo.save(article);
	}

}
