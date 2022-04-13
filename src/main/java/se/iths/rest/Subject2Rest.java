package se.iths.rest;

import se.iths.entity.Subject;
import se.iths.entity.Teacher;
import se.iths.service.StudentService;
import se.iths.service.SubjectService;
import se.iths.util.Err;
import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/subject")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class   Subject2Rest {

    SubjectService subjectService;
    StudentService studentService;
    @Inject
    public Subject2Rest(SubjectService service, StudentService studentService) {
        this.subjectService = service;
        this.studentService = studentService;
    }

    @GET
    public Response getAllSubjects() {
        List<Subject> foundSubject = subjectService.getAllSubjects();
        if (foundSubject == null) {

            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("The list of Subject is empty").build());
        }
        return  Response.ok(foundSubject).build();

    }

    @GET
    @Path("/{id}")
    public Response getSubject(@PathParam("id") Long id)  {
        Subject subject = subjectService.getSubject(id);
        if (subject == null) {
            Err err = new Err ("No Subject with id " + id + " found");
            return  Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(err)
                    .build();
        }
        return Response .status(Response.Status.OK)
                .entity(subject)
                .build();


    }

    @POST
    public Response addSubject(Subject subject) {
        try {
            subjectService.addSubject(subject);
            return Response.status(Response.Status.CREATED).build();
        } catch (ConstraintViolationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getLocalizedMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateSubject(@PathParam("id") Long id,Subject subject) {
        try {
            subjectService.update(id, subject);
            return Response.status(Response.Status.OK).build();
        } catch (Exception error){
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity  ("No Subject with id "  + id +  " found " ).build());
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteSubject(@PathParam("id") Long id) {
        try {
            subjectService.delete(id);
            return Response.status(Response.Status.OK).build();
        } catch (Exception error){
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity  ("No Subject with id "  + id +  " found " ).build());
        }
    }
}





