package es.unican.ps.supermercadoucbussines;

import es.unican.ps.SupermercadoUCCommon.contracts.bussinesLayer.jakarta.*;
import es.unican.ps.SupermercadoUCCommon.contracts.dataLayer.IUsuariosDAO;
import es.unican.ps.SupermercadoUCCommon.exceptions.DataAccessException;
import es.unican.ps.SupermercadoUCCommon.domain.Usuario;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

@Stateless
public class GestionUsuarios implements IGestionUsuariosRemote, IGestionUsuariosLocal {
    @EJB
    private final IUsuariosDAO usuariosDAO;

    public GestionUsuarios(IUsuariosDAO usuariosDAO)
    {
        this.usuariosDAO = usuariosDAO;
    }
    @Override
    public Usuario anhadirUsuario(Usuario usuario) throws DataAccessException {
        return usuariosDAO.creaUsuario(usuario);
    }

    @Override
    public Usuario usuario(String dni) {
        Usuario usuario = null;
        try {
            usuario = usuariosDAO.usuarioPorDni(dni);
        } catch (DataAccessException e) {
            Logger.logErrorAccesoDatos();
        }
        return usuario;
    }
}
