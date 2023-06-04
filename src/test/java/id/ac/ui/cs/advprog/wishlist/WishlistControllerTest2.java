package id.ac.ui.cs.advprog.wishlist;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.ac.ui.cs.advprog.wishlist.controller.WishlistController;
import id.ac.ui.cs.advprog.wishlist.dto.wishlist.WishlistRequest;
import id.ac.ui.cs.advprog.wishlist.dto.wishlist.WishlistUserResponse;
import id.ac.ui.cs.advprog.wishlist.model.Wishlist;
import id.ac.ui.cs.advprog.wishlist.service.WishlistService;
import id.ac.ui.cs.advprog.wishlist.utils.User.Authenticator;
import id.ac.ui.cs.advprog.wishlist.utils.User.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class WishlistControllerTest2 {


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
        mockMvc = MockMvcBuilders.standaloneSetup(wishlistController).build();
    }

    @Test
    void testGetAllUserWishlist() throws Exception {
        // Prepare test data
        List<WishlistUserResponse> wishlistResponses = Arrays.asList(
                WishlistUserResponse.builder().name("Wishlist 1").build(),
                WishlistUserResponse.builder().name("Wishlist 2").build()
        );
        Integer userId = 1;
        String token = "testToken";

        Mockito.when(authenticator.getUserInfo(token)).thenReturn(UserDTO.builder().id(userId).build());
        Mockito.when(wishlistService.findAllByUserIdOrderByCreatedDateDesc(userId)).thenReturn(wishlistResponses);

        // Perform the request and verify the response
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/wishlist/my-wishlist")
                        .header("Authorization", token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(wishlistResponses.size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Wishlist 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Wishlist 2"));
    }

    @Test
    void testGetWishlistById() throws Exception {
        // Prepare test data
        Integer wishlistId = 1;
        Wishlist wishlist = Wishlist.builder().id(wishlistId).name("Wishlist 1").build();
        String token = "testToken";

        Mockito.when(wishlistService.findById(wishlistId)).thenReturn(wishlist);

        // Perform the request and verify the response
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/wishlist/all-wishlist/{wishlistId}", wishlistId)
                        .header("Authorization", token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(wishlistId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Wishlist 1"));
    }

    @Test
    void testGetWishlistByUserIdAndWishlistId() throws Exception {
        // Prepare test data
        Integer userId = 1;
        Integer wishlistId = 1;
        Wishlist wishlist = Wishlist.builder().id(wishlistId).name("Wishlist 1").build();
        String token = "testToken";

        Mockito.when(authenticator.getUserInfo(token)).thenReturn(UserDTO.builder().id(userId).build());
        Mockito.when(wishlistService.findByUserIdAndId(userId, wishlistId)).thenReturn(wishlist);

        // Perform the request and verify the response
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/wishlist/my-wishlist/{wishlistId}", wishlistId)
                        .header("Authorization", token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(wishlistId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Wishlist 1"));

    }

    @Test
    void testCreateWishlist() throws Exception {
        // Prepare test data
        WishlistRequest wishlistRequest = WishlistRequest.builder().name("New Wishlist").foodId(1).build();
        Wishlist createdWishlist = Wishlist.builder().id(1).name("New Wishlist").build();
        String token = "testToken";
        Integer userId = 1;

        Mockito.when(authenticator.getUserInfo(token)).thenReturn(UserDTO.builder().id(userId).build());
        Mockito.when(wishlistService.create(token, userId, wishlistRequest)).thenReturn(createdWishlist);

        // Perform the request and verify the response
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/wishlist/my-wishlist/create")
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(wishlistRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("New Wishlist"));
    }

    @Test
    void testUpdateWishlist() throws Exception {
        // Prepare test data
        Integer wishlistId = 1;
        WishlistRequest wishlistRequest = WishlistRequest.builder().name("Updated Wishlist").foodId(1).build();
        Wishlist updatedWishlist = Wishlist.builder().id(wishlistId).name("Updated Wishlist").build();
        String token = "testToken";
        Integer userId = 1;

        Mockito.when(authenticator.getUserInfo(token)).thenReturn(UserDTO.builder().id(userId).build());
        Mockito.when(wishlistService.update(token, userId, wishlistId, wishlistRequest)).thenReturn(updatedWishlist);

        // Perform the request and verify the response
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/wishlist/my-wishlist/update/{wishlistId}", wishlistId)
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(wishlistRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(wishlistId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Updated Wishlist"));
    }

    @Test
    void testDeleteWishlist() throws Exception {
        // Prepare test data
        Integer wishlistId = 1;
        String token = "testToken";
        String expectedResponse = String.format("Deleted Wishlist with id %d", wishlistId);

        // Perform the request and verify the response
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/wishlist/my-wishlist/delete/{wishlistId}", wishlistId)
                        .header("Authorization", token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedResponse));

        Mockito.verify(wishlistService).delete(wishlistId);
    }

    // Helper method to convert an object to JSON string
    private static String asJsonString(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

}
