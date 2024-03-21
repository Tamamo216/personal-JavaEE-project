package myproject.base.exception;

import javax.ws.rs.core.Response;

public class InternalServerException extends BaseException {
    public InternalServerException(String message) {
        super(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), message);
    }
}
