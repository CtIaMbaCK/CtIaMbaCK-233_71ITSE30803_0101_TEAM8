package com.vanlang.thuvien.Controller;

import com.vanlang.thuvien.model.CartItem;
import com.vanlang.thuvien.model.Order;
import com.vanlang.thuvien.service.CartService;
import com.vanlang.thuvien.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @GetMapping("/checkout")
    public String checkout() {
        return "/cart/checkout";
    }

    @PostMapping("/submit")
    public String submitOrder(String customerName, String phoneNumber,
                              LocalDateTime borrowTime, LocalDateTime returnTime) {

        List<CartItem> cartItems = cartService.getCartItems();
        if ( cartItems.isEmpty()) {
            return "redirect:/cart";
        }
        orderService.createOrder(customerName,phoneNumber,borrowTime, returnTime, cartItems);
        return "redirect:/order/confirmation";
    }

    @GetMapping("/confirmation")
    public String orderConfirmation(Model model) {
        model.addAttribute("message","Bạn đã đặt mượn cuốn sách.");
        return "cart/order-confirmation";
    }

    @GetMapping("/list")
    public String showAddForm(Model model) {
        model.addAttribute("orders", orderService.getAllOrder());
        return "/cart/cart-list";
    }

    @GetMapping("/cart/delete/{id}")
    public String deleteOrder(@PathVariable("id") Long id, Model model) {
        Order order = orderService.getOrderById(id).orElseThrow(() -> new IllegalArgumentException("Invalid category Id:" + id));
        orderService.deleteOrder(id);
        model.addAttribute("orders", orderService.getAllOrder());
        return "redirect:/order/list";
    }


}
