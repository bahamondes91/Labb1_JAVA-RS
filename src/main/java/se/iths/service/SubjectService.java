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
        entityManager.persist(subject);
        return subject;
    }

    public List<Subject> getAllStudentsAndOneTeacher() {
        return entityManager.createQuery("SELECT i from Subject i ", Subject.class).getResultList();
    }


}
