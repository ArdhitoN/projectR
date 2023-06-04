package id.ac.ui.cs.advprog.wishlist.utils.Food;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FoodDTO {
    private Integer id;
    private String name;
    private String category;
    private Integer stock;
    private Integer price;
    private Integer donorId;
}
