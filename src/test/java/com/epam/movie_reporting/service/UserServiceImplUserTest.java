package com.epam.movie_reporting.service;

import com.epam.movie_reporting.config.mapper.userMapper.UserRequestMapper;
import com.epam.movie_reporting.config.mapper.userMapper.UserResponseMapper;
import com.epam.movie_reporting.dto.UserRequestDTO;
import com.epam.movie_reporting.dto.UserResponseDTO;
import com.epam.movie_reporting.exeption.DuplicateEntityException;
import com.epam.movie_reporting.exeption.NotFoundException;
import com.epam.movie_reporting.model.User;
import com.epam.movie_reporting.repository.UserRepository;
import com.epam.movie_reporting.util.UserTestHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplUserTest extends UserTestHelper {

    @Spy
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    UserRepository userRepository = Mockito.mock(UserRepository.class);
    UserRequestMapper requestMapper = Mockito.mock(UserRequestMapper.class);
    UserResponseMapper responseMapper = Mockito.mock(UserResponseMapper.class);

    private UserService userService;

    private User user;
    private UserResponseDTO responseDto;
    private UserRequestDTO requestDto;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository, passwordEncoder, responseMapper, requestMapper);
        requestDto = generateUserRequestDTO();
        responseDto = generateUserResponseDTO();
        user = generateUser();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void saveTest() {
        when(userRepository.findByEmail(requestDto.getEmail())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(anyString())).thenCallRealMethod();
        when(requestMapper.mapToEntity(any(UserRequestDTO.class))).thenReturn(user);
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(responseMapper.mapToDTO(any(User.class))).thenReturn(responseDto);

        userService.save(requestDto);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void updateTest() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(requestMapper.mapToEntity(any(UserRequestDTO.class))).thenReturn(user);
        when(responseMapper.mapToDTO(any(User.class))).thenReturn(responseDto);
        when(passwordEncoder.encode(anyString())).thenCallRealMethod();

        assertEquals(user.getFirstName(), requestDto.getFirstName());
        assertEquals(user.getLastName(), requestDto.getLastName());
        assertEquals(user.getAge(), requestDto.getAge());
        assertEquals(user.getEmail(), requestDto.getEmail());
        assertEquals(user.getPassword(), requestDto.getPassword());
        userService.update(requestDto, anyLong());


        verify(userRepository, times(1)).save(any(User.class));

    }

    @Test
    public void deleteTest() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        userService.delete(1L);

        verify(userRepository, times(1)).deleteById(any(Long.class));
    }

    @Test
    void duplicateEntityExceptionTest() {
        when(userRepository.findByEmail(requestDto.getEmail())).thenReturn(Optional.of(user));

        Assertions.assertThrows(DuplicateEntityException.class, () -> userService.save(requestDto));
    }

    @Test
    public void notFoundExceptionTest() {
        when(userRepository.findById(any(Long.class))).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> userService.delete(any(Long.class)));
        Assertions.assertThrows(NotFoundException.class, () -> userService.update(requestDto, any(Long.class)));
    }

}
