package myproject.user.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import myproject.user.dto.UserRequestDTO;
import myproject.user.service.UserService;

import javax.annotation.security.RolesAllowed;
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
    @ApiOperation(value = "Create new user")
    @ApiResponses({
            @ApiResponse(message = "Add user successfully", code = 200),
            @ApiResponse(message = "Bad request", code = 400),
            @ApiResponse(message = "Internal server error", code = 500)
    })
    public Response addUser(UserRequestDTO request) {
        return Response.ok().entity(userService.addUser(request)).build();
    }
}
