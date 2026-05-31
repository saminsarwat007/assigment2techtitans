package com.techtitans.studentrecords.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Matric number is required")
    @Column(unique = true)
    private String matricNo;

    @NotBlank(message = "Program is required")
    private String program;

    @Email(message = "Email must be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @DecimalMin(value = "0.0", message = "CGPA cannot be below 0.0")
    @DecimalMax(value = "4.0", message = "CGPA cannot be above 4.0")
    private double cgpa;

    public Student() {
    }

    public Student(String name, String matricNo, String program, String email, double cgpa) {
        this.name = name;
        this.matricNo = matricNo;
        this.program = program;
        this.email = email;
        this.cgpa = cgpa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMatricNo() {
        return matricNo;
    }

    public void setMatricNo(String matricNo) {
        this.matricNo = matricNo;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getCgpa() {
        return cgpa;
    }

    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }
}
