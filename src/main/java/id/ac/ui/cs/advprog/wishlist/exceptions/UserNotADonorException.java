package id.ac.ui.cs.advprog.wishlist.exceptions;

public class UserNotADonorException extends RuntimeException{
    public UserNotADonorException(Integer id) {
        super("User with id " + id + " is not a donor");
    }
}
