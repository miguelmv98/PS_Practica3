package es.unican.ps.bussines;

import es.unican.ps.common.contracts.bussinesLayer.IGestionCarrito;
import es.unican.ps.common.contracts.bussinesLayer.IGestionPedidos;
import es.unican.ps.common.contracts.dataLayer.IArticulosDAO;
import es.unican.ps.common.contracts.dataLayer.IPedidosDAO;
import es.unican.ps.common.contracts.dataLayer.IUsuariosDAO;
import es.unican.ps.common.contracts.exceptions.DataAccessException;
import es.unican.ps.common.domain.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GestionPedidos implements IGestionPedidos, IGestionCarrito {
    private final IArticulosDAO articuloDAO;
    private final IPedidosDAO pedidosDAO;
    private final IUsuariosDAO usuariosDAO;

    private final List<LineaPedido> articulosPedido;

    public GestionPedidos(IArticulosDAO articuloDAO, IPedidosDAO pedidosDAO, IUsuariosDAO usuariosDAO) {
        this.articuloDAO = articuloDAO;
        this.pedidosDAO = pedidosDAO;
        this.usuariosDAO = usuariosDAO;
        this.articulosPedido = new ArrayList<LineaPedido>();
    }

    @Override
    public LineaPedido inlcuyeArticulo(Articulo articulo, int cantidad) {
        LineaPedido lineaPedido = new LineaPedido();
        lineaPedido.setCantidad(cantidad);
        lineaPedido.setArticulo(articulo);
        lineaPedido.setPrecio(articulo.getPrecio()*cantidad);
        try {
            lineaPedido = pedidosDAO.creaLineaPedido(lineaPedido);
        } catch (DataAccessException e) {
            Logger.logErrorAccesoDatos();
            lineaPedido = null;
        }
        if(lineaPedido != null) {
            articulosPedido.add(lineaPedido);
        }
        return lineaPedido;

    }

    @Override
    public Pedido realizaPedido(Date horaRecogida, String dni) {
        Usuario usuario = null;
        try {
            usuario = usuariosDAO.usuarioPorDni(dni);
        } catch (DataAccessException e) {
            Logger.logErrorAccesoDatos();
            return null;
        }
        Pedido pedido = new Pedido();
        pedido.setHoraRecogida(horaRecogida);
        pedido.setFecha(Date.from(Instant.now()));
        pedido.setEstado(EstadoPedido.REALIZADO);
        pedido.setLineas(articulosPedido);
        pedido.setUsuario(usuario);

        usuario.getPedidos().add(pedido);
        try {
            pedido = pedidosDAO.creaPedido(pedido);
        } catch (DataAccessException e) {
            Logger.logErrorAccesoDatos();
            pedido = null;
        }
        return pedido;
    }

    @Override
    public Pedido procesarPedido() {
        Pedido pedido = null;
        try {
            pedido = pedidosDAO.pedidoPendiente();
        } catch (DataAccessException e) {
            Logger.logErrorAccesoDatos();
        }
        if(pedido != null){
            pedido.setEstado(EstadoPedido.PROCESADO);
            try {
                pedido = pedidosDAO.modificaPedido(pedido);
            } catch (DataAccessException e) {
                Logger.logErrorAccesoDatos();
            }
        }
        return pedido;
    }

    @Override
    public double entregarPedido(int referenciaPedido, String dni) {
        double output = 0.0;
        Pedido pedido = null;
        Usuario usuario = null;
        try {
            pedido = pedidosDAO.pedidoPorReferencia(referenciaPedido);
            if(pedido != null && usuario != null && pedido.getUsuario().getDni().equals(dni)) {
                output = pedido.getPrecioTotal();
                pedido.setEstado(EstadoPedido.ENTREGADO);
                pedidosDAO.modificaPedido(pedido);
            }
        } catch (DataAccessException e) {
            Logger.logErrorAccesoDatos();
        }

        return output;
    }
}
