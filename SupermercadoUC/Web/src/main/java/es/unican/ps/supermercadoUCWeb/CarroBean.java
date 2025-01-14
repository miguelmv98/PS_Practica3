package es.unican.ps.supermercadoUCWeb;


import es.unican.ps.SupermercadoUCCommon.domain.LineaPedido;
import es.unican.ps.SupermercadoUCCommon.domain.Articulo;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class CarroBean implements Serializable {
    private List<LineaPedido> listaCarro = new ArrayList<>();
    private int unidades;
    private double totalPrecio;
    private String horaRecogida;

    public void agregarArticulo(Articulo articulo) {
        LineaPedido item = new LineaPedido();
        item.setArticulo(articulo);
        item.setPrecio(articulo.getPrecio());
        item.setCantidad(unidades);
        listaCarro.add(item);
        totalPrecio += item.getPrecioTotal();
    }

    // Getters y Setters
    public List<LineaPedido> getListaCarro() {
        return listaCarro;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public double getTotalPrecio() {
        return totalPrecio;
    }

    public String getHoraRecogida() {
        return horaRecogida;
    }

    public void setHoraRecogida(String horaRecogida) {
        this.horaRecogida = horaRecogida;
    }
}

