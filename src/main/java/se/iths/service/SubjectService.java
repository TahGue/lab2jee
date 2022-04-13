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

    public Subject addSubject(Subject subject) {
        entityManager.persist(subject);
        return subject;
    }

    public Subject getSubject(Long id) {

        return entityManager.find(Subject.class, id);
    }

    public List<Subject> getAllSubjects() {
        return entityManager.createQuery("SELECT s FROM Subject s", Subject.class).getResultList();
    }

    public void deleteSubject(Long id) {
        Subject subject = entityManager.find(Subject.class, id);
        entityManager.remove(subject);
    }

    public Subject updateSubject(Subject subject) {
        return entityManager.merge(subject);
    }

    public List<Subject> getSubjectsByTeacher(Teacher teacher) {
        return entityManager.createQuery("SELECT s FROM Subject s WHERE s.teachers = :teacher", Subject.class)
                .setParameter("teacher", teacher)
                .getResultList();
    }

    public List<Subject> getSubjectsByStudent(Student student) {
        return entityManager.createQuery("SELECT s FROM Subject s WHERE s.students = :student", Subject.class)
                .setParameter("student", student)
                .getResultList();
    }


    public List<Subject> getTeacherSubjects(Long id) {
        Teacher teacher1 = entityManager.find(Teacher.class, id);
        return teacher1.getSubjects();
    }

    public List<Subject> getStudentSubjects(Long id) {
        Student student1 = entityManager.find(Student.class, id);
        return student1.getSubjects();
    }

    public void update(Subject subject) {
        entityManager.merge(subject);
    }

    public void delete(Long id) {
        entityManager.remove(getSubject(id));
    }


    public void deleteTeacherSubject(Long id, Subject subject) {
        Teacher teacher1 = entityManager.find(Teacher.class, id);
        teacher1.addSubject(entityManager.find(Subject.class, subject));
        entityManager.merge(teacher1);
    }

    public List<Subject> getSubjectsByTeacherId(Long id) {
        Teacher teacher1 = entityManager.find(Teacher.class, id);
        return teacher1.getSubjects();
    }

    public List<Subject> getSubjectsByStudentId(Long id) {
        Student student1 = entityManager.find(Student.class, id);
        return student1.getSubjects();
    }

    public void addTeacherSubject(Long teacherId, Long subjectId) {
        Teacher teachter = entityManager.find(Teacher.class, teacherId);

        Subject subject = entityManager.find(Subject.class, subjectId);
        subject.addTeacher(teachter);
        entityManager.merge(teachter);

    }

    public void addStudentSubject(Long studentId, Long subjectId) {
        Student student = entityManager.find(Student.class, studentId);
        Subject subject = entityManager.find(Subject.class, subjectId);
        subject.addStudent(student);
        entityManager.merge(student);
    }


    public void deleteStudentSubject (Long studentId, Long subjectId) {
        Student student = entityManager.find(Student.class, studentId);

        Subject subject = entityManager.find(Subject.class, subjectId);
        student.removeSubject(subject);

    }

    public void deleteTeacherSubject (Long teacherId, Long subjectId) {
        Teacher teacher = entityManager.find(Teacher.class, teacherId);

        Subject subject = entityManager.find(Subject.class, subjectId);
        teacher.removeSubject(subject);

    }

    public List<Subject> StudentSubjects(Long id) {
        Student student = entityManager.find(Student.class, id);
        return student.getSubjects();
    }

    public List<Subject> TeacherSubjects(Long id) {
        Teacher teacher = entityManager.find(Teacher.class, id);
        return teacher.getSubjects();
    }
}

















