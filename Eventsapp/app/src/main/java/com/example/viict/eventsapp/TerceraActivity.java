package com.example.viict.eventsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TerceraActivity extends AppCompatActivity {
    private EditText nombres;
    private EditText apellidos;
    private EditText fechaNac;
    private  EditText email;
    private EditText password;
    private Button registar;
    private Button volver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tercera);
        nombres= (EditText) findViewById(R.id.nombres);
        apellidos= (EditText) findViewById(R.id.apellidos);
        fechaNac= (EditText) findViewById(R.id.fechaNac);
        email= (EditText) findViewById(R.id.email);
        password= (EditText) findViewById(R.id.password);
        registar= (Button) findViewById(R.id.registrar);
        volver = (Button) findViewById(R.id.volver);
        
        registar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TerceraActivity.this, SegundaActivity.class));
            }
        });
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TerceraActivity.this, MainActivity.class));
            }
        });
    }
}
