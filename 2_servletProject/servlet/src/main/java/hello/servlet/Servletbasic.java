package hello.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "servletbasic", urlPatterns = "/lol")
public class Servletbasic extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Serlvet start!!");
        System.out.println("Request 정보 : " + request);
        System.out.println("Response 정보 : " + response);

        String getuser = request.getParameter("username");
        String getname = request.getParameter("chamname");

        System.out.println("가져온 유저 : " + getuser);
        System.out.println("가져온 chamname : " +getname);

        //전송형태 지정
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");

        response.getWriter().write(getuser);
        response.getWriter().write(getname);


    }
}
