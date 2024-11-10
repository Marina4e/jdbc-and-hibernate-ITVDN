package com.itvdn.lesson4;

import com.itvdn.lesson4.entity.Course;
import com.itvdn.lesson4.entity.Enrollment;
import com.itvdn.lesson4.entity.Student;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;

public class HibernateMapping {

    public static void main(String[] args) {
        var configuration = new Configuration();
        configuration.configure();

        try (var sessionFactory = configuration.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.beginTransaction();

            Student student = Student.builder()
//                    .id(1)
                    .firstName("Test for ")
                    .lastName("Test for ")
                    .email("id@example.com")
                    .build();

            Course course = Course.builder()
//                    .id(1)
                    .name("JDBC Basic 2")
                    .description("JDBC 2")
                    .duration(20)
                    .build();


            Enrollment enrollment = Enrollment.builder()
//                    .id(1)
                    .student(student)
                    .course(course)
                    .registrationDate(LocalDate.of(2023, 11, 12))
                    .build();

            session.save(student);
            session.save(course);
            session.save(enrollment);

//            var studentDB = session.load(Student.class, 1);
//            var enrollmentDB = session.load(Enrollment.class, 1);
//            System.out.println(studentDB);
//            System.out.println(enrollmentDB);
//
//            studentDB.getEnrollments().stream().map(e -> e.getCourse()
//                    .getName()).forEach(System.out::println);

            session.getTransaction().commit();
        }
    }
}
