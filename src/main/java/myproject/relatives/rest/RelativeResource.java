package myproject.relatives.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import myproject.base.exception.NotFoundException;
import myproject.relatives.service.RelativeService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("relatives")
@Produces(MediaType.APPLICATION_JSON)
@Api(tags = "Relatives")
public class RelativeResource {
    @Inject
    RelativeService relativeService;
    @GET
    @ApiOperation(value = "Get a list of relatives")
    @ApiResponses({
            @ApiResponse(message = "Return successfully a list of relative", code = 200),
            @ApiResponse(message = "Something went wrong in the server", code = 500)
    })
    public Response getRelatives(@QueryParam("limit") Integer limit) {
        return Response.ok().entity(relativeService.getRelatives(limit)).build();
    }

    @GET
    @Path("/{relativeId")
    @ApiOperation(value = "Get a list of relatives")
    @ApiResponses({
            @ApiResponse(message = "Return successfully a relative", code = 200),
            @ApiResponse(message = "Relative id doesn't exist", code = 404),
            @ApiResponse(message = "Something went wrong in the server", code = 500)
    })
    public Response getRelatives(@PathParam("relativeId") Long relativeId) throws NotFoundException {
        return Response.ok().entity(relativeService.getRelativeById(relativeId)).build();
    }


}
