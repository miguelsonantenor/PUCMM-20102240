package org.example.controladores;

import io.javalin.Javalin;
import io.javalin.plugin.rendering.JavalinRenderer;
import io.javalin.plugin.rendering.template.JavalinFreemarker;
import org.example.servicios.*;
import org.example.util.BaseControlador;
import org.example.util.GenerateSecret;
import org.example.util.Sha256Code;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.HashMap;
import java.util.Map;

public class CookiesSesionesControlador extends BaseControlador {

    private VolatilDataBaseService dataBaseServiceContext;

    {
        dataBaseServiceContext = VolatilDataBaseService.getInstance();
    }
    public void updatecarrito(int index, int cantidadProcuto, String productoNombre){

        for(int i =0; i < cantidadProcuto; i++){

            Producto producto = VolatilDataBaseService.getInstance().getProductoPorNombre(productoNombre);
            VolatilDataBaseService.getInstance().getCarritoByIndex(index).addProducto(producto);
        }


    }
    public CookiesSesionesControlador(Javalin app) {
        super(app);

    }

    @Override
    public void aplicarRutas() {

        app.get("/",ctx -> {
            Map<String, Object> modelo = new HashMap<>();
            if(ctx.sessionAttribute("log") ==null) {
                if (ctx.cookie("indexcarrito") != null) {
                    for (int i = 0; i < VolatilDataBaseService.getInstance().getProductos().size(); i++) {
                        if(ctx.cookie(VolatilDataBaseService.getInstance().getProductos().get(i).getNombre().replace(" ", ""))!=null){
                            int cantidad = Integer.parseInt(ctx.cookie(VolatilDataBaseService.getInstance().getProductos().get(i).getNombre().replace(" ", "")));
                            String nombre = VolatilDataBaseService.getInstance().getProductos().get(i).getNombre();
                            int index = Integer.parseInt(ctx.cookie("indexcarrito"));
                            updatecarrito(index, cantidad, nombre);
                        }

                    }
                    System.out.println(""+ctx.sessionAttribute("log"));
                    ctx.sessionAttribute("log", 1);
                }
            }
            modelo.put("productos",dataBaseServiceContext.getProductos());
                if(ctx.cookie("indexcarrito")!=null){
                    modelo.put("carrito", VolatilDataBaseService.getInstance().getCarritoByIndex(Integer.parseInt(ctx.cookie("indexcarrito"))).getProductos().size());
                    System.out.println(VolatilDataBaseService.getInstance().getCarritoByIndex(Integer.parseInt(ctx.cookie("indexcarrito"))).getProductos().size());
                }else{
                    modelo.put("carrito",0);
                }

            if(ctx.cookie("productos") == null){
                ctx.cookie("productos",GenerateSecret.getAlphaNumericString(20),180);
                ctx.render("templates/freemarker/listproductos.ftl",modelo);
                ctx.status(200);
                System.out.println("Nuevo posible Clinete");
                return;
            }

            ctx.render("templates/freemarker/listproductos.ftl",modelo);


        });
        app.get("/administrarproductos", ctx->{
            Map<String, Object> modelo = new HashMap<>();
            modelo.put("productos",dataBaseServiceContext.getProductos());
            modelo.put("usuario", ctx.cookie("usuario"));
            modelo.put("carrito",VolatilDataBaseService.getInstance().getCarritoByIndex(Integer.parseInt(ctx.cookie("indexcarrito"))).getProductos().size());

            String password = ctx.cookie("password");
            String usuario = ctx.cookie("usuario");

            if(password != null){
                if(usuario !=null){
                    if(VolatilDataBaseService.getInstance().autheticarUsuario(usuario,password).getUsuario().equals(usuario)){
                        ctx.cookie("usuario",usuario);
                        ctx.cookie("password",password);
                        ctx.render("templates/freemarker/listproductosadmin.ftl",modelo);
                        return;
                    }
                }
            }
            ctx.render("templates/freemarker/index.ftl");

        });
        app.post("/administrarproductos", ctx->{

            Map<String, Object> modelo = new HashMap<>();
            modelo.put("productos",dataBaseServiceContext.getProductos());
            modelo.put("usuario", ctx.formParam("usuario"));
            modelo.put("carrito",VolatilDataBaseService.getInstance().getCarritoByIndex(Integer.parseInt(ctx.cookie("indexcarrito"))).getProductos().size());
            String password = ctx.formParam("password");
            String usuario = ctx.formParam("usuario");
            System.out.println(password+"   "+usuario);
            if(password != null){
                if(usuario !=null){
                    if(VolatilDataBaseService.getInstance().autheticarUsuario(usuario,Sha256Code.sha256(password)).getUsuario().equals(usuario)){
                        ctx.cookie("usuario",usuario,180);
                        ctx.cookie("password", Sha256Code.sha256(password),180);
                        ctx.render("templates/freemarker/listproductosadmin.ftl",modelo);
                        return;
                    }
                    ctx.render("templates/freemarker/registrar.ftl");
                    return;
                }
            }
            ctx.render("templates/freemarker/registrar.ftl");
            return;


        });
        app.get("/logout",ctx -> {
                ctx.removeCookie("usuario");
                ctx.removeCookie("password");
                ctx.redirect("/", 300);
        });

        app.get("/nuevousuario",ctx -> {
            ctx.render("templates/freemarker/registrar.ftl");

        });

    }

}
