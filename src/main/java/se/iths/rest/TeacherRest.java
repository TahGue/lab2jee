package se.iths.rest;

;
import se.iths.entity.Teacher;

import se.iths.service.TeacherService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("teacher")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TeacherRest {


    TeacherService teacherService;

    @Inject
    public TeacherRest(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @Path("")
    @GET
    public Response getAllTeacher() {

        List<Teacher> foundSubject = teacherService.getAllTeacher();
        return Response.ok(foundSubject).build();
    }


    @Path("")
    @POST
    public Response createTeacher(Teacher teacher) {
        teacherService.createTeacher(teacher);
        return Response.ok(teacher).build();
    }

    @Path("")
    @PUT
    public Response updateTeacher(Teacher teacher) {
        teacherService.updateTeacher(teacher);
        return Response.ok(teacher).build();
    }

    @Path("{id}")
    @GET
    public Response findTeacherById(@PathParam("id") Long id) {
        Teacher foundTeacher = teacherService.findTeacherById(id);

        if (foundTeacher == null) {

            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("Subject with ID " + id + " was not found in database.").type(MediaType.TEXT_PLAIN_TYPE).build());
        }
        return Response.ok(foundTeacher).build();
    }


    @Path("{id}")
    @DELETE
    public Response deleteSubject(@PathParam("id") Long id) {
        teacherService.deleteTeacher(id);
        return Response.ok().build();
    }

}