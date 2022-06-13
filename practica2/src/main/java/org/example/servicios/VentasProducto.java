package org.example.servicios;
import org.example.util.RandomIDGenerator;

import java.util.Date;
import java.util.LinkedList;

public class VentasProducto {
    private long id;
    private Date fechaCompra;
    private String nombreCliente;
    private LinkedList<Producto> listaProductos;
    public VentasProducto(){
        fechaCompra =  new Date();
        listaProductos = new LinkedList<>();
        this.id = RandomIDGenerator.generateId();
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public void setListaProductos(LinkedList<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public LinkedList<Producto> getListaProductos() {
        return listaProductos;
    }
}
