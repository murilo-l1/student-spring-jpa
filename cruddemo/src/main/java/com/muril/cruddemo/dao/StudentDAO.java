package com.muril.cruddemo.dao;

import com.muril.cruddemo.entity.Student;

public interface StudentDAO {
    void save(Student theStudent);
    Student findById(Integer studentId);
}
