package com.example.viict.eventsapp.Cliente;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.viict.eventsapp.R;

public class CL031RecuperarPasswordActivity extends AppCompatActivity {

    private Button recuperar;
    private Button cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cl031_recuperar_password);

        recuperar = (Button)findViewById(R.id.recuperar);
        cancelar = (Button)findViewById(R.id.cancelar);

        recuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CL031RecuperarPasswordActivity.this, CL04PrincipalClienteActivity.class));
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CL031RecuperarPasswordActivity.this, CL01LoginActivity.class));
            }
        });

    }
}
