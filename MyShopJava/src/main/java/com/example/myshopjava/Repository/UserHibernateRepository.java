package com.example.myshopjava.Repository;

import com.example.myshopjava.Domain.User;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserHibernateRepository implements UserRepository {
    public void add(User user) {
        HibernateUtils.getSessionFactory().inTransaction(session -> session.persist(user));
    }


    @Override
    public List<User> getAll() {
       try( Session session=HibernateUtils.getSessionFactory().openSession()) {
           return session.createQuery("from User ", User.class).getResultList();
       }
    }

    @Override
    public User getByCredentials(String username, String password) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.createSelectionQuery("from User a where a.username like: usern and a.password like: passw", User.class)
                    .setParameter("usern", username)
                    .setParameter("passw", password)
                    .getSingleResultOrNull();
        }
    }
}
