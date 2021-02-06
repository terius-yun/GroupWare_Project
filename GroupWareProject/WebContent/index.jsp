<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<%
			request.setCharacterEncoding("utf-8");
		%>
		<meta charset="utf-8">
		<title>로그인 화면</title>	
		<script type="text/javascript">
			function checkValue(){
		        inputForm = eval("document.LoginInfo");
		        if(!inputForm.id.value){
		            alert("아이디를 입력하세요");    
		            inputForm.id.focus();
		            return false;
		        }
		        if(!inputForm.password.value){
		            alert("비밀번호를 입력하세요");    
		            inputForm.password.focus();
		            return false;
		        }
		    }
	    </script>
	 <link rel="stylesheet" href="css/indexStyle.css">
	</head>
	<body>
		 <div id="wrap">
		        <form name="loginInfo" method="post" action="MemberLoginAction.do" onsubmit="return checkValue()"  class="bg">	      
						<img src="img/symbol2.png" class="bg"></img>
		            <table>
		                <tr>
		                    <td>아이디</td>
		                    <td><input type="text" name="emp_num" class="join bg"  maxlength="50"></td>
		                </tr>
		                <tr>
		                    <td>비밀번호</td>
		                    <td><input type="password" name="member_pw" class="join bg" maxlength="50"></td>
		                </tr>
		            </table>
		            <br>
		            <input type="submit" id="login" value="로그인"/>
		        </form>
		        <c:set var="failMessage" value="${requestScope.fail}"/>
			    <c:if test="${failMessage!=null}">    
			        <c:choose>
			            <c:when test="${failMessage=='0'}">
			                <br><font color='red'  class="bg">비밀번호를 확인해 주세요.</font>
			            </c:when>
			            <c:otherwise>
			                <br><font color='red'  class="bg">아이디를 확인해 주세요.</font>
			            </c:otherwise>
			        </c:choose>
			    </c:if>
		    </div>  
	</body>
</html>
