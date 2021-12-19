package hello.servlet.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.JSONData;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "httpMessageBodyJson",urlPatterns = "/kms-reqeust-json")
public class HttpMessageBodyJson extends HttpServlet {
    //Json 객체로 받기위해
    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream();
        String text = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        System.out.println("message body : " + text);

        //객체 형태로 받는 방법.
        //위에 Mapper를 선언해야함.

        JSONData jsonData = objectMapper.readValue(text, JSONData.class);
        System.out.println("jsonData.username : " + jsonData.getUsername());
        System.out.println("jsonData.age : " + jsonData.getAge());

        response.getWriter().write("ok");
    }
}
