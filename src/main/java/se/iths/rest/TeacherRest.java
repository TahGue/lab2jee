package se.iths.rest;


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
    public List<Teacher> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @GET
    @Path("{id}")
    public Teacher getTeacher(@PathParam("id") Long id) {

        return teacherService.getTeacherById(  id);
    }

    @POST
    public Response addTeacher(Teacher teacher) {
        try {
            teacherService.addTeacher(teacher);
            return Response.status(Response.Status.CREATED).build();
        } catch (ConstraintViolationException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    @Path("{id}")
    public Response updateTeacher(@PathParam("id") Long id, Teacher teacher) {
        try {
            teacherService.updateTeacher(id, teacher);
            return Response.status(Response.Status.OK).build();
    } catch (
    ConstraintViolationException e) {
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
    }

    @DELETE
    @Path("{id}")
    public Response deleteTeacher(@PathParam("id") Long id) {
        teacherService.deleteTeacher(id);
        return Response.status(Response.Status.OK).build();
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


