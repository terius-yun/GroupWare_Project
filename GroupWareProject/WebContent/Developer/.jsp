<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<h1>등록</h1>
	<form action="WriteFormBoard.bo" method="post">
		<table>
		  <tr align="center" valign="middle">
		   <td colspan="4" style="margin-left: auto;">게시판
		   </td></tr>
		  <tr> <td>작성자</td><td><input type="text"></td></tr>
		  <tr> <td>팀명</td><td><input type="text"></td></tr>
		  <tr> <td>제 목</td><td><input type="text"></td></tr>
		  <tr> <td>내 용</td><td><textarea rows="13" cols="50"></textarea></td></tr>
		  <tr> <td>파일 첨부</td><td><input type="text"></td></tr>
		</table>
		<input type="submit" value="등록"><input type="reset" value="취소">
	</form>
</body>
</html>