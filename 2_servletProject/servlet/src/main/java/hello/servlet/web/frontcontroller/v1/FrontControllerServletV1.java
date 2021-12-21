package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//url을 보면 v1으로 들어오는 url은 모두 이 서블릿을 거치게 되어있다.
@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {

    private Map<String,ControllerV1> controllerV1Map = new HashMap<>();

    //생성자
    public FrontControllerServletV1() {
        controllerV1Map.put("/front-controller/v1/members/new-form",new MemberFormControllerV1());
        controllerV1Map.put("/front-controller/v1/members/save",new MemberSaveControllerV1());
        controllerV1Map.put("/front-controller/v1/members",new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURL = request.getRequestURI();
        //요청 url로 들어온 컨트롤러를 꺼냄
        ControllerV1 controllerV1 = controllerV1Map.get(requestURL);

        //만약 컨트롤러가 없다면
        if(controllerV1 == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //있다면 함수 실행.
        controllerV1.process(request,response);


    }
}
