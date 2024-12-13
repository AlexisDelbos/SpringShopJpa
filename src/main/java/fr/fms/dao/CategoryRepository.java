package fr.fms.dao;

import fr.fms.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByOrderByNameAsc();
    List<Category> findAllByOrderByNameDesc();

    Optional<Category> findByName(String categoryName);

}
