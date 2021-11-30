package se.iths.entity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String grade;


    @ManyToMany(mappedBy = "subjects", cascade = CascadeType.PERSIST)
    private List<Student> students = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Teacher teachers;


    public Subject(String name, String grade) {
        this.name = name;
        this.grade = grade;
    }

    public Subject() {
    }

    public void addStudent(Student student) {
        students.add(student);
        student.getSubjects().add(this);
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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setTeachers(Teacher teachers) {
        this.teachers = teachers;
    }


    public Teacher getTeachers() {
        return teachers;
    }


    public List<Student> getStudents() {
        return students;
    }
}
