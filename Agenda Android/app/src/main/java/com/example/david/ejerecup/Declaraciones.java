package com.example.david.ejerecup;

import java.util.ArrayList;

public class Declaraciones {

    private static ArrayList<Contacto> contactos = new ArrayList<>();


    public static void agregaContacto(Contacto con) {contactos.add(con);}

    public static ArrayList<Contacto> getContactos() {
        return contactos;
    }
}
