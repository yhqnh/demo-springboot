package com.yhq.demospringboot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/index")
@Slf4j
public class IndexController {

    @RequestMapping("/check")
    public String check() {
        log.debug("this is debug message================");
        log.info("this is info message================");
        log.error("this is error message================");
        return "ok";
    }
}
