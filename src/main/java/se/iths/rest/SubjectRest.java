package se.iths.rest;

import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.entity.Teacher;
import se.iths.service.SubjectService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("subjects")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SubjectRest {

    @Inject
    SubjectService subjectService;

    @Path("new")
    @POST
    public Response createSubject(Subject subject){
       Subject subjectResult = subjectService.createSubject(subject);
        return Response.ok(subjectResult).build();
    }

    @Path("{id}")
    @GET
    public Response getSubject(@PathParam("id") Long id){
        Subject foundSubject = subjectService.findSubjectById(id);
        return Response.ok(foundSubject).build();
    }

    @Path("getall")
    @GET
    public Response getAllStudentsAndOneTeacher() {
        List<Subject> foundStudentsAndTeacher = subjectService.getAllStudentsAndOneTeacher();
        String message = "{\"List is empty \"}";

        if ((long) foundStudentsAndTeacher.size() == 0) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(message)
                    .type(MediaType.APPLICATION_JSON)
                    .build());
        }
        return Response.ok(foundStudentsAndTeacher).build();
    }


}
