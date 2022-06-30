package com.epam.movie_reporting.config.mapper.userMapper;

import com.epam.movie_reporting.config.mapper.BaseMapper;
import com.epam.movie_reporting.dto.UserRequestDTO;
import com.epam.movie_reporting.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserRequestMapper implements BaseMapper<User, UserRequestDTO> {

    @Override
    public UserRequestDTO mapToDTO(User user) {
        return UserRequestDTO.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .age(user.getAge())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

    @Override
    public User mapToEntity(UserRequestDTO userRequestDTO) {
        return User.builder()
                .firstName(userRequestDTO.getFirstName())
                .lastName(userRequestDTO.getLastName())
                .age(userRequestDTO.getAge())
                .email(userRequestDTO.getEmail())
                .password(userRequestDTO.getPassword())
                .build();
    }
}
