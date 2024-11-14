package es.unican.ps.SupermercadoUCCommon.contracts.bussinesLayer;

import es.unican.ps.SupermercadoUCCommon.exceptions.DataAccessException;

public interface ITimer {
    boolean reiniciarComprasMensuales() throws DataAccessException;
}
