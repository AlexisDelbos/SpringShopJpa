package fr.fms.business;

import fr.fms.entities.Article;
import fr.fms.entities.Category;

import java.util.List;
import java.util.Optional;

public interface iShop {

    public List<Article> getAllArticles();

    public void addArticle(String description, String brand, double price, String categoryName);

    public Optional<Article> getArticleById(Long id);

    public void updateArticle(Long articleId, String newDescription, String newBrand, double newPrice, String newCategoryName);

    public void deleteArticle(Long articleId);

    public void addCategory(String categoryName);

    public Optional<Category> getCategoryById(Long id);

    public List<Category> getAllCategories();

    public void updateCategory(Long categoryId, String newName);

    public void deleteCategory(Long categoryId);

    public List<Article> getArticlesByCategoryId(Long categoryId);
    }
