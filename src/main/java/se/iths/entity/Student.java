package se.iths.entity;


import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Size (min = 2)
    private String firstName;
    @NotNull
    @Size (min = 2)
    private String lastName;
    @Column (unique = true)
    private String email;
    private String phoneNumber;
    
    @ManyToMany
    @JsonbTransient
    private List<Subject> subjects;



    public Student() {

    }


    public void addSubject(Subject subject) {
        this.subjects.add(subject);
    }
    public void removeSubject(Subject subject) {
        subject.removeStudent(this);
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubject(Subject subject) {
        this.subjects.add(subject);
    }



   @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", subject=" + subjects +
                '}';
    }



}
