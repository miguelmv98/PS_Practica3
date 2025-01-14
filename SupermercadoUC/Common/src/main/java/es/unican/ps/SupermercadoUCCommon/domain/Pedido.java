package es.unican.ps.SupermercadoUCCommon.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "PEDIDOS")
public class Pedido implements Serializable {
    @OneToMany()
    @JoinColumn(name = "ped_ref")
    private List<LineaPedido> lineas;
    @Id
    @Column(name = "ref")
    private int referencia;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name="hora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaRecogida;
    @Enumerated(EnumType.STRING)
    private EstadoPedido estado;
    @ManyToOne
    @JoinColumn(name="usr_ref")
    private Usuario usuario;

    public Pedido() {
        this.estado = EstadoPedido.REALIZADO;
    }

    public double getPrecioTotal(){
        double output = 0.0;
        for(LineaPedido linea : lineas){
            output += linea.getPrecioTotal();
        }
        return output;
    }
}
