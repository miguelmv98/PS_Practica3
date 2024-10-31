package es.unican.ps.common.domain;

import lombok.*;

@Getter
@Setter
public class LineaPedido {
    private Articulo articulo;
    private int cantidad;
    private double precio;

    public double getPrecioTotal(){
        return precio*cantidad;
    }
}
