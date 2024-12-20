package com.javaTutorialApi.javaTutorialApi;

import com.javaTutorialApi.javaTutorialApi.Entity.Employee;
import com.javaTutorialApi.javaTutorialApi.Repository.EmployeeRepo;
import com.javaTutorialApi.javaTutorialApi.Service.EmployeeServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

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
    public void EmployeeServiceImp_updateEmployee_MissingIdThrowsException(){

        Employee employee = Employee.builder()
                .firstName("Jon")
                .lastName("Cena")
                .age(50)
                .emailId("j@cena.com")
                .salary(100000000)
                .build();

        Throwable throwable = assertThrows(EntityNotFoundException.class, () ->
                employeeService.updateEmployee(employee.getId(), employee));

        assertThat(throwable).isInstanceOf(EntityNotFoundException.class);
        assertThat(throwable.getMessage()).isEqualTo("Invalid ID given");

    }

    //    invalid ID, -100

    @ParameterizedTest
    @ValueSource(longs= {-40l, 0l, -100l, 500l})
    public void EmployeeServiceImp_updateEmployee_InvalidIds (long id){

        Employee employee = Employee.builder()
                .id(id)
                .firstName("max")
                .lastName("m")
                .age(25)
                .emailId("max@gmail.com")
                .salary(34000)
                .build();
        Throwable throwable = assertThrows(EntityNotFoundException.class, () -> employeeService.updateEmployee(employee.getId(), employee));
        assertThat(throwable).isInstanceOf(EntityNotFoundException.class);
        assertThat(throwable.getMessage()).isEqualTo("Invalid ID given");



    }

    //    ValidationOfChange
    @Test
    public void EmployeeServiceImpl_updateEmployee_validationOfChange() {
        Employee oldEmployee = Employee.builder()
                .id(1L)
                .firstName("paine")
                .lastName("p")
                .age(15)
                .emailId("p@yahoo.com")
                .salary(55000)
                .build();

        Employee newEmployee = Employee.builder()
                .id(1L)
                .firstName("tim")
                .lastName("p")
                .age(16)
                .emailId("t@yahoo.com")
                .salary(52000)
                .build();

        Mockito.when(employeeRepo.findById(newEmployee.getId())).thenReturn(Optional.of(oldEmployee));
        Mockito.when(employeeRepo.save(newEmployee)).thenReturn(newEmployee);

        Employee result = employeeService.updateEmployee(newEmployee.getId(), newEmployee);
        assertNotEquals(oldEmployee, result);
        assertEquals(newEmployee, result);

    }




//    Delete method

    @Test
    public void employeeServiceImp_deleteEmployee_Success() {
        Long id = 1L;
        Mockito.when(employeeRepo.existsById(id)).thenReturn(true);
        employeeService.deleteEmployee(id);

        verify(employeeRepo).deleteById(id);

    }


//    Delete for invalid ID
    @ParameterizedTest
    @ValueSource(longs = {-15l, 0l, -100l, 500l})
    public void employeeServiceImpl_deleteEmployee_invalidIdThrowsException(long id){
        Mockito.when(employeeRepo.existsById(id)).thenReturn(false);

        Throwable throwable = assertThrows(EntityNotFoundException.class, ()->
                employeeService.deleteEmployee(id));

        assertThat(throwable).isInstanceOf(EntityNotFoundException.class);
        assertThat(throwable.getMessage()).isEqualTo("Employee with id" + id + "not found");
    }


}
