package com.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse {
    private String errorCode;
    private String message;
    private Object data;

    public BaseResponse(ResponseCode responseCode) {
        this.errorCode = responseCode.getErrorCode();
        this.message = responseCode.getMessage();
    }

    public static BaseResponse success(Object data) {
        BaseResponse resp = new BaseResponse();
        resp.errorCode = ResponseCode.SUCCESS.getErrorCode();
        resp.message = ResponseCode.SUCCESS.getMessage();
        resp.setData(data);
        return resp;
    }
}
