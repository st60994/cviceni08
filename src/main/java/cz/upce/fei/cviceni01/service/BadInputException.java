package cz.upce.fei.cviceni01.service;

public class BadInputException extends RuntimeException{
    public BadInputException(String message) {
        super(message);
    }
}
