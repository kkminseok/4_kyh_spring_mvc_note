<%@ page import="hello.servlet.basic.domain.MemberRepository" %>
<%@ page import="hello.servlet.basic.domain.Member" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    MemberRepository memberRepository = MemberRepository.getInstance();
    List<Member> members = memberRepository.findByAll();
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <a href="/index.html">메인</a>
    <table>
        <thead>
        <th>ID</th>
        <th>Username</th>
        <th>Height</th>
        <th>Weight</th>
        <th>Bmi</th>
        </thead>
        <tbody>
        <%
            for(Member member : members){
                out.write("    <tr>\n");
                out.write("        <td>" + member.getId() + "</td>\n");
                out.write("        <td>" + member.getUsername() + "</td>\n");
                out.write("        <td>" + member.getHeight() + "</td>\n");
                out.write("        <td>" + member.getWeight() + "</td>\n");
                out.write("        <td>" + member.getBmi() + "</td>\n");
                out.write("    </tr>\n");
            }
        %>
        </tbody>
    </table>
</body>
</html>
