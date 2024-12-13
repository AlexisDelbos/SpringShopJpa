package fr.fms.dao;

import fr.fms.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    public List<Article> findByBrand(String brand);
    public List<Article> findByBrandContains(String brand);
    public List<Article> findByBrandAndPrice(String brand, double price);

    public List<Article> findByCategoryId(Long id);


    public void deleteById(Long id);

    @Query("select a from Article a WHERE a.id = :y")
    public List<Article> findOneArticle(@Param("y") Long id);


    @Query("SELECT a FROM Article a ")
    public List<Article> findAllArticles();

    // Exo 1.xx
    @Query("select A from Article A where A.brand like %:x% and A.price > :y")
    public List<Article> searchArticles(@Param("x") String kw, @Param("y") double price);

    // Exo 1.xx
    @Query("select a from Article a where a.description like %:description% and a.brand = :brand")
    public List<Article> findArticleByDescriptionAndBrand(@Param("description") String description, @Param("brand") String brand);

}
