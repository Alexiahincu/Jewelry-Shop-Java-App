package com.example.myshopjava.Repository;

import com.example.myshopjava.Domain.Transaction;
import com.example.myshopjava.Domain.TransactionType;

import java.util.List;

public interface TransactionRepository extends Repository<Integer, Transaction> {
    List<Transaction> getByType(TransactionType transactionType);

}
