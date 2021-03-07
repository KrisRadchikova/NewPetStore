package by.tms.petstore.resource.exception;

public class InvalidSuppliedException extends RuntimeException{

    public InvalidSuppliedException(){}

    public InvalidSuppliedException(String message){
        super(message);
    }
}
