package com.vanlang.thuvien.service;

import com.vanlang.thuvien.model.Book;
import com.vanlang.thuvien.repository.BookRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {
    private final BookRepository bookRepository;

    // đưa về tất cả các cuốn sách
    public List<Book> getAllBook() {
        return bookRepository.findAll();
    }


    // đưa sách theo id
    public Optional<Book> getBooKById(Long id) {
        return bookRepository.findById(id);
    }

    // thêm sách mới
    public Book addBook( Book book) {
        return bookRepository.save(book);
    }

    // cập nhật các cuốn sách đã có
    public Book updateBook (@NonNull Book book) {
        Book existingBook = bookRepository.findById(book.getId()).orElseThrow( () ->
                new IllegalStateException(" Book with ID " + book.getId() + "does not exist"));
        existingBook.setCategory(book.getCategory());
        existingBook.setId(book.getId());
        existingBook.setName(book.getName());
        existingBook.setDescription(book.getDescription());
        existingBook.setCategory_code(book.getCategory_code());
        existingBook.setImage(book.getImage());
        return bookRepository.save(existingBook);
    }

    // xóa sản phẩm bằng id
    public void deleteBookById(Long id) {
        if(!bookRepository.existsById(id)) {
            throw new IllegalStateException("Product with ID " + id + "does not exist.");
        }
        bookRepository.deleteById(id);
    }
}
