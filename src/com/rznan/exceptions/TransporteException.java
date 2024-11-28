package com.rznan.exceptions;

public class TransporteException extends Exception{
    public TransporteException(String message) {
        super(message);
    }

    public TransporteException() {
    }

    public TransporteException( Throwable t) {
        super(t);
    }
}
