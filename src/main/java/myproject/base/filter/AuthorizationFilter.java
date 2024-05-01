package myproject.base.filter;

import myproject.auth.AuthErrorMessage;
import myproject.base.exception.ExceptionBody;

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

@Provider
@Priority(Priorities.AUTHORIZATION)
public class AuthorizationFilter implements ContainerRequestFilter {
    @Context
    private ResourceInfo resourceInfo;
    @Context
    private SecurityContext securityContext;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        RolesAllowed rolesAllowed = resourceInfo.getResourceMethod().getAnnotation(RolesAllowed.class);
        if (rolesAllowed == null)
            return;

        if (isNotAllowed(rolesAllowed.value())) {
            containerRequestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED)
                            .entity(new ExceptionBody(AuthErrorMessage.USER_NOT_ALLOWED))
                            .build()
            );
        }
    }

    private boolean isNotAllowed (String[] allowedRoles) {
        return Arrays.stream(allowedRoles)
                .noneMatch(role -> securityContext.isUserInRole(role));
    }


}
