package com.epam.movie_reporting.config.mapper.userMapper;

import com.epam.movie_reporting.config.mapper.BaseMapper;
import com.epam.movie_reporting.dto.UserResponseDTO;
import com.epam.movie_reporting.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserResponseMapper implements BaseMapper<User, UserResponseDTO> {

    @Override
    public UserResponseDTO mapToDTO(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .age(user.getAge())
                .email(user.getEmail())
                .build();
    }

    @Override
    public User mapToEntity(UserResponseDTO userResponseDTO) {
        return User.builder()
                .id(userResponseDTO.getId())
                .firstName(userResponseDTO.getFirstName())
                .lastName(userResponseDTO.getLastName())
                .age(userResponseDTO.getAge())
                .email(userResponseDTO.getEmail())
                .build();
    }
}
