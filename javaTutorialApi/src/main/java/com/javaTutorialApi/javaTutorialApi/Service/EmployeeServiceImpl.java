package com.javaTutorialApi.javaTutorialApi.Service;

import com.javaTutorialApi.javaTutorialApi.Entity.Employee;
import com.javaTutorialApi.javaTutorialApi.Repository.EmployeeRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

//    Field injection
    @Autowired
    private final EmployeeRepo employeeRepo;

//    Constructor
    public EmployeeServiceImpl(EmployeeRepo employeeRepo){
        this.employeeRepo= employeeRepo;
    }


    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    @Override
    public List<Employee> showAllEmployee() {
        return employeeRepo.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepo.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Employee with id" + id + "not found"));
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        Employee oldEmployee = employeeRepo.findById(id).orElseThrow(()->
                new EntityNotFoundException("Invalid ID given"));
        return employeeRepo.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        if (!employeeRepo.existsById(id))
            throw new EntityNotFoundException("Employee with id" + id + "not found");
            employeeRepo.deleteById(id);

    }

    @Override
    public List<Employee> getEmployeeSalarayGreaterThan(Double salary) {
        return employeeRepo.findSalaryGreater(salary);
    }
}
