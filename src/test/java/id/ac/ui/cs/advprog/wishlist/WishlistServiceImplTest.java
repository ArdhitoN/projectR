package id.ac.ui.cs.advprog.wishlist;


//
//@ExtendWith(MockitoExtension.class)
//public class WishlistServiceImplTest {
//
//    @InjectMocks
//    private WishlistServiceImpl wishlistService;
//
//    @Mock
//    private WishlistRepository wishlistRepository;
//
//    @Mock
//    private Authenticator authenticator;
//
//    @Mock
//    private FoodUtil foodUtil;
//
//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testFindAll() {
//        // Given
//        List<WishlistAdminResponse> wishlistList = new ArrayList<>();
//        wishlistList.add(WishlistAdminResponse.fromWishlist(new Wishlist(1, 1, "Wishlist 1", new Date(), 1)));
//
//        when(wishlistRepository.findAll()).thenReturn(wishlistList);
//
//        // When
//        List<WishlistAdminResponse> response = wishlistService.findAll();
//
//        // Then
//        assertNotNull(response);
//        assertEquals(wishlistList.size(), response.size());
//        System.out.println("Expected Wishlist List Size: " + wishlistList.size());
//        System.out.println("Actual Response List Size: " + response.size());
//        // Add assertions for individual WishlistAdminResponse objects in the response list
//        // For example:
//        WishlistAdminResponse firstWishlist = response.get(0);
//        assertEquals(1, firstWishlist.getWishlistId());
//        assertEquals("Wishlist 1", firstWishlist.getName());
//        // Add more assertions as needed for other properties of WishlistAdminResponse
//    }
//
//
//
//
//    @Test
//    public void testFindAllByUserIdOrderByCreatedDateDesc() {
//        // Given
//        Integer userId = 1;
//        List<Wishlist> wishlistList = new ArrayList<>();
//        wishlistList.add(new Wishlist(1, userId, "Wishlist 1", new Date(), 1));
//        wishlistList.add(new Wishlist(2, userId, "Wishlist 2", new Date(), 2));
//        when(wishlistRepository.findAllByUserIdOrderByCreatedDateDesc(userId)).thenReturn(wishlistList);
//
//        // When
//        List<WishlistUserResponse> response = wishlistService.findAllByUserIdOrderByCreatedDateDesc(userId);
//
//        // Then
//        assertNotNull(response);
//        assertEquals(wishlistList.size(), response.size());
//        // Add assertions for individual WishlistUserResponse objects in the response list
//        // For example:
//        WishlistUserResponse firstWishlist = response.get(0);
//        assertEquals(1, firstWishlist.getWishlistId());
//        assertEquals("Wishlist 1", firstWishlist.getName());
//        // Add more assertions as needed for other properties of WishlistUserResponse
//    }
//
//
//
//    @Test
//    public void testFindById() {
//        // Given
//        Integer wishlistId = 1;
//        Wishlist wishlist = new Wishlist(wishlistId, 1, "Wishlist 1", new Date(), 1);
//        when(wishlistRepository.findById(wishlistId)).thenReturn(Optional.of(wishlist));
//
//        // When
//        Wishlist response = wishlistService.findById(wishlistId);
//
//        // Then
//        assertNotNull(response);
//        assertEquals(wishlist, response);
//    }
//
//    @Test
//    public void testFindByUserIdAndId() {
//        // Given
//        Integer userId = 1;
//        Integer wishlistId = 1;
//        Wishlist wishlist = new Wishlist(wishlistId, userId, "Wishlist 1", new Date(), 1);
//        when(wishlistRepository.findWishlistByUserIdAndId(userId, wishlistId)).thenReturn(Optional.of(wishlist));
//
//        // When
//        Wishlist response = wishlistService.findByUserIdAndId(userId, wishlistId);
//
//        // Then
//        assertNotNull(response);
//        assertEquals(wishlist, response);
//    }
//
//    @Test
//    public void testCreate() {
//        // Given
//        String jwtToken = "token";
//        WishlistRequest wishlistRequest = new WishlistRequest("Wishlist 1", 1);
//        UserDTO user = new UserDTO();
//        Integer userId = 1;
//        user.setId(userId);
//        FoodDTO food = new FoodDTO();
//        food.setId(1);
//        food.setName("Food 1");
//        Wishlist createdWishlist = new Wishlist(1, userId, "Wishlist 1", new Date(), 1);
//
//        when(authenticator.getUserInfo(jwtToken)).thenReturn(user);
//        when(foodUtil.getFoodInfo(jwtToken, wishlistRequest.getFoodId())).thenReturn(food);
//        when(wishlistRepository.save(any(Wishlist.class))).thenReturn(createdWishlist);
//
//        // When
//        Wishlist response = wishlistService.create(jwtToken, userId, wishlistRequest);
//
//        // Then
//        assertNotNull(response);
//        assertEquals(createdWishlist, response);
//    }
//
//    @Test
//    public void testUpdate() {
//        // Given
//        String jwtToken = "token";
//        Integer wishlistId = 1;
//        WishlistRequest wishlistRequest = new WishlistRequest("Updated Wishlist", 2);
//        UserDTO user = new UserDTO();
//        Integer userId = 1;
//        user.setId(userId);
//        Wishlist wishlistInDB = new Wishlist(wishlistId, userId, "Wishlist 1", new Date(), 1);
//        FoodDTO food = new FoodDTO();
//        food.setId(2);
//        food.setName("Food 2");
//        Wishlist updatedWishlist = new Wishlist(wishlistId, userId, "Updated Wishlist", new Date(), 2);
//
//        when(authenticator.getUserInfo(jwtToken)).thenReturn(user);
//        when(foodUtil.getFoodInfo(jwtToken, wishlistRequest.getFoodId())).thenReturn(food);
//        when(wishlistRepository.findById(wishlistId)).thenReturn(Optional.of(wishlistInDB));
//        when(wishlistRepository.save(any(Wishlist.class))).thenReturn(updatedWishlist);
//
//        // When
//        Wishlist response = wishlistService.update(jwtToken, userId, wishlistId, wishlistRequest);
//
//        // Then
//        assertNotNull(response);
//        assertEquals(updatedWishlist, response);
//    }
//
//    @Test
//    public void testDelete() {
//        // Given
//        Integer wishlistId = 1;
//        when(wishlistRepository.findById(wishlistId)).thenReturn(Optional.of(new Wishlist()));
//
//        // When
//        assertDoesNotThrow(() -> wishlistService.delete(wishlistId));
//
//        // Then: No exceptions are thrown
//    }
//
//}


