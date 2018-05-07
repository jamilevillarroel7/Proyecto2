package com.example.viict.eventsapp.Cliente;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.viict.eventsapp.R;

public class CL03RecuperarPasswordActivity extends AppCompatActivity {

    private Button btnRecuperar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cl03_recuperar_password);

        btnRecuperar = (Button)findViewById(R.id.btnRecuperar);
        btnRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CL03RecuperarPasswordActivity.this,"Su Contraseña es 123",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
