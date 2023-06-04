package id.ac.ui.cs.advprog.wishlist;

import id.ac.ui.cs.advprog.wishlist.config.ApplicationConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;


public class ApplicationConfigTest {

    @InjectMocks
    private ApplicationConfig applicationConfig;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void restTemplateTest() {
        RestTemplate restTemplate = applicationConfig.restTemplate();
        assertNotNull(restTemplate);
    }
}