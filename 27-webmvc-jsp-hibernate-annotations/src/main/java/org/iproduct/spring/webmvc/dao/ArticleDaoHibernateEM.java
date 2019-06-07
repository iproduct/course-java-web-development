package org.iproduct.spring.webmvc.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.iproduct.spring.webmvc.exceptions.EntityDoesNotExistException;
import org.iproduct.spring.webmvc.model.Article;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;

@Repository
//@Transactional
public class ArticleDaoHibernateEM implements ArticleDao {
	@PersistenceContext
	EntityManager em;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Collection<Article> findAll() {
        return em.createQuery("select article from Article article", Article.class)
                .getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Article find(long id) {
        return em.find(Article.class, id);
    }

    @Override
    @Transactional
    public Article create(Article article) {
    	em.persist(article);
        return article;
    }

    @Override
    @Transactional
    public Article update(Article article) {
        Article toBeMerged = find(article.getId());
        if (toBeMerged == null) {
            throw new EntityDoesNotExistException("Article with ID=" + article.getId() + " does not exist.");
        }

        return em.merge(article);
    }

    @Override
    @Transactional
    public Article remove(long articleId) {
        Article toBeDeleted = find(articleId);
        if (toBeDeleted == null) {
            throw new EntityDoesNotExistException("Article with ID=" + articleId + " does not exist.");
        }
        em.remove(toBeDeleted);
        return toBeDeleted;
    }
}
