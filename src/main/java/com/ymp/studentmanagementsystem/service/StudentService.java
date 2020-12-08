package com.ymp.studentmanagementsystem.service;

import com.ymp.studentmanagementsystem.controller.StudentController;
import com.ymp.studentmanagementsystem.model.Student;
import com.ymp.studentmanagementsystem.repository.StudentRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Yoon Myat Phoo.
 * @Date 8/12/2020.
 */


@Service
public class StudentService {

    @Autowired
    StudentRepository repo;

    Logger logger = LogManager.getLogger(StudentService.class);

    public void saveStudent(Student student) {
        repo.save(student);
    }

    public List<Student> getAllStudentList() {
        //use findAll() method
        List<Student> studentList = repo.findAll();
        return studentList;
    }

    public List<Student> selectAll() {
        List<Student> studentList = repo.selectAll();
        return studentList;
    }

    //use query
    public Student findById(Long id) {
        Optional<Student> stu = repo.findById(id);
        Student stu1 = stu.get();
        return stu1;
    }


    /**
     * insert into database
     */
    public void insertStu(Student student) {
        repo.save(student);
    }

    public void updateById(Student student) {

        Long l = student.getId();
        Optional<Student> optionalStu = repo.findById(l);
        Student stu = optionalStu.get();

        stu.setYear(student.getYear());
        stu.setMajor(student.getMajor());
        stu.setAddress(student.getAddress());
        stu.setName(student.getName());

        logger.info("Student update: " + stu);
        repo.save(stu);

    }

    /**
     * not to write delete query, just set isDeleted into true
     */
    public void delete(Long id) {
        repo.delete(true);
    }
}
