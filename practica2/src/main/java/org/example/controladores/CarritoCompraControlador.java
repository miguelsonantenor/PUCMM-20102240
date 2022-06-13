package org.example.controladores;

import io.javalin.Javalin;
import org.example.servicios.CarritoCompra;
import org.example.servicios.Producto;
import org.example.servicios.VolatilDataBaseService;
import org.example.util.BaseControlador;
import org.example.util.GenerateSecret;
import org.example.util.Sha256Code;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CarritoCompraControlador extends BaseControlador {

    public CarritoCompraControlador(Javalin app) {
        super(app);

    }

    public void aplicarRutas() {

        app.get("/carritodecompra", ctx -> {

            List<Producto> productos = null;
            long totalPrice = 0 ;
            int index = Integer.parseInt( ctx.cookie("indexcarrito"));
            Map<String, Object> modelo = new HashMap<>();
            Map<String, Object> modelo2 =  new HashMap<>();

            if(ctx.cookie("indexcarrito")!=null){
                index = Integer.parseInt( ctx.cookie("indexcarrito"));
                productos = VolatilDataBaseService.getInstance().getCarritoByIndex(index).getProductos();
            }
            if(productos.isEmpty()){
                ctx.render("templates/freemarker/carrodecompra.ftl",modelo);
                System.out.println("First time seeing carrito empty");
                return;
            }else{
                for(int i = 0; i<productos.size(); i++){
                    totalPrice = totalPrice+ productos.get(i).getPrecio();
                }
            }
            modelo.put("productos",productos);
            modelo.put("totalPrice", totalPrice);
            modelo.put("datamap", modelo2);
            modelo.put("cantidad", productos.size());
            ctx.render("templates/freemarker/carrodecompra.ftl",modelo);
            System.out.println("Cliente has returned to Carrito");
            ctx.status(200);

        });

        app.post("/carritodecompra/{id}", ctx -> {
            String formartnumber = ctx.pathParam("id").replace(",","");
            Producto producto = VolatilDataBaseService.getInstance().getProductoPorId(Long.parseLong(formartnumber));
            String productoNombre = producto.getNombre().replace(" ","");
            List<CarritoCompra> carritoCompras = VolatilDataBaseService.getInstance().getCarritoCompras();
            int b = Integer.parseInt(ctx.body());
            int index;
            if (producto != null) {
                if(carritoCompras.isEmpty()){
                    CarritoCompra carrito = new CarritoCompra();
                    if (ctx.cookie(productoNombre) == null) {
                        for(int i =0; i< b; i++){
                            if(ctx.cookie("indexcarrito") ==null){
                                carrito.addProducto(producto);
                                index = VolatilDataBaseService.getInstance().addToCarritoCompra(carrito);
                                ctx.cookie("indexcarrito",""+index);
                            }else{
                                index = Integer.parseInt(ctx.cookie("indexcarrito"));
                                VolatilDataBaseService.getInstance().getCarritoByIndex(index).addProducto(producto);
                            }
                        }
                        ctx.cookie(productoNombre, ctx.body(), 100000);
                        ctx.res.setStatus(200);
                        return;
                    }
                }
                index = Integer.parseInt(ctx.cookie("indexcarrito"));
                if(ctx.cookie( productoNombre) == null){
                    ctx.cookie(productoNombre, ctx.body(), 100000);
                    VolatilDataBaseService.getInstance().getCarritoByIndex(index).addProducto(producto);
                }
                int a = 0;
                if(ctx.cookie( productoNombre) !=null){
                     a = Integer.parseInt(ctx.cookie( productoNombre));
                }

                 for(int i =0; i< b; i++){
                   VolatilDataBaseService.getInstance().getCarritoByIndex(index).addProducto(producto);
                    if(ctx.cookie("indexcarrito") ==null){
                        ctx.cookie("indexcarrito",""+index);
                    }
                 }
                int bodyCountProducto =  a+b;
                ctx.cookie(productoNombre, ""+ bodyCountProducto, 100000);
                ctx.res.setStatus(200);

            }
            System.out.println("I m here to stay"+ ctx.pathParam("id")+" body "+ ctx.cookie(productoNombre));
        });
    }
}
