package es.unican.ps.SupermercadoUCCommon.contracts.dataLayer;

import es.unican.ps.SupermercadoUCCommon.exceptions.DataAccessException;
import es.unican.ps.SupermercadoUCCommon.domain.Usuario;

public interface IUsuariosDAO {
    Usuario creaUsuario(Usuario usuario) throws DataAccessException;
    Usuario modificaUsuario(Usuario usuario) throws DataAccessException;
    Usuario eliminaUsuario(Usuario usuario) throws DataAccessException;
    Usuario usuarioPorDni(String dni) throws DataAccessException;
    boolean reiniciarComprasMensuales() throws DataAccessException;
}
