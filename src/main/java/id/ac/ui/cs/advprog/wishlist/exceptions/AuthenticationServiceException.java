package id.ac.ui.cs.advprog.wishlist.exceptions;

public class AuthenticationServiceException extends RuntimeException {
    public AuthenticationServiceException(String errorMessage) {
        super(errorMessage);
    }
}