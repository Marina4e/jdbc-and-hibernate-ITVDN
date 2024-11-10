package com.itvdn.lesson6;

import com.itvdn.lesson6.entity.Student;
import jakarta.persistence.LockModeType;
import org.hibernate.cfg.Configuration;

public class HibernateLocking {
    public static void main(String[] args) {
        var configuration = new Configuration();
        configuration.configure();

        try (var sessionFactory = configuration.buildSessionFactory();
             var session1 = sessionFactory.openSession();
             var session = sessionFactory.openSession()) {
            session.beginTransaction();
            session1.beginTransaction();

            var result = session.find(Student.class, 1);
//                    , LockModeType.PESSIMISTIC_FORCE_INCREMENT);
            result.setFirstName("test locking 1");

            var theSameStudent = session1.find(Student.class, 1);
//                    , LockModeType.PESSIMISTIC_WRITE);
            theSameStudent.setFirstName("test locking 2");

            session1.getTransaction().commit();
            session.getTransaction().commit();
        }
    }
}
