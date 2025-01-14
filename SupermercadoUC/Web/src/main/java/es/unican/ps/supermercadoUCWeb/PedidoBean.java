package es.unican.ps.supermercadoUCWeb;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class PedidoBean {
    private String referencia;

    public PedidoBean() {
        this.referencia = "123456";  // Simulación de una referencia generada
    }

    // Getters
    public String getReferencia() {
        return referencia;
    }
}

