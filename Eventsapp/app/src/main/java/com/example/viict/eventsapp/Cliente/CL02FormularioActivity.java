package com.example.viict.eventsapp.Cliente;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.viict.eventsapp.R;

public class CL02FormularioActivity extends AppCompatActivity {

    private static final String TAG = "";
    private EditText nombres;
    private EditText apellidos;
    private EditText fechaNac;
    private  EditText email;
    private EditText primeraContraseña;
    private EditText segundaContraseña;
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
        email= (EditText) findViewById(R.id.direccion);
        primeraContraseña= (EditText) findViewById(R.id.primeraContraseña);
        segundaContraseña= (EditText) findViewById(R.id.segundaContraseña);
        registar= (Button) findViewById(R.id.registrar);
        volver = (Button) findViewById(R.id.volver);
        events1 = (TextView) findViewById(R.id.events1);

        Typeface fuente = Typeface.createFromAsset(getAssets(),"fonts/Magettas Regular.otf");
        events1.setTypeface(fuente);


        registar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences preferences= getSharedPreferences("Reg",MODE_PRIVATE);
                String nom = nombres.getText().toString().trim();
                String ape = apellidos.getText().toString().trim();
                String fechNac = fechaNac.getText().toString().trim();
                String correo = email.getText().toString().trim();
                String contra1 = primeraContraseña.getText().toString().trim();
                String contra2 = segundaContraseña.getText().toString().trim();

                if (nom.length()<=0){
                    Toast.makeText(CL02FormularioActivity.this,"Ingrese su nombre",Toast.LENGTH_SHORT).show();
                }
                else if  (ape.length()<=0){
                    Toast.makeText(CL02FormularioActivity.this,"Ingrese apellidos",Toast.LENGTH_SHORT).show();
                }
                else if (fechNac.length()<=0){
                    Toast.makeText(CL02FormularioActivity.this,"Ingrese su fecha de nacimiento",Toast.LENGTH_SHORT).show();
                }
                else if (correo.length()<=0){
                    Toast.makeText(CL02FormularioActivity.this,"Ingrese su correo",Toast.LENGTH_SHORT).show();
                }
                else if (contra1.length()<=0){
                    Toast.makeText(CL02FormularioActivity.this,"Ingrese su contraseña",Toast.LENGTH_SHORT).show();
                }
                else if (contra2.length()<=0) {
                    Toast.makeText(CL02FormularioActivity.this, "Confirme Contraseña", Toast.LENGTH_SHORT).show();

                }else if (contra1.equals(contra2)){
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("Nombres",nom);
                    editor.putString("Apellidos",ape);
                    editor.putString("Fecha de Nacimiento",fechNac);
                    editor.putString("Correo Electronico",correo);
                    editor.putString("Contraseña",contra1);
                    editor.putString("Confirmar Contraseña",contra2);
                    editor.commit();

                    Toast.makeText(CL02FormularioActivity.this,"Usuario Creado con Exito",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(CL02FormularioActivity.this, CL04PrincipalClienteActivity.class));

                }
                else{
                    Toast.makeText(CL02FormularioActivity.this, "Contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                }



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
