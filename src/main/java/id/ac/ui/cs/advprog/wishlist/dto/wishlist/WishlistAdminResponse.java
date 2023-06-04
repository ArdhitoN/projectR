package id.ac.ui.cs.advprog.wishlist.dto.wishlist;

import id.ac.ui.cs.advprog.wishlist.model.Wishlist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WishlistAdminResponse {
    private String name;
    private Integer userId;
    private Integer wishlistId;
    private Date createdDate;
    private Integer foodId;

    public static WishlistAdminResponse fromWishlist(Wishlist wishlist) {
        return WishlistAdminResponse.builder()
                .name(wishlist.getName())
                .userId(wishlist.getUserId())
                .wishlistId(wishlist.getId())
                .foodId(wishlist.getFoodId())
                .createdDate(wishlist.getCreatedDate())
                .build();
    }

}
