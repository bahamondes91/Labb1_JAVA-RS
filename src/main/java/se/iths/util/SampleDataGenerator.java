package se.iths.util;

import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.entity.Teacher;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
@Startup
public class SampleDataGenerator {

    @PersistenceContext
    EntityManager entityManager;

    @PostConstruct
    public void generateData() {
        Student student = new Student("Kalle", "Anka");
        Student student1 = new Student("Bert", "Anka");

        Subject subject = new Subject("Mathematics", "number two");

        Teacher teacher = new Teacher("Carl", "Dustin");

        subject.addStudent(student);
        subject.addStudent(student1);
        subject.setTeachers(teacher);

        entityManager.persist(subject);
        entityManager.persist(teacher);
    }


}
