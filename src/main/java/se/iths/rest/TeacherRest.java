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
        List<Teacher> foundTeacher = teacherService.getAllTeachers();
        if (foundTeacher == null) {

            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("The list of Teachers is empty").build());
        }
        return (List<Teacher>) Response.ok(foundTeacher).build();

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
            Err err = new Err("No teacher added ");
            return Response.status(Response.Status.BAD_REQUEST).entity(err).build();
        }
    }

    @PUT
    @Path("{id}")
    public Response updateTeacher(@PathParam("id") Long id, Teacher teacher) {
        try {
            teacherService.updateTeacher(id, teacher);
            return Response.status(Response.Status.OK).build();
        } catch (Exception error){
            Err err = new Err("No Teacher with id "  + id +  " found ");
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity  (err).build());
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteTeacher(@PathParam("id") Long id) {
        try {
            teacherService.deleteTeacher(id);
            return Response.status(Response.Status.OK).build();
        } catch (Exception error) {
            Err err = new Err("No Teacher with id "  + id +  " found ");
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(err).build());
        }
    }

    @GET
    @Path("{id}/subjects")
    public List<Subject> getTeacherSubjects(@PathParam("id") Long id) {
        if (id == null) {
            Err err = new Err("No Teacher with id "  + id +  " found ");
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).entity(err).build());
        }
        return subjectService.getTeacherSubjects(id);
    }

    @PATCH
    @Path("{teacherId}/{subjectId}")
    public Response addSubjectToTeacher(@PathParam("teacherId") Long teacherId, @PathParam("subjectId") Long subjectId) {
        try {
            subjectService.addTeacherSubject(teacherId, subjectId);
            return Response.status(Response.Status.OK).build();
        } catch (Exception error) {
            Err err = new Err("No  id  found ");
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(err).build());
        }
    }

    @DELETE
    @Path("{teacherId}/{subjectId}")
    public Response removeSubjectFromTeacher(@PathParam("teacherId") Long teacherId, @PathParam("subjectId") Long subjectId) {
        try {
            subjectService.addTeacherSubject(teacherId, subjectId);
            return Response.status(Response.Status.OK).build();
        } catch (Exception error) {
            Err err = new Err("No  id  found ");
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(err).build());
        }
    }

    }