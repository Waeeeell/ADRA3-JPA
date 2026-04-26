package com.projecte2.ad.dto;

import java.util.List;

public class UserResponseDTO {
    private Long id;
    private String email;
    private CustomerResponseDTO customer;
    private List<RoleDTO> roles;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public CustomerResponseDTO getCustomer() { return customer; }
    public void setCustomer(CustomerResponseDTO customer) { this.customer = customer; }
    public List<RoleDTO> getRoles() { return roles; }
    public void setRoles(List<RoleDTO> roles) { this.roles = roles; }
}
