package es.unican.ps.SupermercadoUCCommon.domain;

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
