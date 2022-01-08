package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
}
