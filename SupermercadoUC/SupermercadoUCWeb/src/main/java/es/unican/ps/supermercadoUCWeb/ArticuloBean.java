package es.unican.ps.supermercadoUCWeb;

import es.unican.ps.SupermercadoUCCommon.contracts.bussinesLayer.jakarta.IGestionArticulosLocal;
import es.unican.ps.SupermercadoUCCommon.contracts.bussinesLayer.jakarta.IGestionCarritoLocal;
import es.unican.ps.SupermercadoUCCommon.domain.Articulo;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class ArticuloBean implements Serializable {
    @EJB
    private IGestionCarritoLocal gestionCarrito;
    @EJB
    private IGestionArticulosLocal gestionArticulos;
    @Getter
    @Setter
    private List<Articulo> listaArticulos;
    @Getter
    @Setter
    private Articulo articuloSeleccionado;
    @Getter
    @Setter
    private int unidades;

    @PostConstruct
    public void init() {
        listaArticulos = gestionArticulos.articulos();
    }

    public String agregarArticulo(Articulo articulo){
        setArticuloSeleccionado(articulo);
        return "articuloSeleccionado.xhtml";
    }
    public String agregarArticuloCarrito(){
        gestionCarrito.incluyeArticulo(articuloSeleccionado,unidades);
        return "carro.xhtml";
    }
}
