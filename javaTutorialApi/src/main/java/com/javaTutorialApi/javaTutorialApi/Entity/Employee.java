package com.javaTutorialApi.javaTutorialApi.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employees")
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    @Column(nullable = false, length = 10)
    String firstName;
    @Column(nullable = false, length = 10)
    String lastName;
    @Column(nullable = false, columnDefinition = "DOUBLE CHECK (age>=5)")
    int age;
    @Column(nullable = false)
    String emailId;
    @Column(nullable = false)
    double salary;

}
