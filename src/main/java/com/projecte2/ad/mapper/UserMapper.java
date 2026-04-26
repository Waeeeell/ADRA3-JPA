package com.projecte2.ad.mapper;

import com.projecte2.ad.dto.*;
import com.projecte2.ad.model.*;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    private final CustomerMapper customerMapper;

    public UserMapper(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    public UserResponseDTO toDTO(User user) {
        if (user == null) return null;
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        if (user.getCustomer() != null) {
            dto.setCustomer(customerMapper.toDTO(user.getCustomer()));
        }
        if (user.getRoles() != null) {
            dto.setRoles(user.getRoles().stream()
                .map(role -> {
                    RoleDTO roleDTO = new RoleDTO();
                    roleDTO.setId(role.getId());
                    roleDTO.setName(role.getName());
                    return roleDTO;
                })
                .collect(Collectors.toList()));
        }
        return dto;
    }
}
