package br.com.ewerton.api.service.exceptions;

public class DataIntegrityViolationException extends RuntimeException{
    public DataIntegrityViolationException(String message) {
        super(message);
    }
}
