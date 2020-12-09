package com.ymp.studentmanagementsystem.controller;

import com.ymp.studentmanagementsystem.model.entity.Student;
import com.ymp.studentmanagementsystem.model.response.StudentResponse;
import com.ymp.studentmanagementsystem.model.status.Status;
import com.ymp.studentmanagementsystem.service.StudentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
     * findAll student filter by deleted
     */
    @GetMapping("/findAllStudent")
    public ResponseEntity selectAll() {
        StudentResponse response = new StudentResponse();
        try {
            List<Student> studentList = studentService.selectAll();
            response.setStatus(Status.Success.toString());
            response.setData(studentList);

            logger.info("Response: {}", response);

            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(Status.Fail.toString());
            response.setMessage(e.getMessage());
            logger.warn("Response: {}", response);
        }
        return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * find student by id
     */
    @GetMapping("/findStudentById")
    public ResponseEntity getStudentById(Long id) {
        logger.info("Request ID: " + id);
        StudentResponse response = new StudentResponse();
        try {
            Student stu = studentService.findById(id);
            response.setStatus(Status.Success.toString());
            response.setData(stu);

            logger.info("Response: {}", response);

            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(Status.Fail.toString());
            response.setMessage(e.getMessage());
            logger.warn("Response: {}", response);
        }
        return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * f
     * store student info into database.
     */
    @PostMapping("/insertStudent")
    public ResponseEntity insertStudent(@RequestBody Student student) {
        StudentResponse response = new StudentResponse();
        try {
            logger.info("Request: " + student);
            studentService.insertStu(student);

            response.setStatus(Status.Success.toString());
            logger.info("Response: {}", response);
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(student);
            e.printStackTrace();
            response.setStatus(Status.Fail.toString());
            response.setMessage(e.getMessage());
        }
        logger.info("Response: {}", response);
        return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * modify student by id
     */
    @PostMapping("/updateStudentById")
    public ResponseEntity updateStudentById(@RequestBody Student student) {

        logger.info("Request: " + student);

        StudentResponse response = new StudentResponse();
        try {
            response = studentService.updateById(student);
            logger.info("Response: {}", response);
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(Status.Fail.toString());
            response.setMessage(e.getMessage());
            logger.info("Response: {}", response);
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    /**
     * delete student by id
     */
    @GetMapping("/deleteStudentById")
    public ResponseEntity delete(@RequestParam long id) {


        StudentResponse response = new StudentResponse();
        try {
            logger.info("request id: {}", id);
            response = studentService.delete(id);
            logger.info("Response: {}", response);
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(Status.Fail.toString());
            response.setMessage(e.getMessage());
            logger.error("Response: {}", response);
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

}
