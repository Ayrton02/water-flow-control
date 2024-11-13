package user.presenters.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import user.application.service.UserService;
import user.presenters.dto.UserRequestDTO;
import user.presenters.dto.UserResponseDTO;

@Path("/users")
public class UserController {

    @Inject
    private UserService service;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UserResponseDTO createUser(UserRequestDTO request) {
       return this.service.createUser(request);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public UserResponseDTO findUser(@PathParam("id") String id) {
        return this.service.findUser(id);
    }
}
