package se.iths.entity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;

    @ManyToMany(  cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//   @JoinTable(name = "teacher_subject",
//           joinColumns = @JoinColumn(name = "teacher_id"),
 //          inverseJoinColumns = @JoinColumn(name = "subject_id"))
    @JoinTable(
            name = "teacher_subject",
            joinColumns = {@JoinColumn(name = "teacher_id" )},
            inverseJoinColumns = {@JoinColumn(name = "subject_id")},
            uniqueConstraints = {
                    @UniqueConstraint(
                            columnNames = { "teacher_id", "subject_id" }
                    )
            }
    )




  // private List<Subject> subjects;
    private Set<Subject> subjects = new HashSet<>();


    public Teacher() {
    }

    public Teacher(String firstName, String lastName, Set<Subject> subjects) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.subjects = subjects;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void addSubject(Subject subject){
        boolean added = subjects.add(subject);
        if(added){
            subject.getTeachers().add(this);
        }
    }
    public void removeSubject(Subject subject){
        boolean removed = subjects.remove(subject);
        if(removed){
            subject.getTeachers().remove(this);
        }
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", subjects=" + subjects +
                '}';
    }
}

