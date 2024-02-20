package com.muril.cruddemo;

import com.muril.cruddemo.dao.StudentDAO;
import com.muril.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
			//createStudent(studentDAO);
			readStudent(studentDAO);
		};
	}


	public void createStudent(StudentDAO studentDAO) {
		//criar DAO do estudante
		Student myStudent = new Student("Murilo", "Lusvarghi", "murilolusvarghi@outlook.com");
		System.out.println("Student created: " + myStudent.toString());

		//salvar estudante na bd
		studentDAO.save(myStudent);

		//mostrar id do estudante que foi salvo
		System.out.println("Student ID: " + myStudent.getId());
	}

	public void readStudent(StudentDAO studentDAO){
		//criar novo estudante
		Student tempStudent = new Student("Jhulia", "Felix", "fjhuliavitoria@gmail.com");

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

}