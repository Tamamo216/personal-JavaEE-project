package myproject.base.exception;

import javax.ws.rs.core.Response;

public class UnauthorizedException extends BaseException {
    public UnauthorizedException(String message) {
        super(Response.Status.UNAUTHORIZED.getStatusCode(), message);
    }
}
