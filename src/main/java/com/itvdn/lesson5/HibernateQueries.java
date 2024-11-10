package com.itvdn.lesson5;

import com.itvdn.lesson5.entity.Student;
import org.hibernate.cfg.Configuration;

public class HibernateQueries {

    public static void main(String[] args) {
        var configuration = new Configuration();
        configuration.configure();

        int pageNumber = 1;
        int pageSize = 1;

        try (var sessionFactory = configuration.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.beginTransaction();
            var cb = session.getCriteriaBuilder();
            var criteria = cb.createQuery(Student.class);

            var student = criteria.from(Student.class);
            criteria.select(student).orderBy(cb.desc(student.get("id")));

            var result = session.createQuery(criteria)
                    .setFirstResult((pageNumber - 1) * pageSize)
                    .setMaxResults(pageSize)
                    .list();
            result.forEach(System.out::println);

            session.getTransaction().commit();
        }

    }
}
