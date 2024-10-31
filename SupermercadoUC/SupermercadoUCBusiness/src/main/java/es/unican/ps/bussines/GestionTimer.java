package es.unican.ps.bussines;

import es.unican.ps.common.contracts.bussinesLayer.ITimer;
import es.unican.ps.common.contracts.dataLayer.IUsuariosDAO;
import es.unican.ps.common.contracts.exceptions.DataAccessException;

public class GestionTimer implements ITimer {
    private final IUsuariosDAO usuariosDAO;

    public GestionTimer(IUsuariosDAO usuariosDAO) {
        this.usuariosDAO = usuariosDAO;
    }

    @Override
    public boolean reiniciarComprasMensuales() {
        try {
            return usuariosDAO.reiniciarComprasMensuales();
        } catch (DataAccessException e) {
            Logger.logErrorAccesoDatos();
            return false;
        }
    }
}
