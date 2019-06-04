package simpledemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import simpledemo.model.Article;
import simpledemo.service.ArticlePresenter;

public class AnnotationSpringDIDemo {

	public static void main(String[] args) {
		ApplicationContext ctx. = new AnnotationConfigApplicationContext(simpledemo.AnnotationSpringDIDemo.class);
		ArticlePresenter presenter = ctx.getBean("presenter", ArticlePresenter.class);
		System.out.println("Anntoations Di Demo");
		presenter.present();
		
		System.out.println("\nAfter:");
		presenter.addArticle(Article.builder().title("New Article").content("New content ...").build());
		presenter.present();

	}

}
