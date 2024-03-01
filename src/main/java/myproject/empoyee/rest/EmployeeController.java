package myproject.empoyee.rest;

import myproject.empoyee.dto.EmployeeRequestDTO;
import myproject.empoyee.service.EmployeeService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addEmployees(EmployeeRequestDTO request) {
        return Response.ok().entity(employeeService.addEmployee(request)).build();
    }
}
