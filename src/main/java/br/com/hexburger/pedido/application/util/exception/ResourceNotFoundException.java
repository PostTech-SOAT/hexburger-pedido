package br.com.hexburger.pedido.application.util.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message, null, false, false);
    }

}
