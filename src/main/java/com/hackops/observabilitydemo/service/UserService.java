package com.hackops.observabilitydemo.service;

import com.hackops.observabilitydemo.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public List<UserDTO> getAllUsers() {
        logger.info("Fetching all users");
        return Arrays.asList(
                new UserDTO("1", "Alice", "alice@example.com"),
                new UserDTO("2", "Bob", "bob@example.com")
        );
    }

    public UserDTO getUserById(String id) {
        logger.info("Fetching user by id: {}", id);
        return new UserDTO(id, "Sample User", "sample@example.com");
    }
}

