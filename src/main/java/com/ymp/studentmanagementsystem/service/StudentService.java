package com.ymp.studentmanagementsystem.service;

import com.ymp.studentmanagementsystem.model.entity.Student;
import com.ymp.studentmanagementsystem.model.response.StudentResponse;
import com.ymp.studentmanagementsystem.model.status.Status;
import com.ymp.studentmanagementsystem.repository.StudentRepository;
import com.ymp.studentmanagementsystem.util.ConstantUtil;
import com.ymp.studentmanagementsystem.util.ValidationUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    public List<Student> selectAll() {
        List<Student> studentList = repo.selectAll();
        return studentList;
    }

    public Student findById(Long id) {

        Student stu = repo.findStudentById(id);
        return stu;
    }


    public void insertStu(Student student) {
        student.setDelete(false);
        student.setCreatedDate(new Date());
        student.setUpdatedDate(new Date());
        repo.save(student);
    }

    public StudentResponse updateById(Student student) {

        StudentResponse response = new StudentResponse();

        Long l = student.getId();
        //check input id is null or not.
        if (l == null) {
            logger.warn("user input is null");
            response.setMessage(ConstantUtil.USER_INPUT_ID_NULL);
            response.setStatus(Status.Fail.toString());
            return response;
        }

        Optional<Student> stuOption = repo.findById(l);

        logger.info("db response: " + stuOption);

        //check db response is empty or not
        if (!stuOption.isPresent()) {
            logger.warn("database reponse is empty");
            response.setMessage(ConstantUtil.ID_NOT_FOUND);
            response.setStatus(Status.Fail.toString());
            return response;
        }

        Student stu = stuOption.get();
        logger.info("DB Student response: " + stu);

        //check db response data is deleted or not
        if (stu.isDelete()) {
            response.setMessage(ConstantUtil.ALREADY_DELETED);
            response.setStatus(Status.Fail.toString());
            return response;
        }

//        boolean b = ValidationUtil.isValidString(student.getYear());
        if (ValidationUtil.isValidString(student.getYear()))
            stu.setYear(student.getYear());

        if (ValidationUtil.isValidString(student.getMajor()))
            stu.setMajor(student.getMajor());

        if (ValidationUtil.isValidString(student.getAddress()))
            stu.setAddress(student.getAddress());

        if (ValidationUtil.isValidString(student.getName()))
            stu.setName(student.getName());

        //update updatedDate
        stu.setUpdatedDate(new Date());

        //set remark
        stu.setRemark("Update Student");

        logger.info("Save Student into database: " + stu);
        repo.save(stu);

        response.setStatus(Status.Success.toString());

        return response;

    }

    /**
     * not to write delete query, just set isDeleted into true
     */
    public StudentResponse delete(Long id) {
        StudentResponse response = new StudentResponse();

        if (id == null) {
            logger.warn("user input is null");
            response.setMessage(ConstantUtil.USER_INPUT_ID_NULL);
            response.setStatus(Status.Fail.toString());
            return response;
        }

        Student s = findById(id);
        if (s == null) {
            response.setMessage(ConstantUtil.ID_NOT_FOUND);
            response.setStatus(Status.Fail.toString());
            return response;
        }

        s.setDelete(true);
        s.setUpdatedDate(new Date());
        s.setRemark("Delete");
        repo.save(s);
        response.setStatus(Status.Success.toString());

        return response;

    }
}
