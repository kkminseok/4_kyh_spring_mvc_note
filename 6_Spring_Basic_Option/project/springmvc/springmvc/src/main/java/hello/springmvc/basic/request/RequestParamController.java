package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    //기본적인 형태

    @RequestMapping("/kms-request-param-v1")
    public void requestParamv1(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String username = request.getParameter("username");
        int height = Integer.parseInt(request.getParameter("height"));
        log.info("username = {} , height = {}",username,height);

        response.getWriter().write("ok");
    }

    @ResponseBody
    @RequestMapping("/kms-request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String myname,
            @RequestParam("height") int myheight
    ){
        log.info("username = {} , height = {}",myname,myheight);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/kms-request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int height
    ){
        log.info("username = {} , height = {}",username,height);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/kms-request-param-v4")
    public String requestParamV4(
            String username,
            int height
    ){
        log.info("username = {} , height = {}",username,height);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/kms-request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true, defaultValue ="jyb") String username,
            @RequestParam(required = false, defaultValue = "-1") int height
    ){
        log.info("username = {} , height = {}",username,height);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/kms-request-param-map")
    public String requestParamMap(@RequestParam Map<String,Object> paramMap){
        log.info("username = {} , height = {}",paramMap.get("username"),paramMap.get("height"));

        return "ok";
    }

}
