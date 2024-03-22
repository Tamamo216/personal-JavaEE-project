package myproject.auth.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import myproject.auth.dto.LoginRequestDTO;
import myproject.auth.dto.LoginResponseDTO;
import myproject.base.exception.BadRequestException;
import myproject.base.exception.InternalServerException;
import myproject.base.jwt.JWTProvider;
import myproject.user.dao.UserDAO;
import myproject.user.entity.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Stateless
public class AuthService {

    @Inject
    JWTProvider jwtProvider;
    @Inject
    UserDAO userDAO;

    public LoginResponseDTO validateUser(LoginRequestDTO request) throws InternalServerException, BadRequestException {
        Map<String, String> userInfo = validateCredentials(request.getEmail(), request.getPassword());
        return LoginResponseDTO.builder()
                .token(jwtProvider.generateToken(userInfo))
                .build();
    }


    private Map<String, String> validateCredentials(String email, String password) throws BadRequestException {
        Map<String, String> userInfo = new HashMap<>();
        User user = userDAO.findByEmail(email).orElseThrow(() -> new BadRequestException("Email or password is incorrect"));
        BCrypt.Result res = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword().toCharArray());
        if (!res.verified)
            throw new BadRequestException("Email or password is incorrect");
        userInfo.put("email", user.getEmail());
        Optional.of(user.getDisplayName()).ifPresent(displayName -> userInfo.put("display_name", displayName));
        userInfo.put("role", user.getRole().toString());
        return userInfo;
    }
}
