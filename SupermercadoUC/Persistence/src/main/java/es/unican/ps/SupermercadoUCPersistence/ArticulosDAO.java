package es.unican.ps.SupermercadoUCPersistence;

import es.unican.ps.SupermercadoUCCommon.contracts.dataLayer.IArticulosDAO;
import es.unican.ps.SupermercadoUCCommon.domain.Articulo;
import es.unican.ps.SupermercadoUCCommon.exceptions.DataAccessException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceUnit;
import jakarta.persistence.Query;

import java.util.List;
@Stateless
public class ArticulosDAO implements IArticulosDAO {

    @PersistenceUnit(unitName="supermercadoPU")
    private EntityManager em;

    @Override
    public Articulo creaArticulo(Articulo articulo) throws DataAccessException {
        try{
           return em.merge(articulo);
        }catch(Exception e){
            throw new DataAccessException("Error creando articulo");
        }
    }

    @Override
    public Articulo modificaArticulo(Articulo articulo) throws DataAccessException {
        try{
            return em.merge(articulo);
        }catch(Exception e){
            throw new DataAccessException("Error modificando articulo");
        }
    }

    @Override
    public Articulo eliminaArticulo(Articulo articulo) throws DataAccessException {
        try{
            em.remove(articulo);
        }catch(Exception e){
            throw new DataAccessException("Error eliminando articulo");
        }
        return articulo;
    }

    @Override
    public List<Articulo> articulos() throws DataAccessException {

        Query q = em.createQuery("select a from Articulo a");
        try{
            return q.getResultList();
        }catch(Exception e){
            throw new DataAccessException("Error accediendo a los articulos");
        }
    }

    @Override
    public Articulo articuloPorNombre(String nombre) throws DataAccessException {

        Query q = em.createQuery("select a from Articulo a where a.nombre = :nombre");
        q.setParameter("nombre", nombre);
        try{
            return (Articulo) q.getSingleResult();
        }catch(Exception e){
            throw new DataAccessException("Error accediendo a los articulos");
        }
    }

    @Override
    public boolean articuloConStock(Articulo articulo, int stock) throws DataAccessException {
        Query q = em.createQuery("select a from Articulo a where a.id = :id and a.stock >= :stock");
        q.setParameter("id", articulo.getId());
        q.setParameter("stock", stock);
        try{
            return  q.getSingleResult()!= null;
        }catch(Exception e){
            throw new DataAccessException("Error consultando stock articulo");
        }
    }
}
