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
	<form action="Updateboard.bo" method="post"
	enctype="multipart/form-data">
	<input type="hidden" name="board_num"value="${bvoUpdate.board_num}">
		<table>
		  <tr align="center" valign="middle">
		   <td colspan="4" style="margin-left: auto;">게시판
		   </td></tr>
		  <tr> <td>제 목</td><td><input name="board_title" type="text"value="${bvoUpdate.board_title }"></td></tr>
		  <tr> <td>내 용</td><td><textarea rows="13" cols="50" name="board_content">${bvoUpdate.board_content}</textarea></td></tr>
		  <tr> <td>파일 첨부</td><td><input type="file" name="board_file"><br><input name="board_file02" type="hidden" value="${bvoUpdate.board_file}">${bvoUpdate.board_file}</td></tr>
		</table>
		<input type="submit" value="수정">
		<input type="reset" value="취소">
	</form>
</body>
</html>