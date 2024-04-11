package myproject.department.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import myproject.base.exception.ConflictException;
import myproject.base.exception.NotFoundException;
import myproject.department.dto.DepartmentRequestDTO;
import myproject.department.service.DepartmentService;
import myproject.empoyee.service.EmployeeService;
import myproject.project.service.ProjectService;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("departments")
@Produces(MediaType.APPLICATION_JSON)
@Api(tags = "Departments")
public class DepartmentResource {
    @Inject
    private DepartmentService departmentService;
    @Inject
    private EmployeeService employeeService;
    @Inject
    private ProjectService projectService;


    @GET
    @ApiOperation(value = "Get a list of department")
    @ApiResponses({
            @ApiResponse(message = "Return successfully all department", code = 200),
            @ApiResponse(message = "Internal server error", code = 500)
    })
    public Response getDepartments(@QueryParam("isDesc") boolean isDesc) {
        return Response.ok().entity(departmentService.getDepartments(isDesc)).build();
    }

    @GET
    @Path("/{departmentId}")
    @ApiOperation(value = "Get a department by id")
    @ApiResponses({
            @ApiResponse(message = "Return the correct department by given id", code = 200),
            @ApiResponse(message = "Department id cannot be found", code = 404),
            @ApiResponse(message = "Internal server error", code = 500)
    })
    public Response getDepartmentById(@PathParam("departmentId") Long departmentId) throws NotFoundException {
        return Response.ok().entity(departmentService.getDepartmentById(departmentId)).build();
    }

    @GET
    @Path("/{departmentId}/employees")
    @ApiOperation(value = "Get a list of employees by department")
    @ApiResponses({
            @ApiResponse(message = "Return successfully a list of employee", code = 200),
            @ApiResponse(message = "Department id cannot be found", code = 404),
            @ApiResponse(message = "Internal server error", code = 500)
    })
    public Response getEmployeesByDepartment(@PathParam("departmentId") Long departmentId) {
        return Response.ok().entity(employeeService.getEmployeesByDepartment(departmentId)).build();
    }

    @GET
    @Path("/{departmentId}/projects")
    @ApiOperation(value = "Get a list of projects by department")
    @ApiResponses({
            @ApiResponse(message = "Return successfully a list of projects", code = 200),
            @ApiResponse(message = "Department id cannot be found", code = 404),
            @ApiResponse(message = "Internal server error", code = 500)
    })
    public Response getProjectsByDepartment(
            @QueryParam("order_by") @DefaultValue("name") String orderBy,
            @PathParam("departmentId") Long departmentId) {
        return Response.ok().entity(projectService.getProjectsByDepartment(departmentId, orderBy)).build();
    }

//    @GET
//    @Path("/{departmentId}/employees/leaderboard")
//    @ApiOperation(value = "Get top employees by total working hours")
//    @ApiResponses({
//            @ApiResponse(message = "Return successfully a list of top employees", code = 200),
//            @ApiResponse(message = "Department id doesn't exist", code = 404),
//            @ApiResponse(message = "Internal server error", code = 500)
//    })
//    public Response getTopEmployeesByTotalWorkingHours(
//            @PathParam("departmentId") Long departmentId,
//            @QueryParam("limit") Integer limit) throws NotFoundException {
//        return Response.ok().entity(employeeService.getTopEmployeesByTotalWorkingHoursOfDepartment(departmentId, limit)).build();
//    }
    @POST
    @ApiOperation(value = "Create a new department")
    @ApiResponses({
            @ApiResponse(message = "Return successfully created department", code = 200),
            @ApiResponse(message = "Bad request", code = 400),
            @ApiResponse(message = "Unable to create new department due to authorization", code = 401),
            @ApiResponse(message = "Department name is already existed", code = 409),
            @ApiResponse(message = "Unable to create new department due to internal server error", code = 500)
    })
    public Response createDepartment(DepartmentRequestDTO department) throws ConflictException {
        return Response.ok().entity(departmentService.createDepartment(department)).build();
    }

    @PATCH
    @Path("/{departmentId}")
    @ApiOperation(value = "Update a department by id")
    @ApiResponses({
            @ApiResponse(message = "Return the successfully updated department", code = 200),
            @ApiResponse(message = "Bad request", code = 400),
            @ApiResponse(message = "Unable to update department due to authorization", code = 401),
            @ApiResponse(message = "Department name is already existed", code = 409),
            @ApiResponse(message = "Unable to update department due to internal server error", code = 500)
    })
    public Response updateDepartment(DepartmentRequestDTO department, @PathParam("departmentId") Long departmentId) throws NotFoundException, ConflictException {
        return Response.ok().entity(departmentService.updateDepartment(department, departmentId)).build();
    }

    @DELETE
    @Path("/{departmentId}")
    @ApiOperation(value = "Remove a department by id")
    @ApiResponses({
            @ApiResponse(message = "Return the successfully removed department", code = 200),
            @ApiResponse(message = "Bad request", code = 400),
            @ApiResponse(message = "Unable to remove department due to authorization", code = 401),
            @ApiResponse(message = "Unable to remove department due to internal server error", code = 500)
    })
    public Response removeDepartment(@PathParam("departmentId") Long departmentId) throws NotFoundException {
        return Response.ok().entity(departmentService.removeDepartment(departmentId)).build();
    }

}
