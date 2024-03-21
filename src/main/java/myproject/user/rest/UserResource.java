package myproject.user.rest;

import io.swagger.annotations.Api;
import myproject.user.dto.UserRequestDTO;
import myproject.user.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("users")
@Produces(MediaType.APPLICATION_JSON)
@Api(tags = "Users")
public class UserResource {
    @Inject
    UserService userService;
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(UserRequestDTO request) {
        return Response.ok().entity(userService.addUser(request)).build();
    }
}
