package es.unican.ps.SupermercadoUCCommon.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="USUARIOS")
public class Usuario implements Serializable {
    private String nombre;
    @Id
    private String dni;
    private String email;
    @Column(name = "comp_mes")
    private int comprasMensuales;
    @OneToMany(mappedBy="usuario", fetch = FetchType.EAGER)
    private List<Pedido> pedidos;
}
