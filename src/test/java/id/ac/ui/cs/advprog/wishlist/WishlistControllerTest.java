package id.ac.ui.cs.advprog.wishlist;

import id.ac.ui.cs.advprog.wishlist.controller.WishlistController;
import id.ac.ui.cs.advprog.wishlist.dto.wishlist.WishlistAdminResponse;
import id.ac.ui.cs.advprog.wishlist.dto.wishlist.WishlistRequest;
import id.ac.ui.cs.advprog.wishlist.dto.wishlist.WishlistUserResponse;
import id.ac.ui.cs.advprog.wishlist.model.Wishlist;
import id.ac.ui.cs.advprog.wishlist.service.WishlistService;
import id.ac.ui.cs.advprog.wishlist.utils.User.Authenticator;
import id.ac.ui.cs.advprog.wishlist.utils.User.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class WishlistControllerTest {

    @InjectMocks
    private WishlistController wishlistController;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private WishlistService wishlistService;

    @Mock
    private Authenticator authenticator;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllWishlist() {
        List<WishlistAdminResponse> expected = new ArrayList<>();
        when(wishlistService.findAll()).thenReturn(expected);

        ResponseEntity<List<WishlistAdminResponse>> response = wishlistController.getAllWishlist("testToken");
        assertEquals(expected, response.getBody());
    }

    @Test
    void testGetAllUserWishlist() {
        List<WishlistUserResponse> expected = new ArrayList<>();
        UserDTO user = new UserDTO();
        user.setId(1);
        when(authenticator.getUserInfo(any())).thenReturn(user);
        when(wishlistService.findAllByUserIdOrderByCreatedDateDesc(anyInt())).thenReturn(expected);

        ResponseEntity<List<WishlistUserResponse>> response = wishlistController.getAllUserWishlist("testToken");
        assertEquals(expected, response.getBody());
    }

    @Test
    void testGetWishlistById() {
        // Mock data
        Integer wishlistId = 1;
        Wishlist wishlist = new Wishlist(wishlistId, 1, "Wishlist 1", new Date(), 1);
        Mockito.when(wishlistService.findById(wishlistId)).thenReturn(wishlist);

        // Perform the GET request
        ResponseEntity<Wishlist> response = wishlistController.getWishlistById("token", wishlistId);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(wishlist, response.getBody());
    }


    @Test
    void testGetWishlistByUserIdAndWishlistId() {
        // Mock data
        Integer userId = 1;
        Integer wishlistId = 1;
        Wishlist wishlist = new Wishlist(wishlistId, userId, "Wishlist 1", new Date(), 1);
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userId);
        Mockito.when(authenticator.getUserInfo("token")).thenReturn(userDTO);
        Mockito.when(wishlistService.findByUserIdAndId(userId, wishlistId)).thenReturn(wishlist);

        // Perform the GET request
        ResponseEntity<Wishlist> response = wishlistController.getWishlistByUserIdAndWishlistId("token", wishlistId);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(wishlist, response.getBody());
    }

    @Test
    void testCreateWishlist() {
        // Mock data
        WishlistRequest wishlistRequest = new WishlistRequest("Wishlist 1", 1);
        String jwtToken = "token";
        Integer userId = 1;
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userId);
        Wishlist createdWishlist = new Wishlist(1, userId, "Wishlist 1", new Date(), 1);

        Mockito.when(authenticator.getUserInfo(jwtToken)).thenReturn(userDTO);
        Mockito.when(wishlistService.create(jwtToken, userId, wishlistRequest)).thenReturn(createdWishlist);

        // Perform the POST request
        ResponseEntity<Wishlist> response = wishlistController.createWishlist(jwtToken, wishlistRequest);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(createdWishlist, response.getBody());
    }

    @Test
    void testUpdateWishlist() {
        // Mock data
        WishlistRequest wishlistRequest = new WishlistRequest("Updated Wishlist", 2);
        String jwtToken = "token";
        Integer userId = 1;
        Integer wishlistId = 1;
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userId);
        Wishlist updatedWishlist = new Wishlist(wishlistId, userId, "Updated Wishlist", new Date(), 2);

        Mockito.when(authenticator.getUserInfo(jwtToken)).thenReturn(userDTO);
        Mockito.when(wishlistService.update(jwtToken, userId, wishlistId, wishlistRequest)).thenReturn(updatedWishlist);

        // Perform the PUT request
        ResponseEntity<Wishlist> response = wishlistController.updateWishlist(jwtToken, wishlistId, wishlistRequest);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedWishlist, response.getBody());
    }

    @Test
    void testDeleteWishlist() {
        // Mock data
        Integer wishlistId = 1;

        // Perform the DELETE request
        ResponseEntity<String> response = wishlistController.deleteWishlist("token", wishlistId);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(String.format("Deleted Wishlist with id %d", wishlistId), response.getBody());
    }

}
