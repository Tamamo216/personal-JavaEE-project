package myproject.auth.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import myproject.auth.dto.LoginRequestDTO;
import myproject.auth.service.AuthService;
import myproject.base.exception.BadRequestException;
import myproject.base.exception.InternalServerException;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("auth")
@Produces(MediaType.APPLICATION_JSON)
@Api(tags = "Auth")
public class AuthResource {
    @Inject
    AuthService authService;
    @POST
    @ApiOperation(value = "Authenticating user")
    @ApiResponses({
            @ApiResponse(message = "Return a token when user login successfully", code = 200),
            @ApiResponse(message = "Email or password is incorrect", code = 400),
            @ApiResponse(message = "Failed to generate JWT", code = 500)
    })
    public Response login(LoginRequestDTO request) throws InternalServerException, BadRequestException {
        return Response.ok().entity(authService.validateUser(request)).build();
    }
}
