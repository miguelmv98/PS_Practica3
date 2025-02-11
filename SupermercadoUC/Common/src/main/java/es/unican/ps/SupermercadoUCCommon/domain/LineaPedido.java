package es.unican.ps.SupermercadoUCCommon.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "LINEAS")
public class LineaPedido implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "art_ref")
    private Articulo articulo;
    private int cantidad;
    private double precio;

    public double getPrecioTotal(){
        return precio*cantidad;
    }
}
