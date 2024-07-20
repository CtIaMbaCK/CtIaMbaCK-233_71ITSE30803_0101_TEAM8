package com.vanlang.thuvien.service;

import com.vanlang.thuvien.model.Category;
import com.vanlang.thuvien.repository.CategoryRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {
    private final CategoryRepository categoryRepository;

    /**
     * Retrieve all categories from the database.
     * @return a list of categories
     */
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public List<Category> searchCategory(String keyword){
        return categoryRepository.searchCategory(keyword);
    }
    /**
     * Retrieve a category by its id.
     * @param id the id of the category to retrieve
     * @return an Optional containing the found category or empty if not found
     */
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    // Thêm category mới vào db
    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    // Chỉnh sưa thông tin category
    public void updateCategory(@NonNull Category category) {
        Category existingCategory = categoryRepository.findById(category.getId()).orElseThrow(() -> new IllegalStateException("Category with ID " + category.getId() + " does not exist."));
        existingCategory.setCategory_code(category.getCategory_code());
        existingCategory.setName(category.getName());
        categoryRepository.save(existingCategory);
    }


    //Xóa category qua id
    public void deleteCategory(Long id) {
        if(!categoryRepository.existsById(id)) {
            throw new IllegalStateException("Category with ID " + id + " does not exist.");
        }
        categoryRepository.deleteById(id);
    }
}
