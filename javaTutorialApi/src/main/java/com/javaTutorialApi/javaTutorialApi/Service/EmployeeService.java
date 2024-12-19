package com.javaTutorialApi.javaTutorialApi.Service;


import com.javaTutorialApi.javaTutorialApi.Entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;


public interface EmployeeService {

//    Create
    Employee createEmployee(Employee employee);
//    Read
    List<Employee> showAllEmployee();
//    Get an employee by id
    Employee getEmployeeById(Long id);
//    Update
    Employee updateEmployee(Long id, Employee employee);
//    Delete
    void deleteEmployee(Long id);


//   to get employees whose salary is more than 40000
    List<Employee> getEmployeeSalarayGreaterThan(Double salary);

}
