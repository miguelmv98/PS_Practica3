package es.unican.ps.supermercadoucbussines;

import es.unican.ps.SupermercadoUCCommon.contracts.bussinesLayer.ITimer;
import es.unican.ps.SupermercadoUCCommon.contracts.dataLayer.IUsuariosDAO;
import es.unican.ps.SupermercadoUCCommon.exceptions.DataAccessException;

public class GestionTimer implements ITimer {
    private final IUsuariosDAO usuariosDAO;

    public GestionTimer(IUsuariosDAO usuariosDAO) {
        this.usuariosDAO = usuariosDAO;
    }

    @Override
    public boolean reiniciarComprasMensuales() throws DataAccessException {
        return usuariosDAO.reiniciarComprasMensuales();
    }
}
