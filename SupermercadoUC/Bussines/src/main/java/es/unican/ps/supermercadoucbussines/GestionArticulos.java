package es.unican.ps.supermercadoucbussines;

import es.unican.ps.SupermercadoUCCommon.contracts.bussinesLayer.IConsultaArticulos;
import es.unican.ps.SupermercadoUCCommon.contracts.bussinesLayer.IGestionArticulos;
import es.unican.ps.SupermercadoUCCommon.contracts.dataLayer.IArticulosDAO;
import es.unican.ps.SupermercadoUCCommon.exceptions.DataAccessException;
import es.unican.ps.SupermercadoUCCommon.domain.Articulo;

import java.util.List;

public class GestionArticulos implements IGestionArticulos, IConsultaArticulos {
    private final IArticulosDAO articulosDAO;

    public GestionArticulos(IArticulosDAO articulosDAO) {
        this.articulosDAO = articulosDAO;
    }

    @Override
    public Articulo articulo(String nombre) throws DataAccessException {
        return articulosDAO.articuloPorNombre(nombre);
    }

    @Override
    public Articulo anhadirArticulo(Articulo articulo) throws DataAccessException {
        return articulosDAO.creaArticulo(articulo);
    }

    @Override
    public Articulo modificaArticulo(Articulo articulo) throws DataAccessException {
        return articulosDAO.modificaArticulo(articulo);
    }

    @Override
    public Articulo eliminaArticulo(Articulo articulo) throws DataAccessException {
        return articulosDAO.eliminaArticulo(articulo);
    }

    @Override
    public List<Articulo> articulos() throws DataAccessException {
        return articulosDAO.articulos();
    }

}
