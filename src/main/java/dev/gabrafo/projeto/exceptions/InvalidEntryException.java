package dev.gabrafo.projeto.exceptions;

public class InvalidEntryException extends RuntimeException {
    public InvalidEntryException(String message) {
        super(message);
    }
}
