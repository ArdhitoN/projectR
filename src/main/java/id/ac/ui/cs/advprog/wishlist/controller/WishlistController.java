package id.ac.ui.cs.advprog.wishlist.controller;

import id.ac.ui.cs.advprog.wishlist.dto.wishlist.WishlistAdminResponse;
import id.ac.ui.cs.advprog.wishlist.dto.wishlist.WishlistRequest;
import id.ac.ui.cs.advprog.wishlist.dto.wishlist.WishlistUserResponse;
import id.ac.ui.cs.advprog.wishlist.exceptions.UserNotAConsumerException;
import id.ac.ui.cs.advprog.wishlist.exceptions.UserNotADonorException;
import id.ac.ui.cs.advprog.wishlist.model.Wishlist;
import id.ac.ui.cs.advprog.wishlist.service.WishlistService;
import id.ac.ui.cs.advprog.wishlist.utils.User.Authenticator;
import id.ac.ui.cs.advprog.wishlist.utils.User.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RestController
@RequestMapping("/api/v1/wishlist")
public class WishlistController {

    private final WishlistService wishlistService;

    final RestTemplate restTemplate;

    private final Authenticator authenticator;


    @Autowired
    public WishlistController(WishlistService wishlistService, RestTemplate restTemplate, Authenticator authenticator){
        this.wishlistService = wishlistService;
        this.restTemplate = restTemplate;
        this.authenticator = authenticator;
    }

    @GetMapping("/all-wishlist")
    public ResponseEntity<List<WishlistAdminResponse>> getAllWishlist(@RequestHeader("Authorization") String jwtToken) {
        List<WishlistAdminResponse> response = null;
        validateUserDonor(jwtToken);
        response = wishlistService.findAll();
        return ResponseEntity.ok(response);
    }



    @GetMapping("/my-wishlist")
    public ResponseEntity<List<WishlistUserResponse>> getAllUserWishlist(@RequestHeader("Authorization") String jwtToken) {
        List<WishlistUserResponse> response = null;
        Integer userId =  validateUserConsumer(jwtToken).getId();
        response = wishlistService.findAllByUserIdOrderByCreatedDateDesc(userId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all-wishlist/{wishlistId}")
    public ResponseEntity<Wishlist> getWishlistById(@RequestHeader("Authorization") String jwtToken, @PathVariable Integer wishlistId) {
        Wishlist response = null;
        validateUserDonor(jwtToken);
        response = wishlistService.findById(wishlistId);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/my-wishlist/{wishlistId}")
    public ResponseEntity<Wishlist> getWishlistByUserIdAndWishlistId(@RequestHeader("Authorization") String jwtToken, @PathVariable Integer wishlistId) {
        Wishlist response = null;
        Integer userId =  validateUserConsumer(jwtToken).getId();
        response = wishlistService.findByUserIdAndId(userId, wishlistId);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/my-wishlist/create")
    public ResponseEntity<Wishlist> createWishlist(@RequestHeader("Authorization") String jwtToken,  @RequestBody WishlistRequest wishlistRequest) {
        Wishlist response = null;
        Integer userId = validateUserConsumer(jwtToken).getId();
        response = wishlistService.create(jwtToken, userId, wishlistRequest);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/my-wishlist/update/{wishlistId}")
    public ResponseEntity<Wishlist> updateWishlist(@RequestHeader("Authorization") String jwtToken, @PathVariable Integer wishlistId, @RequestBody WishlistRequest wishlistRequest) {
        Wishlist response = null;
        Integer userId =  validateUserConsumer(jwtToken).getId();
        response = wishlistService.update(jwtToken, userId, wishlistId, wishlistRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/my-wishlist/delete/{wishlistId}")
    public ResponseEntity<String> deleteWishlist(@RequestHeader("Authorization") String jwtToken, @PathVariable Integer wishlistId) {
        validateUserConsumer(jwtToken);
        wishlistService.delete(wishlistId);
        return ResponseEntity.ok(String.format("Deleted Wishlist with id %d", wishlistId));
    }

    public UserDTO validateUserConsumer(String jwtToken){
        UserDTO user = authenticator.getUserInfo(jwtToken);
        if(!user.getRole().equals("USER")){
            throw new UserNotAConsumerException(user.getId());
        }
        return user;
    }

    public UserDTO validateUserDonor(String jwtToken){
        UserDTO user = authenticator.getUserInfo(jwtToken);
        if(!user.getRole().equals("DONOR")){
            throw new UserNotADonorException(user.getId());
        }
        return user;
    }

}
