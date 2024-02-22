<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<link rel="icon" type="image/png" href="images/favicon.png">
        <title>e`지 플래너♡</title>

<!--  네이버 마루부리 글씨체 css -->
<link rel="stylesheet" href="https://hangeul.pstatic.net/hangeul_static/css/maru-buri.css">
<link href="https://hangeul.pstatic.net/hangeul_static/css/NanumJungHagSaeng.css" rel="stylesheet">
<!--  부트스트랩 & 제이쿼리 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<!--  폰트어썸 -->
<script src="https://kit.fontawesome.com/5081dee0c3.js" crossorigin="anonymous"></script>
<link rel="stylesheet" href="css/media_query.css" >

<style type="text/css">


.my_info_wrapper{

/*
height : 100%;
margin : 50px auto;
padding : 100px 10px;
border : 1px solid #ccc;
border-radius: 20px;
box-sizing: border-box;
}

.logo_join{
font-size: 30px;
}
.pp{
 margin : 30px autp;
 font-size: 20px;
}
*/
</style>

</head>

<body>
	
		<div class="my_info_wrapper">
					<div class="form-group">
						<div class="form-group-first">
                        	<div class="logo_join">
                         	 e`지 플래너
                        	</div>
                        	<p class="pp">회원 정보</p>
                   		 </div>
                   	</div>	 
                   	<div class="form-group">
                   		<div class="id_check check_form">
                            <label for="id_join">아이디 </label> 
                            <label for="id_join" class="check_message"></label>
                        </div>
                        <input type="text" id="id" name="id" class="form-control" value="${sessionScope.login_dto.u_id}" readonly="readonly"/>
                    </div>

                    <div class="form-group">
                        <div class="email_check check_form">
                            <label for="email_join">이메일 </label> 
                            <label for="email_join" class="check_message"></label>
                        </div>
                        <input type="text" id="email" name="email" class="form-control" value="${sessionScope.login_dto.u_email}"  />
                    </div>

                    <div class="form-group">
                        <div class="birth_check check_form">
                            <label for="birth_join">생일 </label> <label for="birth_join" class="check_message"></label>
                        </div>
                        <input type="date" id="birth" name="birth" class="form-control" value="${sessionScope.login_dto.u_birth}"  />
                    </div>
                    
                    <br>
                    <br>
                    
					<div class="form-group">
                        <div class="pass_check check_form">
                            <label for="pass_join">비밀번호 </label> <label for="pass_join" class="check_message"></label>
                        </div>
                        <input type="password" id="pass" name="pass" class="form-control" />
                    </div>
                    
                    <br>
		
			<div class="form-group row">
				<a href="users_update.do" class="btn btn-info btn-block">정보 수정</a>
			</div>
		
			<div class="form-group row">
				<a href="users_delete.do" class="btn btn-danger btn-block">회원 탈퇴</a>
			</div>
			<div class="form-group row">
				<input type="button" class="btn btn-danger btn-block" onclick="history.go(-1);" value="뒤로 가기">
			</div>
		</div>


		

</body>

</html>