package es.unican.ps.SupermercadoUCCommon.exceptions;

public class UsuarioInexistenteException extends RuntimeException {
    public UsuarioInexistenteException(String message) {
        super(message);
    }
}
