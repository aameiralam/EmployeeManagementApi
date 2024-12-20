package com.javaTutorialApi.javaTutorialApi;

import com.javaTutorialApi.javaTutorialApi.Entity.Employee;
import com.javaTutorialApi.javaTutorialApi.Repository.EmployeeRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@Slf4j
public class JavaTutorialApiApplication implements CommandLineRunner {

	//Command Line Runner and Application Runner takes in application argument as an argument
//	both of them are interfaces
//	same method run()

	private EmployeeRepo employeeRepo;

	public JavaTutorialApiApplication(EmployeeRepo employeeRepo){
		this.employeeRepo = employeeRepo;
	}

	public static void main(String[] args) {
		SpringApplication.run(JavaTutorialApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		employeeRepo.saveAll(List.of(
				new Employee(0L,"david","d",18, "d@yahoo.com", 35000),
				new Employee(0L,"liam","l",19, "l@yahoo.com", 35000),
				new Employee(0L,"rob","r",22, "r@yahoo.com", 36000)
		));


		log.info(employeeRepo.findSalaryGreater(30000.0).toString());

	}
}
