package com.techtitans.studentrecords.repository;

import com.techtitans.studentrecords.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    boolean existsByMatricNo(String matricNo);

    List<Student> findByNameContainingIgnoreCase(String name);

    List<Student> findByProgramContainingIgnoreCase(String program);
}
