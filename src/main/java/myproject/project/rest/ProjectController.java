package myproject.project.rest;

import myproject.project.service.ProjectService;

import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("projects")
@Produces(MediaType.APPLICATION_JSON)
public class ProjectController {
    @Inject
    ProjectService projectService;

    @GET
    public Response getProjects(@QueryParam("order_by") @DefaultValue("name") String orderBy) {
        return Response.ok().entity(projectService.getProjects(orderBy)).build();
    }
}
