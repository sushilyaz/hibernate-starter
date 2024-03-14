package org.example;

import org.example.converter.BirthdayConverter;
import org.example.entity.Birthday;
import org.example.entity.User;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;

public class HibernateRunner {
    public static void main(String[] args) {
        /**
         * SessionFactory - пул соединений, реализованный в хибере
         */

        User user = User.builder()
                .username("ivan@gmail.com")
                .firstname("Ivan")
                .lastname("Ivanov")
                .build();
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
            try (Session session1 = sessionFactory.openSession()) {

                session1.beginTransaction();

                session1.persist(user);
                session1.evict(user);

                session1.getTransaction().commit();
            }
            try (Session session2 = sessionFactory.openSession()) {

                session2.beginTransaction();

                session2.remove(user);

                session2.getTransaction().commit();
            }
        }
    }
}
