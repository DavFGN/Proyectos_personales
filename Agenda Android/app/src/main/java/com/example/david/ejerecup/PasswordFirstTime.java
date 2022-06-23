package com.example.david.ejerecup;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class PasswordFirstTime extends AppCompatActivity {

    private EditText contra1, contra2;
    private Button login_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_first_time);
        getSupportActionBar().hide();
        Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isFirstRun", false);

        if (isFirstRun) {
            startActivity(new Intent(this, Password.class));
        }


        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                .putBoolean("isFirstRun", true).commit();

        logPassword();
    }


    public void logPassword(){
        contra1 = (EditText) findViewById(R.id.contra1);
        contra2 = (EditText) findViewById(R.id.contra2);
        login_button = (Button)findViewById(R.id.accept);

        login_button.setOnClickListener(
                new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        if (contra1.getText().toString().equals(contra2.getText().toString())){

                            String pass = contra1.getText().toString();
                            Intent i = new Intent(PasswordFirstTime.this,Password.class);
                            i.putExtra("pass", pass);
                            startActivity(i);
                            finish();

                        } else
                        if (!contra1.getText().toString().equals(contra2.getText().toString())){
                            Toast toast = new Toast(PasswordFirstTime.this);
                            ImageView view = new ImageView(PasswordFirstTime.this);
                            view.setImageResource(R.drawable.candado);
                            toast.setView(view);
                            toast.show();

                            Context c = PasswordFirstTime.this; Toast atoast = Toast.makeText(c,
                                    R.string.pass_first,
                                    Toast.LENGTH_LONG);
                            atoast.show();

                            contra1.setText("");
                            contra2.setText("");
                            toast.show();
                        }
                    }
                }
        );
    }



    public void cancelar(View view){
        String contraEmergencia = "";
        Intent i = new Intent(PasswordFirstTime.this,Password.class);
        i.putExtra("pass", contraEmergencia);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        String contraEmergencia = "";
        Intent i = new Intent(PasswordFirstTime.this,Password.class);
        i.putExtra("pass", contraEmergencia);
        finish();
    }
}