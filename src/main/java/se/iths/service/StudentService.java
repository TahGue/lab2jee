package se.iths.service;

import se.iths.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class StudentService {

    @PersistenceContext
    EntityManager entityManager;

    public void addStudent(Student student) {
        entityManager.persist(student);
    }

    public List<Student> getAllStudents() {
        return entityManager.createQuery("SELECT s FROM Student s", Student.class).getResultList();
    }

    public Student getStudentById(Long id) {
        return entityManager.find(Student.class, id);
    }

    public void updateStudent(Long id, Student student) {
        entityManager.merge(student);
    }




    public void deleteStudent(Long id) {
        entityManager.remove(entityManager.find(Student.class, id));
    }


}






