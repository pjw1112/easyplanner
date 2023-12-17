<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>easy planner</title>
        <style type="text/css">
            body {
                width: 100vw;
                height: 100vh;
                position: relative;
            }

            .wrapper {
                position: absolute;
                left: 50%;
                top: 50%;
                transform: translateX(-50%) translateY(-50%);
            }
        </style>
    </head>

    <body>
        <div class="wrapper">
            <h1>로그인에 실패했습니다.</h1>
             <h1>아이디와 비밀번호를 다시 확인해주세요!</h1>
        </div>
        <script type="text/javascript">
            setTimeout(function () {
                location.href = '${pageContext.request.contextPath}/index.jsp';
            }, 2000);
        </script>
    </body>

    </html>