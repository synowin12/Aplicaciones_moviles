package com.movil.eva_grupal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.movil.eva_grupal.dao.TelevisorDAO;
import com.movil.eva_grupal.entity.Televisor;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton btnCambio;
    RecyclerView recyclerTelevisor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setTheme(R.style.SplashTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        asignarReferencias();
        mostrarDatos();
    }

    private void asignarReferencias() {
        btnCambio = findViewById(R.id.btnCambio);
        btnCambio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FormularioCelulares.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fadein,R.anim.fadeout);
            }
        });
        recyclerTelevisor = findViewById(R.id.recyclertelevisor);
    }

    private void mostrarDatos(){
        List<Televisor> listaTelevisor = new ArrayList<>();

        TelevisorDAO televisorDAO = new TelevisorDAO(this);
        televisorDAO.abrirBD();
        listaTelevisor = televisorDAO.finAllTelevisor();

        CustomAdapter adaptador = new CustomAdapter(this,listaTelevisor);
        recyclerTelevisor.setAdapter(adaptador);
        recyclerTelevisor.setLayoutManager(new LinearLayoutManager(this));

    }
}