package es.unican.ps.SupermercadoUCCommon.contracts.bussinesLayer;

import es.unican.ps.SupermercadoUCCommon.domain.Articulo;
import es.unican.ps.SupermercadoUCCommon.exceptions.DataAccessException;

import java.util.List;

public interface IGestionArticulos {
    Articulo anhadirArticulo(Articulo articulo) throws DataAccessException;
    Articulo modificaArticulo(Articulo articulo) throws DataAccessException;
    Articulo eliminaArticulo(Articulo articulo) throws DataAccessException;
    List<Articulo> articulos() throws DataAccessException;
}

