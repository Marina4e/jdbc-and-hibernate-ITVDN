package com.itvdn.lesson6;

import com.itvdn.lesson6.entity.Student;
import org.hibernate.cfg.Configuration;

public class HibernateAdvanced {
    public static void main(String[] args) {
        var configuration = new Configuration();
        configuration.configure();

        try (var sessionFactory = configuration.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.beginTransaction();

            var result = session.createQuery
                    ("select s from Student s join fetch s.enrollments",
                            Student.class).list();
            result.stream().map(Student::getEnrollments).forEach(System.out::println);

            session.getTransaction().commit();
        }
    }
}
