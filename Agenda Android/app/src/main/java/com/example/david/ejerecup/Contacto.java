package com.example.david.ejerecup;

public class Contacto {

    protected String nombre, telefono;

    public Contacto (String nombre, String telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getNombre() {return nombre;}

    public String getTelefono() {return telefono;}
}
