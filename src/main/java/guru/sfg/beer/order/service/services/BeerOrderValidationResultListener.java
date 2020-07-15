package guru.sfg.beer.order.service.services;

import guru.sfg.beer.order.service.config.JmsConfiguration;
import guru.sfg.brewery.model.event.ValidateBeerOrderResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class BeerOrderValidationResultListener {

    private final JmsTemplate jmsTemplate;
    private final BeerOrderManager beerOrderManager;

    @JmsListener(destination = JmsConfiguration.VALIDATE_ORDER_RESULT)
    public void listen(@Payload ValidateBeerOrderResponse validateBeerOrderResponse){
        final UUID beerOrderId = validateBeerOrderResponse.getBeerOrderId();

        log.debug("Validation result for Order Id:" + beerOrderId);

        beerOrderManager.processValidationResult(beerOrderId, validateBeerOrderResponse.isValid());
    }

}
