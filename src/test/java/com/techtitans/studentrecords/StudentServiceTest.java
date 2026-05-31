package com.techtitans.studentrecords;

import com.techtitans.studentrecords.exception.DuplicateMatricException;
import com.techtitans.studentrecords.exception.StudentNotFoundException;
import com.techtitans.studentrecords.model.Student;
import com.techtitans.studentrecords.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class StudentServiceTest {

    @Autowired
    private StudentService service;

    @Test
    void registerAndFetchById() {
        Student saved = service.register(
                new Student("Test One", "B11AA1111", "Data Engineering", "one@graduate.utm.my", 3.2));

        Student found = service.getById(saved.getId());
        assertEquals("Test One", found.getName());
    }

    @Test
    void duplicateMatricIsRejected() {
        service.register(new Student("Test Two", "B11AA2222", "Data Engineering", "two@graduate.utm.my", 3.0));
        assertThrows(DuplicateMatricException.class, () ->
                service.register(new Student("Other", "B11AA2222", "Networking", "other@graduate.utm.my", 2.9)));
    }

    @Test
    void updateChangesValues() {
        Student saved = service.register(
                new Student("Test Three", "B11AA3333", "Networking", "three@graduate.utm.my", 3.1));

        saved.setProgram("Cyber Security");
        Student updated = service.update(saved.getId(), saved);
        assertEquals("Cyber Security", updated.getProgram());
    }

    @Test
    void deleteRemovesStudent() {
        Student saved = service.register(
                new Student("Test Four", "B11AA4444", "Networking", "four@graduate.utm.my", 2.8));

        service.delete(saved.getId());
        assertThrows(StudentNotFoundException.class, () -> service.getById(saved.getId()));
    }

    @Test
    void searchByProgram() {
        service.register(new Student("Test Five", "B11AA5555", "Bioinformatics", "five@graduate.utm.my", 3.4));
        List<Student> result = service.search(null, "Bioinformatics");
        assertFalse(result.isEmpty());
        assertTrue(result.stream().anyMatch(s -> s.getMatricNo().equals("B11AA5555")));
    }
}
