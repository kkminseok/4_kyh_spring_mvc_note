package hello.springmvc.basic.reqeustmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class MappingController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/kms-basic-request")
    public String basicreq(){
        log.info("basic reqeust  kms");
        return "ok";
    }

    /**
     * method 특정 HTTP 메서드 요청만 허용
     * GET, HEAD, POST, PUT, PATCH, DELETE
     */
    @RequestMapping(value = "/kms-mapping-get-v1", method = RequestMethod.GET)
    public String mappingGetv1(){
        log.info("mappingGetv1");
        return "ok";
    }

    /**
     * 편리한 축약 애노테이션
     * @GetMapping
     * @PostMapping
     * @PutMapping
     * @DeleteMapping
     * @PatchMapping
     */

    @GetMapping(value = "/kms/mapping-get-v2")
    public String mappingGetv2(){
        log.info("mappingGetv2");
        return "ok";
    }

    /**
     * PathVariable 사용.
     * @param id
     * @return
     */
    @GetMapping("/kms/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String id){
        log.info("mapping path userId = {}",id);
        return "ok";
    }

    @GetMapping("/kms/mapping/{userId}/champion/{chamname}")
    public String mappingPath(@PathVariable String userId, @PathVariable String chamname){
        log.info("mapping path userId = {}, champion name = {}",userId,chamname);
        return "ok";
    }

    /**
     * 파라미터로 추가 매핑
     * parmas = "lang"
     * params ="!lang"
     * params = "lang=java"
     * params = "lang!=java"
     * params = {"lang=java","name="kms"}
     * 위와 같은 표현도 가능하다는 것.
     */
    @GetMapping(value = "/kms-mapping-param",params = "lang=java")
    public String mappingParam(){
        log.info("mappingParam");
        return "ok";
    }

    /**
     * 특정 헤더로 추가 매핑
     * headers = "champion"
     * headers = "!champion"
     * headers = "champion = vayne"
     * headers = "champion != vayne"
     */
    @GetMapping(value = "/kms-mapping-headers", headers = "champion=vayne")
    public String mappingHeader(){
        log.info("mappingHeader");
        return "ok";
    }

    /**
     * Content-Type 헤더 기반 추가 매핑 Media Type
     * consumes = "application/json"
     * consumes = "!application/json"
     * consumes = "aplication/*"
     * consumes = "*\/*"
     * MediaType.APPLICATION_JSON_VALUE
     */

    @PostMapping(value = "/kms-mapping-consume", consumes = "application/json")
    public String mappingConsumes(){
        log.info("mappingConsumes");
        return "ok";
    }
    /**
    * Accept 헤더 기반 Media Type
    * produces = "text/html"
    * produces = "!text/html"
    * produces = "text/*"
    * produces = "*\/*"
    */
    @PostMapping(value = "/kms-mapping-produce", produces = "text/html")
    public String mappingProduces() {
        log.info("mappingProduces");
        return "ok";
    }
}
