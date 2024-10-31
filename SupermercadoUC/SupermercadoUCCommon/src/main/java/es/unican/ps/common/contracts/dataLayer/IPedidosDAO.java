package es.unican.ps.common.contracts.dataLayer;

import es.unican.ps.common.contracts.exceptions.DataAccessException;
import es.unican.ps.common.domain.LineaPedido;
import es.unican.ps.common.domain.Pedido;

public interface IPedidosDAO {
    Pedido creaPedido(Pedido pedido) throws DataAccessException;
    Pedido modificaPedido(Pedido pedido) throws DataAccessException;
    Pedido pedidoPendiente() throws DataAccessException;
    Pedido pedidoPorReferencia(int referencia) throws DataAccessException;
    LineaPedido creaLineaPedido(LineaPedido lineaPedido) throws DataAccessException;
    LineaPedido modificaLineaPedido(LineaPedido lineaPedido) throws DataAccessException;
    LineaPedido eliminaLineaPedido(LineaPedido lineaPedido) throws DataAccessException;

}
