package com.muril.cruddemo.dao;

import com.muril.cruddemo.entity.Student;

import java.util.List;

public interface StudentDAO {
    void save(Student theStudent);
    Student findById(Integer studentId);
    List<Student> findStudentsByFirstName(String firstName);
    List<Student> findAll();
    void update(Student theStudent);
    void deleteStudentById(Integer studentId);
    Integer deleteAll();
}
