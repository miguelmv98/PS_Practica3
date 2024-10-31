package es.unican.ps.common.contracts.bussinesLayer;

import es.unican.ps.common.domain.Pedido;

public interface IGestionPedidos {
    Pedido procesarPedido();
    double entregarPedido(int referenciaPedido, String dni);
}
