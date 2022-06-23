package com.example.david.ejerecup;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Password extends AppCompatActivity {
    private EditText password;
    private String name;
    private Button login_button;
    private int contador = 3;
    protected boolean changepwd = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        Boolean isFirstRun = getSharedPreferences("PREFERENCES", MODE_PRIVATE).getBoolean("isFirstRun1", true);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        name = preferences.getString("Name", "");
        if(!name.equalsIgnoreCase(""))
        {
            name = name;
        }

        if (!isFirstRun){
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
            changepwd = prefs.getBoolean("bool_p",false);
            if(changepwd) {
                name = prefs.getString("new_pass_to_c", "");
            }
        }

        if (isFirstRun) {
            getSharedPreferences("PREFERENCES", MODE_PRIVATE).edit().putBoolean("isFirstRun1", false).commit();
            Bundle datos = getIntent().getExtras();
            String pass = datos.getString("pass");
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("Name", pass);
            editor.apply();
            name = preferences.getString("Name", "");
        }



        boolean failed = getSharedPreferences("PREF", MODE_PRIVATE).getBoolean("failEnter", false);

        if (failed){
            Toast notificacion= Toast.makeText(Password.this,"La última vez que entró a la app" +
                    " falló en la contraseña al menos 1 vez",Toast.LENGTH_LONG);
            notificacion.show();
            getSharedPreferences("PREF", MODE_PRIVATE).edit().putBoolean("failEnter", false).commit();
        }

        logPassword();
    }

    public void logPassword(){
        password = (EditText) findViewById(R.id.contraCla);
        login_button = (Button)findViewById(R.id.aceptar2);

        login_button.setOnClickListener(
                new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        if (password.getText().toString().equals(name)){

                            startActivity(new Intent(Password.this, Principal.class));


                        } else
                        if (!password.getText().toString().equals(name)){

                            getSharedPreferences("PREF", MODE_PRIVATE).edit().putBoolean("failEnter", true).commit();
                            contador--;
                            if (contador==0){
                                getSharedPreferences("PREFE", MODE_PRIVATE).edit().putBoolean("intento_robo", true).commit();
                                finishAffinity();
                            }
                            Toast notificacion=Toast.makeText(Password.this,"Contraseña incorrecta. " +
                                    "Le quedan "+contador+" oportunidades",Toast.LENGTH_SHORT);


                            notificacion.show();

                        }

                    }
                }
        );
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}
