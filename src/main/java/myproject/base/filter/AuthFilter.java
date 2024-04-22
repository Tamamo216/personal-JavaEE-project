package myproject.base.filter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import myproject.auth.AuthErrorMessage;
import myproject.base.exception.ExceptionBody;
import myproject.base.jwt.JWTProvider;
import myproject.base.security.Secured;
import myproject.base.security.UserCredentials;
import myproject.base.security.UserSecurityContext;

import javax.annotation.Priority;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.Map;

@Provider
@Secured
@Priority(Priorities.AUTHENTICATION)
public class AuthFilter implements ContainerRequestFilter {
    @Inject
    JWTProvider jwtProvider;
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String authHeader = requestContext.getHeaderString("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer")) {
            requestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED)
                            .entity(new ExceptionBody(AuthErrorMessage.TOKEN_REQUIRED))
                            .build());
            return;
        }

        String token = authHeader.substring("Bearer ".length());
        try {
            Map<String, String> userInfo = jwtProvider.validateToken(token);
            UserCredentials userCredentials = new UserCredentials(userInfo);
            requestContext.setSecurityContext(new UserSecurityContext(userCredentials));
            requestContext.setProperty("role", userCredentials.getRole().toString());
        } catch (JWTVerificationException e) {
            requestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED)
                            .entity(new ExceptionBody(AuthErrorMessage.INVALID_TOKEN))
                            .build());
        }

    }
}
