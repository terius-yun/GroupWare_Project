<%@page import="org.apache.catalina.Session"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<style>
	h1 {
		text-align:center;
		margin-bottom:0;
	}
	div {
		text-align: center;
	}
	#frm {
		margin: 50px 25px;
	}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1> 연차 신청</h1>
	<form action="addVCAction.vc" method="post" id="popup">
		<table>
			<c:forEach var="info" items="${requestScope.memberInfo}">
				<tr> <td> 신청자 </td> <td><input type="text" name="member_name" value="${info.member_name}"></td></tr>
				<tr> <td> 부서 </td> <td> <input type="text" name="member_team" value="${info.member_team}"></td></tr>
			</c:forEach>
			<c:forEach var="start_date" items="${requestScope.start_date}">
				<tr> <td> 시작일 : ${start_date} </td> <td> 종료일</td></tr>
			</c:forEach>
			
			<tr> <td>사유</td> <td><textarea rows="5" cols="35" name="vc_content"></textarea> </td></tr>
		</table>
		<div>
			<input type="submit" value="등록">
			<input type="reset" value="취소">
		</div>
	</form>
</body>
</html>