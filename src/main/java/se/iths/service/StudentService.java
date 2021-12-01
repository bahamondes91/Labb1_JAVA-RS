package se.iths.service;


import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.entity.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class StudentService {

    @PersistenceContext
    EntityManager entityManager;

    public void createNewStudent(Student student) {
        entityManager.persist(student);

    }

    public Student updateStudentByName(Long id, String lastName) {
        Student findStudent = entityManager.find(Student.class, id);
        findStudent.setLastName(lastName);
        return entityManager.merge(findStudent);
    }


    public Student findStudentById(Long id) {
        return entityManager.find(Student.class, id);
    }

    public List<Student> getStudentByLastName(String lastName) {
        return entityManager.createQuery("SELECT i FROM Student i WHERE i.lastName LIKE :lastName", Student.class)
                .setParameter("lastName", lastName).getResultList();
    }

    public List<Student> getAllStudents() {
        return entityManager.createQuery("SELECT i from Student i", Student.class).getResultList();
    }

    public void deleteStudent(Long id) {
        Student foundStudent = entityManager.find(Student.class, id);
        entityManager.remove(foundStudent);
    }

}
