package id.ac.ui.cs.advprog.wishlist.exceptions;

public class FoodDoesNotExistException extends RuntimeException {

    public FoodDoesNotExistException(Integer id) {
        super("Food with id " + id + " does not exist");
    }


}
