package com.capgemini.entity;

import java.time.LocalDate;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "order_number", unique = true)
    private String orderNumber;

    @Column(name = "product_name")
    private String productName;

    private int quantity;

    private double price;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @OneToOne(mappedBy = "order")
    private Customer customer;

    public Order() {
    }

    public Order(String orderNumber, String productName, int quantity, double price, LocalDate orderDate) {
        this.orderNumber = orderNumber;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.orderDate = orderDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", orderNumber=" + orderNumber + ", productName=" + productName
                + ", quantity=" + quantity + ", price=" + price + ", orderDate=" + orderDate + "]";
    }
}
