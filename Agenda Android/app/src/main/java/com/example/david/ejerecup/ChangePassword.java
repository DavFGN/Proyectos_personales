package com.example.david.ejerecup;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ChangePassword extends AppCompatActivity {
    private String name;
    private EditText contra1, contra2, contra3;
    private boolean changepw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        name = preferences.getString("Name", "");
        contra1 = (EditText)findViewById(R.id.contra_now);
        contra2 = (EditText)findViewById(R.id.new_pass_c);
        contra3 = (EditText)findViewById(R.id.contra_new_r);
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        changepw = prefs.getBoolean("bool_p",false);
        if(changepw) {
            name = prefs.getString("new_pass_to_c", "");
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("new_pass_to_c", name);
            editor.commit();
        }
    }

    public void modificarB (View view){
                        if (!contra1.getText().toString().equals(name)){
                            Toast notificacion=Toast.makeText(ChangePassword.this,"Contraseña incorrecta. "
                                    ,Toast.LENGTH_SHORT);
                            notificacion.show();
                        }
                        else if (!contra2.getText().toString().equals(contra3.getText().toString())){
                            Toast notificacion=Toast.makeText(ChangePassword.this,"No coinciden las 2 claves. "
                                    ,Toast.LENGTH_SHORT);
                            notificacion.show();

                            contra2.setText("");
                            contra3.setText("");
                        }
                        else {
                            String pass = contra2.getText().toString();
                            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putBoolean("bool_p", true);
                            editor.putString("new_pass_to_c", pass);
                            editor.commit();
                            finish();
                        }
    }

    public void cancelar_Change (View view){
        finish();
    }

    @Override
    public void onBackPressed() {
        finish();
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
