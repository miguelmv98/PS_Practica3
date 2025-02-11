package es.unican.ps.supermercadoUCWeb;

import es.unican.ps.SupermercadoUCCommon.contracts.bussinesLayer.jakarta.IGestionUsuariosLocal;
import es.unican.ps.SupermercadoUCCommon.domain.Usuario;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

@Named
@RequestScoped
public class UserBean {

    @EJB
    private IGestionUsuariosLocal gestionUsuarios;
    // Getters and Setters
    @Setter
    @Getter
    private String dni;


    public String logIn() {
        Usuario usr = gestionUsuarios.usuario(dni);
        if (usr != null) {
            return "carro.xhtml";
        } else {
            return "index.xhtml";
        }
    }
    public String signUp(){
            Usuario usr = gestionUsuarios.usuario(dni);
            if(usr == null) {
                Usuario nuevo = new Usuario();
                nuevo.setDni(dni);
                gestionUsuarios.anhadirUsuario(nuevo);
                return "carro.xhtml";
            }else{
                return "index.xhtml";
            }
    }
}
