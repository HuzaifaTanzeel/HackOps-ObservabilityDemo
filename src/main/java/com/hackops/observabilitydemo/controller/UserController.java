package com.hackops.observabilitydemo.controller;

import com.hackops.observabilitydemo.dto.UserDTO;
import com.hackops.observabilitydemo.service.UserService;
import io.micrometer.core.annotation.Timed;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Timed(value = "users.get.all", description = "Time taken to return all users")
    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @Timed(value = "users.get.byId", description = "Time taken to return a user by id")
    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }
}