package com.ymp.studentmanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "student")
public class Student {

    /**
     * id is manual
     */
    @Id
    @Column(name = "id")
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
    @Column(name = "delete")
    Boolean delete;


}
