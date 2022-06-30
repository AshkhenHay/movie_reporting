package com.epam.movie_reporting.endpoint;

import com.epam.movie_reporting.dto.UserRequestDTO;
import com.epam.movie_reporting.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {


    private final UserService userService;

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
