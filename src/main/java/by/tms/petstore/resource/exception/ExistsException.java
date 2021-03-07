package by.tms.petstore.resource.exception;

public class ExistsException extends RuntimeException{
    public ExistsException(){}

    public ExistsException(String message){
        super(message);
    }
}
