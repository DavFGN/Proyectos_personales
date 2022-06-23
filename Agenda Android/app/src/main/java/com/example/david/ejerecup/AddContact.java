package com.example.david.ejerecup;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AnalogClock;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static java.security.AccessController.getContext;

public class AddContact extends AppCompatActivity {

   private EditText nameN, phoneN;

    private final Context context = this;
    private final BBDD_Helper data =  new BBDD_Helper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle(R.string.titleAdd);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        Context c = getApplicationContext();
        Toast toast = new Toast(this);
        toast.setDuration(Toast.LENGTH_LONG);
        AnalogClock clock = new AnalogClock(c);
        toast.setView(clock);
        toast.show();

        nameN = (EditText)findViewById(R.id.nameEdit);
        phoneN = (EditText)findViewById(R.id.phoneEdit);

    }


    public void verifyInfo (View view){

        String nombre1 = nameN.getText().toString();
        String telefono1 = phoneN.getText().toString();

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
               insertar();
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
           insertar();
        }

        }

        public void insertar(){
            SQLiteDatabase db = data.getWritableDatabase();
            String nombre1 = nameN.getText().toString();
            String telefono1 = phoneN.getText().toString();

            ContentValues values = new ContentValues();
            values.put(Database.NOMBRE_COLUMNA1, nombre1);
            values.put(Database.NOMBRE_COLUMNA2, telefono1);

            db.insert(Database.TABLE_NAME, null, values);

            nameN.setText("");
            phoneN.setText("");
            super.onBackPressed();
        }
    @SuppressLint("ResourceType")
    @Override
    public void onBackPressed() {
        String nombre1 = nameN.getText().toString();
        String telefono1 = phoneN.getText().toString();

        if (!nombre1.isEmpty() || !telefono1.isEmpty()){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            alertDialogBuilder.setTitle("¿Salir?");
            alertDialogBuilder
                    .setMessage("Ha insertado algún dato. Si se sale, perderá esos datos.")
                    .setCancelable(false)
                    .setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                           AddContact.this.finish();
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

            return true;
        }
        if(id==R.id.closeAppMenu){
            finishAffinity();
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
