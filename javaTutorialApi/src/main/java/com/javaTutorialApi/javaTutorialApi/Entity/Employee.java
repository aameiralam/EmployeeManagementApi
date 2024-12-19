package com.javaTutorialApi.javaTutorialApi.Entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Table(name = "employees")
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private int age;
    private String emailId;
    private double salary;

}
