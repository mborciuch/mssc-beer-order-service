package guru.sfg.beer.order.service.services;

import guru.sfg.beer.order.service.web.model.BeerDto;

import java.util.Optional;
import java.util.UUID;

public interface BeerDataService {

    Optional<BeerDto> getBeerByUPC(String upc);

}
