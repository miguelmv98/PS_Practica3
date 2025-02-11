package es.unican.ps.SupermercadoUCPersistence;

import es.unican.ps.SupermercadoUCCommon.contracts.dataLayer.IUsuariosDAO;
import es.unican.ps.SupermercadoUCCommon.contracts.dataLayer.jakarta.IUsuariosDAOLocal;
import es.unican.ps.SupermercadoUCCommon.contracts.dataLayer.jakarta.IUsuariosDAORemote;
import es.unican.ps.SupermercadoUCCommon.domain.Usuario;
import es.unican.ps.SupermercadoUCCommon.exceptions.DataAccessException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceUnit;
import jakarta.persistence.Query;

@Stateless
public class UsuariosDAO implements IUsuariosDAOLocal, IUsuariosDAORemote {

    @PersistenceUnit(unitName="supermercadoPU")
    private EntityManager em;

    @Override
    public Usuario creaUsuario(Usuario usuario) throws DataAccessException {
        try{
            return em.merge(usuario);
        }catch(Exception e){
            throw new DataAccessException("Error creando usuario");
        }
    }

    @Override
    public Usuario modificaUsuario(Usuario usuario) throws DataAccessException {
        try{
            return em.merge(usuario);
        }catch(Exception e){
            throw new DataAccessException("Error modificando usuario");
        }
    }

    @Override
    public Usuario eliminaUsuario(Usuario usuario) throws DataAccessException {
        try{
            em.remove(usuario);
        }catch(Exception e){
            throw new DataAccessException("Error eliminando usuario");
        }
        return usuario;
    }

    @Override
    public Usuario usuarioPorDni(String dni) throws DataAccessException {
        Query q = em.createQuery("select u from Usuario u where u.dni = :dni");
        q.setParameter("dni", dni);
        try{
            return (Usuario) q.getSingleResult();
        }catch(Exception e){
            throw new DataAccessException("Error buscando usuario por dni");
        }
    }

    @Override
    public boolean reiniciarComprasMensuales() throws DataAccessException {
        Query q = em.createQuery("update Usuario u set u.comprasMensuales = 0");
        try{
            q.executeUpdate();
        }catch(Exception e){
            throw new DataAccessException("Error actualizando compras mensuales");
        }
        return true;
    }
}
