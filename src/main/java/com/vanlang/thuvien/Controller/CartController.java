package com.vanlang.thuvien.Controller;

import com.vanlang.thuvien.model.CartItem;
import com.vanlang.thuvien.model.Category;
import com.vanlang.thuvien.model.Order;
import com.vanlang.thuvien.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping
    public String showCart(Model model) {
        model.addAttribute("cartItems",cartService.getCartItems());
        return "/cart/cart";
    }
    @PostMapping("/add")
    public String addToCart(@RequestParam Long bookId) {
        cartService.addToCart(bookId);
        return "redirect:/cart";
    }

    @GetMapping("/remove/{bookId}")
    public String removeFromCart(@PathVariable Long bookId) {
        cartService.removeFromCart(bookId);
        return "redirect:/cart";
    }
    @GetMapping("/clear")
    public String cleanCart() {
        cartService.clearCard();
        return "redirect:/cart";
    }


}
