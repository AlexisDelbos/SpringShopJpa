package fr.fms;

import fr.fms.dao.ArticleRepository;
import fr.fms.dao.CategoryRepository;
import fr.fms.entities.Article;
import fr.fms.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringShopJpaApplication implements CommandLineRunner {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringShopJpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

//        categoryRepository.save(new Category("Smartphone"));
//        for(Article article : articleRepository.searchArticles("Sung",200)) {
//            System.out.println(article);
//        }

        Category smartphone = categoryRepository.save(new Category("Smartphone"));
        Category tablet = categoryRepository.save(new Category("Tablet"));
        Category pc = categoryRepository.save(new Category("PC"));

        articleRepository.save(new Article("Samsung S10", "Samsung", 1100, smartphone));
        articleRepository.save(new Article("iPhone 15", "Apple", 1600, smartphone));
        articleRepository.save(new Article("One Plus 7", "One Plus", 700, smartphone));

        articleRepository.save(new Article("GalaxyTab", "Samsung", 450, tablet));
        articleRepository.save(new Article("Ipad", "Apple", 350, tablet));

        articleRepository.save(new Article("Mac", "Apple", 2600, pc));
        articleRepository.save(new Article("Asus Rog", "Asus", 1600, pc));


//        // One Article
//        System.out.println("==========1.2 - One Article================");
//        for (Article article : articleRepository.findOneArticle(7L)) {
//            System.out.println(article);
//        }
//        // All article
//        System.out.println("==========1.2 - All Article================");
//        for (Article article : articleRepository.findAll()) {
//            System.out.println(article);
//        }
//
//        // Par marque et description
//        System.out.println("==========1.3 - Brand & Description================");
//        for(Article article : articleRepository.findArticleByDescriptionAndBrand("S10","Samsung")) {
//               System.out.println("Article trouvé " + article);
//        }
//
//        System.out.println("==========1.4 - Supprimer ================");
//        Long articleId = 1L;
//        Optional<Article> articleOpt = articleRepository.findById(articleId);
//        if (articleOpt.isPresent()) {
//            articleRepository.deleteById(articleId);
//            System.out.println("Article supprimé avec succès.");
//        } else {
//            System.out.println("Article avec l'ID " + articleId + " non trouvé.");
//        }
//
//        System.out.println("==========1.4 - Update ================");
//        Long articleIdUpdate = 6L;
//        Optional<Article> articleOptUpdate = articleRepository.findById(articleIdUpdate);
//
//        if (articleOptUpdate.isPresent()) {
//            Article articleToUpdate = articleOpt.get();
//            articleToUpdate.setBrand("Iphone");
//            articleToUpdate.setDescription("Iphone15");
//            articleToUpdate.setPrice(1500);
//
//            articleRepository.save(articleToUpdate);
//            System.out.println("Article mis à jour : " + articleToUpdate);
//        } else {
//            System.out.println("Article avec l'ID " + articleId + " non trouvé.");
//        }
//
//        System.out.println("==========1.5 - Categorie ASC ================");
//        for (Category category : categoryRepository.findAllByOrderByNameAsc()) {
//            System.out.println(category.getName());
//        }
//
//        System.out.println("==========1.5 - Categorie DESC ================");
//        for (Category category : categoryRepository.findAllByOrderByNameDesc()) {
//            System.out.println(category.getName());
//        }
    }

}




