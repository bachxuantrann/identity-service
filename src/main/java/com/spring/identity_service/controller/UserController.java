package com.spring.identity_service.controller;

import com.spring.identity_service.dto.request.UserCreationRequest;
import com.spring.identity_service.dto.request.UserUpdateRequest;
import com.spring.identity_service.dto.response.ApiResponse;
import com.spring.identity_service.entity.User;
import com.spring.identity_service.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping()
    public ApiResponse<User> createUser(@RequestBody @Valid UserCreationRequest request) {
        ApiResponse<User> response = new ApiResponse<>();
        response.setResult(userService.createUser(request));
        return response;
    }

    @GetMapping()
    public List<User> getAllUsers() {
        return this.userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id) {
        return this.userService.getUserById(id);
    }
    @PutMapping("/{id}")
    public User updateUser(@PathVariable String id, @RequestBody UserUpdateRequest request) {
        return this.userService.updateUser(id,request);
    }
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable String id) {
        this.userService.deleteUser(id);
        return "User has been deleted";
    }
}
