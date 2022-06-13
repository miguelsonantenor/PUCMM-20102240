package org.example.servicios;

import org.example.util.RandomIDGenerator;

import java.util.LinkedList;

public class CarritoCompra {
    private long id;
    LinkedList<Producto> productos;
    public CarritoCompra(){
        this.productos = new LinkedList<>();
        this.id = RandomIDGenerator.generateId();


    }

    public long getId() {
        return id;
    }

    public LinkedList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(LinkedList<Producto> productos) {
        this.productos = productos;
    }
    public void addProducto(Producto pro){
        this.productos.add(pro);
    }
}
