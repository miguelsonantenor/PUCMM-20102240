package org.example;
import io.javalin.plugin.rendering.JavalinRenderer;
import io.javalin.plugin.rendering.template.JavalinFreemarker;
import org.example.controladores.CarritoCompraControlador;
import org.example.controladores.CookiesSesionesControlador;
import  org.example.servicios.Usuario;
import io.javalin.Javalin;
import io.javalin.core.util.RouteOverviewPlugin;
import io.javalin.http.staticfiles.Location;
import org.example.servicios.VolatilDataBaseService;

import java.util.Date;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        LinkedList<Usuario> usuarios=  new LinkedList<>();
        Date date = new Date();
        System.out.println(date);


        //Creando la instancia del servidor.
        Javalin app = Javalin.create(config ->{
            config.addStaticFiles(staticFileConfig -> {
                staticFileConfig.hostedPath = "/";
                staticFileConfig.directory = "/publico";
                staticFileConfig.location = Location.CLASSPATH;
            });
            config.registerPlugin(new RouteOverviewPlugin("/rutas")); //aplicando plugins de las rutas
            config.enableCorsForAllOrigins();


        }).start(7000);
        VolatilDataBaseService.getInstance();

        new CookiesSesionesControlador(app).aplicarRutas();

        new CarritoCompraControlador(app).aplicarRutas();


    }
}