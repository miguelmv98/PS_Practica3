package es.unican.ps.SupermercadoUCPersistence;

import es.unican.ps.SupermercadoUCCommon.contracts.dataLayer.IPedidosDAO;
import es.unican.ps.SupermercadoUCCommon.domain.EstadoPedido;
import es.unican.ps.SupermercadoUCCommon.domain.LineaPedido;
import es.unican.ps.SupermercadoUCCommon.domain.Pedido;
import es.unican.ps.SupermercadoUCCommon.exceptions.DataAccessException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceUnit;
import jakarta.persistence.Query;

@Stateless
public class PedidosDAO implements IPedidosDAO {

    @PersistenceUnit(unitName="supermercadoPU")
    private EntityManager em;

    @Override
    public Pedido creaPedido(Pedido pedido) throws DataAccessException {
        try{
            return em.merge(pedido);
        }catch(Exception e){
            throw new DataAccessException("Error creando pedido");
        }
    }

    @Override
    public Pedido modificaPedido(Pedido pedido) throws DataAccessException {
        try{
            return em.merge(pedido);
        }catch(Exception e){
            throw new DataAccessException("Error modificando pedido");
        }
    }

    @Override
    public Pedido pedidoPendiente() throws DataAccessException {
        Query q = em.createQuery("select a from Pedido a where a.estado = :estado order by a.fecha asc");
        q.setParameter("estado", EstadoPedido.REALIZADO);
        try{
            return (Pedido) q.getSingleResult();
        }catch(Exception e){
            throw new DataAccessException("Error consultando pedido pendiente");
        }
    }

    @Override
    public Pedido pedidoPorReferencia(int referencia) throws DataAccessException {
        Query q = em.createQuery("select a from Pedido a where a.referencia = :referencia");
        q.setParameter("referencia", referencia);
        try{
            return (Pedido) q.getSingleResult();
        }catch(Exception e){
            throw new DataAccessException("Error buscando pedido por referencia");
        }
    }

    @Override
    public LineaPedido creaLineaPedido(LineaPedido lineaPedido) throws DataAccessException {
        try{
            return em.merge(lineaPedido);
        }catch(Exception e){
            throw new DataAccessException("Error creando linea pedido");
        }
    }

    @Override
    public LineaPedido modificaLineaPedido(LineaPedido lineaPedido) throws DataAccessException {
        try{
            return em.merge(lineaPedido);
        }catch(Exception e){
            throw new DataAccessException("Error modificando linea pedido");
        }
    }

    @Override
    public LineaPedido eliminaLineaPedido(LineaPedido lineaPedido) throws DataAccessException {
        try{
            em.remove(lineaPedido);
        }catch(Exception e){
            throw new DataAccessException("Error eliminando linea pedido");
        }
        return lineaPedido;
    }
}
