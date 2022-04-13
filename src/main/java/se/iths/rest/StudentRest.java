package se.iths.rest;

import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.service.StudentService;
import se.iths.service.SubjectService;
import se.iths.util.Err;

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
    public Response getAllStudents() {
        List<Student> foundStudent = studentService.getAllStudents();
        if (foundStudent == null) {

            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("The list of Student is empty").build());
        }
        return Response.ok(foundStudent).build();

    }



    @GET @Path("/{id}")
    public Response getStudent(@PathParam("id") Long id)  {
        Student student = studentService.getStudentById(id);
        if (student == null) {
            Err err = new Err ("No student with id " + id + " found");
            return  Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(err)
                    .build();
        }
            return Response .status(Response.Status.OK)
                     .entity(student)
                     .build();


    }

    @POST
    public Response addStudent(Student student) {
        try {
            studentService.addStudent(student);

            return Response.ok(student).build();
        }

        catch ( ConstraintViolationException error){
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity  ( "Insert a name").build());
        }

        catch (Exception error) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("Email is used" ).build());


        }
    }
    @PUT
    @Path("/{id}")
    public Response updateStudent(@PathParam("id") Long id, Student student) {
        try {
            studentService.updateStudent(id, student);
            return Response.status(Response.Status.OK).build();
        } catch (Exception error){
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity  ("No student with id "  + id +  " found " ).build());
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteStudent(@PathParam("id") Long id) {
        try {
            studentService.deleteStudent(id);
            return Response.status(Response.Status.OK).build();
        } catch (Exception error){
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity  ("No student with id "  + id +  " found " ).build());
        }
    }



    @GET
    @Path("{id}/subjects")
    public List<Subject> getStudentSubjects(@PathParam("id") Long id) {
            if (id == null) {
                throw new WebApplicationException(Response.Status.BAD_REQUEST);
            }
            return subjectService.StudentSubjects(id);
        
    }

    @PATCH
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

