package es.unican.ps.supermercadoUCWeb;

import es.unican.ps.SupermercadoUCCommon.domain.Articulo;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.util.List;

@Named
@RequestScoped
public class ArticuloBean {
    private List<Articulo> listaArticulos;
    private Articulo articuloSeleccionado;


    // Simulación de datos
    public ArticuloBean() {
        Articulo articulo = new Articulo();
        articulo.setNombre("Patatas (3 kg)");
        articulo.setPrecio(2.0);
        listaArticulos = List.of(articulo);
    }

    // Getters y Setters
    public List<Articulo> getListaArticulos() {
        return listaArticulos;
    }

    public Articulo getArticuloSeleccionado() {
        return articuloSeleccionado;
    }

    public void setArticuloSeleccionado(Articulo articuloSeleccionado) {
        this.articuloSeleccionado = articuloSeleccionado;
    }
}
