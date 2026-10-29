package kz.iitu.studentapi;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String studentGroup;

    protected Student() {}

    public Student(String name, String email, String studentGroup) {
        this.name = name;
        this.email = email;
        this.studentGroup = studentGroup;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getStudentGroup() { return studentGroup; }

    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setStudentGroup(String studentGroup) {
        this.studentGroup = studentGroup;
    }
}
