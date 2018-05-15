package com.example.viict.eventsapp.Organizacion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.viict.eventsapp.Cliente.CL04PrincipalClienteActivity;
import com.example.viict.eventsapp.R;

public class ORG02CrearEventosDatosActivity extends AppCompatActivity {

    private Button siguiente,cancelar;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_org02_crear_eventos);

        siguiente = (Button) findViewById(R.id.btnSiguiente);
        cancelar = (Button) findViewById(R.id.btnCancelar);

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ORG02CrearEventosDatosActivity.this, ORG02CrearEventosUbicacionActivity.class));
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ORG02CrearEventosDatosActivity.this, CL04PrincipalClienteActivity.class));
            }
        });
    }
}
