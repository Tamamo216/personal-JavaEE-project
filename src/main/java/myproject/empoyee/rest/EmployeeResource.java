package myproject.empoyee.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import myproject.base.exception.NotFoundException;
import myproject.base.security.Secured;
import myproject.empoyee.dto.EmployeeRequestDTO;
import myproject.empoyee.service.EmployeeService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("employees")
@Produces(MediaType.APPLICATION_JSON)
@Api(tags = "Employees")
public class EmployeeResource {
    @Inject
    private EmployeeService employeeService;

    @GET
    @Secured
    @ApiOperation(value = "Get a list of employees")
    @ApiResponses({
            @ApiResponse(message = "Return successfully", code = 200),
            @ApiResponse(message = "Something wrong in the server", code = 500)
    })
    public Response getEmployees(@QueryParam("limit") Integer limit) {
        return Response.ok().entity(employeeService.getAllEmployees(limit)).build();
    }

    @GET
    @Secured
    @Path("/{employeeId}")
    @ApiOperation(value = "Get employee by id")
    @ApiResponses({
            @ApiResponse(message = "Return a employee successfully", code = 200),
            @ApiResponse(message = "Cannot found employee by the given id", code = 404),
            @ApiResponse(message = "Something wrong in the server", code = 500)
    })
    public Response findEmployeeById(@PathParam("employeeId") Long employeeId) throws NotFoundException {
        return Response.ok().entity(employeeService.findEmployeeById(employeeId)).build();
    }

    @POST
    @Secured
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Add a new employee")
    @ApiResponses({
            @ApiResponse(message = "Return the successfully added employee", code = 200),
            @ApiResponse(message = "Bad request", code = 400),
            @ApiResponse(message = "The employee already exists", code = 409),
            @ApiResponse(message = "Something wrong in the server", code = 500)
    })
    public Response addEmployee(@Valid EmployeeRequestDTO request) throws NotFoundException, ValidationException {
        return Response.ok().entity(employeeService.addEmployee(request)).build();
    }

    @PUT
    @Secured
    @Path("/{employeeId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Update an employee")
    @ApiResponses({
            @ApiResponse(message = "Return the successfully updated employee", code = 200),
            @ApiResponse(message = "Bad request", code = 400),
            @ApiResponse(message = "Something wrong in the server", code = 500)
    })
    public Response updateEmployee(
            @Valid EmployeeRequestDTO request,
            @PathParam("employeeId") Long employeeId) throws NotFoundException, ValidationException {
        return Response.ok().entity(employeeService.updateEmployee(request, employeeId)).build();
    }

    @GET
    @Secured
    @Path("/{employeeId}/projects")
    @ApiOperation(value = "Get a list of projects that a certain employee has joined")
    @ApiResponses({
            @ApiResponse(message = "Return a list of projects", code = 200),
            @ApiResponse(message = "Employee id doesn't exist", code = 404),
            @ApiResponse(message = "Something wrong in the server", code = 500)
    })
    public Response getEmployeesWithProjects(
            @PathParam("employeeId") Long employeeId,
            @QueryParam("limit") Integer limit
    ) throws NotFoundException {
        return Response.ok().entity(employeeService.getEmployeeWithProjects(employeeId, limit)).build();
    }

    @DELETE
    @Secured
    @Path("/{employeeId}")
    @ApiOperation(value = "Remove an employee by id")
    @ApiResponses({
            @ApiResponse(message = "Return the successfully removed employee", code = 200),
            @ApiResponse(message = "The id doesn't exist", code = 404),
            @ApiResponse(message = "Something wrong in the server", code = 500)
    })
    public Response removeEmployeeById(@PathParam("employeeId") Long employeeId) throws NotFoundException {
        return Response.ok().entity(employeeService.removeEmployeeById(employeeId)).build();
    }
}
