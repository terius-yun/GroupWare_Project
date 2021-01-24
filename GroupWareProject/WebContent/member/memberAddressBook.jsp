<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>주소록</title>
		
		<style>
			.menu a {
			 cursor:pointer;
			}
			.menu .hide {
			 display:none;
			}
			#kategorie{
				float: left;
			}
			.hide{
				list-style: none;
				padding-left: 0px;
			}
		</style>
		
		<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<script>
		$(document).ready(function(){
			$(".menu>a").click(function(){
				
				var submenu = $(this).next("ul");
				
				if( submenu.is(":visible") ){
					submenu.slideUp();
				}else{
					submenu.slideDown();
				}
			});
		  });
		</script>
	</head>
	<body>
	
	<div id="kategorie">
		<ul>
		  	<!-- 대표 -->
		    <li class="menu"> 
				<a>대표</a>
				<ul class="hide">
					<c:forEach var="team1" items="${requestScope.team1}" varStatus="status">
						<form method="post" action="MemberLoadAddressInfo.do" id="form1_${status.count}">
							<input type="hidden" name="team" value="${team1.member_name}_ceo">
								<li><a onclick="document.getElementById('form1_${status.count}').submit();">${team1.member_name}</a></li>
						</form>
					</c:forEach>
				</ul>
		    </li>
		    <!-- 대표 -->
		    
		    <!-- 기획팀 -->
		    <li class="menu"> 
				<a>기획팀</a>
				<ul class="hide">
					<c:forEach var="team2" items="${requestScope.team2}" varStatus="status">
						<form method="post" action="MemberLoadAddressInfo.do" id="form2_${status.count}">
							<input type="hidden" name="team" value="${team2.member_name}_plan">
								<li><a onclick="document.getElementById('form2_${status.count}').submit();">${team2.member_name}</a></li>
						</form>
					</c:forEach>
				</ul>
		    </li>
		    <!-- 기획팀 -->
		    
		    <!-- 개발팀 -->
		    <li class="menu"> 
				<a>개발팀</a>
				<ul class="hide">
					<c:forEach var="team3" items="${requestScope.team3}" varStatus="status">
						<form method="post" action="MemberLoadAddressInfo.do" id="form3_${status.count}">
							<input type="hidden" name="team" value="${team3.member_name}_dev">
								<li><a onclick="document.getElementById('form3_${status.count}').submit();">${team3.member_name}</a></li>
						</form>
					</c:forEach>
				</ul>
		    </li>
		    <!-- 개발팀 -->
		    
		    <!-- 디자인팀 -->
		    <li class="menu"> 
				<a>디자인팀</a>
				<ul class="hide">
					<c:forEach var="team4" items="${requestScope.team4}" varStatus="status">
						<form method="post" action="MemberLoadAddressInfo.do" id="form4_${status.count}">
							<input type="hidden" name="team" value="${team4.member_name}_desi">
								<li><a onclick="document.getElementById('form4_${status.count}').submit();">${team4.member_name}</a></li>
						</form>
					</c:forEach>
				</ul>
		    </li>
		    <!-- 디자인팀 -->
		</ul>
	</div>
	
	<!-- 검색창 -->
	<form method="post" action="MemberAddressSearchAction.do">
		<input type="text" name="memberSearch" placeholder="성함을 입력해 검색하세요.">
		<input type="submit">
	</form>
	
	<!-- 정보 표시 -->
	<table>
	<c:forEach var="member_info" items="${requestScope.memberUniqueInfo}">
		<tr>
			<td>이름</td>
			<td>${member_info.member_name}</td>
		</tr>
		<tr>
			<td>전화번호</td>
			<td>${member_info.member_pNum}</td>
		</tr>
		<tr>
			<td>이메일</td>
			<td>${member_info.member_email}</td>
		</tr>
		<tr>
			<td>부서</td>
			<td>${member_info.member_team}</td>
		</tr>
		<tr>
			<td>직급</td>
			<td>${member_info.member_rank}</td>
		</tr>
		</c:forEach>
	</table>
	
	</body>
</html>