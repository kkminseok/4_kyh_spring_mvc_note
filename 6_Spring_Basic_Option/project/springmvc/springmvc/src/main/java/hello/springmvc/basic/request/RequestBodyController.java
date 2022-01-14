package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyController {

    @PostMapping("/kms-request-body-mappingV1")
    public void requestbodyV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messagebody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messagebody = {}",messagebody);

        response.getWriter().write("ok");
    }

    @PostMapping("/kms-request-body-mappingV2")
    public void requestbodyV2(InputStream inputStream, Writer writer) throws IOException {
        String messagebody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("v2 messagebody = {}",messagebody);

        writer.write("ok");
    }

    @PostMapping("/kms-request-body-mappingV3")
    public HttpEntity<String> requestbodyV3(HttpEntity<String> httpEntity)  {
        String messagebody = httpEntity.getBody();

        log.info("v3 messagebody = {}",messagebody);

        return new HttpEntity<>("im kms OK");
    }


    @PostMapping("/kms-request-body-mappingV4")
    public HttpEntity<String> requestbodyV4(RequestEntity<String> requestEntity){
        String messagebody =  requestEntity.getBody();

        log.info("v4 messagebody = {}",messagebody);

        return new ResponseEntity<String>("ok", HttpStatus.CREATED);
    }

    @ResponseBody
    @PostMapping("/kms-request-body-mappingV5")
    public String requestV5(@RequestBody String messagebody){
        log.info("v5 messagebody = {}", messagebody);
        return "v5 ok!!";
    }
}
