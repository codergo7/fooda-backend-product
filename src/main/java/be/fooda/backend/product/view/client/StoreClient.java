package be.fooda.backend.product.view.client;

import java.util.UUID;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class StoreClient {

    // private final RestTemplate restClient;

    // EXISTS_BY_ID
    public boolean exists(final UUID storeId) {

        return true;
    }

}
