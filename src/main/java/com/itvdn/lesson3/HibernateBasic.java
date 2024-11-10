package com.itvdn.lesson3;

import com.itvdn.lesson3.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateBasic {

    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure();

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Student student = Student.builder()
                    .id(10)
                    .firstName("Hibernate test 2")
                    .lastName("Hibernate test 2")
                    .email("hibernate@example.com")
                    .build();

            session.save(student);
            session.getTransaction().commit();
        }

    }
}
