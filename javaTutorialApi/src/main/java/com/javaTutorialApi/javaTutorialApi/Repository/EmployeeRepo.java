package com.javaTutorialApi.javaTutorialApi.Repository;

import com.javaTutorialApi.javaTutorialApi.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

    List<Employee> findSalaryGreater (Double salary);


}
