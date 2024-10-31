package es.unican.ps.bussines;

import es.unican.ps.common.contracts.bussinesLayer.IGestionUsuarios;
import es.unican.ps.common.contracts.dataLayer.IUsuariosDAO;
import es.unican.ps.common.contracts.exceptions.DataAccessException;
import es.unican.ps.common.domain.Usuario;

public class GestionUsuarios implements IGestionUsuarios {
    private final IUsuariosDAO usuariosDAO;

    public GestionUsuarios(IUsuariosDAO usuariosDAO)
    {
        this.usuariosDAO = usuariosDAO;
    }
    @Override
    public Usuario anhadirUsuario(String nombre, String dni, String email) {
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setDni(dni);
        usuario.setEmail(email);
        try {
            usuariosDAO.creaUsuario(usuario);
        } catch (DataAccessException e) {
            Logger.logErrorAccesoDatos();
            usuario = null;
        }
        return usuario;
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
