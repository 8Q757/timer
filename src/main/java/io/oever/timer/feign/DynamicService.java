package io.oever.timer.feign;

import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

public interface DynamicService {


    @PostMapping("{url}")
    Object executePostJsonApi(@PathVariable("url") String url, @RequestBody Object params);

    @PostMapping("{url}")
    Object executePostFormApi(@PathVariable("url") String url, @RequestParam Object params);

    @GetMapping("{url}")
    Object executeGetApi(@PathVariable("url") String url, @SpringQueryMap Object params);

}