package myproject.department.rest;

import myproject.department.service.DepartmentService;
import myproject.empoyee.service.EmployeeService;
import myproject.project.service.ProjectService;

import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("departments")
@Produces(MediaType.APPLICATION_JSON)
public class DepartmentController {
    @Inject
    private DepartmentService departmentService;
    @Inject
    private EmployeeService employeeService;
    @Inject
    private ProjectService projectService;


    @GET
    public Response getDepartments(@QueryParam("isDesc") boolean isDesc) {
        return Response.ok().entity(departmentService.getDepartments(isDesc)).build();
    }

    @GET
    @Path("/{departmentId}/employees")
    public Response getEmployeesByDepartment(@PathParam("departmentId") Long departmentId) {
        return Response.ok().entity(employeeService.getEmployeesByDepartment(departmentId)).build();
    }

    @GET
    @Path("/{departmentId}/projects")
    public Response getProjectsByDepartment(
            @QueryParam("order_by") @DefaultValue("name") String orderBy,
            @PathParam("departmentId") Long departmentId) {
        return Response.ok().entity(projectService.getProjectsByDepartment(departmentId, orderBy)).build();
    }
}
