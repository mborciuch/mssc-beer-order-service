package guru.sfg.beer.order.service.config;

        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
        import org.springframework.jms.support.converter.MessageConverter;
        import org.springframework.jms.support.converter.MessageType;

@Configuration
public class JmsConfiguration {

    public static final String VALIDATE_ORDER_QUEUE = "validate_order_queue";
    public static final String VALIDATE_ORDER_RESULT_QUEUE = "validate_order_result_queue";
    public static final String ALLOCATE_ORDER_QUEUE = "allocate_order_queue";
    public static final String ALLOCATE_ORDER_RESPONSE_QUEUE = "allocate_order_response_queue";

    @Bean
    public MessageConverter messageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }
}
