package com.epam.movie_reporting.endpoint;

import com.epam.movie_reporting.dto.UserRequestDTO;
import com.epam.movie_reporting.dto.UserResponseDTO;
import com.epam.movie_reporting.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;


    @GetMapping("")
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable("id") int id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @PostMapping("")
    public ResponseEntity saveUser(@RequestBody UserRequestDTO userRequestDTO) {
        log.info("User with " + userRequestDTO.getEmail() + " email was registered");
        return ResponseEntity.ok(userService.save(userRequestDTO));

    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") long id, @RequestBody UserRequestDTO userRequestDTO) {
        log.info("User data successfully modified  ");
        return ResponseEntity.ok(userService.update(userRequestDTO, id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        log.info("User with id " + id + "  was delete");
        userService.delete(id);

    }

}
