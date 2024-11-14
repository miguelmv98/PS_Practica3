package es.unican.ps.SupermercadoUCCommon.contracts.bussinesLayer;

import es.unican.ps.SupermercadoUCCommon.domain.Usuario;
import es.unican.ps.SupermercadoUCCommon.exceptions.DataAccessException;

public interface IGestionUsuarios {
    Usuario anhadirUsuario(Usuario usuario) throws DataAccessException;
    Usuario usuario(String dni);
}
