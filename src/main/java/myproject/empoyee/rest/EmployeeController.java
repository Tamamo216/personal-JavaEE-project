package myproject.empoyee.rest;

import myproject.base.exception.NotFoundException;
import myproject.empoyee.dto.EmployeeRequestDTO;
import myproject.empoyee.service.EmployeeService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("employees")
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeController {
    @Inject
    private EmployeeService employeeService;

    @GET
    public Response getEmployees(@QueryParam("isDesc") @DefaultValue("true") boolean isDesc) {
        return Response.ok().entity(employeeService.getAllEmployees(isDesc)).build();
    }

    @GET
    @Path("/{employeeId}")
    public Response findEmployeeById(@PathParam("employeeId") Long employeeId) throws NotFoundException {
        return Response.ok().entity(employeeService.findEmployeeById(employeeId)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addEmployee(@Valid EmployeeRequestDTO request) throws NotFoundException, ValidationException {
        return Response.ok().entity(employeeService.addEmployee(request)).build();
    }

    @PUT
    @Path("/{employeeId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEmployee(
            @Valid EmployeeRequestDTO request,
            @PathParam("employeeId") Long employeeId) throws NotFoundException, ValidationException {
        return Response.ok().entity(employeeService.updateEmployee(request, employeeId)).build();
    }

    @GET
    @Path("employees-projects")
    public Response getEmployeesWithProjects(
            @QueryParam("area") @DefaultValue("") String area
    ) {
        return Response.ok().entity(employeeService.getEmployeesWithProjects(area)).build();
    }
}
