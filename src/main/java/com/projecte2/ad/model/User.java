package com.projecte2.ad.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;
    
    private String status;

    private LocalDateTime dataCreated;

    private LocalDateTime dataUpdated;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

    @PrePersist
    protected void onCreate() {
        dataCreated = LocalDateTime.now();
        dataUpdated = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        dataUpdated = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getDataCreated() { return dataCreated; }
    public void setDataCreated(LocalDateTime dataCreated) { this.dataCreated = dataCreated; }
    public LocalDateTime getDataUpdated() { return dataUpdated; }
    public void setDataUpdated(LocalDateTime dataUpdated) { this.dataUpdated = dataUpdated; }
    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }
    public List<Role> getRoles() { return roles; }
    public void setRoles(List<Role> roles) { this.roles = roles; }
}
