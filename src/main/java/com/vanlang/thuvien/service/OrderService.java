package com.vanlang.thuvien.service;

import com.vanlang.thuvien.model.CartItem;
import com.vanlang.thuvien.model.Category;
import com.vanlang.thuvien.model.Order;
import com.vanlang.thuvien.model.OrderDetail;
import com.vanlang.thuvien.repository.OrderDetailRepository;
import com.vanlang.thuvien.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private CartService cartService;

    @Transactional
    public Order createOrder(String customerName, String phoneNumber,
                             LocalDateTime borrowTime, LocalDateTime returnTime, List<CartItem> cartItems) {

        Order order = new Order();
        order.setCustomerName(customerName);
        order.setPhoneNumber(phoneNumber);
        order.setBorrowTime(borrowTime);
        order.setReturnTime(returnTime);
        order = orderRepository.save(order);

        for (CartItem item: cartItems) {
            OrderDetail detail = new OrderDetail();
            detail.setOrder(order);
            detail.setBook(item.getBook());

            orderDetailRepository.save(detail);
        }
        cartService.clearCard();
        return order;
    }
//
    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    //Xóa order qua id
    public void deleteOrder(Long id) {
        if(!orderDetailRepository.existsById(id)) {
            throw new IllegalStateException("Order with ID " + id + " does not exist.");
        }
        orderDetailRepository.deleteById(id);

    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }
}
