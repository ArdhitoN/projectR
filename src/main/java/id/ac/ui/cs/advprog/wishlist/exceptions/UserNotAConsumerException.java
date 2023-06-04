package id.ac.ui.cs.advprog.wishlist.exceptions;

public class UserNotAConsumerException extends RuntimeException{
    public UserNotAConsumerException(Integer id) {
        super("User with id " + id + " is not a consumer");
    }
}
