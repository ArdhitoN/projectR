package id.ac.ui.cs.advprog.wishlist.exceptions;

public class WishlistDoesNotExistException extends RuntimeException {
    public WishlistDoesNotExistException(Integer id) {
        super("Wishlist with id " + id + " does not exist");
    }

}
