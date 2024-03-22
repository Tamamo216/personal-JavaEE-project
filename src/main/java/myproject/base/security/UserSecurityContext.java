package myproject.base.security;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

@AllArgsConstructor
@NoArgsConstructor

public class UserSecurityContext implements SecurityContext {
    private UserCredentials userCredentials;
    @Override
    public Principal getUserPrincipal() {
        return userCredentials;
    }

    @Override
    public boolean isUserInRole(String s) {
        return userCredentials.getRole().name().contains(s);
    }

    @Override
    public boolean isSecure() {
        return true;
    }

    @Override
    public String getAuthenticationScheme() {
        return null;
    }
}
