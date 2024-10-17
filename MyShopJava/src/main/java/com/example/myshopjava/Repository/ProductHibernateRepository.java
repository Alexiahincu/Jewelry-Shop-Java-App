package com.example.myshopjava.Repository;

import com.example.myshopjava.Domain.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class ProductHibernateRepository implements ProductRepository {

    @Override
    public void add(Product product) {
        HibernateUtils.getSessionFactory().inTransaction(session -> session.persist(product));
    }

    @Override
    public List<Product> getAll() {

        try( Session session=HibernateUtils.getSessionFactory().openSession()) {
            return session.createQuery("from Product ", Product.class).getResultList();
        }
    }


    public List<Product> findByName(String name) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.createSelectionQuery("from Product p where p.name = :n", Product.class)
                    .setParameter("n", name)
                    .getResultList();
        } catch (IllegalArgumentException e) {
            System.out.println("Numele produsului nu corespunde cu niciun element din enum.");
            return null;
        }
    }

    @Override
    public void update(Integer id, Product product) {
        HibernateUtils.getSessionFactory().inTransaction(session -> {
            if (!Objects.isNull(session.find(Product.class, id))) {
                System.out.println("In update, am gasit mesajul cu id-ul "+id);
                session.merge(product);
                session.flush();
            }
        });
    }

    @Override
    public void updateQuantity(String name, Integer newQuantity) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            int updatedEntities = session.createQuery("update Product p set p.quantity = :newQuantity where p.name = :name")
                    .setParameter("newQuantity", newQuantity)
                    .setParameter("name", name)
                    .executeUpdate();

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updatePrice(String name, Integer newPrice) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            int updatedEntities = session.createQuery("update Product p set p.price = :newPrice where p.name = :name")
                    .setParameter("newPrice", newPrice)
                    .setParameter("name", name)
                    .executeUpdate();

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public Product find(Integer id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.createSelectionQuery("from Product where id=:idM ", Product.class)
                    .setParameter("idM", id)
                    .getSingleResultOrNull();
        }
    }

}
