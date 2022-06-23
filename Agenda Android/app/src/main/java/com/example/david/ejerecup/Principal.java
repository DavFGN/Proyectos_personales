package com.example.david.ejerecup;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.ListPreference;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;

import static com.example.david.ejerecup.Database.TABLE_NAME;

public class Principal extends AppCompatActivity {
    private TextView nombre;
    private final Context context = this;
    protected String m_Text = "", color = "";
    private ListView myListview;
    private Button borrar, guardar;
    private String nombre_v, phone_v;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle(R.string.titulo_p);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        nombre = (TextView) findViewById(R.id.nombreP);
        myListview = findViewById(R.id.lista_cargar);
        borrar = findViewById(R.id.deleteContact);
        guardar = findViewById(R.id.save_c);
        nombreApp();
        colores();
        llenarListView();

        Boolean robo = getSharedPreferences("PREFE", MODE_PRIVATE).getBoolean("intento_robo", false);
        if (robo){
            Toast notificacion=Toast.makeText(Principal.this,"Hubo un intento de entrar a la app fallido."
                    ,Toast.LENGTH_SHORT);
            notificacion.show();
            getSharedPreferences("PREFE", MODE_PRIVATE).edit().putBoolean("intento_robo", false).commit();
        }

        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setTitle("Borrar todos los contactos");
                alertDialogBuilder
                        .setMessage("¿Está seguro de borrar todos los contactos?")
                        .setCancelable(false)
                        .setPositiveButton("CONFIRMAR", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                myListview.setAdapter(null);
                                deleteA();
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

        myListview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> parent, View view,int position, long id) {

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

   public void deleteC (){
       BBDD_Helper dbHelper = new BBDD_Helper(this);
       dbHelper.removeSingle(nombre_v);
       llenarListView();
    }

    protected void onStart() {
        super.onStart();
        nombreApp();
        llenarListView();
        colores();
        }


    public void deleteA(){
            BBDD_Helper dbhelper = new BBDD_Helper(this);
            dbhelper.borrar();
        }

        public void guardarBD(View view){
            Toast notificacion=Toast.makeText(Principal.this,"Guardando en la base de datos",Toast.LENGTH_SHORT);
            notificacion.show();
            BBDD_Helper dbhelper = new BBDD_Helper(this);
            dbhelper.GuardarBase();
            Toast notify=Toast.makeText(Principal.this,"Datos guardados",Toast.LENGTH_SHORT);
            notify.show();
        }

    public void llenarListView() {
        BBDD_Helper dbhelper = new BBDD_Helper(this);
        ArrayList<Contacto> conList = dbhelper.getAllData();
        CustomAdapter myAdapter = new CustomAdapter(conList, this);
        myListview.setAdapter(myAdapter);
        myListview.setEmptyView(findViewById(R.id.data));

    }



    public void nombreApp(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String username = preferences.getString("chan_name", "No indicado");
        nombre.setText(username);
    }

    public void colores(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        color = preferences.getString("list_color", "");
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.prin);

            if (color.equals("Rojo")) {
                    rl.setBackgroundResource(R.color.red);
            } else if (color.equals("Gris")) {
                rl.setBackgroundResource(R.color.gray);
            } else {
                rl.setBackgroundResource(R.color.green);
            }

        }

    @Override
    public void onBackPressed() {
        if (PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getBoolean("checkbox_salir", true)) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            alertDialogBuilder.setTitle("¿Salir?");
            alertDialogBuilder
                    .setMessage("Se cerrará la aplicación, ¿Continuar?.")
                    .setCancelable(false)
                    .setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Toast notificacion=Toast.makeText(Principal.this,"Guardando en la base de datos",Toast.LENGTH_SHORT);
                            notificacion.show();
                            BBDD_Helper dbhelper = new BBDD_Helper(Principal.this);
                            dbhelper.GuardarBase();
                            Toast notify=Toast.makeText(Principal.this,"Datos guardados",Toast.LENGTH_SHORT);
                            notify.show();
                            finishAffinity();
                        }
                    })
                    .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

        } else {
            Toast notificacion=Toast.makeText(Principal.this,"Guardando en la base de datos",Toast.LENGTH_SHORT);
            notificacion.show();
            BBDD_Helper dbhelper = new BBDD_Helper(Principal.this);
            dbhelper.GuardarBase();
            Toast notify=Toast.makeText(Principal.this,"Datos guardados",Toast.LENGTH_SHORT);
            notify.show();
            finishAffinity();
        }
    }
    public void consultaContacto (View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Consultas por Nombre");
        builder.setMessage("Inserte un nombre:");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("CONSULTAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = input.getText().toString();
                Intent i = new Intent(Principal.this,QueryResult.class);
                i.putExtra("consulta_text", m_Text);
                startActivity(i);
            }
        });
        builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();

    }
    public void addC (View view)
    {
        Intent intencion = new Intent(this, AddContact.class);
        startActivity(intencion);
    }


    @Override public boolean onCreateOptionsMenu(Menu mimenu){

        getMenuInflater().inflate(R.menu.menu_activity, mimenu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem optionM) {
        int id=optionM.getItemId();
        if(id==R.id.configuration){
            startActivity(new Intent(this, Preferencias.class));
            return true;
        }
        if(id==R.id.database){
            Toast notificacion=Toast.makeText(Principal.this,"Guardando en la base de datos",Toast.LENGTH_SHORT);
            notificacion.show();
            BBDD_Helper dbhelper = new BBDD_Helper(this);
            dbhelper.GuardarBase();
            Toast notify=Toast.makeText(Principal.this,"Datos guardados",Toast.LENGTH_SHORT);
            notify.show();
            return true;
        }
        if(id==R.id.closeAppMenu){
            onBackPressed();
            return true;
        }
        if(id==R.id.musicB){

            return true;
        }
        if(id==R.id.videoM){

            return true;
        }
        return super.onOptionsItemSelected(optionM);
    }
}
