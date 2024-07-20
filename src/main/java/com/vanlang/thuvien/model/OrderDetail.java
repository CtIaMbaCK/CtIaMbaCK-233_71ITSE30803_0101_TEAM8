package com.vanlang.thuvien.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table ( name = "order_details")
public class OrderDetail {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn ( name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn ( name = "order_id")
    private Order order;

}
