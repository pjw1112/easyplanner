<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
            <h1>회원 탈퇴 페이지 입니다.</h1>
            <h3>아이디와 비밀번호를 입력하고 탈퇴 버튼을 눌러주세요.</h3>
            <h3>주의. 모든 회원 정보가 삭제됩니다.</h3>
            
            
            <form action="users_delete.do" id="users_create_form" method="post">
                <fieldset>
                    <div class="form-group-first">
                        <div class="logo_join">
                            <img src="images/logo.png" class="logo_join">
                        </div>
                        <p>회원 탈퇴</p>
                    </div>

                    <div class="form-group">
                        <div class="id_check check_form">
                            <label for="id_delete">아이디 </label> 
                            <label for="id_delete"class="check_message"></label>
                        </div>
                        <input type="text" id="id_delete" name="id_delete" class="form-control" />
                    </div>

                    <div class="form-group">
                        <div class="pass_check check_form">
                            <label for="pass_delete">비밀번호 </label> 
                            <label for="pass_delete" class="check_message"></label>
                        </div>
                        <input type="password" id="pass_delete" name="pass_delete" class="form-control" />
                    </div>

                   <br>
                    <div class="form-group">
                        <input type="submit" value="탈퇴" class="btn btn-danger btn-block ">
                        <a href="${pageContext.request.contextPath}/index.jsp">메인으로 돌아가기</a>
                    </div>

                </fieldset>
            </form>
            
        </div>
      
    </body>

    </html>