package com.movil.eva_grupal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.movil.eva_grupal.dao.TelevisorDAO;
import com.movil.eva_grupal.entity.Televisor;

public class FormularioCelulares extends AppCompatActivity {
    EditText txtMarca,txtModelo,txtDescipcion,txtTienda,txtCompra,txtVenta;
    Button btnRegistrar;
    Boolean registra = true;
    String marca,modelo,tienda,descripcion;
    double compra,venta;
    int id;
    TextView mensajeTextview;
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fadein,R.anim.fadeout);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_peliculas);
        asignarReferencias();
        recibirDatos();
    }

    private void recibirDatos(){
        if(getIntent().hasExtra("varId")){
            mensajeTextview = findViewById(R.id.txtFormulario);
            mensajeTextview.setText("Editar Formulario Televisor");
            btnRegistrar.setText("Editar Televisor");
            registra = false;
            id = getIntent().getIntExtra("varId",0);
            marca = getIntent().getStringExtra("varMarca");
            modelo = getIntent().getStringExtra("varModelo");
            tienda = getIntent().getStringExtra("varTienda");
            compra = getIntent().getDoubleExtra("varCompra",0);
            venta = getIntent().getDoubleExtra("varVenta",0);
            descripcion = getIntent().getStringExtra("varDescripcion");


            txtMarca.setText(marca);
            txtModelo.setText(modelo);
            txtTienda.setText(tienda);
            txtCompra.setText(compra+"");
            txtVenta.setText(venta+"");
            txtDescipcion.setText(descripcion);

        }
    }

    private void asignarReferencias() {
        txtMarca = findViewById(R.id.txtMarca);
        txtModelo = findViewById(R.id.txtModelo);
        txtTienda = findViewById(R.id.txtTienda);
        txtCompra = findViewById(R.id.txtCompra);
        txtVenta = findViewById(R.id.txtVenta);
        txtDescipcion = findViewById(R.id.txtDescripcion);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validar();
            }
        });
    }
    private void registrar(){
        String marca, modelo, tienda,descripcion;
        double compra,venta;
        marca = txtMarca.getText().toString();
        modelo = txtModelo.getText().toString();
        tienda = txtTienda.getText().toString();
        descripcion = txtDescipcion.getText().toString();
        compra = Double.parseDouble(txtCompra.getText().toString());
        venta = Double.parseDouble(txtVenta.getText().toString());

        TelevisorDAO televisorDAO = new TelevisorDAO(this);
        televisorDAO.abrirBD();
        String mensaje;
        if(registra == true) {
            Televisor televisor = new Televisor(marca, modelo, tienda, compra, venta, descripcion);
            mensaje = televisorDAO.registrarTelevisor(televisor);
        }else{
         Televisor televisor = new Televisor(id,marca,modelo,tienda,compra,venta,descripcion);
            mensaje = televisorDAO.editarTelevisor(televisor);
        }
        AlertDialog.Builder ventana = new AlertDialog.Builder(FormularioCelulares.this);
        ventana.setTitle("Mensaje Informativo");
        ventana.setMessage(mensaje);
        ventana.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(FormularioCelulares.this,MainActivity.class);
                overridePendingTransition(R.anim.fadein,R.anim.fadeout);
                startActivity(intent);
            }
        });
        ventana.create().show();


    }
    private boolean validar(){
        boolean rpta=true;
        String marca, modelo, tienda,descripcion;
        String compra,venta;
        marca = txtMarca.getText().toString();
        modelo = txtModelo.getText().toString();
        tienda = txtTienda.getText().toString();
        descripcion = txtDescipcion.getText().toString();
        compra = txtCompra.getText().toString();
        venta = txtVenta.getText().toString();
        if(marca.equals("") && modelo.equals("") && tienda.equals("") && descripcion.equals("") && compra.equals("") && venta.equals("") ) {
            txtMarca.setError("Marca obligatoria");
            txtModelo.setError("Modelo obligatoria");
            txtTienda.setError("Tienda obligatoria");
            txtCompra.setError("Precio compra obligatorio");
            txtVenta.setError("Precio venta obligatorio");
            txtDescipcion.setError("Descripción obligatoria");
            rpta=false;
        }else if(marca.equals("")) {
            txtMarca.setError("Marca obligatoria");
            rpta=false;
        }else if(modelo.equals("")) {
            txtModelo.setError("Modelo obligatoria");
            rpta=false;
        }else if(tienda.equals("")) {
            txtTienda.setError("Tienda obligatoria");
            rpta=false;
        }else if(descripcion.equals("")) {
            txtDescipcion.setError("Descripción obligatoria");
            rpta = false;
        }else if (compra.equals("")) {
            txtCompra.setError("Precio compra obligatorio");
            rpta = false;
        }else if (venta.equals("")){
            txtVenta.setError("Precio venta obligatorio");
            rpta = false;
        }else if (marca.equals("") && modelo.equals("") && tienda.equals("") && descripcion.equals("") && compra.equals("")) {
            txtMarca.setError("Marca obligatoria");
            txtModelo.setError("Modelo obligatoria");
            txtTienda.setError("Tienda obligatoria");
            txtCompra.setError("Precio compra obligatorio");
            txtDescipcion.setError("Descripción obligatoria");
            rpta=false;
        }else if (marca.equals("") && modelo.equals("") && tienda.equals("") && descripcion.equals("")  && venta.equals("") ) {
            txtMarca.setError("Marca obligatoria");
            txtModelo.setError("Modelo obligatoria");
            txtTienda.setError("Tienda obligatoria");
            txtVenta.setError("Precio venta obligatorio");
            txtDescipcion.setError("Descripción obligatoria");
            rpta=false;
        }else if (marca.equals("") && modelo.equals("") && tienda.equals("")   && venta.equals("") ) {
            txtMarca.setError("Marca obligatoria");
            txtModelo.setError("Modelo obligatoria");
            txtTienda.setError("Tienda obligatoria");
            txtVenta.setError("Precio venta obligatorio");
            rpta=false;
        }else if (marca.equals("") && modelo.equals("") && tienda.equals("") && compra.equals("")){
            txtMarca.setError("Marca obligatoria");
            txtModelo.setError("Modelo obligatoria");
            txtTienda.setError("Tienda obligatoria");
            txtCompra.setError("Precio compra obligatorio");
            rpta=false;
        }else if(marca.equals("") && modelo.equals("") && tienda.equals("") && descripcion.equals("")) {
            txtMarca.setError("Marca obligatoria");
            txtModelo.setError("Modelo obligatoria");
            txtTienda.setError("Tienda obligatoria");
            txtDescipcion.setError("Descripción obligatoria");
            rpta=false;
        }else if(marca.equals("") && modelo.equals("") && tienda.equals("")) {
            txtMarca.setError("Marca obligatoria");
            txtModelo.setError("Modelo obligatoria");
            txtTienda.setError("Tienda obligatoria");
            rpta=false;
        }else if (marca.equals("") && modelo.equals("")  && venta.equals("")) {
            txtMarca.setError("Marca obligatoria");
            txtModelo.setError("Modelo obligatoria");
            txtVenta.setError("Precio venta obligatorio");
            rpta=false;
        }else if (marca.equals("") && modelo.equals("") && compra.equals("")){
            txtMarca.setError("Marca obligatoria");
            txtModelo.setError("Modelo obligatoria");
            txtCompra.setError("Precio compra obligatorio");
            rpta=false;
        }else if (venta.equals("") && compra.equals("")){
            txtVenta.setError("Precio venta obligatorio");
            txtCompra.setError("Precio compra obligatorio");
        }else if(marca.equals("") && modelo.equals("")) {
            txtMarca.setError("Marca obligatoria");
            txtModelo.setError("Modelo obligatoria");
            rpta=false;
        }else if (marca.equals("") && tienda.equals("")) {
            txtMarca.setError("Marca obligatoria");
            txtTienda.setError("Tienda obligatoria");
            rpta=false;
        }else if (marca.equals("") && descripcion.equals("")) {
            txtMarca.setError("Marca obligatoria");
            txtDescipcion.setError("Descripción obligatoria");
            rpta=false;
        }else if (marca.equals("") && compra.equals("")) {
            txtMarca.setError("Marca obligatoria");
            txtCompra.setError("Precio compra obligatorio");
            rpta=false;
        } else if (marca.equals("") && venta.equals("")) {
            txtMarca.setError("Marca obligatoria");
            txtVenta.setError("Precio venta obligatorio");
            rpta=false;
        }else if(modelo.equals("") && tienda.equals("")) {
            txtModelo.setError("Modelo obligatoria");
            txtTienda.setError("Tienda obligatoria");
            rpta=false;
        }else if(modelo.equals("") && descripcion.equals("")) {
            txtModelo.setError("Modelo obligatoria");
            txtDescipcion.setError("Descripción obligatoria");
            rpta=false;
        }else if (modelo.equals("") && compra.equals("")) {
            txtModelo.setError("Modelo obligatoria");
            txtCompra.setError("Precio compra obligatorio");
            rpta=false;
        }else if (modelo.equals("") && venta.equals("")){
            txtModelo.setError("Modelo obligatoria");
            txtVenta.setError("Precio venta obligatorio");
            rpta=false;
        }else if (tienda.equals("") && descripcion.equals("")) {
            txtTienda.setError("Tienda obligatoria");
            txtDescipcion.setError("Descripción obligatoria");
            rpta=false;
        }else if (tienda.equals("") && compra.equals("")) {
            txtTienda.setError("Tienda obligatoria");
            txtCompra.setError("Precio compra obligatorio");
            rpta=false;
        }else if (tienda.equals("") && venta.equals("")) {
            txtTienda.setError("Tienda obligatoria");
            txtVenta.setError("Precio venta obligatorio");
            rpta = false;
        }else{
            rpta=true;
            registrar();
        }
        return rpta;
    }
}