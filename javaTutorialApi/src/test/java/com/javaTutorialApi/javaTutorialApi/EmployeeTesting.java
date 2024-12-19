package com.javaTutorialApi.javaTutorialApi;

import com.javaTutorialApi.javaTutorialApi.Entity.Employee;
import com.javaTutorialApi.javaTutorialApi.Repository.EmployeeRepo;
import com.javaTutorialApi.javaTutorialApi.Service.EmployeeServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EmployeeTesting {

    //Mockito

    @InjectMocks
    private EmployeeServiceImpl employeeService; //dummy injection

    @Mock
    private EmployeeRepo employeeRepo; //what should be our expected return if it was successful

    private AutoCloseable closeable;

    @BeforeEach
    void setup(){
        closeable= MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void takeDown() throws Exception {
        closeable.close();
    }

    @Test
    public void EmployeeServiceImpl_updateEmployee_Success(){
        Employee employee = Employee.builder()
                .id(1L)
                .firstName("Binary")
                .lastName("Logic")
                .age(10)
                .emailId("b@binary.com")
                .salary(100000)
                .build();
        Mockito.when(employeeRepo.findById(employee.getId())).thenReturn(Optional.of(employee));
        Mockito.when(employeeRepo.save(employee)).thenReturn(employee); // saves it after updating

        Employee employee1 = employeeService.updateEmployee(employee.getId(), employee);

        assertThat(employee1).isEqualTo(employee);
        assertEquals(employee,employee1);


    }

    @Test
    public void EmployeeServiceImp_update_MissingIdThrowsException() {
        Employee employee = Employee.builder()
                .firstName("Liam")
                .lastName("L")
                .age(24)
                .salary(41000)
                .emailId("l@yahoo.com")
                .build();

        Throwable throwable = assertThrows(EntityNotFoundException.class, ()->
                employeeService.updateEmployee(employee.getId(), employee));

        assertThat(throwable).isInstanceOf(EntityNotFoundException.class);
        assertThat(throwable.getMessage()).isEqualTo("Invalid ID given");

    }

//    invalid ID, -100
//    ValidationOfChange
//    Delete method
//    Delete for invalid ID


}
