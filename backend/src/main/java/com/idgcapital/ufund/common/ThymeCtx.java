package com.idgcapital.ufund.common;

import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;

import java.util.Map;

/**
 * Created by yi_zhang on 2018/1/28.
 */
@Component
public class ThymeCtx {


    private Context context = new Context();


    public Context getContext(Map<String, Object> params) {

        params.keySet().forEach(key -> {
            context.setVariable(key, params.get(key));

        });
        return context;
    }


}
