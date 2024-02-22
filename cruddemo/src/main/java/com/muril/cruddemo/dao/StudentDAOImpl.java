package com.muril.cruddemo.dao;

import com.muril.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public class StudentDAOImpl implements StudentDAO{

    private EntityManager entityManager;

    @Autowired
    public StudentDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }

    @Override
    public Student findById(Integer studentId) {
        Student retrivedStudent = entityManager.find(Student.class, studentId);
        if(retrivedStudent != null){
            return retrivedStudent;
        }else{
            System.out.println("Student of id " + studentId + " is not in our database");
            return null;
        }
    }

    @Override
    public List<Student> findStudentsByFirstName(String firstName) {
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student WHERE firstName=:theData", Student.class);
        theQuery.setParameter("theData", firstName);
        return theQuery.getResultList();
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> studentQuery = entityManager.createQuery("FROM Student", Student.class);
        return studentQuery.getResultList();
    }

    @Override
    @Transactional
    public void update(Student theStudent) {
        entityManager.merge(theStudent);
    }

    @Override
    @Transactional
    public void deleteStudentById(Integer studentId) {
        Student retrievedStudent = findById(studentId);
        entityManager.remove(retrievedStudent);
    }

    @Override
    @Transactional
    public Integer deleteAll() {
        return entityManager.createQuery("DELETE FROM Student")
                     .executeUpdate();
    }
}