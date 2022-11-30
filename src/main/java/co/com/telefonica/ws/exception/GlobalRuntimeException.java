package co.com.telefonica.ws.exception;

public class GlobalRuntimeException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public GlobalRuntimeException() { }
    public GlobalRuntimeException(String message) {
        super(message);
    }
}
