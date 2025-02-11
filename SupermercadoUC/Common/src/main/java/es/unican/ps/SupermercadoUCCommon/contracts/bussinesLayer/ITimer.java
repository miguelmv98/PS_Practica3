package es.unican.ps.SupermercadoUCCommon.contracts.bussinesLayer;

import es.unican.ps.SupermercadoUCCommon.exceptions.DataAccessException;

public interface ITimer {
    void reiniciarComprasMensuales() throws DataAccessException;
}
