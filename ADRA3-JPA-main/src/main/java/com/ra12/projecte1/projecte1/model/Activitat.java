package com.ra12.projecte1.projecte1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.time.LocalDateTime;

@Entity(name = "activitats")
public class Activitat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombreRuta;

    @Column(nullable = false)
    private String descripcio;

    @Column(nullable = false)
    private int dias;

    @Column(nullable = false)
    private int horas;

    @Column(nullable = false)
    private int minuts;

    @Column(nullable = false)
    private int distancia;

    @Column(nullable = true)
    private LocalDateTime dataCreated;

    @Column(nullable = true)
    private LocalDateTime dateUpdate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreRuta() {
        return nombreRuta;
    }

    public void setNombreRuta(String nombreRuta) {
        this.nombreRuta = nombreRuta;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public int getMinuts() {
        return minuts;
    }

    public void setMinuts(int minuts) {
        this.minuts = minuts;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    @PrePersist
    protected void onCreated() {
        dataCreated = LocalDateTime.now();
        dateUpdate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        dateUpdate = LocalDateTime.now();
    }
}