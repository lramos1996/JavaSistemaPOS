/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formulario;

/**
 *
 * @author Luis Ramos
 */
public class ConsultaVenta {
    //PRODUCTO","TALLA","COLOR","CANTIDAD","MONTO","VENDEDOR","FECHA","HORA","COMPROBANTE","TIPO VENTA
    String producto,talla,color,cantidad,monto,vendedor,fecha,hora,comprobante,tipoVenta,filas;
    ConsultaVenta(String producto,String talla,String color,String cantidad,String monto,String vendedor,String fecha,String hora,String comprobante,String tipoVenta,String filas){
        this.producto=producto;
        this.talla=talla;
        this.color=color;
        this.cantidad=cantidad;
        this.monto=monto;
        this.vendedor=vendedor;
        this.fecha=fecha;
        this.hora=hora;       
        this.comprobante=comprobante;
        this.tipoVenta=tipoVenta; 
        this.filas=filas; 
    }

    public String getFilas() {
        return filas;
    }

    public void setFilas(String filas) {
        this.filas = filas;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getComprobante() {
        return comprobante;
    }

    public void setComprobante(String comprobante) {
        this.comprobante = comprobante;
    }

    public String getTipoVenta() {
        return tipoVenta;
    }

    public void setTipoVenta(String tipoVenta) {
        this.tipoVenta = tipoVenta;
    }
    
}
