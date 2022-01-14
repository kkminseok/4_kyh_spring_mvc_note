package hello.springmvc.basic.response;

import hello.springmvc.basic.BmiData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@Controller + @ResponseBody = @RestController
@RestController
public class ResponseController {

    @GetMapping("/kms-response-string-v1")
    public void responseBodyV1(HttpServletRequest request, HttpServletResponse response)throws IOException {
        response.getWriter().write("V1 ok");
    }

    @GetMapping("/kms-response-string-v2")
    public ResponseEntity<String> responseBodyV2(){
        return new ResponseEntity<>("V2 ok", HttpStatus.OK);
    }

    @GetMapping("/kms-response-string-v3")
    public String responseBodyV3(){
        return "V3 ok";
    }

    @GetMapping("/kms-response-json-v1")
    public ResponseEntity<BmiData> responseJsonV1(){
        BmiData bmiData = new BmiData();
        bmiData.setName("kms");
        bmiData.setHeight(178);
        bmiData.setWeight(74);
        return new ResponseEntity<>(bmiData,HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/kms-response-json-v2")
    public BmiData responseJsonV2(){
        BmiData bmiData = new BmiData();
        bmiData.setName("jyb");
        bmiData.setHeight(160);
        bmiData.setWeight(47);
        return bmiData;
    }
}
