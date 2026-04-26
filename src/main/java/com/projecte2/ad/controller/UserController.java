package com.projecte2.ad.controller;

import com.projecte2.ad.dto.UserCreateDTO;
import com.projecte2.ad.dto.UserResponseDTO;
import com.projecte2.ad.dto.UserUpdateDTO;
import com.projecte2.ad.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Integrant 1
    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserCreateDTO dto) {
        return new ResponseEntity<>(userService.createUser(dto), HttpStatus.CREATED);
    }

    // Integrant 1
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    // Integrant 2
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @RequestBody UserUpdateDTO dto) {
        return ResponseEntity.ok(userService.updateUser(id, dto));
    }

    // Integrant 2
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // Gestió de rols - Integrant 2
    @PostMapping("/{id}/roles")
    public ResponseEntity<UserResponseDTO> addRoles(@PathVariable Long id, @RequestBody List<Long> roleIds) {
        return ResponseEntity.ok(userService.addRoles(id, roleIds));
    }

    // Gestió de rols - Integrant 1
    @DeleteMapping("/{id}/roles")
    public ResponseEntity<UserResponseDTO> removeRoles(@PathVariable Long id, @RequestBody List<Long> roleIds) {
        return ResponseEntity.ok(userService.removeRoles(id, roleIds));
    }
}
