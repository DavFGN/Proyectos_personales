package com.example.david.ejerecup;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import static com.example.david.ejerecup.Database.NOMBRE_COLUMNA1;
import static com.example.david.ejerecup.Database.NOMBRE_COLUMNA2;
import static com.example.david.ejerecup.Database.TABLE_NAME;


public class BBDD_Helper extends SQLiteOpenHelper{

    protected static final int Version= 1;
    protected static final String NameDatabase= "Agenda.db";

    public BBDD_Helper(Context context) {
        super(context,NameDatabase,null, Version);

        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Database.SQL_CREAR);
        Log.d("hay", "hay");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL(Database.SQL_DROP);
        onCreate(db);
    }

    public ArrayList<Contacto> getAllData() {
        ArrayList<Contacto> conList = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);

        while(res.moveToNext()) {
            String name = res.getString(0);
            String phone = res.getString(1);

            Contacto newCont = new Contacto(name, phone);
            conList.add(newCont);
        }
        return conList;
    }


    public void borrar() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(Database.SQL_DELETE);
    }


    public void GuardarBase(){
        SQLiteDatabase db=getWritableDatabase();

        for (int i=0; i < Declaraciones.getContactos().size(); i++) {
            Contacto contacto = Declaraciones.getContactos().get(i);
            String nombre = contacto.getNombre();
            String tel = contacto.getTelefono();
            db.execSQL("INSERT INTO "+Database.TABLE_NAME+" VALUES('" + nombre + "', " + tel + "')");
        }

        db.close();
    }

    public ArrayList<Contacto> getlist(String search) {
        ArrayList<Contacto> alTasklist = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
            Cursor res = db.rawQuery("SELECT * FROM " + Database.TABLE_NAME + " WHERE "
                    + Database.NOMBRE_COLUMNA1 + " LIKE  '%" + search + "%'", null);


            while (res.moveToNext()) {
                String name = res.getString(0);
                String phone = res.getString(1);

                Contacto newCont = new Contacto(name, phone);
                alTasklist.add(newCont);
            }
            return alTasklist;
    }

    public void removeSingle(String name) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("DELETE FROM "+TABLE_NAME+" WHERE "+NOMBRE_COLUMNA1+"='"+name+"'");
    }

    public void modificarSingle(String name, String phone, String nameB) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("UPDATE "+TABLE_NAME+" SET "+NOMBRE_COLUMNA1+" " +
         "= '"+name+"', "+NOMBRE_COLUMNA2+" = '"+phone+"' WHERE "+NOMBRE_COLUMNA1+"='"+nameB+"'");
    }


}
