package com.easydevel.prueba.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "t_section")
public class Section implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    public Section() {
    }

    public Section(String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Section [id=" + id + ", nombre=" + nombre + "]";
    }

}
