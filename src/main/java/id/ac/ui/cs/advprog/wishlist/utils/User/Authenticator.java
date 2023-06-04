package id.ac.ui.cs.advprog.wishlist.utils.User;


import id.ac.ui.cs.advprog.wishlist.exceptions.AuthenticationServiceException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class Authenticator {
    @Value("${auth.service.url}")
    private String getUserURL;

    private final RestTemplate restTemplate;

    public UserDTO getUserInfo(String jwtToken){

        var headers = new HttpHeaders();
        headers.set("Authorization", jwtToken);
        HttpEntity<String> entity = new HttpEntity<>("",headers);
        ResponseEntity<UserDTO> response = null;
        UserDTO userInfo;
        try{
            response = restTemplate.exchange(getUserURL, HttpMethod.GET, entity, UserDTO.class);
            userInfo = response.getBody();
            return userInfo;
        } catch (RestClientException e) {
            throw new AuthenticationServiceException("Authentication failed");
        }
    }
}
