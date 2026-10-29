package kz.iitu.studentapi;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/mailebai-beksultan/students")
public class StudentController {
    private final StudentRepository repository;

    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Student> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Student not found: " + id));
    }

    @PostMapping
    public ResponseEntity<Student> create(
            @Valid @RequestBody StudentRequest request) {
        Student student = repository.save(new Student(
            request.name(), request.email(), request.studentGroup()));
        return ResponseEntity.created(URI.create(
            "/api/mailebai-beksultan/students/" + student.getId()))
            .body(student);
    }

    @PutMapping("/{id}")
    @Transactional
    public Student update(@PathVariable Long id,
            @Valid @RequestBody StudentRequest request) {
        Student student = getById(id);
        student.setName(request.name());
        student.setEmail(request.email());
        student.setStudentGroup(request.studentGroup());
        return repository.save(student);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Student student = getById(id);
        repository.delete(student);
        return ResponseEntity.noContent().build();
    }
}
