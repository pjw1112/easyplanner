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
<!--  hammer -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/hammer.js/2.0.8/hammer.min.js"></script>
<!--  내가 만든 script -->
<script src="script/json_list.js" defer></script>
<script src="script/form_script.js" defer></script>
<script src="script/basic_script.js" defer></script>

<!--  내가 만든 css -->
<link rel="stylesheet" href="css/basic.css" >
<link rel="stylesheet" href="css/media_query.css" >

</head>
<body>

    <div class="wrapper">
        <div class="header">
            <div class="logo">
                <a href="index.jsp"><!--<img src="images/logo.png" alt="">--><span class="letter_e">e</span>`지 플래너</a>
            </div>
            <div class="menu">
                <ul>
                <c:choose>
				    <c:when test="${empty sessionScope.login_id}">
				        <!-- 로그인이 되어있지 않은 경우 -->
				        <li><a class="login_click color99" style="cursor:pointer">로그인</a></li>
				        <li><a class="join_click color99" style="cursor:pointer">회원가입</a></li>
				        <li><a href="users_logout.do" class="log_out color99" style="display:none">로그아웃</a></li>
				        <li><a href="users_info.do" class="my_info color99" style="display:none">내정보</a></li>
				    	
				    </c:when>
				    <c:otherwise>
				        <!-- 로그인이 되어있는 경우 -->
				        <li><a class="login_click color99" style="display:none">로그인</a></li>
				        <li><a class="join_click color99" style="display:none">회원가입</a></li>
				        <li><a href="users_logout.do" class="log_out color99">로그아웃</a></li>
				        <li><a href="users_info.do" class="my_info color99">내정보</a></li>
				     
				    </c:otherwise>
				</c:choose>
                </ul>
            </div>
        </div>

        <div class="body">

            <div class="side">
                <div class="br"></div>
                <div class="br"></div>
                <div class="br"></div>
                <div class="menu1">


                    <div class="dropdown">
                        <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown">월별
                            보기</button>
                        <ul class="dropdown-menu dropdown-menu-right">
                            <li class="view_type"><a href="#">
                                    <div class="view_type_choice">
                                        <div>일</div>
                                        <div>1</div>
                                    </div>
                                </a></li>
                            <li class="view_type"><a href="#">
                                    <div class="view_type_choice">
                                        <div>주</div>
                                        <div>2</div>
                                    </div>
                                </a></li>
                            <li class="view_type"><a href="#">
                                    <div class="view_type_choice">
                                        <div>월</div>
                                        <div>3</div>
                                    </div>
                                </a></li>
                            <li class="view_type"><a href="#">
                                    <div class="view_type_choice">
                                        <div>년</div>
                                        <div>4</div>
                                    </div>
                                </a></li>
                        </ul>
                    </div>







                </div>
                <div class="menu2">
                    <ul>
                        <li></li>
                        <li></li>
                        <li></li>
                        <li></li>
                        <li></li>
                        <li></li>
                        <li></li>
                    </ul>
                </div>
                <div class="menu3">
                    <ul>
                        <li></li>
                        <li></li>
                        <li></li>
                        <li></li>
                    </ul>

                </div>


            </div>
            <div class="calendar">
            
            <hr>
            	
               	<div class="mobile_view_wrapper">
               	
                	<div class="btn_question btn-view btn-view-left"  >?</div>
                	<div class="btn_y btn-view"  >년</div>
                	<div class="btn_m btn-view"  >월</div>
                	<div class="btn_w btn-view"  >주</div>
                	<div class="btn_d btn-view"  >일</div>
               	</div>
            <hr class="hr2">
            
				<div class="c_top">
                    <div class="c_top_left"></div>
                    <div class="c_top_center">
                        <div class="c_top_center_in left_arrow">&lt;</div>
                        <div class="c_top_center_in"></div>
                        <div class="c_top_center_in">
                            <div class="month_display"></div>
                        </div>
                        <div class="c_top_center_in"></div>
                        <div class="c_top_center_in right_arrow">&gt;</div>
                    </div>
                    <div class="c_top_right"></div>

                </div>
                
                <div class="list_column"></div>
                <div id="calendar_body"></div>
                
                <div style="height: 50px; padding: 10px 0;">
                	<input type="text" class="form-control" id="easy_prompt" placeholder="대화형으로 일정을 관리해보세요">
				</div>
			</div>
    </div>


    <!-- default display none -->
    <div class="login_box">
        <div class="login_box_inner">
            <form action="users_login.do" method="post">
 				<fieldset>

                <div class="form-group">
                    <label for="id_login">아이디 </label> <input type="text" id="id_login" name="id_login"
                        class="form-control" />
                </div>

                <div class="form-group">
                    <label for="pass_login">비밀번호 </label> <input type="password" id="pass_login" name="pass_login"
                        class="form-control" />
                </div>

                <div class="form-group">
                	<div>
                    <input type="checkbox" id="remember_id" name="remember_id" />
                    <label for="remember_id"> 아이디 기억하기</label>
                    </div>
                	<div>
                    <input type="checkbox" id="remember_login" name="remember_login" />
                    <label for="remember_login"> 로그인 유지하기</label>
                    </div>
                </div>
                <br>
                <div class="form-group">
                    <input type="submit" value="로그인" class="btn btn-danger btn-block ">
                    <!-- 
                    <a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=5311dcef4df09cce6029d72479addf8c&redirect_uri=http://localhost:8080/easy_planner/Kakao_login" id="kakao_login_button" class="btn btn-success btn-warning ">카카오로 로그인</a>
                    <a href="#" id="naver_login_button" class="btn btn-success btn-block ">네이버로 로그인</a>
                     -->
                </div>
                <!-- 
                <div class="form-group find_id_pass">
                    <span class="find_id_button"><a href="#">아이디 찾기</a></span> <span class="find_pass_button"><a
                            href="#">비밀번호 찾기</a></span>
                </div>
 				-->
                </fieldset>
            </form>
        </div>
    </div>


    <!-- default display none -->
    <div class="join_box">
        <div class="join_box_inner">
            <form action="users_join.do" id="users_create_form" method="post">
                <fieldset>
                    <div class="form-group-first">
                        <div class="logo_join">
                           <!--   <img src="images/logo.png" class="logo_join">-->
                        e`지 플래너
                        </div>
                        <p>회원으로 가입해주셔서 감사합니다</p>
                    </div>

                    <div class="form-group">
                        <div class="id_check check_form">
                            <label for="id_join">아이디 </label> 
                            <label for="id_join" class="check_message"></label>
                        </div>
                        <input type="text" id="id_join" name="id_join" class="form-control" />
                    </div>

                    <div class="form-group">
                        <div class="pass_check check_form">
                            <label for="pass_join">비밀번호 </label> 
                            <label for="pass_join" class="check_message"></label>
                        </div>
                        <input type="password" id="pass_join" name="pass_join" class="form-control" />
                    </div>

                    <div class="form-group">
                        <div class="pass2_check check_form">
                            <label for="pass2_join">비밀번호 확인 </label> 
                            <label for="pass2_join" class="check_message"></label>
                        </div>
                        <input type="password" id="pass2_join" name="pass2_join" class="form-control" />
                    </div>

                    <div class="form-group">
                        <div class="email_check check_form">
                            <label for="email_join">이메일 </label> 
                            <label for="email_join" class="check_message"></label>
                        </div>
                        <input type="text" id="email_join" name="email_join" class="form-control" />
                    </div>

                    <div class="form-group">
                        <div class="birth_check check_form">
                            <label for="birth_join">생일 </label> 
                            <label for="birth_join" class="check_message"></label>
                        </div>
                        <input type="date" id="birth_join" name="birth_join" class="form-control" />
                    </div>
                    <br>
                    <br>
                    <div class="form-group">
                        <input type="submit" value="회원가입" class="btn btn-danger btn-block ">
                        <input type="button" value="취소" class="btn btn-danger btn-block cancel ">
                    </div>

                </fieldset>
            </form>
        </div>
    </div>




    <!-- default display none -->
    <div class="find_id">
        <div class="subject">아이디 찾기</div>
        <div class="description">회원가입시 입력한 이메일로 인증번호를 보내드립니다.</div>
        <div class="content">
            <form action="id_find1" method="post">
                <fieldset>
                    <div class="form-group">
                        <label for="email_f">이메일 </label> 
                        <input type="email" id="email_f" name="email_f" class="form-control" />
                    </div>
                    <div class="form-group">
                        <input type="submit" value="인증메일 보내기" class="btn btn-info btn-block ">
                    </div>
                </fieldset>

            </form>
            <br>
            <form action="id_find2" method="post">
                <fieldset>
                    <div class="form-group">
                        <label for="number">인증번호 </label> <input type="text" id="number" name="number"
                            class="form-control" />
                    </div>
                    <div class="form-group">
                        <input type="submit" value="인증번호 확인" class="btn btn-success btn-block ">
                    </div>
                </fieldset>
            </form>
        </div>


    </div>



    <!-- default display none -->
    <div class="insert_schedule">
        <div class="subject">일정 추가</div>
        <div class="description">일정 내용과 시작 ~ 종료 시간을 설정해주세요</div>
        <div class="content">
            <form action="schedule_create.do" method="post">
                <fieldset>
                    <div class="form-group">
                        <label for="content">일정</label>
                        <textarea rows="5" id="content" name="content" class="form-control insert_schedule_write_form"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="start_date">시작 시간</label> 
                        <input type="datetime-local" id="start_date" name="start_date" class="form-control" />
                    </div>
                    <div class="form-group">
                        <label for="end_date">종료 시간</label> 
                        <input type="datetime-local" id="end_date" name="end_date" class="form-control" />
                    </div>
                    <div class="form-group insert_schedule_end">
                        <input type="submit" value="저장" class="btn btn-info cancel btn_margin_left"> 
                        <a href="#" class="btn btn-danger cancel">취소</a>
					</div>
                </fieldset>
            </form>
        </div>
    </div>






    <!-- default display none -->
    <div class="update_schedule">
        <div class="subject">일정 수정</div>
        <div class="description">일정 내용과 시작 ~ 종료 시간을 설정해주세요</div>
        <div class="content">
            <form action="schedule_update.do" method="post">
                <fieldset>
                    <div class="form-group">
                    	<input type="hidden" id="s_index" name="s_index" class="form-control hidden" />
                        <label for="content">일정</label>
                        <textarea rows="5" id="content2" name="content2" class="form-control insert_schedule_write_form"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="start_date">시작 시간</label> 
                        <input type="datetime-local" id="start_date2" name="start_date2" class="form-control" />
                    </div>
                    <div class="form-group">
                        <label for="end_date">종료 시간</label> 
                        <input type="datetime-local" id="end_date2" name="end_date2" class="form-control" />
                    </div>
                    <div class="form-group insert_schedule_end">
                        <input type="submit" value="저장" class="btn btn-info btn_margin_left"> 
                        <a href="schedule_delete.do" class="btn btn-danger schedule_delete btn_margin_left">삭제</a>
                        <input type="reset" value="취소" class="btn btn-danger cancel">
					</div>
                </fieldset>
            </form>
        </div>
    </div>

	<div class="q_box">
	<div class="pp">도움말</div>
	<br>
	<div class="pp">1. 로그인 이후 일정 추가/변경/삭제 가능</div>
	<br>
	<div class="pp">2. 키보드 사용 1, 2, 3, 4, esc, 방향키 </div>
	<br>
	<div class="pp">3. 아래쪽 대화형 프롬프트 문장형 입력 가능</div>
	<br>
	</div>


    <div class="black"></div>
</div>

<!-- 제미나이 ai api -->
<script type="importmap">
{"imports": {"@google/generative-ai": "https://esm.run/@google/generative-ai"}}
</script>
<script type="module" src="script/gemini_script.js"></script>

</body>

</html>