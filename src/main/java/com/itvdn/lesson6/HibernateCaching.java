package com.itvdn.lesson6;

import com.itvdn.lesson6.entity.Student;
import jakarta.persistence.LockModeType;
import org.hibernate.cfg.Configuration;

public class HibernateCaching {
    public static void main(String[] args) {
        var configuration = new Configuration();
        configuration.configure();

        try (var sessionFactory = configuration.buildSessionFactory();
             var session1 = sessionFactory.openSession();
             var session = sessionFactory.openSession()) {
            session.beginTransaction();
            session1.beginTransaction();

            session.find(Student.class, 1);
            session.find(Student.class, 1);

            session1.find(Student.class, 1);
            session1.find(Student.class, 1);

            session1.getTransaction().commit();
            session.getTransaction().commit();
        }
    }
}
