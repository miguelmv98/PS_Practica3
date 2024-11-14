package es.unican.ps.SupermercadoUCCommon.exceptions;

public class StockInsuficenteException extends RuntimeException {
    public StockInsuficenteException(String message) {
        super(message);
    }
}
