package se.iths.rest;

import se.iths.entity.Student;
import se.iths.entity.Teacher;
import se.iths.exceptions.CustomException;
import se.iths.service.TeacherService;

import javax.inject.Inject;
import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.util.List;

@Path("teachers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TeacherRest {

    @Inject
    TeacherService teacherService;

    @Path("")
    @POST
    public Response createTeacher(Teacher teacher) {
        try {
            teacherService.createTeacher(teacher);
            return Response.ok(teacher).build();
        } catch (Exception e) {
            throw new CustomException("{\"There was an error when creating a new teacher\"}");
        }
    }

    @Path("{id}")
    @GET
    public Response getTeacher(@PathParam("id") Long id) {
        Teacher foundTeacher = teacherService.findTeacherById(id);
        String message = "{\"ID NOT FOUND \": " + id + " }";

        if (foundTeacher == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(message)
                    .type(MediaType.APPLICATION_JSON)
                    .build());
        }
        return Response.ok(foundTeacher).build();
    }

    @Path("getall")
    @GET
    public Response getAllTeachers() {
        List<Teacher> foundTeachers = teacherService.getAllTeacher();
        String message = "{\"List is empty \"}";

        if ((long) foundTeachers.size() == 0) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(message)
                    .type(MediaType.APPLICATION_JSON)
                    .build());
        }
        return Response.ok(foundTeachers).build();
    }
}
