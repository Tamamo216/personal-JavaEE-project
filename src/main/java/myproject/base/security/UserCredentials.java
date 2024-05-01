package myproject.base.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import myproject.user.entity.Role;

import java.security.Principal;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserCredentials implements Principal {
    private String email;
    private Role role;

    public UserCredentials(Map<String, String> payload) {
        email = payload.get("email");
        role = switch (payload.get("role").toLowerCase()) {
            case "user" -> Role.USER;
            case "admin" -> Role.ADMIN;
            default -> null;
        };
    }
    @Override
    public String getName() {
        return email;
    }
}
