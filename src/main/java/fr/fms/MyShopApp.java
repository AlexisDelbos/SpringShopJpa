package fr.fms;

import fr.fms.business.iShopImpl;
import fr.fms.entities.Article;
import fr.fms.entities.Category;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class MyShopApp implements CommandLineRunner {

    private static iShopImpl iShop = new iShopImpl();
    private Scanner scanner = new Scanner(System.in);
    private int pageSize = 5;
    private int pageNumber = 0;

    public static void main(String[] args) {
        SpringApplication.run(MyShopApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        int option;
        do {
            System.out.println(" ==== Bienvenue dans l'application MyShop ! ==== ");
            System.out.println("1. Afficher les articles sans pagination");
            System.out.println("2. Afficher les articles avec pagination");
            System.out.println("==== Articles ==== ");
            System.out.println("3. Ajouter un article");
            System.out.println("4. Afficher un article");
            System.out.println("5. Supprimer un article");
            System.out.println("6. Mettre à jour un article");
            System.out.println("===== Catégorie ===== ");
            System.out.println("7. Ajouter une catégorie");
            System.out.println("8. Afficher une catégorie");
            System.out.println("9. Supprimer une catégorie");
            System.out.println("10. Mettre à jour une catégorie");
            System.out.println("11. Afficher tous les articles d'une catégorie");
            System.out.println("12. Quitter");
            System.out.print("Choisissez une option: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    displayAllArticles();
                    break;
                case 2:
                    displayAllArticlesByPaging();
                    break;
                case 3:
                    addArticle();
                    break;
                case 4:
                    displayArticle();
                    break;
                case 5:
                    removeArticle();
                    break;
                case 6:
                    updateArticle();
                    break;
                case 7:
                    addCategory();
                    break;
                case 8:
                    displayCategory();
                    break;
                case 9:
                    removeCategory();
                    break;
                case 10:
                    updateCategory();
                    break;
                case 11:
                    displayArticleByCategory();
                    break;
                case 12:
                    System.out.println("Au revoir");
                    break;
                default:
                    System.out.println("Option invalide.");
            }
        } while (option != 12);
    }

    private void displayAllArticles() {
        List<Article> articles = iShop.getAllArticles();
        for (Article article : articles) {
            System.out.println(article);
        }
    }

    private void displayAllArticlesByPaging() {
        int option;
        boolean exit = false;
        while (!exit) {
            Page<Article> page = iShop.getArticlesWithPagination(pageNumber, pageSize);
            System.out.println("\n==== Affichage des articles - Page " + (pageNumber + 1) + " ====");

            for (Article article : page.getContent()) {
                System.out.println(article);
            }

            System.out.println("\nTotal des pages : " + page.getTotalPages());
            System.out.println("Total des articles : " + page.getTotalElements());
            System.out.println("Articles par page : " + pageSize);

            System.out.println("\nQue voulez-vous faire ?");
            System.out.println("1. Page suivante");
            System.out.println("2. Page précédente");
            System.out.println("3. Modifier le nombre d'articles par page (actuellement : " + pageSize + ")");
            System.out.println("4. Retour au menu principal");
            System.out.print("Choisissez une option : ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    if (pageNumber < page.getTotalPages() - 1) {
                        pageNumber++;
                    } else {
                        System.out.println("Vous êtes déjà à la dernière page.");
                    }
                    break;

                case 2:
                    if (pageNumber > 0) {
                        pageNumber--;
                    } else {
                        System.out.println("Vous êtes déjà à la première page.");
                    }
                    break;

                case 3:
                    System.out.print("Entrez le nouveau nombre d'articles par page : ");
                    pageSize = scanner.nextInt();
                    break;

                case 4:
                    exit = true;
                    break;

                default:
                    System.out.println("Option invalide. Essayez encore.");
            }
        }
    }

    private void addArticle() {
        System.out.print("Description : ");
        String description = scanner.nextLine();
        System.out.print("Marque : ");
        String brand = scanner.nextLine();
        System.out.print("Prix : ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Nom de la catégorie : ");
        String categoryName = scanner.nextLine();

        try {
            iShop.addArticle(description, brand, price, categoryName);
            System.out.println("Article ajouté !");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void displayArticle() {
        System.out.print("ID de l'article : ");
        Long id = scanner.nextLong();
        Optional<Article> article = iShop.getArticleById(id);
        if (article.isPresent()) {
            System.out.println(article.get());
        } else {
            System.out.println("Article non trouvé.");
        }
    }

    private void removeArticle() {
        System.out.print("ID de l'article à supprimer : ");
        Long id = scanner.nextLong();
        try {
            iShop.deleteArticle(id);
            System.out.println("Article supprimé.");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateArticle() {
        System.out.print("ID de l'article : ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        System.out.print("Nouvelle description : ");
        String description = scanner.nextLine();
        System.out.print("Nouvelle marque : ");
        String brand = scanner.nextLine();
        System.out.print("Nouveau prix : ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Nouvelle catégorie : ");
        String categoryName = scanner.nextLine();

        try {
            iShop.updateArticle(id, description, brand, price, categoryName);
            System.out.println("Article mis à jour.");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void addCategory() {
        System.out.print("Nom de la catégorie : ");
        String name = scanner.nextLine();
        try {
            iShop.addCategory(name);
            System.out.println("Catégorie ajoutée.");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void displayCategory() {
        System.out.print("ID de la catégorie : ");
        Long id = scanner.nextLong();
        Optional<Category> category = iShop.getCategoryById(id);
        if (category.isPresent()) {
            System.out.println(category.get());
        } else {
            System.out.println("Catégorie non trouvée.");
        }
    }

    private void removeCategory() {
        System.out.print("ID de la catégorie : ");
        Long id = scanner.nextLong();
        try {
            iShop.deleteCategory(id);
            System.out.println("Catégorie supprimée.");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateCategory() {
        System.out.print("ID de la catégorie : ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        System.out.print("Nouveau nom : ");
        String newName = scanner.nextLine();
        try {
            iShop.updateCategory(id, newName);
            System.out.println("Catégorie mise à jour.");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void displayArticleByCategory() {
        System.out.print("ID de la catégorie : ");
        Long id = scanner.nextLong();
        List<Article> articles = iShop.getArticlesByCategoryId(id);
        if (articles.isEmpty()) {
            System.out.println("Aucun article trouvé.");
        } else {
            for (Article article : articles) {
                System.out.println(article);
            }
        }
    }
}