package com.vanlang.thuvien.repository;

import com.vanlang.thuvien.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT c FROM Category c WHERE c.category_code LIKE ?1%")
    List<Category> searchCategory(String keyword);
}
