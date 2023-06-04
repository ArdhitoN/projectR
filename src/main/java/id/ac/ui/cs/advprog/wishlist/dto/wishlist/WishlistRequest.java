package id.ac.ui.cs.advprog.wishlist.dto.wishlist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WishlistRequest {
    private String name;
    private Integer foodId;
}
