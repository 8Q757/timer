package io.oever.timer.feign;

import cn.hutool.core.util.ObjectUtil;
import io.oever.timer.param.FeignCallerParam;
import org.springframework.cloud.openfeign.FeignClientBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class DynamicFeignClient {

    private FeignClientBuilder feignClientBuilder;

    public DynamicFeignClient(ApplicationContext appContext) {
        this.feignClientBuilder = new FeignClientBuilder(appContext);
    }


    public Object executeApi(FeignCallerParam feignCallerParam) {

        Object result = null;
        DynamicService dynamicService = this.feignClientBuilder.forType(DynamicService.class, feignCallerParam.getName())
                .build();

        if (ObjectUtil.equal(feignCallerParam.getMethod(), "POST")) {
            result = dynamicService.executePostJsonApi(feignCallerParam.getUrl(), feignCallerParam.getData());
        } else if (ObjectUtil.equal(feignCallerParam.getMethod(), "GET")) {
            result = dynamicService.executeGetApi(feignCallerParam.getUrl(), feignCallerParam.getData());
        }
        return result;
    }
}