import id.ac.ui.cs.advprog.wishlist.dto.wishlist.WishlistAdminResponse;
import id.ac.ui.cs.advprog.wishlist.dto.wishlist.WishlistRequest;
import id.ac.ui.cs.advprog.wishlist.dto.wishlist.WishlistUserResponse;
import id.ac.ui.cs.advprog.wishlist.model.Wishlist;
import id.ac.ui.cs.advprog.wishlist.repository.WishlistRepository;
import id.ac.ui.cs.advprog.wishlist.service.WishlistServiceImpl;
import id.ac.ui.cs.advprog.wishlist.utils.Food.FoodDTO;
import id.ac.ui.cs.advprog.wishlist.utils.Food.FoodUtil;
import id.ac.ui.cs.advprog.wishlist.utils.User.Authenticator;
import id.ac.ui.cs.advprog.wishlist.utils.User.UserDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(MockitoExtension.class)
class WishlistServiceImplTest {

    @Mock
    private WishlistRepository wishlistRepository;

    @Mock
    private Authenticator authenticator;

    @Mock
    private FoodUtil foodUtil;

    @InjectMocks
    private WishlistServiceImpl wishlistService;

    @Test
    void testFindAll() {
        // Prepare test data
        Wishlist wishlist1 = Wishlist.builder()
                .id(1)
                .name("Wishlist 1")
                .userId(1)
                .foodId(1)
                .createdDate(new Date())
                .build();

        Wishlist wishlist2 = Wishlist.builder()
                .id(2)
                .name("Wishlist 2")
                .userId(1)
                .foodId(2)
                .createdDate(new Date())
                .build();

        List<Wishlist> wishlists = Arrays.asList(wishlist1, wishlist2);

        Mockito.when(wishlistRepository.findAll()).thenReturn(wishlists);

        // Perform the test
        List<WishlistAdminResponse> result = wishlistService.findAll();

        // Assertions
        assertEquals(2, result.size());
        assertEquals("Wishlist 1", result.get(0).getName());
        assertEquals("Wishlist 2", result.get(1).getName());
    }

    @Test
    void testFindAllByUserIdOrderByCreatedDateDesc() {
        // Prepare test data
        Wishlist wishlist1 = Wishlist.builder()
                .id(1)
                .name("Wishlist 1")
                .userId(1)
                .foodId(1)
                .createdDate(new Date())
                .build();

        Wishlist wishlist2 = Wishlist.builder()
                .id(2)
                .name("Wishlist 2")
                .userId(1)
                .foodId(2)
                .createdDate(new Date())
                .build();

        List<Wishlist> wishlists = Arrays.asList(wishlist1, wishlist2);

        Mockito.when(wishlistRepository.findAllByUserIdOrderByCreatedDateDesc(1)).thenReturn(wishlists);

        // Perform the test
        List<WishlistUserResponse> result = wishlistService.findAllByUserIdOrderByCreatedDateDesc(1);

        // Assertions
        assertEquals(2, result.size());
        assertEquals("Wishlist 1", result.get(0).getName());
        assertEquals("Wishlist 2", result.get(1).getName());
    }

