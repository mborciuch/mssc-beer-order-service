package guru.sfg.beer.order.service.statemachine.action;

import guru.sfg.beer.order.service.config.JmsConfiguration;
import guru.sfg.beer.order.service.domain.BeerOrderEventEnum;
import guru.sfg.beer.order.service.domain.BeerOrderStatusEnum;
import guru.sfg.beer.order.service.repositories.BeerOrderRepository;
import guru.sfg.beer.order.service.services.BeerOrderManagerImpl;
import guru.sfg.beer.order.service.web.mappers.BeerOrderMapper;
import guru.sfg.brewery.model.BeerOrderDto;
import guru.sfg.brewery.model.event.ValidateBeerOrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ValidateOrderAction  implements Action<BeerOrderStatusEnum, BeerOrderEventEnum> {

    private final JmsTemplate jmsTemplate;
    private final BeerOrderRepository beerOrderRepository;
    private final BeerOrderMapper beerOrderMapper;

    @Override
    public void execute(StateContext<BeerOrderStatusEnum, BeerOrderEventEnum> stateContext) {
        String beerOrderId = (String) stateContext.getMessageHeaders().getOrDefault(BeerOrderManagerImpl.ORDER_ID_HEADER, "");
        BeerOrderDto beerOrderDto = beerOrderMapper.beerOrderToDto(beerOrderRepository.findOneById(UUID.fromString(beerOrderId)));
        ValidateBeerOrderRequest validateBeerOrderRequest = ValidateBeerOrderRequest.builder()
                .beerOrderDto(beerOrderDto)
                .build();
        jmsTemplate.convertAndSend(JmsConfiguration.VALIDATE_ORDER_QUEUE, validateBeerOrderRequest);
    }
}
