package com.axonactive.department.rest;

import com.axonactive.department.service.DepartmentService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("departments")
@Produces(MediaType.APPLICATION_JSON)
public class DepartmentController {
    @Inject
    private DepartmentService departmentService;

    @GET
    public Response getDepartments(@QueryParam("isDesc") boolean isDesc) {
        return Response.ok().entity(departmentService.getDepartments(isDesc)).build();
    }
}
