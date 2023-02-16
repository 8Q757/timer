package io.oever.timer.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeignCallerParam {

    /**
     * 请求方式
     */
    private String method;
    /**
     * 请求微服务名
     */
    private String name;
    /**
     * 请求路径
     */
    private String url;
    /**
     * 请求参数
     */
    private Map Data;

}