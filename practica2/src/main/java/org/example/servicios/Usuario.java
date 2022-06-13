package org.example.servicios;

public class Usuario {
    private String usuario;
    private String nombre;
    private String apellido;
    private String password;

    private String role;

   public Usuario(String usuario,String nombre, String apellido, String password, String role){
        this.usuario =usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.password = password;
        this.role = role;
    }


    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
