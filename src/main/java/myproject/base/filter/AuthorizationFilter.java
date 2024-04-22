package myproject.base.filter;

import myproject.auth.AuthErrorMessage;
import myproject.base.exception.ExceptionBody;
import myproject.base.security.Secured;
import myproject.user.entity.Role;

import javax.annotation.Priority;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Provider
@Priority(Priorities.AUTHORIZATION)
public class AuthorizationFilter implements ContainerRequestFilter {
    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        String role;
        if (containerRequestContext.getProperty("role") == null)
            return;
        role = containerRequestContext.getProperty("role").toString();
        if (!Arrays.asList(resourceInfo.getResourceMethod().getAnnotation(RolesAllowed.class).value()).contains(role)) {
            containerRequestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED)
                            .entity(new ExceptionBody(AuthErrorMessage.USER_NOT_ALLOWED))
                            .build()
            );
        }
    }


}
