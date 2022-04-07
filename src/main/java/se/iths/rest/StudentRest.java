package se.iths.rest;

import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.service.StudentService;
import se.iths.service.SubjectService;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/student")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentRest {

    StudentService studentService;
    SubjectService subjectService;

    @Inject
    public StudentRest(StudentService studentService, SubjectService subjectService) {
        this.studentService = studentService;
        this.subjectService = subjectService;
    }

    @GET
    public List<Student> getAllStudents() {
        return studentService.getStudents();
    }

    @GET
    @Path("/{id}")
    public Student getStudent(@PathParam("id") Long id) {
        return studentService.getStudent(id);
    }

    @POST
    public Response addStudent(Student student) {
        try {
            studentService.addStudent(student);
            return Response.status(Response.Status.CREATED).build();
        } catch (ConstraintViolationException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteStudent(@PathParam("id") Long id) {
        try {
            studentService.deleteStudent(id);
            return Response.status(Response.Status.OK).build();
        } catch (ConstraintViolationException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateStudent(@PathParam("id") Long id, Student student) {
        try {
            studentService.updateStudent(id, student);
            return Response.status(Response.Status.OK).build();
        } catch (ConstraintViolationException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("{id}/subjects")
    public List<Subject> getStudentSubjects(@PathParam("id") Long id) {
        return subjectService.StudentSubjects(id);
    }

    @POST
    @Path("{id}/subjects")
    public Response addStudentSubject(@PathParam("id") Long id, Subject subject) {
        subjectService.addStudentSubject(id, subject);
        return Response.status(Response.Status.CREATED).entity(subject).build();
    }

    @DELETE
    @Path("{id}/subjects")
    public Response deleteStudentSubject(@PathParam("id") Long id, Subject subject) {
        subjectService.deleteStudentSubject(id, subject);
        return Response.status(Response.Status.OK).build();
    }









}

