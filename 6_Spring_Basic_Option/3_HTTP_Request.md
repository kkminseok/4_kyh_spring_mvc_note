# HTTP 요청 파라미터 - 쿼리파라미터, HTML FORM

클라이언트에서 서버로 요청 데이터를 전달할 때는 주로 다음 3가지 방법을 사용한다.

- GET - 쿼리 파라미터
  - 메시지 바디 없이, URL의 쿼리 파라미터에 데이터를 포함해서 전달
  - /url?username=kms&hegiht=178

- POST - HTML Form
  - content-type:application/x-www-form-urlencoded
  - 메시지 바디에 쿼리 파라미터 형식으로 전달함.  

- HTTP message body에 데이터를 직접 담아서 요청
  - HTTP API에 주로 사용된다. JSON, XML, TEXT
  - 데이터 형식은 주로 JSON이 사용된다.

## GET,쿼리 파라미터로 전송.

자바 파일을 만들자.

```java
package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
public class RequestParamController {

    //기본적인 형태

    @RequestMapping("/kms-request-param-v1")
    public void requestParamv1(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String username = request.getParameter("username");
        int height = Integer.parseInt(request.getParameter("height"));
        log.info("username = {} , height = {}",username,height);

        response.getWriter().write("ok");
    }
}

```

기본적인 예제이다. <http://localhost:8080/kms-request-param-v1?username=kms&height=178> 으로 들어가면 된다.

## Post로 Form 전송

전송하기 위해 html 파일을 만들어야한다.

경로는 _main/resources/static/basic/kms-from.html_ 이다.

```html
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Title</title>
</head>
<body>
<form action="/kms-request-param-v1" method="post">
  username: <input type="text" name="username" />
  height: <input type="text" name="height" />
  <button type="submit">전송</button>
</form>
</body>
</html>
```

<localhost:8080/kms-request-param-v1> 으로 가면 입력폼에 입력하고 전송 버튼을 누르면 

위에서 작성한 java에서 요청값을 받아 로그에 잘 찍고 잘 받아온다.

![](img/querybasic.png)  

쿼리 파라미터와 다른 점은 뭘까?

url에 데이터를 넣냐 html에 데이터 넣냐의 차이다.

보안적으로 GET Query보다는 Post Form이 더 좋아보이지 않는가?

GET은 데이터를 보낸다는 것 보다는 조회목적으로 쓰자.



