package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

//url : localhost:8080//kms-request-querystring?username=kms&age=27
@WebServlet(name = "requestquerystring", urlPatterns = "/kms-request-querystring")
public class ReqeustQueryString extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("전체 파라미터 조회하는 법");
        request.getParameterNames().asIterator()
                .forEachRemaining(param -> System.out.println(request.getParameter(param)));

        System.out.println("단일 파라미터 조회하는 법");
        String username = request.getParameter("username");
        String age = request.getParameter("age");
        System.out.println("username : " +username);
        System.out.println("age : " + age);

        System.out.println("복수 파라미터 조회하는 법");
        //url : localhost:8080/kms-request-querystring?username=kms&age=27&username=Vayne
        String[] names = request.getParameterValues("username");
        for(String name : names){
            System.out.println("usernames : " + name);
        }

        response.getWriter().write("ok");
    }
}
