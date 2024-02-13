package com.cesur.dam.Entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "aseguradoras")
public class Aseguradora {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAseguradora")
    private Long idAseguradora;
    

    @Column(name = "cif")
    private String cif;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "localizacion")
    private String ciudad;

    public Long getIdAseguradora() {
        return idAseguradora;
    }

    public void setIdAseguradora(Long idAseguradora) {
        this.idAseguradora = idAseguradora;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    
}
