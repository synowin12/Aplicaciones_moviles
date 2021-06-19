package com.movil.eva_grupal.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.movil.eva_grupal.entity.Televisor;
import com.movil.eva_grupal.util.Constantes;
import com.movil.eva_grupal.util.TelevisorBD;

import java.util.ArrayList;
import java.util.List;

public class TelevisorDAO {
    private TelevisorBD televisorBD;
    private SQLiteDatabase db;
    private Context context;


    public TelevisorDAO(Context context){
        televisorBD = new TelevisorBD(context);
        this.context = context;
    }

    public void abrirBD(){
        db = televisorBD.getWritableDatabase();
    }

    public String registrarTelevisor(Televisor televisor){
        String mensaje = "";
        try{
            ContentValues valores = new ContentValues();
            valores.put("marca",televisor.getMarca());
            valores.put("modelo",televisor.getModelo());
            valores.put("tienda",televisor.getTienda());
            valores.put("precio_compra",televisor.getPrecio_compra());
            valores.put("precio_venta",televisor.getPrecio_venta());
            valores.put("descripcion",televisor.getDescripcion());
            long resultado = db.insert(Constantes.NOMBRE_TABLA,null,valores);
            if(resultado == -1){
               mensaje = "Error al insertar";
            }else{
                mensaje = "Se registró correctamente";
            }
        }catch (Exception e){
            mensaje = e.getMessage();
        }
        return mensaje;
    }

    public String editarTelevisor(Televisor televisor){

        String mensaje = "";
        try {
            ContentValues valores = new ContentValues();
            valores.put("marca",televisor.getMarca());
            valores.put("modelo",televisor.getModelo());
            valores.put("tienda",televisor.getTienda());
            valores.put("precio_compra",televisor.getPrecio_compra());
            valores.put("precio_venta",televisor.getPrecio_venta());
            valores.put("descripcion",televisor.getDescripcion());
            long resultado = db.update(Constantes.NOMBRE_TABLA,valores,"id="+televisor.getId(),null);
            if(resultado == -1){
                mensaje = "Error al actualizar";
            }else{
                mensaje = "Se actualizó correctamente";
            }
        }catch (Exception e){
            mensaje = e.getMessage();
        }
        return mensaje;
    }

    public List<Televisor> finAllTelevisor(){
        List<Televisor> listaTelevisor = new ArrayList<>();
        try {
            Cursor c = db.rawQuery("SELECT * FROM "+Constantes.NOMBRE_TABLA,null);
            while(c.moveToNext()){
                listaTelevisor.add(new Televisor(
                        c.getInt(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3),
                        c.getDouble(4),
                        c.getDouble(5),
                        c.getString(6)
                ));
            }
        }catch (Exception e){
            Log.d("=>",e.getMessage());
        }
        return listaTelevisor;
    }
    public String eliminarTelevisor(int id){
        String mensaje="";
        try {
            long resultado = db.delete(Constantes.NOMBRE_TABLA,"id="+id,null);
            if(resultado == -1){
                mensaje = "Error al eliminar";
            }else{
                mensaje = "Se eliminó correctamente el televisor";
            }
        }catch (Exception e){
            mensaje = e.getMessage();
        }
        return mensaje;
    }
}
