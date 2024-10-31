package es.unican.ps.common.domain;

import lombok.*;
import java.util.List;

@Getter
@Setter
public class Usuario {
    private String nombre;
    private String dni;
    private String email;
    private String comprasMensuales;
    private List<Pedido> pedidos;
}
