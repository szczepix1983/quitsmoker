package com.szczepix.quitsmoker.services.requestService;

import org.codehaus.jackson.map.ObjectMapper;

public class BaseResponse extends Object {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String toString() {
        String objectToString = "object not mapped";
        try {
            objectToString = mapper.writeValueAsString(this);
        } catch (Exception ignored) {
        }
        return objectToString;
    }
}
