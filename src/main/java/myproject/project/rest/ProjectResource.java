package myproject.project.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import myproject.base.exception.NotFoundException;
import myproject.base.security.Secured;
import myproject.project.dto.ProjectRequestDTO;
import myproject.project.service.ProjectService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("projects")
@Produces(MediaType.APPLICATION_JSON)
@Api(tags = "Projects")
public class ProjectResource {
    @Inject
    ProjectService projectService;

    @ApiOperation(value = "Get a list of projects")
    @ApiResponses({
            @ApiResponse(message = "Return successfully a list of projects", code = 200),
            @ApiResponse(message = "Something wrong with the server", code = 500)
    })
    @GET
    @Secured
    public Response getProjects(
            @QueryParam("limit") Integer limit,
            @QueryParam("order_by") @DefaultValue("name") String orderBy) {
        return Response.ok().entity(projectService.getProjects(limit, orderBy)).build();
    }

    @GET
    @Path("/{projectId}")
    @Secured
    @ApiOperation(value = "Get a project by id")
    @ApiResponses({
            @ApiResponse(message = "Return successfully a project by id", code = 200),
            @ApiResponse(message = "Project cannot be found by the given id", code = 404),
            @ApiResponse(message = "Something wrong with the server", code = 500)
    })
    public Response getProjectById(@PathParam("projectId") Long projectId) throws NotFoundException {
        return Response.ok().entity(projectService.getProjectById(projectId)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Secured
    @ApiOperation(value = "Add a new project")
    @ApiResponses({
            @ApiResponse(message = "Return successfully the new project by id", code = 200),
            @ApiResponse(message = "Bad request", code = 400),
            @ApiResponse(message = "Something wrong with the server", code = 500)
    })
    public Response addProject(ProjectRequestDTO request) {
        return Response.ok().entity(projectService.addProject(request)).build();
    }

    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{projectId}")
    @Secured
    @ApiOperation(value = "Update a certain project by id")
    @ApiResponses({
            @ApiResponse(message = "Return successfully the update project", code = 200),
            @ApiResponse(message = "Bad request", code = 400),
            @ApiResponse(message = "Something wrong with the server", code = 500)
    })
    public Response getProjectById(
            ProjectRequestDTO request,
            @PathParam("projectId") Long projectId) throws NotFoundException {
        return Response.ok().entity(projectService.updateProject(request, projectId)).build();
    }

    @DELETE
    @Path("/{projectId}")
    @Secured
    @ApiOperation(value = "Remove a certain project by id")
    @ApiResponses({
            @ApiResponse(message = "Return successfully the removed project", code = 200),
            @ApiResponse(message = "Bad request", code = 400),
            @ApiResponse(message = "Project id doesn't exist", code = 404),
            @ApiResponse(message = "Something wrong with the server", code = 500)
    })
    public Response removeProjectById(@PathParam("projectId") Long projectId) throws NotFoundException {
        return Response.ok().entity(projectService.removeProject(projectId)).build();
    }

}
