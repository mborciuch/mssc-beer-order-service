package guru.sfg.beer.order.service.services;

import guru.sfg.brewery.model.BeerDto;

import java.util.Optional;

public interface BeerDataService {

    Optional<BeerDto> getBeerByUPC(String upc);

}
