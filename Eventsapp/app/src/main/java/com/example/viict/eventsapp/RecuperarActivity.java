package com.example.viict.eventsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class RecuperarActivity extends AppCompatActivity {

    private Button btnRecuperar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar);

        btnRecuperar = (Button)findViewById(R.id.btnRecuperar);
        btnRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(RecuperarActivity.this,"Su Contraseña es 123",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
