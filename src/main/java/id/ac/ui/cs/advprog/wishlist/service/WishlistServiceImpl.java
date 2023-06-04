package id.ac.ui.cs.advprog.wishlist.service;

import id.ac.ui.cs.advprog.wishlist.dto.wishlist.WishlistAdminResponse;
import id.ac.ui.cs.advprog.wishlist.dto.wishlist.WishlistRequest;
import id.ac.ui.cs.advprog.wishlist.dto.wishlist.WishlistUserResponse;
import id.ac.ui.cs.advprog.wishlist.exceptions.FoodDoesNotExistException;
import id.ac.ui.cs.advprog.wishlist.exceptions.UserDoesNotExistException;
import id.ac.ui.cs.advprog.wishlist.exceptions.WishlistDoesNotExistException;
import id.ac.ui.cs.advprog.wishlist.model.Wishlist;
import id.ac.ui.cs.advprog.wishlist.repository.WishlistRepository;
import id.ac.ui.cs.advprog.wishlist.utils.Food.FoodDTO;
import id.ac.ui.cs.advprog.wishlist.utils.Food.FoodUtil;
import id.ac.ui.cs.advprog.wishlist.utils.User.Authenticator;
import id.ac.ui.cs.advprog.wishlist.utils.User.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService{
    private final WishlistRepository wishlistRepository;

    private final Authenticator authenticator;

    private final FoodUtil foodUtil;

    @Override
    public List<WishlistAdminResponse> findAll() {
        return wishlistRepository.findAll()
                .stream()
                .map(WishlistAdminResponse::fromWishlist)
                .collect(Collectors.toList());
    }


    @Override
    public List<WishlistUserResponse> findAllByUserIdOrderByCreatedDateDesc(Integer userId) {
        return wishlistRepository.findAllByUserIdOrderByCreatedDateDesc(userId)
                .stream()
                .map(WishlistUserResponse::fromWishlist)
                .collect(Collectors.toList());
    }


    @Override
    public Wishlist findById(Integer wishlistId) {
        return wishlistRepository.findById(wishlistId)
                .orElseThrow(() -> new WishlistDoesNotExistException(wishlistId));
    }

    @Override
    public Wishlist findByUserIdAndId(Integer userId, Integer wishlistId) {
        return wishlistRepository.findWishlistByUserIdAndId(userId, wishlistId)
                .orElseThrow(() -> new WishlistDoesNotExistException(wishlistId));
    }


    @Override
    public Wishlist create(String jwtToken, Integer userId, WishlistRequest wishlistRequest){
        UserDTO user = authenticator.getUserInfo(jwtToken);
        if (user == null) {
            throw new UserDoesNotExistException(userId);
        }

        FoodDTO food = foodUtil.getFoodInfo(jwtToken, wishlistRequest.getFoodId());
        var wishlist = Wishlist.builder()
                .name(wishlistRequest.getName())
                .userId(userId)
                .foodId(food.getId())
                .createdDate(new Date())
                .build();
        wishlistRepository.save(wishlist);
        return wishlist;
    }

    @Override
    public Wishlist update(String jwtToken, Integer userId, Integer id, WishlistRequest wishlistRequest){
        UserDTO user = authenticator.getUserInfo(jwtToken);
        if (user == null) {
            throw new UserDoesNotExistException(userId);
        }

        Wishlist wishlistInDB = wishlistRepository.findById(id)
                .orElseThrow(() -> new WishlistDoesNotExistException(id));

        FoodDTO food = foodUtil.getFoodInfo(jwtToken, wishlistRequest.getFoodId());
        if (food == null) {
            throw new FoodDoesNotExistException(wishlistRequest.getFoodId());
        }

        Wishlist.builder()
                .id(id)
                .name(wishlistRequest.getName())
                .userId(userId)
                .foodId(food.getId())
                .createdDate(new Date())
                .build();

        wishlistInDB.setName(wishlistRequest.getName());
        wishlistInDB.setFoodId(food.getId());
        wishlistInDB.setCreatedDate(new Date());

        return wishlistRepository.save(wishlistInDB);
    }

    @Override
    public void delete(Integer id){
        if (isWishlistDoesNotExist(id)) {
            throw new WishlistDoesNotExistException(id);
        }
        wishlistRepository.deleteById(id);
    }

    private boolean isWishlistDoesNotExist(Integer id) {
        Optional<Wishlist> wishlistOptional = wishlistRepository.findById(id);
        return wishlistOptional.isEmpty();
    }
}
