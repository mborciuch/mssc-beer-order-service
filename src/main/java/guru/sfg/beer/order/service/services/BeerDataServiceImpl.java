package guru.sfg.beer.order.service.services;

import guru.sfg.beer.order.service.web.model.BeerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Component
@ConfigurationProperties(prefix = "sfg.brewery", ignoreInvalidFields = false)
@Slf4j
public class BeerDataServiceImpl implements BeerDataService {

    private final RestTemplate restTemplate;

    private final String BEER_PATH = "/api/v1/beer/{beerUpc}";
    private String beerServiceHost;

    public BeerDataServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public Optional<BeerDto> getBeerByUPC(String upc) {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Content-Type", "application/json");
        httpHeaders.set("Accept", "application/json");
        HttpEntity entity = new HttpEntity<>(httpHeaders);
        Map<String, String> urlParams = new HashMap<String, String>();
        urlParams.put("beerUpc", upc);

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(beerServiceHost + BEER_PATH)
                .queryParam("by", "upc");

        return Optional.of(restTemplate.exchange(builder.buildAndExpand(urlParams).toUri(), HttpMethod.GET, entity, BeerDto.class).getBody());
    }

    public void setBeerServiceHost(String beerServiceHost) {
        this.beerServiceHost = beerServiceHost;
    }

}
