package com.example.springsecurityjwt.repositories;

import com.example.springsecurityjwt.models.post.Post;
import com.example.springsecurityjwt.models.post.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);

    @Query("SELECT CASE WHEN COUNT(cat)> 0 THEN TRUE ELSE FALSE END FROM Category cat WHERE LOWER(cat.name) LIKE LOWER(:name)")
    boolean findExistingCategoryByName(@Param("name") String name);

    @Query("SELECT cat FROM Category cat WHERE cat.status like('ACTIVE')")
    ArrayList<Category> findAllActiveCategories();

    @Query("SELECT cat FROM Category cat WHERE cat.id = :id")
    Category findCategoryById(@Param("id") Long id);
}
