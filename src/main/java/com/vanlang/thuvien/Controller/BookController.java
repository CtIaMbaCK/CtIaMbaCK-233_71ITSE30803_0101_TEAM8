package com.vanlang.thuvien.Controller;

import com.vanlang.thuvien.model.Book;
import com.vanlang.thuvien.service.BookService;
import com.vanlang.thuvien.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService; // them category service vao

    //show books
    @GetMapping
    public String showBooks(Model model) {
        model.addAttribute("books",bookService.getAllBook());
        return "/books/index";
    }

    // them san pham
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categories",categoryService.getAllCategories());
        return "/books/add-book";
    }

    @PostMapping("/add")
    public String addBook (@Valid Book book, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "books/add-book";
        }
        bookService.addBook(book);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Book book = bookService.getBooKById(id).orElseThrow( () ->
                new IllegalArgumentException("Invalid book ID: " + id));
        model.addAttribute("book",book);
        model.addAttribute("categories",categoryService.getAllCategories());
        return "/books/update-book";
    }

    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable("id") Long id, @Valid Book book, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            book.setId(id);
            return "/books/update-book";
        }
        bookService.updateBook(book);
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook (@PathVariable("id") Long id) {
        bookService.deleteBookById(id);
        return "redirect:/books";
    }

}
