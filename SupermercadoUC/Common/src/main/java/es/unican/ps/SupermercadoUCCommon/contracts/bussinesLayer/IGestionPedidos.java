package es.unican.ps.SupermercadoUCCommon.contracts.bussinesLayer;

import es.unican.ps.SupermercadoUCCommon.domain.Pedido;
import es.unican.ps.SupermercadoUCCommon.exceptions.DataAccessException;
import es.unican.ps.SupermercadoUCCommon.exceptions.PedidoInexistenteException;
import es.unican.ps.SupermercadoUCCommon.exceptions.UsuarioInexistenteException;
import es.unican.ps.SupermercadoUCCommon.exceptions.UsuarioPedidoIncorrectoException;

public interface IGestionPedidos {
    Pedido procesarPedido() throws DataAccessException;
    double entregarPedido(int referenciaPedido, String dni) throws DataAccessException, PedidoInexistenteException, UsuarioInexistenteException, UsuarioPedidoIncorrectoException;
}
