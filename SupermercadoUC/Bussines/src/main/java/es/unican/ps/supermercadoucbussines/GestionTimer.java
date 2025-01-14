package es.unican.ps.supermercadoucbussines;

import es.unican.ps.SupermercadoUCCommon.contracts.bussinesLayer.jakarta.*;
import es.unican.ps.SupermercadoUCCommon.contracts.dataLayer.IUsuariosDAO;
import es.unican.ps.SupermercadoUCCommon.exceptions.DataAccessException;
import jakarta.ejb.Stateless;

@Stateless
public class GestionTimer implements ITimerLocal, ITimerRemote {
    private final IUsuariosDAO usuariosDAO;

    public GestionTimer(IUsuariosDAO usuariosDAO) {
        this.usuariosDAO = usuariosDAO;
    }

    @Override
    public boolean reiniciarComprasMensuales() throws DataAccessException {
        return usuariosDAO.reiniciarComprasMensuales();
    }
}
