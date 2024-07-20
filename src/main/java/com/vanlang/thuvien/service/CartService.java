package com.vanlang.thuvien.service;

import com.vanlang.thuvien.model.Book;
import com.vanlang.thuvien.model.CartItem;
import com.vanlang.thuvien.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@SessionScope
public class CartService {

    private List<CartItem> cartItems = new ArrayList<CartItem>();

    @Autowired
    private BookRepository bookRepository;

    public void addToCart(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() ->
                new IllegalArgumentException("Book not found:  " + bookId));
        cartItems.add(new CartItem(book));
    }

    public List<CartItem> getCartItems() {
        return cartItems;

    }

    public void removeFromCart(Long productId) {
        cartItems.removeIf(cartItem -> cartItem.getBook().getId().equals(productId));
    }

    public void clearCard() {
        cartItems.clear();
    }


}
