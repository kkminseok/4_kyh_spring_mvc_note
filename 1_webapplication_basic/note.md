# 웹서버와 웹어플리케이션 서버

정적인 페이지를 관리하는 웹 서버와 어플리케이션 로직을 관리하는 웹 어플리케이션 서버가 있다.

## 웹 서버

- 웹 서버는 주로 html, css, js, 이미지, 영상 등을 관리한다.
- Apache, NGINX가 있다.

## 웹 어플리케이션 서버(WAS)

- 프로그램 코드를 실행해서 어플리케이션 로직을 수행한다.
- 동적 HTML, HTTP API(JSON) 등
- 서블릿, jsp, Spring MVC
- 톰캣, jetty, Undertow 등이 있다.


## 서버 구성
1  
기존에는 WAS + DB로 서버를 구성하였다.

WAS는 정적리소스와 동적리소스 둘 다 다룰 수 있기 때문이다.  
시간이 지나면서 WAS에 과부하가 걸리면서 많은 에러를 쏟아냈고, 비싼값을 가진 어플리케이션 로직을 수행하느라 간단한 정적요청을 수행 못하는 일도 있었다.  
무엇보다도 WAS서버는 장애가 일어나면 사용자에게 에러페이지를 띄우기도 힘들었다.

2  
Web server + WAS + DB 구성

정적 리소스를 담당하는 Web을 앞에 두어 처리하고 동적인 요청처리가 필요하면 WAS로 넘겨서 처리한다.  
효율적인 리소스 관리가 가능하고 정적인 요청이 많은 경우 Web Server를 늘리고, 동적인 요청이 많은 경우 WAS서버를 늘리는 유동적인 증설도 가능했다.  
또한 WAS에서 요청이 불통이어도 Web서버를 거치기에 사용자에게 에러페이지를 보여주기에도 용이했다.

-----  

# 서블릿(Servlet)

클라이언트가 데이터를 전송할 때 처리해야할 로직이 많다.

- 서버 TCP/IP 대기, 소켓 연결
- HTTP 요청 메시지를 파싱해서 읽기.
- POST방식, GET방식 등 인지
- Content-Type 확인
- HTTP 메시지 바디 내용 파싱 
- 저장 프로세스 실행
- *비즈니스 로직 실행*
- HTTP 응답 메시지 생성 시작
- TCP/IP에 응답 전달, 소켓 종료

이렇게 많은 처리를 예전에는 직접했다.

서블릿을 지원하는 WAS를 사용하면서 '비즈니스 로직'을 제외한 모든 일은 서블릿이 자동으로 처리해주기 시작했다.

## 하는 일

서블릿을 사용할 경우 우리는 다음과 같은 로직만 작성하면 된다.
- WAS는 Request, Response 객체를 새로 만들어서 서블릿 객체 호출
- 개발자는 Request 객체에서 HTTP 요청 정보를 편리하게 꺼내서 사용.
- 개발자는 Response 객체에서 HTTP 응답 정보를 편리하게 작성.
- WAS는 Response 객체에 담겨있는 내용으로 HTTP 응답 정보를 생성.
- 서블릿 객체는 싱글톤으로 관리하기 때문에 공유 변수 사용에 주의해야한다.
- 동시 요청을 위한 멀티 쓰레드 처리 지원도 중요한 일이다.

## 멀티 쓰레드

만약 요청이 여러개 들어오면 어떻게 해야할까?

## 쓰레드  

- 쓰레드는 코드를 실행해주는 것들을 의미한다.
- 즉, 자바 메인 메서드를 실행하면 main이라는 이름의 쓰레드가 실행된다.
- 쓰레드는 한 번에 하나의 코드 라인만 수행한다.
- 동시 처리가 필요하면 쓰레드를 추가로 생성해야 한다.

요청이 하나인 경우 쓰레드 하나를 할당시켜서 처리하면 된다.

쓰레드 하나를 할당시켜서 처리하고 있는 와중에 하나의 요청이 더 들어온다면?

처리하고 있는 요청도 처리하지 못하고 새로들어온 요청도 처리하지 못한다.

이를 해결하려면 어떻게 해야할까?
간단하다 하나의 쓰레드를 더 생성하면 된다.

즉, 요청마다 쓰레드를 생성하는 것이다.

이를 통해서 동시 요청을 처리할 수 있지만, 쓰레드 생성에 드는 비용이 비싸다보니 요청때마다 쓰레드를 생성하면 응답이 느려진다.  
또한, 쓰레드 생성에 제한이 없다보니까 고객 요청이 너무 많이 오면, CPU, 메모리 임계점을 넘어서 서버가 죽어버릴 수 있다는 단점이 있다.

### 쓰레드 풀

위의 문제를 해결하기 위해서 쓰레드 풀이라는 쓰레드저장소를 애초에 만들어 놓아서 사용한다.

쓰레드 풀에 200개의 쓰레드가 있으면 요청이 들어올 때마다 가져다가 쓰면 될 것이다. 요청때마다 사용하는 것이 아니기에 비용이 절약되고, 응답시간도 빠를 것이다.

만약 쓰레드풀에 쓰레드가 없으면 요청을 거절하거나 대기시켜 서버 장애를 막을 수 있다.

그렇기에 서버를 튜닝할 때 쓰레드 관리가 중요하다.  
최대 쓰레드(max Thread)를 너무 높게 설정하면 임계치 초과로 서버가 다운될 확률이 있고, 너무 낮게 설정하면 요청이 많은 경우 클라이언트 응답이 느려지는 경우가 있다.  

자신이 만약 클라우드를 사용하고 있으면 일단 쓰레드를 관리하고, 인스턴스를 추가하는 등의 작업을 취하도록 하자.

## WAS의 멀티 쓰레드 지원

개발자는 멀티 쓰레드에 관한 것을 신경쓰지 않게 WAS가 멀티 쓰레드에 대한 부분을 처리한다.  

# HTML, HTTP API, CSR, SSR

## HTTP API

HTML페이지가 아닌 JSON형식을 사용한 데이터를 넘겨버림.  
앱, 웹 클라이어트 - 서버 / 서버 - 서버 통신 가능

## SSR(Server Side Rendering)

- 서버쪽에서 HTML 결과를 만들어서 웹 브라우저에 전달함.
- 주로 정적인 화면
- JSP, 타임리프 등 -> 정적이지는 않음.

## CSR(Client Side Rendering)

- js등을 통해 동적으로 HTML을 작성하여 웹 브라우저에 전달.
- React, Vue.js 등을 통한 Gmail, 구글 캘린더 등 

# 자바 웹 기술의 역사

- 스프링에서 최신 기술인 웹 플럭스라는 기술을 알아두자. 굉장히 어렵다.
- jsp는 속도가 느리고 기능도 부족하여 Spring boot에서 Thymeleaf를 권장.
- 

