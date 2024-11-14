package es.unican.ps.SupermercadoUCCommon.exceptions;

public class PedidoInexistenteException extends RuntimeException {
    public PedidoInexistenteException(String message) {
        super(message);
    }
}
