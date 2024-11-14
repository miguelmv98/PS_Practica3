package es.unican.ps.SupermercadoUCCommon.contracts.bussinesLayer;

import es.unican.ps.SupermercadoUCCommon.domain.Articulo;
import es.unican.ps.SupermercadoUCCommon.exceptions.DataAccessException;

import java.util.List;

public interface IConsultaArticulos {
    List<Articulo> articulos() throws DataAccessException;
    Articulo articulo(String nombre) throws DataAccessException;
}
