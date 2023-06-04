package id.ac.ui.cs.advprog.wishlist.exceptions.advice;

import id.ac.ui.cs.advprog.wishlist.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserDoesNotExistException.class)
    public ResponseEntity<String> handleUserDoesNotExistException(UserDoesNotExistException userDoesNotExistException) {
        return new ResponseEntity<>(userDoesNotExistException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(WishlistDoesNotExistException.class)
    public ResponseEntity<String> handleWishlistDoesNotExistException(WishlistDoesNotExistException wishlistDoesNotExistException) {
        return new ResponseEntity<>(wishlistDoesNotExistException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AuthenticationServiceException.class)
    public ResponseEntity<String> handleAuthenticationServiceException(AuthenticationServiceException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserNotAConsumerException.class)
    public ResponseEntity<String> handleUserNotAConsumerException(UserNotAConsumerException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserNotADonorException.class)
    public ResponseEntity<String> handleUserNotADonorException(UserNotADonorException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(FoodDoesNotExistException.class)
    public ResponseEntity<String> handleFoodDoesNotExistException(FoodDoesNotExistException foodDoesNotExistException) {
        return new ResponseEntity<>(foodDoesNotExistException.getMessage(), HttpStatus.NOT_FOUND);
    }

}
