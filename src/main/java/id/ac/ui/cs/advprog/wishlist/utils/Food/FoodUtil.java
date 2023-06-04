package id.ac.ui.cs.advprog.wishlist.utils.Food;

import id.ac.ui.cs.advprog.wishlist.exceptions.FoodDoesNotExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class FoodUtil {

    private final RestTemplate restTemplate;

    @Value("${food.service.url}")
    private String getFoodURL;

    public FoodDTO getFoodInfo(String jwtToken, Integer objectId){
        var headers = new HttpHeaders();
        headers.set("Authorization", jwtToken);
        HttpEntity<String> entity = new HttpEntity<>("",headers);
        ResponseEntity<FoodDTO> response;
        FoodDTO food;

        try{
         response = restTemplate.exchange(getFoodURL+objectId.toString(), HttpMethod.GET, entity, FoodDTO.class);
         food = response.getBody();
         if (food == null) {
            throw new FoodDoesNotExistException(objectId);
         }
         return food;

        } catch (RestClientException e) {
            throw new FoodDoesNotExistException(objectId);
        }

    }

}
