package com.muril.cruddemo;

import com.muril.cruddemo.dao.StudentDAO;
import com.muril.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
			createStudent(studentDAO);
			readStudent(studentDAO);
			//queryForStudents(studentDAO);
			//queryForStudentsByFirstName(studentDAO);
            //updateStudentName(studentDAO);
			//deleteStudent(studentDAO);
			//deleteAll(studentDAO);
		};
	}


	public void createStudent(StudentDAO studentDAO) {
		//criar DAO do estudante
		Student myStudent = new Student("Murilo", "Lusvarghi", "murilolusvarghi@outlook.com");
		System.out.println("Student created: " + myStudent);

		//salvar estudante na bd
		studentDAO.save(myStudent);

	}

	public void readStudent(StudentDAO studentDAO){
		//criar novo estudante
		Student tempStudent = new Student("Arthur", "Rosisca", "tutudenguevirus@gmail.com");

		//salvando novo estudante na base de dados
		System.out.println("Saving student...");
		studentDAO.save(tempStudent);

		//mostrando a id do novo estudante
		System.out.println("Saved student, ID: " + tempStudent.getId());

		//procurando o novo estudante
		System.out.println("Finding student by id...");
		Student retrievedStudent = studentDAO.findById(tempStudent.getId());
		System.out.println("Student found! " + retrievedStudent);
	}

	public void queryForStudents(StudentDAO studentDAO){
		List<Student> students = studentDAO.findAll();

		for (Student tempStudent : students) {
			System.out.println(tempStudent);
		}
	}

	public void queryForStudentsByFirstName(StudentDAO studentDAO){
		List<Student> lastNameList = studentDAO.findStudentsByFirstName("Murilo");
		for(Student tempStudent : lastNameList){
			System.out.println(tempStudent);
		}
	}

    public void updateStudentName(StudentDAO studentDAO){
        // procurar estudante pelo ID
        Student retrievedStudent = studentDAO.findById(1);
        System.out.println("Finding student...");

        //colocando o atributo atualizado
        retrievedStudent.setFirstName("Paulão");

        studentDAO.update(retrievedStudent);
        System.out.println("Updated student: " + retrievedStudent);
    }

	public void deleteStudent(StudentDAO studentDAO){
		int studentId = 2;
		System.out.println("Deleting student with id " + studentId);
		studentDAO.deleteStudentById(studentId);
	}

	public void deleteAll(StudentDAO studentDAO){
		Integer numsofRowsDeleted = studentDAO.deleteAll();
		System.out.println("Deleted dataBase with " + numsofRowsDeleted + " rows.");
	}

}