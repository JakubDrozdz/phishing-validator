package pl.jakubdrozdz.phishingvalidator.subscriber;

public class SubscriberNotExistingException extends RuntimeException{
    public SubscriberNotExistingException(String message){
        super(message);
    }

    public SubscriberNotExistingException(String message, Throwable errorCause){
        super(message, errorCause);
    }
}
