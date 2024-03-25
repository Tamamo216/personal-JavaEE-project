package myproject.assignment.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import myproject.assignment.dto.AssignmentRequestDTO;
import myproject.assignment.entity.Assignment;
import myproject.assignment.service.AssignmentService;
import myproject.base.exception.NotFoundException;
import myproject.base.security.Secured;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("assignments")
@Produces(MediaType.APPLICATION_JSON)
@Api(tags = "Assignments")
public class AssignmentResource {
    @Inject
    AssignmentService assignmentService;
    @GET
    @Secured
    @ApiOperation(value = "Get a list of assignments")
    @ApiResponses({
            @ApiResponse(message = "Return successfully a list of assignment", code = 200),
            @ApiResponse(message = "Something wrong in the server", code = 500)
    })
    public Response getAssignments(@QueryParam("limit") Integer limit) {
        return Response.ok().entity(assignmentService.getAssignments(limit)).build();
    }

    @GET
    @Secured
    @Path("/{assignmentId}")
    @ApiOperation(value = "Get assignment by id")
    @ApiResponses({
            @ApiResponse(message = "Return successfully an assignment", code = 200),
            @ApiResponse(message = "Assignment id doesn't exist", code = 404),
            @ApiResponse(message = "Something wrong in the server", code = 500)
    })
    public Response getAssignmentById(
            @PathParam("assignmentId") Long assignmentId) throws NotFoundException {
        return Response.ok().entity(assignmentService.getAssignmentById(assignmentId)).build();
    }

    @POST
    @Secured
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Add a new assignment")
    @ApiResponses({
            @ApiResponse(message = "Return successfully the new created assignment", code = 200),
            @ApiResponse(message = "Something wrong in the server", code = 500)
    })
    public Response addAssignment(AssignmentRequestDTO request) throws NotFoundException {
        return Response.ok().entity(assignmentService.addAssignment(request)).build();
    }

//    @PUT
//    @Path("/{assignmentId}")
//    @ApiOperation(value = "Update an assignment")
//    @ApiResponses({
//            @ApiResponse(message = "Return successfully updated assignment", code = 200),
//            @ApiResponse(message = "Assignment id doesn't exist", code = 404),
//            @ApiResponse(message = "Something wrong in the server", code = 500)
//    })
//    public Response updateAssignment(
//            AssignmentRequestDTO request,
//            @PathParam("assignmentId") Long assignmentId) {
//        return Response.ok().entity(assignmentService.updateAssignment(request, assignmentId)).build();
//    }
//
//    @DELETE
//    @Path("{/assignmentId}")
//    @ApiOperation(value = "Remove an assignment")
//    @ApiResponses({
//            @ApiResponse(message = "Return successfully the removed assignment", code = 200),
//            @ApiResponse(message = "Assignment id doesn't exist", code = 404),
//            @ApiResponse(message = "Something wrong in the server", code = 500)
//    })
//    public Response removeAssignment(
//            @PathParam("assignmentId") Long assignmentId
//    ) {
//        return Response.ok().entity(assignmentService.removeAssignment(assignmentId)).build();
//    }
}
