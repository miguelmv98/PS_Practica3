package es.unican.ps.common.contracts.bussinesLayer;

import es.unican.ps.common.domain.Articulo;
import es.unican.ps.common.domain.LineaPedido;
import es.unican.ps.common.domain.Pedido;

import java.util.Date;

public interface IGestionCarrito {
    LineaPedido inlcuyeArticulo(Articulo articulo, int cantidad);
    Pedido realizaPedido(Date horaRecogida, String dni);
}
