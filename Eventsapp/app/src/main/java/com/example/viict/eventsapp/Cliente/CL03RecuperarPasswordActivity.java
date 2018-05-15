package com.example.viict.eventsapp.Cliente;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.viict.eventsapp.R;

public class CL03RecuperarPasswordActivity extends AppCompatActivity {

    private Button siguiente;
    private Button cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cl03_recuperar_password);

        siguiente = (Button) findViewById(R.id.btnSiguiente);
        cancelar = (Button) findViewById(R.id.btnCancelar);

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CL03RecuperarPasswordActivity.this, CL031RecuperarPasswordActivity.class));
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CL03RecuperarPasswordActivity.this, CL01LoginActivity.class));
            }
        });

        /*btnRecuperar = (Button)findViewById(R.id.btnRecuperar);
        btnRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CL03RecuperarPasswordActivity.this,"Su Contraseña es 123",Toast.LENGTH_SHORT).show();
            }
        });*/
    }
}
