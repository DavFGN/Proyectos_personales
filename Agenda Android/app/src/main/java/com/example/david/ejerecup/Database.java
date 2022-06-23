package com.example.david.ejerecup;

import android.provider.BaseColumns;

public class Database {

    protected Database(){}

        public static final String TABLE_NAME = "CONTACTO";
        public static final String NOMBRE_COLUMNA1 = "Nombre";
        public static final String NOMBRE_COLUMNA2 = "Telefono";

    protected static final String SQL_CREAR = "create table " + Database.TABLE_NAME + "(" +
            Database.NOMBRE_COLUMNA1 + " text primary key not null, " + Database.NOMBRE_COLUMNA2
            + " text);";

    protected static final String SQL_DROP = "drop table if exists " + Database.TABLE_NAME;
    protected static final String SQL_DELETE = "delete from " + Database.TABLE_NAME;

}
