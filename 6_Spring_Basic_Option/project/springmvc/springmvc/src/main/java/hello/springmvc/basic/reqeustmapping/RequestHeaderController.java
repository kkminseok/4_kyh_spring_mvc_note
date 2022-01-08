package hello.springmvc.basic.reqeustmapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Slf4j
@RestController
public class RequestHeaderController {

    @RequestMapping("/headers")
    public String headers(HttpServletRequest request,
                          HttpServletResponse response,
                          HttpMethod httpMethod,
                          Locale locale,
                          @RequestHeader MultiValueMap<String,String> headerMap,
                          @RequestHeader("host") String host,
                          @CookieValue(value = "kms-cookie",required = false) String cookie){
        log.info("request = {}",request);
        log.info("response = {}",response);
        log.info("httpMethod = {}",httpMethod);
        log.info("Locale = {}",locale);
        log.info("headerMap = {}",headerMap);
        log.info("header host = {}",host);
        log.info("kms-cookie = {}",cookie);

        return "ok";
    }
}
