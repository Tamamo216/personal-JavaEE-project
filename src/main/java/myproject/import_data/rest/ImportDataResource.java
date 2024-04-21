package myproject.import_data.rest;

import myproject.import_data.service.ImportDataService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;

@Path("import")
@Produces(MediaType.APPLICATION_JSON)
public class ImportDataResource {
    @Inject
    private ImportDataService importDataService;

    @POST
    @Path("/employees/")
    public Response importEmployeeData(InputStream data) throws IOException {
        return Response.ok().entity(importDataService.insertEmployeeData(data, "csv")).build();
    }

    @POST
    @Path("/employees/generate")
    @Consumes({MediaType.APPLICATION_OCTET_STREAM})
    public Response generateEmployeeData(InputStream initDataFile) throws IOException {
        return Response.ok().entity(importDataService.generateMillionEmployeeData(initDataFile)).build();
    }

    @POST
    @Path("/employees/insert")
    public Response insertOneMillionEmployees() throws IOException {
        return Response.ok().entity(importDataService.insertMillionEmployees()).build();
    }
}
