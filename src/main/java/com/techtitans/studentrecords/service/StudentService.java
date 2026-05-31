package com.techtitans.studentrecords.service;

import com.techtitans.studentrecords.exception.DuplicateMatricException;
import com.techtitans.studentrecords.exception.StudentNotFoundException;
import com.techtitans.studentrecords.model.Student;
import com.techtitans.studentrecords.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    // Functionality 1: register a new student
    public Student register(Student student) {
        if (repository.existsByMatricNo(student.getMatricNo())) {
            throw new DuplicateMatricException(student.getMatricNo());
        }
        return repository.save(student);
    }

    // Functionality 2: list all students / get one by id
    public List<Student> getAll() {
        return repository.findAll();
    }

    public Student getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
    }

    // Functionality 3: update an existing student
    public Student update(Long id, Student updated) {
        Student student = getById(id);

        if (!student.getMatricNo().equals(updated.getMatricNo())
                && repository.existsByMatricNo(updated.getMatricNo())) {
            throw new DuplicateMatricException(updated.getMatricNo());
        }

        student.setName(updated.getName());
        student.setMatricNo(updated.getMatricNo());
        student.setProgram(updated.getProgram());
        student.setEmail(updated.getEmail());
        student.setCgpa(updated.getCgpa());
        return repository.save(student);
    }

    // Functionality 4: delete a student
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new StudentNotFoundException(id);
        }
        repository.deleteById(id);
    }

    // Functionality 5: search students by name or program
    public List<Student> search(String name, String program) {
        if (name != null && !name.isBlank()) {
            return repository.findByNameContainingIgnoreCase(name);
        }
        if (program != null && !program.isBlank()) {
            return repository.findByProgramContainingIgnoreCase(program);
        }
        return repository.findAll();
    }
}
