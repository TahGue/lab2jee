package se.iths.rest;

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
    public List<Subject> getAll() {
        return subjectService.getAllSubjects();
    }

    @GET
    @Path("/{id}")
    public Subject getById (@PathParam("id") Long id) {
        return subjectService.getSubject(id);
    }

    @POST
    public Response add(Subject subject) {
        try {
            subjectService.addSubject(subject);
            return Response.status(Response.Status.CREATED).entity(subject).build();
        } catch (ConstraintViolationException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    public Response update(Subject subject) {
        try {
            subjectService.update(subject);
            return Response.status(Response.Status.OK).entity(subject).build();
        } catch (ConstraintViolationException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            subjectService.delete(id);
            return Response.status(Response.Status.OK).build();
        } catch (ConstraintViolationException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}





