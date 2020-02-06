package guru.sfg.beer.order.service.web.mappers;

import guru.sfg.beer.order.service.domain.BeerOrderLine;
import guru.sfg.beer.order.service.services.BeerDataService;
import guru.sfg.brewery.model.BeerOrderLineDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class BeerOrderLineMapperDecorator implements BeerOrderLineMapper {

    private BeerOrderLineMapper beerOrderLineMapper;

    private BeerDataService beerDataService;

    @Override
    public BeerOrderLineDto beerOrderLineToDto(BeerOrderLine line) {
        BeerOrderLineDto beerOrderLineDto = beerOrderLineMapper.beerOrderLineToDto(line);
        beerDataService.getBeerByUPC(line.getUpc()).ifPresent(
                beerDto -> {
                    beerOrderLineDto.setBeerName(beerDto.getBeerName());
                    beerOrderLineDto.setUpc(beerDto.getUpc());
                    beerOrderLineDto.setBeerStyle(beerDto.getBeerStyle());
                    beerOrderLineDto.setBeerId(beerDto.getId());
                }

        );
        return beerOrderLineDto;
    }

    @Autowired
    @Qualifier("delegate")
    public void setBeerOrderLineMapper(BeerOrderLineMapper beerOrderLineMapper) {
        this.beerOrderLineMapper = beerOrderLineMapper;
    }

    @Autowired
    public void setBeerDataService(BeerDataService beerDataService) {
        this.beerDataService = beerDataService;
    }
}
