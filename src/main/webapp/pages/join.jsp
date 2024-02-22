<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <link rel="icon" type="image/png" href="images/favicon.png">
        <title>e`지 플래너♡</title>
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
            <h1>easy planner 에 가입하신것을 환영합니다!</h1>
        </div>
        <script type="text/javascript">
            setTimeout(function () {
                location.href = '${pageContext.request.contextPath}/index.jsp';
            }, 2000);
        </script>
    </body>

    </html>