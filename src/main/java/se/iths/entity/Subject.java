package se.iths.entity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

   @ManyToMany(mappedBy = "subjects")

    private Set<Student> students = new HashSet<>();
   @ManyToMany(mappedBy = "subjects")

    private Set<Teacher> teachers = new HashSet<>();







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

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", students=" + students +
                ", teachers=" + teachers +
                '}';
    }
}



