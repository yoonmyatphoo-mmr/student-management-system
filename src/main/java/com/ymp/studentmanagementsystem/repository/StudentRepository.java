package com.ymp.studentmanagementsystem.repository;

import com.ymp.studentmanagementsystem.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Yoon Myat Phoo.
 * @Date 8/12/2020.
 */


public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query(nativeQuery = true, value = "select * from Student where is_delete=false ")
    List<Student> selectAll();

    @Query(nativeQuery = true, value = "select* from Student s where s.id = :id and is_delete = false ")
    Student findStudentById(@Param("id") Long id);

}
