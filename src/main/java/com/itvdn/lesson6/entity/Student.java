package com.itvdn.lesson6.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.List;

@NamedQuery(name = "studentByCourse", query =
        "select s from Enrollment e join e.student s where e.course.name = :courseName")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
//@OptimisticLocking(type = OptimisticLockType.ALL)
//@DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "students", schema = "public")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //    @Version
//    private Integer version;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    @ToString.Exclude
//    // N/3 + 1 = 90/3 + 1 = 31
//    @BatchSize(size = 3)
//    @Fetch(FetchMode.SUBSELECT)
    private List<Enrollment> enrollments;
}
