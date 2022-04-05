package se.iths.service;

import se.iths.entity.Student;
import se.iths.entity.Subject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class StudentService {

    @PersistenceContext
    EntityManager entityManager;

    public void createStudent(Student student) {
        entityManager.persist(student);
    }

    public void updateStudent(Student student) {
        entityManager.merge(student);
    }

    public Student findStudentById(Long id) {
        return entityManager.find(Student.class, id);
    }

    public List<Student> getAllStudents() {
        return entityManager.createQuery("SELECT i from Student i", Student.class).getResultList();
    }

    public void deleteStudent(Long id) {
        Student foundItem = entityManager.find(Student.class, id);
        entityManager.remove(foundItem);
    }

    public Student updatePhoneNumber(Long id, String phoneNumber) {
        Student foundItem = entityManager.find(Student.class, id);
        foundItem.setPhoneNumber(phoneNumber);
        return foundItem;
    }

    public Subject addSubject(Long subjectId, Long studentId) {
        {
            Student student = entityManager.find(Student.class, studentId);
            Subject subject = entityManager.find(Subject.class, subjectId);
            student.getSubjects().add(subject);
            return subject;
        }
    }
    public List<Student> getBylastName(String lastName) {
        String query = "SELECT i FROM Student i WHERE i.lastName = :lastName";
        return entityManager.createQuery(query, Student.class).setParameter("lastName", lastName).getResultList();
    }



}
