package simpledemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import simpledemo.model.Article;
import simpledemo.service.ArticlePresenter;

public class AnnotationSpringDIDemo {

	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext("simpledemo");
		ArticlePresenter presenter = ctx.getBean(ArticlePresenter.class);
		System.out.println("Anntoations Di Demo");
		presenter.present();
		presenter.addArticle(Article.builder().title("New Article").content("New content ...").build());
		System.out.println("\nAfter:");
		presenter.present();

	}

}
