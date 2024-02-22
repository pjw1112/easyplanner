<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" >
<meta name="viewport" content="width=device-width, initial-scale=1" >
<link rel="icon" type="image/png" href="images/favicon.png">
<title>e`지 플래너♡</title>
<!--  네이버 마루부리 글씨체 css -->
<link rel="stylesheet" href="https://hangeul.pstatic.net/hangeul_static/css/maru-buri.css" >
<link href="https://hangeul.pstatic.net/hangeul_static/css/NanumJungHagSaeng.css" rel="stylesheet">
<!--  부트스트랩 & 제이쿼리 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" >
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<!--  폰트어썸 -->
<script src="https://kit.fontawesome.com/5081dee0c3.js" crossorigin="anonymous"></script>
<!--  내가 만든 script -->
<!-- 
<script src="script/json_list.js" defer></script>
<script src="script/form_script.js" defer></script>
<script src="script/basic_script.js" defer></script>
 -->
<!--  내가 만든 css -->
<link rel="stylesheet" href="css/basic.css" >
<link rel="stylesheet" href="css/media_query.css" >
<style type="text/css">


.my_info_wrapper{


height : 100%;
margin : 50px auto;
padding : 30px;
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

</style>
</head>
<body>
<div class="my_info_wrapper">
				<form action="users_update.do" method="post">
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
                            <label for="id">아이디 </label> 
                            <label for="id" class="check_message"></label>
                        </div>
                        <input type="text" id="id" name="id" class="form-control" value="${sessionScope.login_dto.u_id}" readonly="readonly"/>
                    </div>

                    <div class="form-group">
                        <div class="email_check check_form">
                            <label for="email">이메일 </label> 
                            <label for="emai" class="check_message"></label>
                        </div>
                        <input type="text" id="email" name="email" class="form-control" value="${sessionScope.login_dto.u_email}"  />
                    </div>

                    <div class="form-group">
                        <div class="birth_check check_form">
                            <label for="birth">생일 </label> 
                            <label for="birth_join" class="check_message"></label>
                        </div>
                        <input type="date" id="birth" name="birth" class="form-control" value="${sessionScope.login_dto.u_birth}"  />
                    </div>
                    
                    <br>
                    <br>
                    
					<div class="form-group">
                        <div class="pass_check check_form">
                            <label for="pass">비밀번호 </label> 
                            <label for="pass" class="check_message"></label>
                        </div>
                        <input type="password" id="pass" name="pass" class="form-control" />
                    </div>
                    
                    <br>
		
			<div class="form-group">
				<input type="submit" class="btn btn-info btn-block" value="정보 수정">
			</div>
		
			<div class="form-group">
				<input type="button" class="btn btn-danger btn-block" id="user_delete" value="회원 탈퇴">
			</div>
			<div class="form-group">
				<input type="button" class="btn btn-danger btn-block" onclick="location.href='view.do'" value="뒤로 가기">
			</div>
			</form>
		</div>
<script type="text/javascript">
$("#user_delete").on("click", function() {

	if ($("#pass").val().length < 4 ) {
		alert("비밀번호 입력을 확인해주세요");
		$("#pass").focus();
		return false;
		
	}else{

		
	}
	
	$.ajax({
		url : "users_delete.do",
		type : "POST",
		dataType : "text",
		data : {
			id : $("#id").val(),
			pass : $("#pass").val(),
			
		},
		error : function(xhr, status, msg) {
			alert(status + "/" + msg);
		},
		success : function(text) {
			console.log("유저 딜리트 : " + (text==1?"성공":"실패"));
			let message;
			let loc;
			if(text==1){
				message="탈퇴 처리 되었습니다.";
				loc="view.do";
			}else{
				message="탈퇴 실패. 비밀번호를 다시 확인해주세요.";
				loc="";
			}

			
			
			alert(message);
			location.href=loc;
			
		}
	});
	
	
	
});




</script>


</body>

</html>