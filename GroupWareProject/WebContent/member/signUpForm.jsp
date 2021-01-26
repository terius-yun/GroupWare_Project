<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
	<head>
	    <title>회원가입 화면</title>

	    <script type="text/javascript">
	    
// 	        // 필수 입력정보인 아이디, 비밀번호가 입력되었는지 확인하는 함수
// 	        function checkValue()
// 	        {
// 	            if(!document.userInfo.id.value){
// 	                alert("아이디를 입력하세요.");
// 	                return false;
// 	            }
	            
// 	            if(!document.userInfo.password.value){
// 	                alert("비밀번호를 입력하세요.");
// 	                return false;
// 	            }
	            
// 	            // 비밀번호와 비밀번호 확인에 입력된 값이 동일한지 확인
// 	            if(document.userInfo.password.value != document.userInfo.passwordcheck.value ){
// 	                alert("비밀번호를 동일하게 입력하세요.");
// 	                return false;
// 	            }
// 	        }
	        
	        function goLoginForm(){
	        	location.href = "main.do";
	        }
	    </script>
	</head>
	<body>
	    <!-- div 왼쪽, 오른쪽 바깥여백을 auto로 주면 중앙정렬된다.  -->
	    <div id="wrap">
	        <br><br>
	        <b><font size="6" color="gray">회원가입</font></b>
	        <br><br><br>
	        
	        
	        <!-- 입력한 값을 전송하기 위해 form 태그를 사용한다 -->
	        <!-- 값(파라미터) 전송은 POST 방식, 전송할 페이지는 signPro.jsp -->
	        
	        <form method="post" action="MemberSignUpAction.do"name="userInfo"
	        onsubmit="return checkValue()" >
	            <table>
	                <tr>
	                    <td id="title">사번</td>
	                    <td>
	                        <input type="text" name="emp_num" maxlength="20">
	                    </td>
	                </tr>    
	                <tr>
	                    <td id="title">비밀번호</td>
	                    <td>
	                        <input type="text" name="member_pw" maxlength="15">
	                    </td>
	                </tr>
	                <tr>
	                    <td id="title">이름</td>
	                    <td>
	                        <input type="text" name="member_name" maxlength="20">
	                    </td>
	                </tr>    
	                <tr>
	                    <td id="title">생년월일</td>
	                    <td>
	                        <input type="text" name="member_birth" maxlength="15">
	                    </td>
	                </tr>
	                <tr>
	                    <td id="title">전화번호</td>
	                    <td>
	                        <input type="text" name="member_pNum" maxlength="20">
	                    </td>
	                </tr>    
	                <tr>
	                    <td id="title">이메일</td>
	                    <td>
	                        <input type="text" name="member_email" maxlength="15">
	                    </td>
	                </tr>
	                <tr>
	                    <td id="title">계좌정보</td>
	                    <td>
	                        <input type="text" name="member_bank_account" maxlength="20">
	                    </td>
	                </tr>    
	                <tr>
	                    <td id="title">부서</td>
	                    <td>
	                        <input type="text" name="member_team" maxlength="15">
	                    </td>
	                </tr>
	                <tr>
	                    <td id="title">직급</td>
	                    <td>
	                        <input type="text" name="member_rank" maxlength="20">
	                    </td>
	                </tr>    
	                <tr>
	                    <td id="title">관리자권한</td>
	                    <td>
	                        <input type="text" name="member_administrator" maxlength="15">
	                    </td>
	                </tr>
	            </table>
	            <br>
	            <input type="submit" value="생성"/>
	            <input type="button" value="취소" onclick="goMain()">
	        </form>
	    </div>
	</body>
</html>