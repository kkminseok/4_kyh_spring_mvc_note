package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "httpResponse",urlPatterns = "/kms-response-test")
public class HttpResponse extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //status line 지정 200
        response.setStatus(HttpServletResponse.SC_OK);

        //response header 셋팅
        response.setHeader("Content-Type","text/plain;charset=utf8");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("my-header","hello");

        //header 편의 메서드
        content(response);
        cookie(response);
        redirect(response);
        //message body
        response.getWriter().write("ok");
    }

    private void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/basic/kms-message-body.html");

        //편의 기능없이 setHeader로 만든 것. 302
        //response.setStatus(HttpServletResponse.SC_FOUND);
        //response.setHeader("Location","/basic/kms-message-body.html");

    }

    private void cookie(HttpServletResponse response) {
        //쿠키 생성
        Cookie cookie = new Cookie("mycookie","kms");
        //쿠키 만료일자 지정. 단위는 초
        cookie.setMaxAge(600);
        response.addCookie(cookie);

        //밑 부분은 편의기능 없이 직접 헤더를 조작하여 쿠키를 설정
        //response.setHeader("Set-Cookie", "mycookie=kms; Max-Age=600");
    }
    private void content(HttpServletResponse response) {
        //setHeader()없이 해당 메소드로 편하게 지정해줄 수 있다.
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
    }
}
