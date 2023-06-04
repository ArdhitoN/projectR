package id.ac.ui.cs.advprog.wishlist.utils.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Integer id;

    private String name;

    private String email;

    private String address;

    private String phone;

    private String role;

    private Integer balance;

    private boolean active;
}
