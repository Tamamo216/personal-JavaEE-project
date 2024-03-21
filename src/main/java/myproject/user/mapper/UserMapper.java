package myproject.user.mapper;

import myproject.user.dto.UserRequestDTO;
import myproject.user.dto.UserResponseDTO;
import myproject.user.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
        componentModel = "cdi"
)
public interface UserMapper {
    UserResponseDTO toUserResponseDTO(User user);
    List<UserResponseDTO> toUserResponseDTOs(List<User> users);

    User toUser(UserRequestDTO userRequestDTO);

}
