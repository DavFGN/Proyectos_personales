package com.example.david.ejerecup;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class QueryResult extends AppCompatActivity {
    private TextView top;
    private ListView myListview;
    private final Context context = this;
    private String cadena, nombre_v, phone_v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_result);
        getSupportActionBar().hide();
        top = (TextView) findViewById(R.id.text_top);

        Bundle datos = getIntent().getExtras();
        cadena = datos.getString("consulta_text");
        top.setText("Lista de contactos que contiene la cadena: " + cadena);
        myListview = (ListView) findViewById(R.id.lista_cargar_c);

        cargarResultados();

        myListview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                TextView nombre = (TextView) view.findViewById(R.id.no_cargar_v);
                TextView telefono = (TextView) view.findViewById(R.id.ph_cargar_v);
                String nombre_v = nombre.getText().toString();
                String phone_v = telefono.getText().toString();

                Intent visor = new Intent(view.getContext(),Modify.class);
                visor.putExtra("no", nombre_v);
                visor.putExtra("ph", phone_v);
                startActivity(visor);

                return true;
            }
        });

        myListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView nombre = (TextView) view.findViewById(R.id.no_cargar_v);
                TextView telefono = (TextView) view.findViewById(R.id.ph_cargar_v);
                nombre_v = nombre.getText().toString();
                phone_v = telefono.getText().toString();

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setTitle("Borrar Contacto\n");
                alertDialogBuilder
                        .setMessage("¿Está seguro de borrar el contacto?" +
                                "\n\nNOMBRE: "+nombre_v+"" +
                                "\n TELEFONO: "+phone_v)
                        .setCancelable(false)
                        .setPositiveButton("CONFIRMAR", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                deleteC();
                            }
                        })
                        .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }
    protected void onStart() {
        super.onStart();
        cargarResultados();
    }
    public void deleteC (){
        BBDD_Helper dbHelper = new BBDD_Helper(this);
        dbHelper.removeSingle(nombre_v);
        cargarResultados();
    }


    public void cargarResultados(){
        BBDD_Helper dbhelper = new BBDD_Helper(this);
        ArrayList<Contacto> conList = dbhelper.getlist(cadena);
        CustomAdapter myAdapter = new CustomAdapter(conList, this);
        myListview.setAdapter(myAdapter);
        if (myAdapter.getCount()==0){
            ArrayList<Contacto> vacia = dbhelper.getAllData();
            CustomAdapter Adapter = new CustomAdapter(vacia, this);
            myListview.setAdapter(Adapter);
        }
    }
}
