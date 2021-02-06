<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
	    
<html>
	<head>
	    <title>top</title>
	    <link rel="stylesheet" href="css/headerStyle.css">
		<script type="text/javascript">

			function changeView(value){
				if(value == "0"){//홈페이지 이동
					location.href="main.do";
				}else if(value == "1"){
					location.href="CalendarDetail.cal";
				}else if(value == "2"){
					location.href="#";
				}else if(value == "2"){//게시판
					location.href="#";
				}else if(value == "3"){//출결,연차
					location.href="MainHRFormAction.vc";
				}else if(value == "4"){//주소록
					location.href="MemberAddressBookAction.do";
				}else if(value == "5"){//마이페이지
					location.href="MemberLoadProfileAction.do";
				}else if (value == "6") {//로그아웃
					location.href="MemberLogoutAction.do";
				}else if(value == "7"){//회원등록
					location.href="signUpForm.do";
				}
			}
		</script>

	</head>
	<body>
		<div id = "wrap">
			<p id="wrap2">
				<button id="symbolBtn" class = "btn btn-primary"><img src="img/symbol.png"></button>
				<button id="homeBtn"class = "btn btn-primary" onclick = "changeView(0)"><img src="img/home.png"></button>
		        <button id="sceduleBtn" class="btn btn-primary" onclick="changeView(1)"><img src="img/schedule.png"></button>
		        <button id="boardBtn" class="btn btn-primary" onclick="changeView(2)"><img src="img/board.png"></button>
		        <button id="calBtn" class="btn btn-primary" onclick = "changeView(3)"><img src="img/calendar.png"></button>
		        <button id="addrBtn" class="btn btn-primary" onclick="changeView(4)"><img src="img/addr.png"></button>
		        <c:forEach var="member" items="${sessionScope.memberInfo}">
					<c:choose>
				        	<c:when test="${member.member_administrator > 1}">
				        		<button id="profileBtn" class="btn btn-primary" onclick="changeView(7)"><img src="img/addMember.png"></button>
				        	</c:when> 
				    </c:choose>
				</c:forEach>
		        <button id="proBtn" class="btn btn-primary" onclick="changeView(5)"><img src="img/profile.png"></button>
		        <button id="logoutBtn" class="btn btn-primary" onclick = "changeView(6)"><img src="img/logout.png"></button>
			</p>
		</div>
	</body>
</html>
