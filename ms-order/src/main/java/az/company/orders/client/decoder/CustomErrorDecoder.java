package az.company.orders.client.decoder;

import az.company.orders.exception.CustomFeignException;
import com.fasterxml.jackson.databind.JsonNode;
import feign.Response;
import feign.codec.ErrorDecoder;

import static az.company.orders.client.decoder.JsonNodeFiledName.MESSAGE;
import static az.company.orders.mapper.MapperUtil.MAPPER_UTIL;
import static az.company.orders.model.enums.ErrorMessage.CLIENT_ERROR;

public class CustomErrorDecoder implements ErrorDecoder {


    @Override
    public Exception decode(String s, Response response) {
        var status = response.status();
        var errorMessage = CLIENT_ERROR.getMessage();

        JsonNode jsonNode;

        try (var body = response.body().asInputStream()) {
            jsonNode = MAPPER_UTIL.map(body, JsonNode.class);
        } catch (Exception e) {
            throw new CustomFeignException(status, CLIENT_ERROR.getMessage());
        }

        if (jsonNode.has(MESSAGE.getFieldName()))
            errorMessage = jsonNode.get(MESSAGE.getFieldName()).asText();
        return new CustomFeignException(status, errorMessage);
    }
}
