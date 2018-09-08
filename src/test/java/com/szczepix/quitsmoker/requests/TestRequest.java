package com.szczepix.quitsmoker.requests;

import com.szczepix.quitsmoker.services.requestService.BaseGetRequest;
import com.szczepix.quitsmoker.services.requestService.BaseResponse;

public class TestRequest extends BaseGetRequest<String> {

    public int completeCount = 0;

    public TestRequest(final Object scope, final String invokeMethod) {
        super(scope, invokeMethod);
    }

    @Override
    protected void complete() {
        super.complete();
        completeCount++;
    }

    @Override
    protected BaseResponse getResponse() {
        return new TestResponse(content);
    }

    @Override
    public String getPath() {
        return "https://google.com";
    }
}
