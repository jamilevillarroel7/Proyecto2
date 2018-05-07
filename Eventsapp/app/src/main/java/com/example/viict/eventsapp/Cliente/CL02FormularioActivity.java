package com.example.viict.eventsapp.Cliente;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.viict.eventsapp.R;

public class CL02FormularioActivity extends AppCompatActivity {
    private EditText nombres;
    private EditText apellidos;
    private EditText fechaNac;
    private  EditText email;
    private EditText password;
    private Button registar;
    private Button volver;
    private TextView events1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cl02_formulario);
        nombres= (EditText) findViewById(R.id.nombres);
        apellidos= (EditText) findViewById(R.id.apellidos);
        fechaNac= (EditText) findViewById(R.id.fechaNac);
        email= (EditText) findViewById(R.id.email);
        password= (EditText) findViewById(R.id.password);
        registar= (Button) findViewById(R.id.registrar);
        volver = (Button) findViewById(R.id.volver);
        events1 = (TextView) findViewById(R.id.events1);

        Typeface fuente = Typeface.createFromAsset(getAssets(),"fonts/Magettas Regular.otf");
        events1.setTypeface(fuente);


        registar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CL02FormularioActivity.this, CL04PrincipalClienteActivity.class));
            }
        });
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CL02FormularioActivity.this, CL01LoginActivity.class));
            }
        });



    }
}
