package es.unican.ps.supermercadoucbussines;

import es.unican.ps.SupermercadoUCCommon.contracts.dataLayer.IArticulosDAO;
import es.unican.ps.SupermercadoUCCommon.contracts.dataLayer.IPedidosDAO;
import es.unican.ps.SupermercadoUCCommon.contracts.dataLayer.IUsuariosDAO;
import es.unican.ps.SupermercadoUCCommon.domain.Articulo;
import es.unican.ps.SupermercadoUCCommon.domain.Usuario;
import es.unican.ps.SupermercadoUCCommon.exceptions.DataAccessException;
import es.unican.ps.SupermercadoUCCommon.exceptions.StockInsuficenteException;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class GestionPedidosTest {

    @Nested
    @DisplayName("Test for method incluyeArticulo")
    class IncluyeArticulo {
        private GestionPedidos sut;
        private Articulo articuloA;
        private Articulo articuloB;
        private Articulo articuloF;

        @Mock
        IPedidosDAO mockPedidosDAO;
        @Mock
        IArticulosDAO  mockArticulosDAO;
        @Mock
        IUsuariosDAO  mockUsuariosDAO;

        @Captor
        ArgumentCaptor<Articulo> articuloCaptor;

        @BeforeEach
        public void setup(){
            articuloA = new Articulo();
            articuloA.setNombre("Articulo A");
            articuloA.setPrecio(20.0);
            articuloA.setStock(30);

            articuloB = new Articulo();
            articuloB.setNombre("Articulo B");
            articuloB.setPrecio(20.0);
            articuloB.setStock(0);

            articuloF = new Articulo();
            articuloF.setNombre("Articulo F");
            articuloF.setPrecio(20.0);
            articuloF.setStock(10);

            openMocks(this);
            Usuario user = new Usuario();
            sut = new GestionPedidos(mockArticulosDAO,mockPedidosDAO,mockUsuariosDAO,user);

            try {
                when(mockArticulosDAO.articuloConStock(articuloA,10)).thenReturn(true);
                when(mockArticulosDAO.articuloConStock(articuloA,20)).thenReturn(true);
                when(mockArticulosDAO.articuloConStock(articuloA,31)).thenReturn(false);
                when(mockArticulosDAO.articuloConStock(articuloB,10)).thenReturn(false);
                when(mockArticulosDAO.articuloConStock(articuloF,10)).thenThrow( new DataAccessException());
            } catch (DataAccessException e) {
                throw new RuntimeException(e);
            }

        }
        @Test
        public void anhadeArticuloNuevo(){
            //PRE

            //CASE
            assertDoesNotThrow(() -> sut.incluyeArticulo(articuloA,10));
            //ASSERT
            assertEquals(1,sut.getArticulosPedido().size());
            assertEquals("Articulo A",sut.getArticulosPedido().getFirst().getArticulo().getNombre());
            assertEquals(10,sut.getArticulosPedido().getFirst().getCantidad());
            try {
                verify(mockArticulosDAO).articuloConStock(articuloCaptor.capture(), eq(10));
                assertEquals("Articulo A", articuloCaptor.getValue().getNombre());
            } catch (DataAccessException ignored) {
            }

        }

        @Test
        public void anhadeArticuloDuplicado(){
            //PRE
            assertDoesNotThrow(() -> sut.incluyeArticulo(articuloA,10));
            //CASE
            assertDoesNotThrow(() -> sut.incluyeArticulo(articuloA,10));
            //ASSERT
            assertEquals(1,sut.getArticulosPedido().size());
            assertEquals("Articulo A",sut.getArticulosPedido().getFirst().getArticulo().getNombre());
            assertEquals(20,sut.getArticulosPedido().getFirst().getCantidad());
            try {
                verify(mockArticulosDAO,times(2)).articuloConStock(articuloCaptor.capture(),anyInt());
                assertEquals("Articulo A", articuloCaptor.getAllValues().get(0).getNombre());
                assertEquals("Articulo A", articuloCaptor.getAllValues().get(1).getNombre());
            } catch (DataAccessException ignored) {
            }
        }

        @Test
        public void anhadeArticuloDuplicadoStockInsuficiente(){
            //PRE
            assertDoesNotThrow(() -> {
                sut.incluyeArticulo(articuloA,10);
                sut.incluyeArticulo(articuloA,10);
            });
            //CASE
            assertThrows(StockInsuficenteException.class,() -> sut.incluyeArticulo(articuloA,11));
            //ASSERT
            assertEquals(1,sut.getArticulosPedido().size());
            assertEquals("Articulo A",sut.getArticulosPedido().getFirst().getArticulo().getNombre());
            assertEquals(20,sut.getArticulosPedido().getFirst().getCantidad());

            try {
                verify(mockArticulosDAO,times(3)).articuloConStock(articuloCaptor.capture(),anyInt());
                assertEquals("Articulo A", articuloCaptor.getAllValues().get(0).getNombre());
                assertEquals("Articulo A", articuloCaptor.getAllValues().get(1).getNombre());
                assertEquals("Articulo A", articuloCaptor.getAllValues().get(2).getNombre());
            } catch (DataAccessException ignored) {
            }
        }

        @Test
        public void anhadeArticuloNuevoStockInsuficiente(){
            //PRE
            assertThrows(StockInsuficenteException.class,() -> sut.incluyeArticulo(articuloB,10));
            //ASSERT
            assertEquals(0,sut.getArticulosPedido().size());
            try {
                verify(mockArticulosDAO).articuloConStock(articuloCaptor.capture(),eq(10));
                assertEquals("Articulo B", articuloCaptor.getValue().getNombre());
            } catch (DataAccessException ignored) {
            }
        }

        @Test
        public void anhadeArticuloNuevoErrorDatabase(){
            //PRE

            //CASE
            assertThrows(DataAccessException.class,() -> sut.incluyeArticulo(articuloF,10));
            //ASSERT
            assertEquals(0,sut.getArticulosPedido().size());
            try {
                verify(mockArticulosDAO).articuloConStock(articuloCaptor.capture(),eq(10));
                assertEquals("Articulo F", articuloCaptor.getValue().getNombre());
            } catch (DataAccessException ignored) {
            }
        }
    }

}
