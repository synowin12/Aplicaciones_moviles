package com.movil.eva_grupal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Pantallainicio extends AppCompatActivity {
    Button btnAdministrar, btnUbicacion, btnmiubicacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantallainicio);


        asignarReferencias();

    }
    private void asignarReferencias(){
        btnAdministrar=findViewById(R.id.btnAdministrar);
        btnAdministrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pantallainicio.this,MainActivity.class);
                startActivity(intent);

            }
        });
        btnUbicacion=findViewById(R.id.btnUbicacion);
        btnUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pantallainicio.this, mapaActivity.class);
                startActivity(intent);
            }
        });

        btnmiubicacion= findViewById(R.id.btnmiUbi);
        btnmiubicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pantallainicio.this, mapa2Activity.class);
                startActivity(intent);
            }
        });
    }

}