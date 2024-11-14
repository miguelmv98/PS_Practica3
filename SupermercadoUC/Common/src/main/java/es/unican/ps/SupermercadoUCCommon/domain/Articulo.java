package es.unican.ps.SupermercadoUCCommon.domain;

import lombok.*;

@Getter
@Setter
public class Articulo {
    private String nombre;
    private int stock;
    private double precio;
}
