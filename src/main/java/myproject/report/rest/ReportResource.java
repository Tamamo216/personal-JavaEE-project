package myproject.report.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import myproject.report.service.ReportService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("reports")
@Produces(MediaType.APPLICATION_JSON)
@Api(tags = "Reports")
public class ReportResource {
    @Inject
    ReportService reportService;

    @GET
    @Path("/area-salary")
    @ApiOperation(value = "Show the area with the avarage average salary in each department")
    @ApiResponses({
            @ApiResponse(message = "Return successfully", code = 200),
            @ApiResponse(message = "Something went wrong in the server", code = 500)
    })
    public Response getTopAreaBySalaryInEachDepartment(@QueryParam("limit") Integer limit) {
        return Response.ok().entity(reportService.getTopAreaBySalaryInEachDepartmentDTO(limit)).build();
    }
}
