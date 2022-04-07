package se.iths.entity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;


    @ManyToMany(mappedBy = "subjects", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonbTransient
    private List<Student> students = new ArrayList<>();
    @ManyToMany(mappedBy = "subjects", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonbTransient
    private List<Teacher> teachers = new ArrayList<>();

    public void addStudent(Student student) {
        this.students.add(student);
    }



    public void removeStudent(Student student) {
        this.students.remove(student);

    }

    public void addTeacher(Teacher teacher) {
        this.teachers.add(teacher);

    }

    public void removeTeacher(Teacher teacher) {
        this.teachers.remove(teacher);
    }


    public Subject() {
    }


    public Subject(String name) {
        this.name = name;
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


    public List<Student> getStudents() {
        return students;
    }

    public void setStudent(Student student) {
        this.students.add(student);
    }

    public List<Teacher> getTeacher() {
        return teachers;
    }

    public void seTeacher(Teacher teacher) {
        this.teachers.add(teacher);
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", student=" + students +
                ", teacher=" + teachers +
                '}';
    }
}

