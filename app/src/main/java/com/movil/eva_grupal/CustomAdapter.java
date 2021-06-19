package com.movil.eva_grupal;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.movil.eva_grupal.dao.TelevisorDAO;
import com.movil.eva_grupal.entity.Televisor;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Context context;
    private List<Televisor> listaTelevisor = new ArrayList<>();

    public CustomAdapter(Context context,List<Televisor> listaTelevisor){
        this.context = context;
        this.listaTelevisor = listaTelevisor;
    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View vista = inflater.inflate(R.layout.fila,parent,false);
        return new MyViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {
        holder.fila_marca_modelo.setText(listaTelevisor.get(position).getMarca()+" "+listaTelevisor.get(position).getModelo());
        holder.fila_tienda.setText(listaTelevisor.get(position).getTienda()+"");
        holder.fila_compra_venta.setText(listaTelevisor.get(position).getPrecio_compra()+" "+listaTelevisor.get(position).getPrecio_venta()+" ");
        holder.fila_escripcion.setText(listaTelevisor.get(position).getDescripcion()+"");
        holder.btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FormularioCelulares.class);
                intent.putExtra("varId",listaTelevisor.get(position).getId());
                intent.putExtra("varMarca",listaTelevisor.get(position).getMarca());
                intent.putExtra("varModelo",listaTelevisor.get(position).getModelo());
                intent.putExtra("varTienda",listaTelevisor.get(position).getTienda());
                intent.putExtra("varCompra",listaTelevisor.get(position).getPrecio_compra());
                intent.putExtra("varVenta",listaTelevisor.get(position).getPrecio_venta());
                intent.putExtra("varDescripcion",listaTelevisor.get(position).getDescripcion());
                context.startActivity(intent);
            }
        });
        holder.btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ventana = new AlertDialog.Builder(context);
                ventana.setTitle("Eliminar: ");
                ventana.setMessage("¿Desea eliminar el televisor: "+listaTelevisor.get(position).getMarca()+"?");
                ventana.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TelevisorDAO dao = new TelevisorDAO(context);
                        dao.abrirBD();
                        String mensaje = dao.eliminarTelevisor(listaTelevisor.get(position).getId());
                        AlertDialog.Builder v = new AlertDialog.Builder(context);
                        v.setTitle("Mensaje Informativo");
                        v.setMessage(mensaje);
                        v.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(context,MainActivity.class);
                                context.startActivity(intent);
                            }
                        });
                        v.create().show();
                    }
                });
                ventana.setNegativeButton("No",null);
                ventana.create().show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaTelevisor.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView fila_marca_modelo,fila_tienda,fila_compra_venta,fila_escripcion;
        ImageButton btnEditar,btnEliminar;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            fila_marca_modelo = itemView.findViewById(R.id.fila_marca_modelo);
            fila_tienda = itemView.findViewById(R.id.fila_tienda);
            fila_compra_venta = itemView.findViewById(R.id.fila_compra_venta);
            fila_escripcion = itemView.findViewById(R.id.fila_descripcion);
            btnEditar = itemView.findViewById(R.id.btnEditar);
            btnEliminar = itemView.findViewById(R.id.btnEliminar);
        }
    }
}
