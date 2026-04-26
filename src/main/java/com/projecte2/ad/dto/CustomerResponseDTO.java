package com.projecte2.ad.dto;

import java.util.List;

public class CustomerResponseDTO {
    private Long id;
    private String nom;
    private String cognom;
    private String telefon;
    private List<AddressDTO> addresses;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getCognom() { return cognom; }
    public void setCognom(String cognom) { this.cognom = cognom; }
    public String getTelefon() { return telefon; }
    public void setTelefon(String telefon) { this.telefon = telefon; }
    public List<AddressDTO> getAddresses() { return addresses; }
    public void setAddresses(List<AddressDTO> addresses) { this.addresses = addresses; }
}
