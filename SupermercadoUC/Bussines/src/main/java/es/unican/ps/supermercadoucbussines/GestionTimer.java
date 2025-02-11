package es.unican.ps.supermercadoucbussines;

import es.unican.ps.SupermercadoUCCommon.contracts.bussinesLayer.jakarta.*;
import es.unican.ps.SupermercadoUCCommon.contracts.dataLayer.jakarta.IUsuariosDAOLocal;
import es.unican.ps.SupermercadoUCCommon.exceptions.DataAccessException;
import jakarta.ejb.EJB;
import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;

@Singleton
public class GestionTimer implements ITimerLocal, ITimerRemote {
    @EJB
    private IUsuariosDAOLocal usuariosDAO;

    @Schedule(dayOfMonth = "1")
    @Override
    public void reiniciarComprasMensuales() throws DataAccessException {
        usuariosDAO.reiniciarComprasMensuales();
    }
}
