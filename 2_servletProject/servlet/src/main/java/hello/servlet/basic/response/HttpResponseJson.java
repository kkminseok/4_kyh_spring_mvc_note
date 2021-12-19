package hello.servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.JSONData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "httpResponseJson",urlPatterns = "/kms-response-jsontest")
public class HttpResponseJson extends HttpServlet {
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //ContentType은 application/json으로 설정해야한다.
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        //JSon으로 만들 객체 생성
        JSONData jsonData = new JSONData();
        jsonData.setUsername("kms");
        jsonData.setAge(95);

        //Json 형식으로 바꿔주자.
        String convertdata = objectMapper.writeValueAsString(jsonData);

        response.getWriter().write(convertdata);
    }
}
