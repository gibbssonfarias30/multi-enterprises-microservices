package com.backdevfc.msregister.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class Util {

    private Util(){}

    public static <T> String convertToJson(T response) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(response);
        } catch (Exception e) {
            // Manejar la excepción (puede ser JsonProcessingException)
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T convertFromJson(String json, Class<T> valueType) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, valueType);
        } catch (Exception e) {
            // Manejar la excepción (puede ser IOException o JsonProcessingException)
            e.printStackTrace();
            return null;
        }
    }
}