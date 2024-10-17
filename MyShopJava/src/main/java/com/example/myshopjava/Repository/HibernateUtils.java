package com.example.myshopjava.Repository;

import com.example.myshopjava.Domain.Transaction;
import com.example.myshopjava.Domain.Product;
import com.example.myshopjava.Domain.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import java.util.Properties;


@Component
public class HibernateUtils  {

    private static SessionFactory sessionFactory;
//    private static Environment environment;

//    public void setEnvironment(Environment environment) {
//        HibernateUtils.environment = environment;
//    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null || sessionFactory.isClosed()) {
            sessionFactory = createNewSessionFactory();
        }
        return sessionFactory;
    }

    public static void closeSessionFactory() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
        }
    }

    private static SessionFactory createNewSessionFactory() {
        try {
            Properties properties = new Properties();
            properties.load(HibernateUtils.class.getResourceAsStream("/application.properties"));
            Configuration configuration = new Configuration()
                    .addAnnotatedClass(User.class)
                    .addAnnotatedClass(Product.class)
                    .addAnnotatedClass(Transaction.class)
                    .setProperties(properties);

            return configuration.buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create SessionFactory", e);
        }
    }
}
