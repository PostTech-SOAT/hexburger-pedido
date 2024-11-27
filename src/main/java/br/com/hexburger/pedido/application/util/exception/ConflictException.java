package br.com.hexburger.pedido.application.util.exception;

public class ConflictException extends RuntimeException {

    public ConflictException(String message) {
        super(message, null, false, false);
    }
}