    @Test
    void testFindById() {
        // Prepare test data
        Wishlist wishlist = Wishlist.builder()
                .id(1)
                .name("Wishlist 1")
                .userId(1)
                .foodId(1)
                .createdDate(new Date())
                .build();

        Mockito.when(wishlistRepository.findById(1)).thenReturn(Optional.of(wishlist));

        // Perform the test
        Wishlist result = wishlistService.findById(1);

        // Assertions
        assertNotNull(result);
        assertEquals("Wishlist 1", result.getName());
    }

    // Add more unit tests for other methods
    @Test
    void testCreate() {
        // Mock dependencies
        String jwtToken = "testToken";
        Integer userId = 1;
        Integer foodId = 1;
        String wishlistName = "Test Wishlist";

        WishlistRequest wishlistRequest = WishlistRequest.builder()
                .name(wishlistName)
                .foodId(foodId)
                .build();

        UserDTO user = UserDTO.builder()
                .id(userId)
                .build();

        FoodDTO food = FoodDTO.builder()
                .id(foodId)
                .build();

        Mockito.when(authenticator.getUserInfo(jwtToken)).thenReturn(user);
        Mockito.when(foodUtil.getFoodInfo(jwtToken, foodId)).thenReturn(food);

        // Perform the test
        Wishlist createdWishlist = wishlistService.create(jwtToken, userId, wishlistRequest);

        // Assertions
        assertNotNull(createdWishlist);
        assertEquals(wishlistName, createdWishlist.getName());
        assertEquals(userId, createdWishlist.getUserId());
        assertEquals(foodId, createdWishlist.getFoodId());
        assertNotNull(createdWishlist.getCreatedDate());
        Mockito.verify(wishlistRepository).save(Mockito.any(Wishlist.class));
    }

    @Test
    void testUpdate() {
        // Mock dependencies
        String jwtToken = "testToken";
        Integer userId = 1;
        Integer wishlistId = 1;
        Integer foodId = 1;
        String updatedWishlistName = "Updated Wishlist";

        WishlistRequest wishlistRequest = WishlistRequest.builder()
                .name(updatedWishlistName)
                .foodId(foodId)
                .build();

        UserDTO user = UserDTO.builder()
                .id(userId)
                .build();

        Wishlist existingWishlist = Wishlist.builder()
                .id(wishlistId)
                .name("Old Wishlist")
                .userId(userId)
                .foodId(2) // Different food ID from the updated request
                .createdDate(new Date())
                .build();

        FoodDTO food = FoodDTO.builder()
                .id(foodId)
                .build();

        Mockito.when(authenticator.getUserInfo(jwtToken)).thenReturn(user);
        Mockito.when(wishlistRepository.findById(wishlistId)).thenReturn(Optional.of(existingWishlist));
        Mockito.when(foodUtil.getFoodInfo(jwtToken, foodId)).thenReturn(food);
        Mockito.when(wishlistRepository.save(Mockito.any(Wishlist.class))).thenAnswer(invocation -> invocation.getArguments()[0]);

        // Perform the test
        Wishlist updatedWishlist = wishlistService.update(jwtToken, userId, wishlistId, wishlistRequest);

        // Assertions
        assertNotNull(updatedWishlist);
        assertEquals(wishlistId, updatedWishlist.getId());
        assertEquals(updatedWishlistName, updatedWishlist.getName());
        assertEquals(userId, updatedWishlist.getUserId());
        assertEquals(foodId, updatedWishlist.getFoodId());
        assertNotNull(updatedWishlist.getCreatedDate());
        Mockito.verify(wishlistRepository).findById(wishlistId);
        Mockito.verify(wishlistRepository).save(Mockito.any(Wishlist.class));
    }

    @Test
    void testDelete() {
        // Mock dependencies
        Integer wishlistId = 1;

        Mockito.when(wishlistRepository.findById(wishlistId)).thenReturn(Optional.of(Wishlist.builder().build()));

        // Perform the test
        wishlistService.delete(wishlistId);

        // Assertions
        Mockito.verify(wishlistRepository).findById(wishlistId);
        Mockito.verify(wishlistRepository).deleteById(wishlistId);
    }



}

