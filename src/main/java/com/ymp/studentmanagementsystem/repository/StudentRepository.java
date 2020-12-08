package com.ymp.studentmanagementsystem.repository;

import com.ymp.studentmanagementsystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * @author Yoon Myat Phoo.
 * @Date 8/12/2020.
 */


public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query(nativeQuery = true, value = "select * from Student where delete=false ")
    List<Student> selectAll();

    @Query(nativeQuery = true, value = "select* from Student s where s.id = :na and delete = false ")
    Optional<Student> findById(@Param("na") Long n);

    void delete(boolean b);
}
