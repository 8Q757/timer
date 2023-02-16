package io.oever.timer.api;

import cn.hutool.json.JSONUtil;
import io.oever.timer.param.FeignCallerParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping
public class FeignCallerApi {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @RequestMapping("/callFeignDelay")
    public String callFeignDelay(@RequestBody FeignCallerParam feignCallerParam) {
        SendResult delayTopic = rocketMQTemplate.syncSend("DELAY_FEIGN_TOPIC", MessageBuilder.withPayload(feignCallerParam)
                .build(), 3000, 3);
        log.info("发送延时回调=>{}", JSONUtil.toJsonStr(delayTopic));
        return delayTopic.toString();
    }

    @RequestMapping("/callFeignNow")
    public String callFeignNow(@RequestBody FeignCallerParam feignCallerParam) {
        SendResult nowTopic = rocketMQTemplate.syncSend("NOW_FEIGN_TOPIC", MessageBuilder.withPayload(feignCallerParam)
                .build());
        log.info("发送实时回调=>{}", JSONUtil.toJsonStr(nowTopic));
        return nowTopic.toString();
    }
}