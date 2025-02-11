package es.unican.ps.supermercadoUCWeb;


import es.unican.ps.SupermercadoUCCommon.contracts.bussinesLayer.jakarta.IGestionCarritoLocal;
import es.unican.ps.SupermercadoUCCommon.domain.LineaPedido;
import es.unican.ps.SupermercadoUCCommon.domain.Pedido;
import jakarta.ejb.EJB;
import jakarta.ejb.PostActivate;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named
@SessionScoped
public class CarroBean implements Serializable {

    @EJB
    private IGestionCarritoLocal gestionCarrito;
    // Getters y Setters
    @Getter
    private List<LineaPedido> listaCarro = new ArrayList<>();

    @Getter
    private double totalPrecio;

    @Getter
    @Setter
    private Date horaRecogida;

    @Getter
    private Pedido pedido;
    public String confirmarPedido(){
        pedido = gestionCarrito.realizaPedido(horaRecogida);
        return "pedido.xhtml";
    }

    @PostActivate
    private void init(){
        listaCarro = gestionCarrito.getArticulosPedido();
        totalPrecio = 0;
        for (int i= 0; i < listaCarro.size() ; i++) {
            totalPrecio += listaCarro.get(i).getPrecioTotal();
        }
    }

}

