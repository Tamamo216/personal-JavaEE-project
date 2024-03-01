package myproject.base.exception;

import javax.ws.rs.core.Response;

public class BadRequestException extends BaseException {

    public BadRequestException(String message) {
        super(Response.Status.BAD_REQUEST.getStatusCode(), message);
    }
}
