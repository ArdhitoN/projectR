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
public class WishlistUserResponse {
    private String name;
    private Integer wishlistId;
    private Date createdDate;
    private Integer foodId;

    public static WishlistUserResponse fromWishlist(Wishlist wishlist) {
        return WishlistUserResponse.builder()
                .name(wishlist.getName())
                .wishlistId(wishlist.getId())
                .createdDate(wishlist.getCreatedDate())
                .foodId(wishlist.getFoodId())
                .build();
    }
}
