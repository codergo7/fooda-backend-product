package be.fooda.backend.product.view.client;

import java.util.UUID;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class MediaClient {

    //private RestTemplate restClient;

    // EXISTS_BY_ID
    public boolean exist(UUID imageId) {

        return true;
    }
}
