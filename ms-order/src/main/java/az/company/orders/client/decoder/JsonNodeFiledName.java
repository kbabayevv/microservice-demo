package az.company.orders.client.decoder;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum JsonNodeFiledName {
    MESSAGE("message");
    private final String fieldName;
}
