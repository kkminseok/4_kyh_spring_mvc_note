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
import java.util.List;

@WebServlet(name = "memberListServlet",urlPatterns = "/servlet/member")
public class MemberListServlet extends HttpServlet {
    MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //셋팅
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");

        List<Member> members = memberRepository.findByAll();

        PrintWriter w = response.getWriter();

        w.write("<html>");
        w.write("<head>");
        w.write("    <meta charset=\"UTF-8\">");
        w.write("    <title>Title</title>");
        w.write("</head>");
        w.write("<body>");
        w.write("<a href=\"/index.html\">메인</a>");
        w.write("<table>");
        w.write("    <thead>");
        w.write("    <th>id</th>");
        w.write("    <th>username</th>");
        w.write("    <th>Height</th>");
        w.write("    <th>Weight</th>");
        w.write("    <th>BMI</th>");
        w.write("    </thead>");
        w.write("    <tbody>");
        for(Member member : members){
            w.write("    <tr>");
            w.write("        <td>" + member.getId() + "</td>") ;
            w.write("        <td>" + member.getUsername() + "</td>") ;
            w.write("        <td>" + member.getHeight() + "</td>") ;
            w.write("        <td>" + member.getWeight() + "</td>") ;
            w.write("        <td>" + member.getBmi() + "</td>") ;
        }
        w.write("    </tbody>");
        w.write("</table>");
        w.write("</body>");
        w.write("</html>");
    }
}
