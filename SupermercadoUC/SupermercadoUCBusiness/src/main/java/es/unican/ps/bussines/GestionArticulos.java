package es.unican.ps.bussines;

import es.unican.ps.common.contracts.bussinesLayer.IConsultaArticulos;
import es.unican.ps.common.contracts.bussinesLayer.IGestionArticulos;
import es.unican.ps.common.contracts.dataLayer.IArticulosDAO;
import es.unican.ps.common.contracts.exceptions.DataAccessException;
import es.unican.ps.common.domain.Articulo;

import java.util.List;

public class GestionArticulos implements IGestionArticulos, IConsultaArticulos {
    private final IArticulosDAO articulosDAO;

    public GestionArticulos(IArticulosDAO articulosDAO) {
        this.articulosDAO = articulosDAO;
    }

    @Override
    public Articulo articulo(String nombre) {
        Articulo articulo = null;
        try {
            articulo = articulosDAO.articuloPorNombre(nombre);
        } catch (DataAccessException e) {
            Logger.logErrorAccesoDatos();
        }
        return articulo;
    }

    @Override
    public Articulo anhadirArticulo(String nombre, int stock, double precio) {
        Articulo articulo = new Articulo();
        articulo.setNombre(nombre);
        articulo.setStock(stock);
        articulo.setPrecio(precio);
        try {
            articulo = articulosDAO.creaArticulo(articulo);
        } catch (DataAccessException e) {
            articulo = null;
            Logger.logErrorAccesoDatos();
        }
        return articulo;
    }

    @Override
    public Articulo modificaArticulo(Articulo articulo) {
        Articulo output = null;
        try {
            output = articulosDAO.modificaArticulo(articulo);
        } catch (DataAccessException e) {
            Logger.logErrorAccesoDatos();
        }
        return output;
    }

    @Override
    public Articulo eliminaArticulo(Articulo articulo) {
        Articulo output = null;
        try {
            output = articulosDAO.eliminaArticulo(articulo);
        } catch (DataAccessException e) {
            Logger.logErrorAccesoDatos();
        }
        return output;
    }

    @Override
    public List<Articulo> articulos() {
        List<Articulo> output = null;
        try {
            output = articulosDAO.articulos();
        } catch (DataAccessException e) {
            Logger.logErrorAccesoDatos();
        }
        return output;
    }

}
