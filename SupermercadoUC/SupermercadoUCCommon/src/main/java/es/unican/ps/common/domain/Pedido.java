package es.unican.ps.common.domain;

import lombok.*;
import java.util.*;

@Getter
@Setter
public class Pedido {
    private List<LineaPedido> lineas;
    private int referencia;
    private Date fecha;
    private Date horaRecogida;
    private EstadoPedido estado;
    private Usuario usuario;

    public double getPrecioTotal(){
        double output = 0.0;
        for(LineaPedido linea : lineas){
            output += linea.getPrecioTotal();
        }
        return output;
    }
}
