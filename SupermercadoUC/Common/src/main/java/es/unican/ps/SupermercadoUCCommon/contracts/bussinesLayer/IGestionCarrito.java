package es.unican.ps.SupermercadoUCCommon.contracts.bussinesLayer;

import es.unican.ps.SupermercadoUCCommon.domain.Articulo;
import es.unican.ps.SupermercadoUCCommon.domain.LineaPedido;
import es.unican.ps.SupermercadoUCCommon.domain.Pedido;
import es.unican.ps.SupermercadoUCCommon.exceptions.DataAccessException;
import es.unican.ps.SupermercadoUCCommon.exceptions.StockInsuficenteException;

import java.util.Date;
import java.util.List;

public interface IGestionCarrito {
    LineaPedido incluyeArticulo(Articulo articulo, int cantidad) throws DataAccessException, StockInsuficenteException;
    Pedido realizaPedido(Date horaRecogida) throws DataAccessException;
    List<LineaPedido> getArticulosPedido();
}
