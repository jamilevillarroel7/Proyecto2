package com.example.viict.eventsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TerceraActivity extends AppCompatActivity {

    private Button volver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tercera);

        volver = (Button) findViewById(R.id.volver);

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TerceraActivity.this, MainActivity.class));
            }
        });
    }
}
