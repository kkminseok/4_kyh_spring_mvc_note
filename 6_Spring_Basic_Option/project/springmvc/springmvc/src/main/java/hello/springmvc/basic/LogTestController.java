package hello.springmvc.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogTestController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest(){
        String name = "Log test Project by Kms";

        

        log.trace("trace log ={}",name);
        log.debug("debug log ={}",name);
        log.info("info log = {}",name);
        log.warn("warn log = {}",name);
        log.error("error log = {}",name);


        //다른 방식. 이런식으로 사용하지 말자. 계산 로직이 먼저 실행되어서 불필요한 CPU, 메모리 사용으로 이어질 수 있다.
        log.debug("String concat log = " + name);
        return "ok";
    }
}
