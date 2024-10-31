package es.unican.ps.common.contracts.bussinesLayer;

import es.unican.ps.common.domain.Usuario;

public interface IGestionUsuarios {
    Usuario anhadirUsuario(String nombre, String dni, String email);
    Usuario usuario(String dni);
}
