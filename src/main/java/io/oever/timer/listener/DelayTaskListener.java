package io.oever.timer.listener;

import cn.hutool.json.JSONUtil;
import io.oever.timer.feign.DynamicFeignClient;
import io.oever.timer.param.FeignCallerParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
@Slf4j
@RocketMQMessageListener(consumerGroup = "DELAY_CONSUMER_GROUP", topic = "DELAY_FEIGN_TOPIC")
public class DelayTaskListener implements RocketMQListener<FeignCallerParam> {

    @Resource
    private DynamicFeignClient dynamicClient;

    @Override
    public void onMessage(FeignCallerParam feignCallerParam) {
        log.info("延时调用微服务请求=>{}", JSONUtil.toJsonStr(feignCallerParam));
        Object result = dynamicClient.executeApi(feignCallerParam);
        log.info("延时调用微服务响应=>{}", JSONUtil.toJsonStr(result));
    }


}