package id.ac.ui.cs.advprog.wishlist.service;


import id.ac.ui.cs.advprog.wishlist.dto.wishlist.WishlistAdminResponse;
import id.ac.ui.cs.advprog.wishlist.dto.wishlist.WishlistRequest;
import id.ac.ui.cs.advprog.wishlist.dto.wishlist.WishlistUserResponse;
import id.ac.ui.cs.advprog.wishlist.model.Wishlist;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WishlistService {
    List<WishlistAdminResponse> findAll();

    List<WishlistUserResponse> findAllByUserIdOrderByCreatedDateDesc(Integer userId);

    Wishlist findById(Integer id);

    Wishlist findByUserIdAndId(Integer userId, Integer id);

    Wishlist create(String jwtToken, Integer userId, WishlistRequest wishlistRequest);

    Wishlist update(String jwtToken, Integer userId, Integer wishlistId, WishlistRequest wishlistRequest);

    void delete(Integer id);
}
