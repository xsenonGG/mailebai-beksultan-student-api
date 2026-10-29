package kz.iitu.studentapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentApiApplicationTests {
    @Autowired
    StudentController controller;

    @Autowired
    StudentRepository repository;

    @BeforeEach
    void cleanDatabase() {
        repository.deleteAll();
    }

    Student createStudent() {
        return controller.create(new StudentRequest(
            "Mailebai Beksultan",
            "mailebai06@mail.ru",
            "IT2-2313"
        )).getBody();
    }

    @Test
    void createsAndRetrievesStudent() {
        Student created = createStudent();
        assertNotNull(created);
        assertNotNull(created.getId());
        Student found = controller.getById(created.getId());
        assertEquals("Mailebai Beksultan", found.getName());
        assertEquals("mailebai06@mail.ru", found.getEmail());
        assertEquals(1, controller.getAll().size());
    }

    @Test
    void updatesPersistedStudent() {
        Student created = createStudent();
        controller.update(created.getId(), new StudentRequest(
            "Beksultan Updated", "updated@example.com", "IT2-2313"));
        Student found = repository.findById(created.getId()).orElseThrow();
        assertEquals("Beksultan Updated", found.getName());
        assertEquals("updated@example.com", found.getEmail());
    }

    @Test
    void deletesStudentAndReturns204() {
        Student created = createStudent();
        assertEquals(204,
            controller.delete(created.getId()).getStatusCode().value());
        assertFalse(repository.existsById(created.getId()));
    }

    @Test
    void missingStudentReturns404() {
        ResponseStatusException error = assertThrows(
            ResponseStatusException.class,
            () -> controller.getById(Long.MAX_VALUE));
        assertEquals(404, error.getStatusCode().value());
    }

    @Test
    void missingUpdateDoesNotCreateStudent() {
        ResponseStatusException error = assertThrows(
            ResponseStatusException.class,
            () -> controller.update(Long.MAX_VALUE, new StudentRequest(
                "Missing", "missing@example.com", "IT2-2313")));
        assertEquals(404, error.getStatusCode().value());
        assertEquals(0, repository.count());
    }

    @Test
    void missingDeleteReturns404() {
        ResponseStatusException error = assertThrows(
            ResponseStatusException.class,
            () -> controller.delete(Long.MAX_VALUE));
        assertEquals(404, error.getStatusCode().value());
    }
}
