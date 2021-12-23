# Adapter 패턴 구형

Controller를 다형성을 이용해서 다양한 Controller를 호출할 수 있게 하자.

![](img/V5_Sturcture.JPG)  

>출처 김영한 선생님 강의

- 핸들러 어댑터 : 중간에 어댑터 역할을 하는 어댑터이다. 다양한 종류의 컨트롤러를 호출할 수 있게 한다.
- 핸들러 : 컨트롤러의 이름을 더 넓은 범위인 핸들러로 변경.  

먼저 어댑터 인터페이스를 만들어주자.

## 1. MyHandlerAdapter

```java
package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface MyHandlerAdapter {

    boolean supports(Object handler);

    ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException;
}
```

앞에서 구현했던 ControllerV3을 지원하는 어댑터를 만들 것이다.

## 2. ControllerV3HandlerAdapter

```java
package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {


    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV3);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        ControllerV3 controller = (ControllerV3) handler;

        Map<String,String> paramMap = createParamMap(request);

        ModelView mv = controller.process(paramMap);
        return mv;
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String,String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName,request.getParameter(paramName)));
        return paramMap;
    }
}

```

## 3. FrontControllerServletV5

```java

```