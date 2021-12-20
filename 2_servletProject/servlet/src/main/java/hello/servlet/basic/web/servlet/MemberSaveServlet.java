package hello.servlet.basic.web.servlet;

import hello.servlet.basic.domain.Member;
import hello.servlet.basic.domain.MemberRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "memberSaveServlet",urlPatterns = "/servlet/members/save")
public class MemberSaveServlet extends HttpServlet {
    MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //동작 확인용
        System.out.println("memberSaveServlet");

        //회원정보를 받아오자. 정보들은 전부 String이므로 int로 반환해줄건 해주자.
        String username = request.getParameter("username");
        int height = Integer.parseInt(request.getParameter("height"));
        int weight = Integer.parseInt(request.getParameter("weight"));

        //멤버 객체 만들고 저장소에 저장.
        Member member  = new Member(username,height,weight);
        System.out.println("member : " + member);
        memberRepository.save(member);

        //셋팅
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        PrintWriter w = response.getWriter();

        w.write("<html>\n" +
                "<head>\n" +
                " <meta charset=\"UTF-8\">\n" + "</head>\n" +
                "<body>\n" +
                "성공\n" +
                "<ul>\n" +
                "    <li>id="+member.getId()+"</li>\n" +
                "    <li>username="+member.getUsername()+"</li>\n" +
                "    <li>height="+member.getHeight()+"</li>\n" +
                "    <li>weight="+member.getWeight()+"</li>\n" +
                "    <li>bmi="+member.getBmi()+"</li\n"+
                "</ul>\n" +
                "<a href=\"/index.html\">메인</a>\n" + "</body>\n" +
                "</html>");
    }
}
