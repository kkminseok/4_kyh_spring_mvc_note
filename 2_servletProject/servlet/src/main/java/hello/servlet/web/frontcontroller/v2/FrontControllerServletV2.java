package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//url을 보면 v1으로 들어오는 url은 모두 이 서블릿을 거치게 되어있다.
@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {

    private Map<String,ControllerV2> controllerV2Map = new HashMap<>();

    //생성자
    public FrontControllerServletV2() {
        controllerV2Map.put("/front-controller/v2/members/new-form",new MemberFormControllerV2());
        controllerV2Map.put("/front-controller/v2/members/save",new MemberSaveControllerV2());
        controllerV2Map.put("/front-controller/v2/members",new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURL = request.getRequestURI();
        //요청 url로 들어온 컨트롤러를 꺼냄
        ControllerV2 controllerV2 = controllerV2Map.get(requestURL);

        //만약 컨트롤러가 없다면
        if(controllerV2 == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //있다면 함수 실행.
        MyView myView = controllerV2.process(request, response);
        myView.render(request,response);
    }
}
