<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    

<html>
	<head>
	    <title>top</title>
		<script type="text/javascript">
			function changeView(value){
				if(value == "0"){//홈페이지 이동
					location.href="main.do";
				}else if(value == "1"){//일정
					location.href="#";
				}else if(value == "2"){//게시판
					location.href="#";
				}else if(value == "3"){//출결,연차
					location.href="#";
				}else if(value == "4"){//주소록
					location.href="#";
				}else if(value == "5"){//마이페이지
					location.href="MemberLoadProfileAction.do";
				}else if (value == "6") {//로그아웃
					location.href="MemberLogoutAction.do";
				}
			}
		</script>
	</head>
	<body>
		<div id = "wrap">
			<p>
				<button id="homeBtn"class = "btn btn-primary" onclick = "changeView(0)">HOME</button>
		        <button id="logoutBtn" class="btn btn-primary" onclick="changeView(1)">일정</button>
		        <button id="updateBtn" class="btn btn-primary" onclick="changeView(2)">게시판</button>
		        <button id="homeBtn" class="btn btn-primary" onclick = "changeView(3)">출결,연차</button>
		        <button id="logoutBtn" class="btn btn-primary" onclick="changeView(4)">주소록</button>
		        <button id="updateBtn" class="btn btn-primary" onclick="changeView(5)">마이페이지</button>
		        <button id="homeBtn" class="btn btn-primary" onclick = "changeView(6)">로그아웃</button>
			</p>
		</div>
	</body>
</html>
