package com.javaTutorialApi.javaTutorialApi.Controller;

import com.javaTutorialApi.javaTutorialApi.Entity.Employee;
import com.javaTutorialApi.javaTutorialApi.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")

public class EmployeeController {

    @Autowired
    private final EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
        return ResponseEntity.ok(employeeService.createEmployee(employee));
    }

    @GetMapping("/")
    public ResponseEntity<List<Employee>> showAllEmployee() {
        return ResponseEntity.ok(employeeService.showAllEmployee());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee){
        return ResponseEntity.ok(employeeService.updateEmployee(id, employee));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok().body("Employee info has been deleted");
    }

    @GetMapping("/salary/{salary}")
    public ResponseEntity<List<Employee>> getEmployeeBySalary (@PathVariable Double salary){
        return ResponseEntity.ok(employeeService.getEmployeeSalarayGreaterThan(salary));
    }
}
