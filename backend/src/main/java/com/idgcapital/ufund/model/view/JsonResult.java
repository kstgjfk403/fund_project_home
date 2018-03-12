package com.idgcapital.ufund.model.view;

import com.fasterxml.jackson.annotation.JsonView;
import com.idgcapital.ufund.common.controller.AppView;

/**
 * Created by yi_zhang on 2017/11/14.
 */
public class JsonResult<T> {

    @JsonView({AppView.BaseResult.class})
    private ReturnCode code;

    @JsonView({AppView.BaseResult.class})
    private String returnMessage;

    @JsonView({AppView.BaseResult.class})
    private T data;

    @JsonView({AppView.BaseResult.class})
    private Long count;

    public ReturnCode getCode() {
        return code;
    }

    public void setCode(ReturnCode code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
