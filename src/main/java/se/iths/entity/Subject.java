package se.iths.entity;

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

   
@ManyToMany (mappedBy = "subjects", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
private List<Student> students = new ArrayList<>();
@ManyToMany (mappedBy = "subjects", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
private List<Teacher> teachers = new ArrayList<>();

public void addStudenet(Student student){
    students.add(student);
  student.getSubjects().add(this);
}
public void addTeacher(Teacher teacher){
    teachers.add(teacher);
    teacher.getSubjects().add(this);
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

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

   

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                
                '}';
    }
}



