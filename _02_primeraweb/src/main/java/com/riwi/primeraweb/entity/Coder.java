package com.riwi.primeraweb.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//Entity le indica a spring boot que esta clase es una entidad
@Entity
//Table permite configurar la tabla que creará el ORM (Hibernate)
@Table(name = "coder")
public class Coder {
    // Id indica que el atributo siguiente será la llave primaria
    @Id
    // Generate value indica que el atributo sera auto generado con la estrategia(auto_increment)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String clan;
    private int edad;

    
    public Coder() {
    }


    public Coder(Long id, String nombre, String clan, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.clan = clan;
        this.edad = edad;
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


    public String getClan() {
        return clan;
    }


    public void setClan(String clan) {
        this.clan = clan;
    }


    public int getEdad() {
        return edad;
    }


    public void setEdad(int edad) {
        this.edad = edad;
    }


    @Override
    public String toString() {
        return "Coder [id=" + id + ", nombre=" + nombre + ", clan=" + clan + ", edad=" + edad + "]";
    }

    
}
