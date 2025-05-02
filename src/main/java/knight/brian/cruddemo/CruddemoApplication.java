package knight.brian.cruddemo;

import knight.brian.cruddemo.dao.StudentDAO;
import knight.brian.cruddemo.entity.Student;
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
//		return runner -> createStudent(studentDAO);
//		return runner -> createMultipleStudents(studentDAO);
//		return runner -> readStudent(studentDAO);
		return runner -> queryForStudents(studentDAO);
	}

	private void queryForStudents(StudentDAO studentDAO) {
		List<Student> students = studentDAO.findAll();

		students.forEach(System.out::println);
	}

	private void readStudent(StudentDAO studentDAO) {
		System.out.println("Creating new student object...");
		Student tempStudent = new Student("James", "Knight", "james@example.com");

		System.out.println("Saving the student...");
		studentDAO.save(tempStudent);

		int id = tempStudent.getId();
		System.out.println("Saved student. Generated id: " + tempStudent.getId());

		System.out.println("Retrieving student with id: " + id);
		Student myStudent = studentDAO.findById(id);
		System.out.println(myStudent);
	}

	private void createStudent(StudentDAO studentDAO){
		System.out.println("Creating new student object...");
		Student tempStudent = new Student("Brian", "Knight", "brian@example.com");

		System.out.println("Saving the student...");
		studentDAO.save(tempStudent);

		System.out.println("Saved student. Generated id: " + tempStudent.getId());
	}

	private void createMultipleStudents(StudentDAO studentDAO) {
		System.out.println("Creating 3 new student objects...");
		Student tempStudent1 = new Student("JohnA", "DoeA", "JohnA@example.com");
		Student tempStudent2 = new Student("JohnB", "DoeB", "JohnB@example.com");
		Student tempStudent3 = new Student("JohnC", "DoeC", "JohnC@example.com");

		System.out.println("Saving 3 students...");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);

		System.out.println("Saved student 1. Generated id: " + tempStudent1.getId());
		System.out.println("Saved student 2. Generated id: " + tempStudent2.getId());
		System.out.println("Saved student 3. Generated id: " + tempStudent3.getId());
	}
}
