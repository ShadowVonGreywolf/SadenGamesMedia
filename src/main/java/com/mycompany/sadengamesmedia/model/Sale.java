/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sadengamesmedia.model;

import java.time.LocalDateTime;

/**
 *
 * @author denia
 */
public class Sale {
    private int saleId;
    private int productId;
    private int accountId;
    private int quantity;
    private double price;
    private double totalAmount;
    private LocalDateTime timeStamp;

    public Sale(int saleId, int productId, int accountId, int quantity, double price, double totalAmount, LocalDateTime timeStamp) {
        this.saleId = saleId;
        this.productId = productId;
        this.accountId = accountId;
        this.quantity = quantity;
        this.price = price;
        this.totalAmount = totalAmount;
        this.timeStamp = timeStamp;
    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
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

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}
