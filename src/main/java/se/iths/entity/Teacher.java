package se.iths.entity;


import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;


@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;

  
@ManyToMany
@JsonbTransient
private List<Subject> subjects;


    public Teacher() {
    }

    public void addSubject(Subject subject) {
        this.subjects.add(subject);
    }
    public void removeSubject(Subject subject) {
        subject.removeTeacher(this);
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
    
    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubject(Subject subject) {
        this.subjects.add(subject);
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

