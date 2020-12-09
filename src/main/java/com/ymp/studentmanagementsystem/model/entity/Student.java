package com.ymp.studentmanagementsystem.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "student")
public class Student {

    /**
     * id is auto generated.
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "address")
    String address;

    @Column(name = "major")
    String major;

    @Column(name = "year")
    String year;

    @JsonIgnore
    @Column(name = "is_delete")
    boolean delete;

    @Column(name = "created_date")
    Date createdDate;

    @Column(name = "updated_date")
    Date updatedDate;

    @JsonIgnore
    @Column(name = "remark")
    String remark;
}
