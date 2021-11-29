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


    @ManyToMany(mappedBy = "subject")
    private List<Student> students = new ArrayList<>();
    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private  List<Teacher> teachers = new ArrayList<>();



    public void addStudent(Student student) {
        students.add(student);
        student.getSubject().add(this);
    }
    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
        teacher.setSubject(this);
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
    @JsonbTransient
    public List<Teacher> getTeachers() {
        return teachers;
    }

    @JsonbTransient
    public List<Student> getStudents() {
        return students;
    }
}
