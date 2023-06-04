package id.ac.ui.cs.advprog.wishlist.exceptions;

public class UserDoesNotExistException extends RuntimeException {
    public UserDoesNotExistException(Integer id) {
        super("User with id " + id + " does not exist");
    }
}
