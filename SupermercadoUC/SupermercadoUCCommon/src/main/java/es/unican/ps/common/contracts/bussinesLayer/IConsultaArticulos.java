package es.unican.ps.common.contracts.bussinesLayer;

import es.unican.ps.common.domain.Articulo;

import java.util.List;

public interface IConsultaArticulos {
    List<Articulo> articulos();
    Articulo articulo(String nombre);
}
