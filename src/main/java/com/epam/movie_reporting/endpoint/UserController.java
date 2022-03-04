package com.epam.movie_reporting.endpoint;

import com.epam.movie_reporting.model.User;
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


    @GetMapping("/")
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @PostMapping("/")
    public ResponseEntity saveUser(@RequestBody User user) {
        log.info("User with " + user.getEmail() + " email was registered");
        return ResponseEntity.ok(userService.save(user));

    }

    @PutMapping("/")
    public ResponseEntity update(@RequestBody User user) {
        log.info("User data successfully modified  " );
        return ResponseEntity.ok(userService.update(user));
    }

    @DeleteMapping("/")
    public void delete(@PathVariable int id) {
        log.info("User with id " + id + "  was delete");
        userService.delete(id);

    }

}
