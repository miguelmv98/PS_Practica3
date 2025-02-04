package es.unican.ps.SupermercadoUCPersistence;

import es.unican.ps.SupermercadoUCCommon.contracts.dataLayer.IArticulosDAO;
import es.unican.ps.SupermercadoUCCommon.domain.Articulo;
import es.unican.ps.SupermercadoUCCommon.exceptions.DataAccessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ArticulosDAOTest {

    @InjectMocks
    private ArticulosDAO sut;

    @Mock
    private EntityManager em;

    @Mock
    private Query query;

    @BeforeEach
    public void setUp() {
        sut = new ArticulosDAO();
        MockitoAnnotations.openMocks(this);
        when(em.createQuery(anyString())).thenReturn(query);
    }

    @Test
    public void testArticuloConStock_ExisteConStockSuficiente() throws DataAccessException {
        Articulo articulo = new Articulo();
        articulo.setId(10);
        int stock = 10;
        //Simulamos que la query devuelve articulo
        when(query.getSingleResult()).thenReturn(articulo);

        boolean resultado = sut.articuloConStock(articulo, stock);

        assertTrue(resultado);

        verify(query).setParameter("id", 10);
        verify(query).setParameter("stock", stock);
    }

    @Test
    public void testArticuloConStock_NoExisteConStockSuficiente() throws DataAccessException {
        Articulo articulo = new Articulo();
        articulo.setId(5);
        int stock = 5;
        // Simulamos que el query devuelve null
        when(query.getSingleResult()).thenReturn(null);

        boolean resultado = sut.articuloConStock(articulo, stock);

        assertFalse(resultado);

        verify(query).setParameter("id", 5);
        verify(query).setParameter("stock", stock);
    }

    @Test
    public void testArticuloConStock_ExcepcionEnQuery() {
        Articulo articulo = new Articulo();
        articulo.setId(20);
        int stock = 20;

        // Simulamos que el query lanza una excepción
        when(query.getSingleResult()).thenThrow(new RuntimeException("Error en BD"));

        DataAccessException exception = assertThrows(DataAccessException.class, () -> {
            sut.articuloConStock(articulo, stock);
        });
        assertEquals("Error consultando stock articulo", exception.getMessage());
    }
}
