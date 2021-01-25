<%@page import="org.apache.catalina.Session"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script>
	function frmCheck(){
		if (!document.appVC.vc_content.value){
			alert("사유를 입력하세요.")
			return false;
		}
	}
</script>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
   
</head>
<body>

	<h1> 연차 신청</h1>
	<form action="AddVCAction.vc" method="post" id="appVC">
		<table>
			<c:forEach var="info" items="${requestScope.memberInfo}">
				<tr> <td> 이름 : </td> <td><input type="text" name="member_name" value="${info.member_name}"></td></tr>
				<tr> <td> 부서 : </td> <td> <input type="text" name="member_team" value="${info.member_team}"></td></tr>
			</c:forEach>
			<c:forEach var="start_date" items="${requestScope.start_date}">
				<tr>
					<td> 시작일 :</td> <td><input type="text" name="vc_start_date" value="${start_date}"> </td> 
					<td>종료일 :</td><td><input type="text" name="vc_end_date" value="" placeholder="0000/0/0"></td></tr>
			</c:forEach>
			
			<tr> <td>사유</td> <td><textarea rows="5" cols="35" name="vc_content" placeholder="ex) 연차, 병가, 정기휴가 등 종류와 사유를 입력하세요."></textarea> </td></tr>
		</table>
		<div>
			<input type="submit" value="등록" onclick="return frmCheck()">
			<input type="reset" value="다시 작성">
			<input type="button" value="취소" onclick="history.go(-1)">
		</div>
	</form>
</body>
</html>