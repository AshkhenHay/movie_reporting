package com.epam.movie_reporting.service;

import com.epam.movie_reporting.exeption.DuplicateEntityExeption;
import com.epam.movie_reporting.exeption.NotFoundExeption;
import com.epam.movie_reporting.model.User;
import com.epam.movie_reporting.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements GenericService<User> {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        Optional<User> byEmail = userRepository.findByEmail(user.getEmail());
        if (!byEmail.isPresent()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        } else {
            throw new DuplicateEntityExeption("Email already exist");
        }

    }



    @Override
    public User update(User entity) {
        Optional<User> existingUser = userRepository.findById(entity.getId());
        if (existingUser.isEmpty()) {
            throw new NotFoundExeption("User not found");
        } else {
            User user = existingUser.get();
            user.setFirstName(entity.getFirstName());
            user.setLastName(entity.getLastName());
            user.setAge(entity.getAge());
            user.setEmail(entity.getEmail());
            user.setPassword(passwordEncoder.encode(entity.getPassword()));
            return userRepository.save(user);
        }

    }

    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }


}
