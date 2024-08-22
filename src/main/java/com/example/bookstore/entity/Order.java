package com.example.bookstore.entity;

import jakarta.persistence.*;

import java.util.Date;



@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "order_date")
    private Date orderDate;

    public enum Status {
        PENDING, COMPLETED, CANCELED
    }

    @Column(name = "status")
    private Enum<Status> status;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "total_price")
    private double totalPrice;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

}
