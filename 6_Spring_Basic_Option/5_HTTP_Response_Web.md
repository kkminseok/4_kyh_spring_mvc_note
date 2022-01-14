# 1. HTTP Response - 정적 리소스,뷰 템플릿

스프링에서 응답(데이터)를 만드는 방법은 크게 3가지이다.

- 정적 리소스   
  - HTML, CSS, js 등
- 뷰 템플릿 사용
  - 동직인 HTML
- HTTP 메시지 사용
  - HTTP API를 제공하는 경우에는 HTML이 아닌 데이터를 실어 넣어야하므로 HTTP 메시지 바디에 JSON같은 형식의 데이터를 실어서 보냄.

## 1.1 정적 리소스

Spring boot에서 정적 리소스 경로는 정해져있다.

```text
/static 또는
/public 또는
/resources 또는
/META-INF/resources 
```

로 정해져있다. src/main/resources는 리소스를 보관하는 곳이고, 또 클래스패스의 시작경로이다.  
따라서 다음 디렉토리에 리소스를 넣어두면 스프링 부트가 정적 리소스로 서비스를 제공한다.

```text
src/main/resources/static
```

다음 경로에 파일이 들어있다고 치면

```text
src/main/resources/static/basic/kms-form.html
```

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


<localhost:8080/basic/kms-form.html>

으로 접근이 가능하다.

![](img/view1.png)
> 경로

![](img/view1_url.png)
> URL

## 1.2 뷰 템플릿






