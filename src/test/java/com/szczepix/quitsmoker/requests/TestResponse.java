package com.szczepix.quitsmoker.requests;

import com.szczepix.quitsmoker.services.requestService.BaseResponse;

public class TestResponse extends BaseResponse {

    public final String content;

    public TestResponse(final String content) {
        this.content = content;
    }
}
