package se.iths.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

   @ManyToMany(  cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  //  @JoinTable(name = "student_subject",
  //          joinColumns = @JoinColumn(name = "student_id",referencedColumnName = "id"),
   //         inverseJoinColumns = @JoinColumn(name = "subject_id", referencedColumnName = "id"))
    @JoinTable(
            name = "student_subject",
            joinColumns = {@JoinColumn(name = "student_id" )},
            inverseJoinColumns = {@JoinColumn(name = "subject_id")},
            uniqueConstraints = {
                    @UniqueConstraint(
                            columnNames = { "student_id", "subject_id" }
                    )
            }
    )
   // private List<Subject> subjects;
    private Set<Subject> subjects = new HashSet<>();

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    public Student() {

    }




    public Student(String firstName, String lastName, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
    @JsonbTransient
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
    public void addSubject(Subject subject){
        boolean added = subjects.add(subject);
        if(added){
            subject.getStudents().add(this);
        }
    }
    public void removeSubject(Subject subject){
        boolean removed = subjects.remove(subject);
        if(removed){
            subject.getStudents().remove(this);
        }
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", phoneNumber=" + phoneNumber + ", subjects=" + subjects + "]";
    }




}
