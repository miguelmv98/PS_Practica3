package es.unican.ps.supermercadoUCWeb;

import es.unican.ps.SupermercadoUCCommon.domain.Pedido;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;

@Named
@RequestScoped
public class PedidoBean {

    @Inject
    private CarroBean carroBean;

    @Getter
    private Pedido pedido;


   @PostConstruct
    private void init(){
        pedido = carroBean.getPedido();
   }
}

