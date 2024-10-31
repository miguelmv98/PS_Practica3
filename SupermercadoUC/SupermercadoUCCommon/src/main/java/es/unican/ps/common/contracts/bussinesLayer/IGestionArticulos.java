package es.unican.ps.common.contracts.bussinesLayer;

import es.unican.ps.common.domain.Articulo;

import java.util.List;

public interface IGestionArticulos {
    Articulo anhadirArticulo(String nombre, int stock, double precio);
    Articulo modificaArticulo(Articulo articulo);
    Articulo eliminaArticulo(Articulo articulo);
    List<Articulo> articulos();
}

