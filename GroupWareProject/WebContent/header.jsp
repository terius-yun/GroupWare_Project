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
				}else if(value == "2"){//게시판 이동
					location.href="BoardListForm.bo";
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
			<div id="navbar">
				<div id="symbolbox">
					<button id="symbolBtn" class = "btn btn-primary"><img id="symbol" src="img/symbol2.png"></button>
				</div>
				<ul id="wrap2">
						<li><button id="homeBtn"class = "btn btn-primary" onclick = "changeView(0)"><img src="img/home.png"></button></li>
				       <li><button id="sceduleBtn" class="btn btn-primary" onclick="changeView(1)"><img src="img/schedule.png"></button></li>
				       <li><button id="boardBtn" class="btn btn-primary" onclick="changeView(2)"><img src="img/board.png"></button></li>
				       <li><button id="calBtn" class="btn btn-primary" onclick = "changeView(3)"><img src="img/calendar.png"></button></li>
				       <li><button id="addrBtn" class="btn btn-primary" onclick="changeView(4)"><img src="img/addr.png"></button></li>
				        <c:forEach var="member" items="${sessionScope.memberInfo}">
							<c:choose>
						        	<c:when test="${member.member_administrator > 1}">
						        		<li><button id="profileBtn" class="btn btn-primary" onclick="changeView(7)"><img src="img/addMember.png"></button></li>
						        	</c:when> 
						    </c:choose>
						</c:forEach>
				        <li><button id="proBtn" class="btn btn-primary" onclick="changeView(5)"><img src="img/profile.png"></button></li>
				 </ul>
				 <div id="logout">
			        <button id="logoutBtn" class="btn btn-primary" onclick = "changeView(6)"><img src="img/logout.png"></button>
			     </div>
		     </div>
		</div>
	</body>
</html>
