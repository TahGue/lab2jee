package se.iths.rest;


import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.entity.Teacher;
import se.iths.service.SubjectService;
import se.iths.service.TeacherService;
import se.iths.util.Err;
import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("teacher")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TeacherRest {


    TeacherService teacherService;
    SubjectService subjectService;

    @Inject
    public TeacherRest(TeacherService teacherService, SubjectService subjectService) {
        this.teacherService = teacherService;
        this.subjectService = subjectService;
    }

    @GET
    public Response getAllTeachers() {
        List<Teacher> foundTeacher = teacherService.getAllTeachers();
        if (foundTeacher == null) {

            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("The list of Teachers is empty").build());
        }
        return Response.ok(foundTeacher).build();

    }

    @GET
    @Path("{id}")
    public Response getTeacher(@PathParam("id") Long id)  {
        Teacher teacher = teacherService.getTeacherById(id);
        if (teacher == null) {
            Err err = new Err ("No Teacher with id " + id + " found");
            return  Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(err)
                    .build();
        }
        return Response .status(Response.Status.OK)
                .entity(teacher)
                .build();


    }

    @POST
    public Response addTeacher(Teacher teacher) {
        try {
            teacherService.addTeacher(teacher);
            return Response.status(Response.Status.CREATED).build();
        } catch (ConstraintViolationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getLocalizedMessage()).build();
        }
    }

    @PUT
    @Path("{id}")
    public Response updateTeacher(@PathParam("id") Long id, Teacher teacher) {
        try {
            teacherService.updateTeacher(id, teacher);
            return Response.status(Response.Status.OK).build();
    } catch (Exception error){
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity  ("No Teacher with id "  + id +  " found " ).build());
    }
    }

    @DELETE
    @Path("{id}")
    public Response deleteTeacher(@PathParam("id") Long id) {
        try {
            teacherService.deleteTeacher(id);
            return Response.status(Response.Status.OK).build();
        } catch (Exception error) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("No Teacher with id" + id + " found ").build());
        }
    }

    @GET
    @Path("{id}/subjects")
    public List<Subject> getTeacherSubjects(@PathParam("id") Long id) {
       if (id == null) {
           throw new WebApplicationException(Response.Status.BAD_REQUEST);
       }
       return subjectService.getTeacherSubjects(id);
    }

    @PATCH
    @Path("{id}/subjects")
    public Response addTeacherSubject(@PathParam("id") Long id, Subject subject) {
        subjectService.addTeacherSubject(id, subject);
        return Response.status(Response.Status.CREATED).entity(subject).build();
    }

    @DELETE
    @Path("{id}/subjects")
    public Response deleteTeacherSubject(@PathParam("id") Long id, Subject subject) {
        subjectService.deleteTeacherSubject(id, subject);
        return Response.status(Response.Status.OK).build();
    }
}


