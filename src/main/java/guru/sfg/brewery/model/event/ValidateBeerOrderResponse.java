package guru.sfg.brewery.model.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValidateBeerOrderResponse {

    private UUID beerOrderId;
    private boolean isValid;

}
