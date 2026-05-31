package com.techtitans.studentrecords;

import com.techtitans.studentrecords.model.Student;
import com.techtitans.studentrecords.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final StudentRepository repository;

    public DataLoader(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        if (repository.count() > 0) {
            return;
        }

        repository.save(new Student("Mohammad Areeb", "A22EC4035", "Software Engineering", "areeb@graduate.utm.my", 3.75));
        repository.save(new Student("Samin Sarwat", "A22EC4040", "Software Engineering", "samin@graduate.utm.my", 3.60));
        repository.save(new Student("Someyo Kamal Utsho", "A22EC9007", "Computer Science", "someyo@graduate.utm.my", 3.85));
        repository.save(new Student("Mariam Hanif", "A22EC4034", "Software Engineering", "mariam@graduate.utm.my", 3.90));
    }
}
