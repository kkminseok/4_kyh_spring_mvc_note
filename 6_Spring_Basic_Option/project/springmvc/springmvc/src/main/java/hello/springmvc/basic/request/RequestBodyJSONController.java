package hello.springmvc.basic.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.BmiData;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.mapper.Mapper;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyJSONController {
    private ObjectMapper mapper = new ObjectMapper();

    @PostMapping("/kms-request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException{
        ServletInputStream inputStream = request.getInputStream();
        String messagebody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("message = {}",messagebody);
        BmiData data = mapper.readValue(messagebody,BmiData.class);
        log.info("name = {}, height = {}, weight = {}",data.getName(),data.getHeight(),data.getWeight());

        response.getWriter().write("ok");
    }

    @ResponseBody
    @PostMapping("/kms-request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String messagebody) throws JsonProcessingException {

        log.info("message = {}", messagebody);
        BmiData data = mapper.readValue(messagebody, BmiData.class);
        log.info("name = {}, height = {}, weight = {}",data.getName(),data.getHeight(),data.getWeight());

        return "json v2 ok";
    }

    @ResponseBody
    @PostMapping("/kms-request-body-json-v3")
    public String requestBodyJsonV3(BmiData data)  {
        log.info("name = {}, height = {}, weight = {}",data.getName(),data.getHeight(),data.getWeight());

        return "json v3 ok";
    }

    @ResponseBody
    @PostMapping("/kms-request-body-json-v4")
    public String requestBodyJsonV4(HttpEntity<BmiData> data)  {
        BmiData body = data.getBody();
        log.info("name = {}, height = {}, weight = {}",body.getName(),body.getHeight(),body.getWeight());

        return "json v4 ok";
    }

    @ResponseBody
    @PostMapping("/kms-request-body-json-v5")
    public BmiData requestBodyJsonV5(@RequestBody BmiData data)  {
        log.info("name = {}, height = {}, weight = {}",data.getName(),data.getHeight(),data.getWeight());
        return data;
    }
}
