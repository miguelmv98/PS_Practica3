package es.unican.ps.common.contracts.dataLayer;

import es.unican.ps.common.contracts.exceptions.DataAccessException;
import es.unican.ps.common.domain.Articulo;

import java.util.List;

public interface IArticulosDAO {

    Articulo creaArticulo(Articulo articulo) throws DataAccessException;
    Articulo modificaArticulo(Articulo articulo) throws DataAccessException;
    Articulo eliminaArticulo(Articulo articulo) throws DataAccessException;
    List<Articulo> articulos() throws DataAccessException;
    Articulo articuloPorNombre(String nombre) throws DataAccessException;
    boolean articuloConStock(Articulo articulo, int stock) throws DataAccessException;
}
