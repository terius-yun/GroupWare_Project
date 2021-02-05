<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	td{border: 1px solid;}
</style>
</head>
<body>
	<h1><a href="main.do">홈</a>/등록</h1>
	<form action="AddPlan.pl" method="post"
	enctype="multipart/form-data">
		<table>
		  <tr align="center" valign="middle">
		   <td colspan="4" style="margin-left: auto;">게시판
		   </td></tr>
		  <tr> <td>제 목</td><td><input name="plan_title" type="text"></td></tr>
		  <tr> <td>내 용</td><td><textarea rows="13" cols="50" name="plan_content"></textarea></td></tr>
		  <tr> <td>파일 첨부</td><td><input type="file" name="gw_plan_file"></td></tr>
		</table>
		<input type="submit" value="등록"><input type="reset" value="취소">
	</form>
</body>
</html>