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
	<form action="Updateform.bo" method="post">
	<input type="hidden"name="board_num"value="${view.board_num}">
	
		<table>
		  <tr align="center" valign="middle">
		   <td colspan="4" style="margin-left: auto;">게시판
		   </td></tr>
		  <tr> <td>제 목</td><td>${view.board_title}</td></tr>
		  <tr> <td>내 용</td><td>${view.board_content}</td></tr>
		  <tr> <td>파일 첨부</td><td><img src="UploadFolder/${view.board_file}"><br>${view.board_file}</td></tr>
		</table>
		<button name="답글">답글</button>
		<input type="submit" value="수정">
		<input type="button" value="삭제" onclick="location.href='DeleteBoardAction.bo?num=${view.board_num}'">
	</form>
	
</body>
</html>