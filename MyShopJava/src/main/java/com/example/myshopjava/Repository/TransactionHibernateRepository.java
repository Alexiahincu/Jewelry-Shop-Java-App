package com.example.myshopjava.Repository;

import com.example.myshopjava.Domain.Product;
import com.example.myshopjava.Domain.Transaction;
import com.example.myshopjava.Domain.TransactionType;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransactionHibernateRepository implements TransactionRepository {

    @Override
    public void add(Transaction elem) {
        HibernateUtils.getSessionFactory().inTransaction(session -> session.persist(elem));
    }

    @Override
    public List<Transaction> getAll() {

        try( Session session=HibernateUtils.getSessionFactory().openSession()) {
            return session.createQuery("from Transaction ", Transaction.class).getResultList();
        }
    }

    @Override
    public List<Transaction> getByType(TransactionType transactionType) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.createSelectionQuery("from Transaction t where t.transactionType = :transType", Transaction.class)
                    .setParameter("transType", transactionType)
                    .getResultList();
        } catch (IllegalArgumentException e) {
            System.out.println("Numele produsului nu corespunde cu niciun element din enum.");
            return null;
        }
    }
}
