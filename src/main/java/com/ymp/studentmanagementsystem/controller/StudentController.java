package com.ymp.studentmanagementsystem.controller;

import com.ymp.studentmanagementsystem.model.Student;
import com.ymp.studentmanagementsystem.service.StudentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Yoon Myat Phoo.
 * @Date 8/12/2020.
 */

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    Logger logger = LogManager.getLogger(StudentController.class);

    /**
     * save student info into database
     */
    @PostMapping("/saveStudent")
    public ResponseEntity saveStudent(@RequestBody Student student) {
        logger.info("student: " + student);
        studentService.saveStudent(student);
        return new ResponseEntity("success", HttpStatus.OK);
    }


    /**
     * use findAll() method
     */
    @GetMapping("/getAllStudent")
    public ResponseEntity getAllStudent() {
        List<Student> studentList = studentService.getAllStudentList();
        Student s = new Student();
        s.setName("Yee Yee");
        s.setId(4L);
        s.setAddress("NayPyiTaw");
        s.setMajor("CT");
        s.setYear("Third Year");
        studentList.add(s);

        return new ResponseEntity(studentList, HttpStatus.OK);
    }

    /**
     * use query and ifDelete method
     */
    @GetMapping("/selectAll")
    public ResponseEntity selectAll() {
        List<Student> studentList = studentService.selectAll();
        return new ResponseEntity(studentList, HttpStatus.OK);
    }

    /**
     * use query and ifDelete method
     */
    @GetMapping("/findById")
    public ResponseEntity getStudentById(Long id) {
        logger.info(id);
        Student stu = studentService.findById(id);
        logger.info("stu: " + stu);
        return new ResponseEntity(stu, HttpStatus.OK);
    }

    @PostMapping("/insertStu")
    public ResponseEntity insertStudent(@RequestBody Student student) {
        try {
            logger.info(student);
            studentService.insertStu(student);
            return new ResponseEntity(studentService, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(student);
            e.printStackTrace();
        }

        return new ResponseEntity("fail", HttpStatus.BAD_REQUEST);

    }

    @GetMapping("/updateById")
    public ResponseEntity updateStudentById(@RequestBody Student student) {

        logger.info(student);
        studentService.updateById(student);
        return new ResponseEntity("Success", HttpStatus.OK);

    }

    @GetMapping("/delete")
    public ResponseEntity delete(Long id) {
        studentService.delete(id);
        return new ResponseEntity("delete", HttpStatus.OK);
    }

}
