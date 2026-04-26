package com.projecte2.ad.dto;

public class UserUpdateDTO {
    private String email;
    private String nom;
    private String cognom;
    private String telefon;

    // Getters and Setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getCognom() { return cognom; }
    public void setCognom(String cognom) { this.cognom = cognom; }
    public String getTelefon() { return telefon; }
    public void setTelefon(String telefon) { this.telefon = telefon; }
}
