package se.iths.rest;


import se.iths.entity.Subject;
import se.iths.exceptions.CustomException;
import se.iths.entity.Student;
import se.iths.service.StudentService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("student")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentRest {

    @Inject
    StudentService studentService;


    @Path("new")
    @POST
    public Response createStudent(Student student) {

        try {
            studentService.createNewStudent(student);
            return Response.ok(student).build();
        } catch (Exception e) {
            throw new CustomException("{\"There was an error when creating a new student\"}");
        }
    }

    @Path("{id}")
    @GET
    public Response getStudent(@PathParam("id") Long id) {
        Student foundStudent = studentService.findStudentById(id);
        String message = "{\"ID NOT FOUND \": " + id + " }";

        if (foundStudent == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(message)
                    .type(MediaType.APPLICATION_JSON)
                    .build());
        }
        return Response.ok(foundStudent).build();

    }

    @Path("updatename/{id}")
    @PATCH
    public Response uptadeLastName(@PathParam("id") Long id, @QueryParam("lastName") String lastName) {
        String message = "{\"ID NOT FOUND \": " + id + " }";
        try {
            Student updateStudent = studentService.updateStudentByName(id,lastName);
            return Response.ok(updateStudent).build();
        }catch (NullPointerException e){
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(message)
                    .type(MediaType.APPLICATION_JSON)
                    .build());
        }

    }

    @Path("getall")
    @GET
    public Response getAllStudents() {
        List<Student> foundStudents = studentService.getAllStudents();
        String message = "{\"List is empty \"}";

        if ((long) foundStudents.size() == 0) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(message)
                    .type(MediaType.APPLICATION_JSON)
                    .build());
        }
        return Response.ok(foundStudents).build();
    }


    @Path("{id}")
    @DELETE
    public Response deleteStudent(@PathParam("id") Long id) {

        String message = "{\"ID NOT FOUND \": " + id + " }";
        String message2 = "{\"ID TO DELETE \": " + id + " }";
        try {
            studentService.deleteStudent(id);
            return Response.ok(message2).type(MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                   .entity(message)
                   .type(MediaType.APPLICATION_JSON)
                    .build());

       }

    }


}
