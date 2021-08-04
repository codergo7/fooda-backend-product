package be.fooda.backend.product.view.client;

import be.fooda.backend.product.model.extension.RestClient;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.Objects;

// LOMBOK
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)

// SPRING
@RestClient

public class MediaClient {

    RestTemplate restTemplate;

    @Value("${fooda.backend.media.url}")
    String url;

    // EXISTS_BY_ID
    public boolean existsById(final Long mediaId) {

        // QUERY_PARAMETERS
        final var params = new LinkedHashMap<String, Object>();
        params.put("mediaId", mediaId);

        // READ_RESPONSE_VIA_HTTP_REQUEST
        final var response = restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY, Boolean.class, params);

        // RETURN STORE_EXISTS_BY_ID
        return response.getStatusCode() == HttpStatus.FOUND && response.hasBody() && Objects.equals(response.getBody(), Boolean.TRUE);
    }
}
