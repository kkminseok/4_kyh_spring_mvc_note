package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "httpResponseHTML", urlPatterns = "/kms-response-htmltest")
public class HttpResponseHTML extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Content-Type을 HTML로 지정
        response.setContentType("text/html");
        //인코딩 타입 지정
        response.setCharacterEncoding("utf-8");

        PrintWriter writer = response.getWriter();

        writer.println("<html>");
        writer.println("<body>");
        writer.println("<div> 이렇게 코딩은 하지 말자. </div>");
        writer.println("</body>");
        writer.println("</html>");

    }
}
