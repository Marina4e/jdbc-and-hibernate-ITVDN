package com.itvdn.lesson2;

import com.itvdn.lesson2.dao.StudentDao;
import com.itvdn.lesson2.entity.Student;

public class JdbcAdvanced {

    public static void main(String[] args) {
        StudentDao studentDao = StudentDao.getInstance();
//        Student student = new Student();
//        student.setFirstName("Test main");
//        student.setLastName("Test main");
//        student.setEmail("main@example.com");
//
//        var studentFromDB = studentDao.save(student);
//        System.out.println(studentFromDB.toString());
        var studentById = studentDao.findById(1);
        System.out.println(studentById.toString());
    }
}
