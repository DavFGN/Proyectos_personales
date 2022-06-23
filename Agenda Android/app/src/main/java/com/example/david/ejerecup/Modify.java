package com.example.david.ejerecup;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Modify extends AppCompatActivity {
    private Button boton;
    private String nombre_i, nombre1, telefono1;
    private EditText nombre, telef;

    final Context context = this;
    final BBDD_Helper data =  new BBDD_Helper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);
        getSupportActionBar().hide();

        nombre = (EditText) findViewById(R.id.nameEditM);
        telef = (EditText) findViewById(R.id.phoneEditM);

        boton = (Button) findViewById(R.id.aceptar_mod);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        if (b!=null){
            nombre.setText(b.getString("no"));
            telef.setText(b.getString("ph"));
            nombre_i = b.getString("no");
        }
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre1 = nombre.getText().toString();
                telefono1 = telef.getText().toString();
                if (PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getBoolean("checkbox_tel", true)) {
                    if (nombre1.isEmpty() && telefono1.isEmpty()) {

                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                        alertDialogBuilder.setTitle("Faltan datos");
                        alertDialogBuilder
                                .setMessage("Debe de indicar el nombre y el teléfono de contacto")
                                .setCancelable(false)
                                .setNegativeButton("ACEPTAR", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();

                    } else if (nombre1.isEmpty() || telefono1.isEmpty() ) {

                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                        alertDialogBuilder.setTitle("Faltan datos");
                        alertDialogBuilder
                                .setMessage("Debe rellenar las 2 opciones (Si solo quiere incluir el nombre, debes" +
                                        " desmarcar la opción en preferencias)")
                                .setCancelable(false)
                                .setNegativeButton("ACEPTAR", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                    } else {
                        modificarContact();
                    }

                } else if (nombre1.isEmpty()) {

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                    alertDialogBuilder.setTitle("Faltan datos");
                    alertDialogBuilder
                            .setMessage("Debe indicar el nombre de contacto")
                            .setCancelable(false)
                            .setNegativeButton("ACEPTAR", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                } else {
                    modificarContact();
                }

                }

        });

    }

    public void onBackPressed() {
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        String nombre2 = (b.getString("no"));
        String telefono2 = (b.getString("ph"));
        nombre1 = nombre.getText().toString();
        telefono1 = telef.getText().toString();

        if (!nombre1.equals(nombre2) || !telefono1.equals(telefono2)){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            alertDialogBuilder.setTitle("¿Salir?");
            alertDialogBuilder
                    .setMessage("Ha hecho algún cambio en los datos. Si se sale, perderá esos datos.")
                    .setCancelable(false)
                    .setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
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
            super.onBackPressed();
        }

    }

public void modificarContact(){
    BBDD_Helper dbHelper = new BBDD_Helper(this);
    dbHelper.modificarSingle(nombre1, telefono1, nombre_i);
    finish();
    }

}
