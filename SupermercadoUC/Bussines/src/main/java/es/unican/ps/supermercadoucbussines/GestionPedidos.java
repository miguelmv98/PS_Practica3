package es.unican.ps.supermercadoucbussines;

import es.unican.ps.SupermercadoUCCommon.contracts.bussinesLayer.IGestionCarrito;
import es.unican.ps.SupermercadoUCCommon.contracts.bussinesLayer.IGestionPedidos;
import es.unican.ps.SupermercadoUCCommon.contracts.dataLayer.IArticulosDAO;
import es.unican.ps.SupermercadoUCCommon.contracts.dataLayer.IPedidosDAO;
import es.unican.ps.SupermercadoUCCommon.contracts.dataLayer.IUsuariosDAO;
import es.unican.ps.SupermercadoUCCommon.exceptions.*;
import es.unican.ps.SupermercadoUCCommon.domain.*;
import jakarta.ejb.Stateful;
import lombok.Getter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Stateful
public class GestionPedidos implements IGestionPedidos, IGestionCarrito {
    private final IArticulosDAO articulosDAO;
    private final IPedidosDAO pedidosDAO;
    private final IUsuariosDAO usuariosDAO;

    @Getter
    private final List<LineaPedido> articulosPedido;
    private final Usuario usuario;

    public GestionPedidos(IArticulosDAO articulosDAO, IPedidosDAO pedidosDAO, IUsuariosDAO usuariosDAO,Usuario usuario) {
        this.articulosDAO = articulosDAO;
        this.pedidosDAO = pedidosDAO;
        this.usuariosDAO = usuariosDAO;
        this.articulosPedido = new ArrayList<LineaPedido>();
        this.usuario = usuario;
    }

    @Override
    public LineaPedido inlcuyeArticulo(Articulo articulo, int cantidad) throws DataAccessException, StockInsuficenteException {

        LineaPedido lineaPedido = null;
        for (LineaPedido lp : articulosPedido) {
            if(lineaPedido.getArticulo().equals(articulo)){
                lineaPedido = lp;
            }
        }
        if(lineaPedido != null){
            lineaPedido.setCantidad(lineaPedido.getCantidad()+cantidad);
        }else{
            lineaPedido = createLineaPedido(articulo,cantidad);
        }
        if(!articulosDAO.articuloConStock(articulo,cantidad)){
            throw new StockInsuficenteException("No queda stock suficiente de el articulo "+ articulo.getNombre());
        }
        articulosPedido.add(lineaPedido);
        
        return lineaPedido;

    }

    private LineaPedido createLineaPedido(Articulo articulo, int cantidad) {
        LineaPedido output = new LineaPedido();
        output.setCantidad(cantidad);
        output.setArticulo(articulo);
        output.setPrecio(articulo.getPrecio()*cantidad);
        return output;
    }

    @Override
    public Pedido realizaPedido(Date horaRecogida) throws DataAccessException {
        Pedido pedido = createPedido(horaRecogida);
        pedido = pedidosDAO.creaPedido(pedido);
        usuario.getPedidos().add(pedido);
        return pedido;
    }

    private Pedido createPedido(Date horaRecogida) {
        Pedido pedido = new Pedido();
        pedido.setHoraRecogida(horaRecogida);
        pedido.setFecha(Date.from(Instant.now()));
        pedido.setLineas(articulosPedido);
        pedido.setUsuario(usuario);
        return pedido;
    }

    @Override
    public Pedido procesarPedido() throws DataAccessException {
        Pedido pedido = pedidosDAO.pedidoPendiente();
        if(pedido == null) {
            return null;
        }
        pedido.setEstado(EstadoPedido.PROCESADO);
        pedidosDAO.modificaPedido(pedido);
        return pedido;
    }

    @Override
    public double entregarPedido(int referenciaPedido, String dni) throws DataAccessException, PedidoInexistenteException, UsuarioInexistenteException, UsuarioPedidoIncorrectoException {
        Pedido pedido = pedidosDAO.pedidoPorReferencia(referenciaPedido);
        Usuario usuario = usuariosDAO.usuarioPorDni(dni);
        if(pedido == null) {
            throw new PedidoInexistenteException("No existe el pedido con referencia "+ referenciaPedido);
        }
        if(usuario == null) {
            throw new UsuarioInexistenteException("No existe el usuario con dni "+ dni);
        }
        if(pedido.getUsuario().getDni().equals(dni)) {
            throw new UsuarioPedidoIncorrectoException("El pedidio con referencia "+pedido.getReferencia()+" no es del cliente con dni "+usuario.getDni());
        }
        pedido.setEstado(EstadoPedido.ENTREGADO);
        pedidosDAO.modificaPedido(pedido);
        return pedido.getPrecioTotal();
    }
}
