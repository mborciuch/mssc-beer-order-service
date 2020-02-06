package guru.sfg.brewery.model.event;

import guru.sfg.brewery.model.BeerOrderDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ValidateBeerOrderRequest {

    BeerOrderDto beerOrderDto;

}
