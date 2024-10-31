package es.unican.ps.common.contracts.dataLayer;

import es.unican.ps.common.contracts.exceptions.DataAccessException;
import es.unican.ps.common.domain.Usuario;

public interface IUsuariosDAO {
    Usuario creaUsuario(Usuario usuario) throws DataAccessException;
    Usuario modificaUsuario(Usuario usuario) throws DataAccessException;
    Usuario eliminaUsuario(Usuario usuario) throws DataAccessException;
    Usuario usuarioPorDni(String dni) throws DataAccessException;
    boolean reiniciarComprasMensuales() throws DataAccessException;
}
