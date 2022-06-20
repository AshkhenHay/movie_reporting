package com.epam.movie_reporting.service;


import com.epam.movie_reporting.config.mapper.userMapper.UserRequestMapper;
import com.epam.movie_reporting.config.mapper.userMapper.UserResponseMapper;
import com.epam.movie_reporting.dto.UserRequestDTO;
import com.epam.movie_reporting.dto.UserResponseDTO;
import com.epam.movie_reporting.exeption.DuplicateEntityException;
import com.epam.movie_reporting.exeption.NotFoundException;
import com.epam.movie_reporting.model.User;
import com.epam.movie_reporting.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService implements GenericService<UserRequestDTO, UserResponseDTO> {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserResponseMapper userResponseMapper;
    private final UserRequestMapper userRequestMapper;

    @Autowired
    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       UserResponseMapper userResponseMapper,
                       UserRequestMapper userRequestMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userResponseMapper = userResponseMapper;
        this.userRequestMapper = userRequestMapper;
    }


    @Override
    public UserResponseDTO save(UserRequestDTO userRequestDTO) {
        Optional<User> existingUser = userRepository.findByEmail(userRequestDTO.getEmail());
        if (!existingUser.isPresent()) {
            userRequestDTO.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
            User user = userRequestMapper.mapToEntity(userRequestDTO);
            User saveUser = userRepository.save(user);
            return userResponseMapper.mapToDTO(saveUser);
        } else {
            throw new DuplicateEntityException("Email already exist");
        }
    }


    @Override
    public UserResponseDTO update(UserRequestDTO entity, long id) {
        Optional<User> existingUser = userRepository.findById(id);
        if (!existingUser.isPresent()) {
            throw new NotFoundException("User not found");
        } else {
            User user = existingUser.get();
            user.setFirstName(entity.getFirstName());
            user.setLastName(entity.getLastName());
            user.setAge(entity.getAge());
            user.setEmail(entity.getEmail());
            user.setPassword(passwordEncoder.encode(entity.getPassword()));
            User saveUser = userRepository.save(user);
            return userResponseMapper.mapToDTO(saveUser);

        }
    }

    @Override
    public void delete(long id) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new NotFoundException("User not found");
        }

    }


}
