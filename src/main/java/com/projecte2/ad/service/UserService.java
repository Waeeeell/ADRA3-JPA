package com.projecte2.ad.service;

import com.projecte2.ad.dto.UserCreateDTO;
import com.projecte2.ad.dto.UserResponseDTO;
import com.projecte2.ad.dto.UserUpdateDTO;
import com.projecte2.ad.mapper.UserMapper;
import com.projecte2.ad.model.Customer;
import com.projecte2.ad.model.Role;
import com.projecte2.ad.model.User;
import com.projecte2.ad.repository.RoleRepository;
import com.projecte2.ad.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
    }

    @Transactional
    public UserResponseDTO createUser(UserCreateDTO dto) {
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setStatus("ACTIVE");

        Customer customer = new Customer();
        customer.setNom(dto.getNom());
        customer.setCognom(dto.getCognom());
        customer.setTelefon(dto.getTelefon());
        
        user.setCustomer(customer);
        customer.setUser(user);

        User savedUser = userRepository.save(user);
        return userMapper.toDTO(savedUser);
    }

    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toDTO(user);
    }

    @Transactional
    public UserResponseDTO updateUser(Long id, UserUpdateDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (dto.getEmail() != null) user.setEmail(dto.getEmail());
        if (user.getCustomer() != null) {
            if (dto.getNom() != null) user.getCustomer().setNom(dto.getNom());
            if (dto.getCognom() != null) user.getCustomer().setCognom(dto.getCognom());
            if (dto.getTelefon() != null) user.getCustomer().setTelefon(dto.getTelefon());
        }

        User updatedUser = userRepository.save(user);
        return userMapper.toDTO(updatedUser);
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public UserResponseDTO addRoles(Long userId, List<Long> roleIds) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<Role> roles = roleRepository.findAllById(roleIds);
        user.getRoles().addAll(roles);
        return userMapper.toDTO(userRepository.save(user));
    }

    @Transactional
    public UserResponseDTO removeRoles(Long userId, List<Long> roleIds) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<Role> rolesToRemove = roleRepository.findAllById(roleIds);
        user.getRoles().removeAll(rolesToRemove);
        return userMapper.toDTO(userRepository.save(user));
    }
}
