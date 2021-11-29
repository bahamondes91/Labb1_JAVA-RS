package se.iths.service;

import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.entity.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class SubjectService {

    @PersistenceContext
    EntityManager entityManager;

    public Subject findSubjectById(Long id) {
        return entityManager.find(Subject.class, id);
    }

    public Subject createSubject(Subject subject) {

        //Adding Students for Testing
       subject.addStudent(new Student("Anna", "Sjöblom"));
        subject.addTeacher(new Teacher("Karl","brutal"));
        //  subject.addStudent(new Student("Bert","Olof"));

        entityManager.persist(subject);
        return subject;
    }

    public List<Teacher> getAllStudentsAndOneTeacher() {
        return entityManager.createQuery("SELECT i from Teacher i inner join Student a on i.id = a.id", Teacher.class).getResultList();
    }


}
