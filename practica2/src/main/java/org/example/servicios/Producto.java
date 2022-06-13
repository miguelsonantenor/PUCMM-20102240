package org.example.servicios;

import org.example.util.RandomIDGenerator;

import java.math.BigDecimal;

public class Producto {
    private long id;
    private String nombre;
    private long precio;

    public Producto(String nombre, long precio){
        this.id = RandomIDGenerator.generateId();
        this.nombre =nombre;
        this.precio =  precio;
    }

    public long getId() {

        return id;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getPrecio() {
        return precio;
    }

    public void setPrecio(long precio) {
        this.precio = precio;
    }
}
