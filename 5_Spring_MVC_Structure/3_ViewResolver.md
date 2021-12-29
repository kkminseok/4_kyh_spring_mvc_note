# 뷰 리졸버

전에 작성한 OldController를 View를 조회할 수 있게 수정할 것이다.

```java
System.out.println("OldController.handlerRequest");
return new ModelAndView("new-form");
```

리턴값만 수정해주었다. 다시 실행해보면 'Whitelabel Error Page'가 뜨면서 콘솔에는 문자열이 찍힐 것이다.

application.properties에서 다음과 같이 코드를 추가해주면?

```java
spring.mvc.view.prefix=/WEB-INF/views/
spring.mvc.view.suffix=.jsp
```

다시 실행해보면

![](img/propertiesweb.JPG)  

내가 예전에 작성한 페이지가 뜬다.

왜 그런걸까?

스프링 부트는 **InternalResourceViewResolver**라는 뷰 리졸버를 자동으로 등록하는데, 이때 application.properties에 등록한 **spring.mvc.view.prefix**, **spring.mvc.view.suffix** 설정정보를 사용해서 등록한다.

권장하지 않지만 사실상 이렇게 경로를 주어도 동작은한다.

```java
return new ModelAndView("/WEB-INF/views/new-form.jsp");
```

뷰 리졸버도 우선순위에 따라 스프링 부트가 찾는다.

```text
(생략된 부분이 많다.)
1 = BeanNameViewResolver : 빈 이름으로 뷰를 찾아서 반환한다.
2 = InternalResourceViewResolver : JSP를 처리할 수 있는 뷰를 반환한다.
```

위 코드같은 경우 빈 이름을 지정해주지 않았으므로 2번인 InternalResourceViewResolver에서 찾게 된다.

흐름을 알아보자.

1. 핸들러 어댑터를 통해 **new-form**이라는 논리 뷰 이름을 획득.
2. new-form이라는 뷰 이름으로 viewResolver를 순서대로 호출하는데, 빈 이름으로 등록되지 않았으므로 타고타고 내려가다가 **InternalResourceViewResolver**가 호출
3. InternalResourceViewResolver는 InternalResourceView를 반환한다.
4. InternalResourceView는 JSP처럼 forward()를 호출해서 처리할 수 있는 경우에 사용한다.
5. view.render()가 호출되고, InternalResourceView는 forward()를 사용해서 JSP를 실행한다.

참고로 JSP를 제외한 나머지 뷰 템플릿들은 forward() 과정 없이 바로 렌더링된다.


