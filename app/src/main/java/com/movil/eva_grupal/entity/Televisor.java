package com.movil.eva_grupal.entity;

public class Televisor {
    private int id;
    private String marca;
    private String modelo;
    private String tienda;
    private double precio_compra;
    private double precio_venta;
    private String descripcion;

    public Televisor(String marca, String modelo, String tienda, double precio_compra, double precio_venta, String descripcion) {
        this.marca = marca;
        this.modelo = modelo;
        this.tienda = tienda;
        this.precio_compra = precio_compra;
        this.precio_venta = precio_venta;
        this.descripcion = descripcion;
    }

    public Televisor(int id, String marca, String modelo, String tienda, double precio_compra, double precio_venta, String descripcion) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.tienda = tienda;
        this.precio_compra = precio_compra;
        this.precio_venta = precio_venta;
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTienda() {
        return tienda;
    }

    public void setTienda(String tienda) {
        this.tienda = tienda;
    }

    public double getPrecio_compra() {
        return precio_compra;
    }

    public void setPrecio_compra(double precio_compra) {
        this.precio_compra = precio_compra;
    }

    public double getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(double precio_venta) {
        this.precio_venta = precio_venta;
    }
}
