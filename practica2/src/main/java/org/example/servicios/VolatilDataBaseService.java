package org.example.servicios;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class VolatilDataBaseService {

    private static VolatilDataBaseService volatilDataBaseService;
    private List<Usuario> usuarios = new ArrayList<>();
    private List<Producto> productos = new ArrayList<>();
    private List<CarritoCompra> carritoCompras = new ArrayList<>();
    private List<VentasProducto> ventasProductos = new ArrayList<>();

    private VolatilDataBaseService(){
        String passwordSha256Hex = org.apache.commons.codec.digest.DigestUtils.sha256Hex("admin");
        this.usuarios.add(new Usuario("admin","PUCMM","Programacion Web",passwordSha256Hex,"admin"));
        this.productos.add(new Producto("nintendo switch",15000));
        this.productos.add(new Producto("Playstation 5",18000));
        this.productos.add(new Producto("Xbox serie x",15500));
        this.productos.add(new Producto("Iphone 12",40000));
    }
    /*
    * Constructor privado
    * */
    public static VolatilDataBaseService getInstance(){
        if(volatilDataBaseService == null){
            volatilDataBaseService = new VolatilDataBaseService();
        }
        return volatilDataBaseService;
    }

    public List<Producto> getProductos() {
        return productos;
    }
    public List<Usuario> getUsuarios(){
        return usuarios;
    }
    public List<CarritoCompra> getCarritoCompras(){
        return carritoCompras;
    }
    public List<VentasProducto> getVentasProductos(){
        return ventasProductos;
    }
    public Usuario getUsuario(Usuario usuario){
        return usuarios.stream().filter(u->u.getUsuario() == usuario.getUsuario()).findFirst().orElse(null);
    }
    public Usuario crearUsuario(Usuario usuario){
        if(getUsuario(usuario) !=null){
            System.out.println("Usuario Registrado");
            return null;
        }
        usuarios.add(usuario);
        return usuario;
    }

    public Usuario autheticarUsuario(String usuario, String sha256Password){

        for(int i = 0; i < this.usuarios.size(); i++){
            Usuario usr = usuarios.get(i);
            String usuari = usr.getUsuario();
            String pass = usr.getPassword();
            if(usuari.equals(usuario) ){
                if(pass.equals(sha256Password) ){
                    return usr;
                }
            }
        }
        return null;
    }

    public Producto crearProducto(Producto producto){
       if(getProductoPorNombre(producto.getNombre()) !=null){
           System.out.println("Producto Registrado");
           return null;
       }
       productos.add(producto);
       return producto;
    }
    public Producto getProductoPorNombre(String producto){
        return productos.stream().filter(p->p.getNombre()== producto).findFirst().orElse(null);

    }
    public Producto getProductoPorId(long productoId){
        return productos.stream().filter(p->p.getId()== productoId).findFirst().orElse(null);

    }
    public boolean updateProducto(Producto producto){
        int index = productos.indexOf(getProductoPorId(producto.getId()));
        if(index !=-1){
            productos.set(index,producto);
            System.out.println("Producto updated");
            return true;
        }
        return false;
    }
    public boolean removeProducto(Producto producto){
        int index = productos.indexOf(getProductoPorId(producto.getId()));
        if(index !=-1){
            productos.remove(index);
            System.out.println("Producto Deleted");
            return true;
        }
        return false;
    }
    public List<VentasProducto> realizarVentas(CarritoCompra carritoCompra){
        return null;
    }
    public List<VentasProducto> getVentasProductosPorNombreCliente(String nombre){
        if(ventasProductos != null){
            List<VentasProducto> listaPro = ventasProductos.stream().filter(v->v.getNombreCliente() == nombre).toList();
            return listaPro;
        }
        return null;
    }
    public int addToCarritoCompra(CarritoCompra carrito){
        this.carritoCompras.add(carrito);
        return this.carritoCompras.indexOf(carrito);

    }
    public List<CarritoCompra>  getCarritoCompra(){
        return this.carritoCompras;
    }
    public CarritoCompra getCarritoByIndex(int index){
        if(carritoCompras.isEmpty()){
            CarritoCompra carrito = new CarritoCompra();
            carritoCompras.add(carrito);
        }
        return this.carritoCompras.get(index);
    }

}
