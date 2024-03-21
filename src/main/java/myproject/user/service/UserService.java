package myproject.user.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import myproject.base.exception.NotFoundException;
import myproject.user.dao.UserDAO;
import myproject.user.dto.UserRequestDTO;
import myproject.user.dto.UserResponseDTO;
import myproject.user.entity.User;
import myproject.user.mapper.UserMapper;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UserService {
    @Inject
    private UserDAO userDAO;
    @Inject
    private UserMapper userMapper;

    public UserResponseDTO getUserByEmail(String email) throws NotFoundException {
        User user = userDAO.findByEmail(email).orElseThrow(() -> new NotFoundException("Email doesn't exist"));
        return userMapper.toUserResponseDTO(user);
    }

    public UserResponseDTO addUser(UserRequestDTO newUser) {
        User user = userMapper.toUser(newUser);
        String hashedPassword = BCrypt.withDefaults().hashToString(6, newUser.getPassword().toCharArray());
        user.setPassword(hashedPassword);
        userDAO.add(user);
        return userMapper.toUserResponseDTO(user);
    }
}
