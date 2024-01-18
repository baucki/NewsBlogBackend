package raf.rs.projekat_test.resources;

import raf.rs.projekat_test.entities.Category;
import raf.rs.projekat_test.entities.User;
import raf.rs.projekat_test.requests.LoginRequest;
import raf.rs.projekat_test.services.UserService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/users")
public class UserResource {

    @Inject
    private UserService userService;

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@Valid LoginRequest loginRequest) {
        Map<String, String> response = new HashMap<>();

        String jwt = this.userService.login(loginRequest.getEmail(), loginRequest.getPassword());
        if (jwt == null) {
            response.put("message", "These credentials do nto match our records");
            return Response.status(422, "Unprocessable Entity").entity(response).build();
        }

        response.put("jwt", jwt);

        return Response.ok(response).build();

    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public User create(User user) {
        return userService.addUser(user);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all() {
        return Response.ok(this.userService.allUsers()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User find(@PathParam("id") Integer id) {
        return this.userService.findUser(id);
    }

    @GET
    @Path("/email/{userEmail}")
    @Produces(MediaType.APPLICATION_JSON)
    public User findByEmail(@PathParam("userEmail") String email) {
        return this.userService.findUserByEmail(email);
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User update(@PathParam("id") Integer id, User user) {
        return this.userService.updateUser(id, user);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") Integer id) {
        this.userService.deleteUser(id);
    }


}
