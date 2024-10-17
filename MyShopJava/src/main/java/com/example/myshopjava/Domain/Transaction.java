package com.example.myshopjava.Domain;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serial;
import java.io.Serializable;

@Table(name = "Transactions")
@jakarta.persistence.Entity
public class Transaction implements Entity<Integer>, Serializable {
    private Integer id;
    private TransactionType transactionType;
    private String time;
    private Integer productId;
    private Integer quantity;

    @Serial
    private static final long serialVersionUID = 1000L;

    public Transaction() {
        id = quantity = productId = 0;
        time = "default";
        transactionType = TransactionType.DEFAULT;
    }

    public Transaction(Integer id, TransactionType transactionType, String time, Integer productId, Integer quantity) {
        this.id = id;
        this.transactionType = transactionType;
        this.time = time;
        this.productId = productId;
        this.quantity = quantity;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    @Column(name = "time")
    public String getTime() {
        return time;
    }

    @Column(name = "productId")
    public Integer getProductId() {
        return productId;
    }

    @Column(name = "quantity")
    public Integer getQuantity() {
        return quantity;
    }

    @Column(name = "type")
    @Convert(converter = TransTypeConverter.class)
    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String toString(){
        return "Transaction { " + id +
                ", tip: " + transactionType.toString() +
                ", time: " + time +
                ", product ID: " + productId.toString() +
                ", quantity: " + quantity.toString() + " }";
    }

}
