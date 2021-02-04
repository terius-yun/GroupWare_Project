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
	<form action="UpdateDesign.dgi" method="post"
	enctype="multipart/form-data">
	<input type="hidden" name="DESIGN_NUM"value="${bvoUpdate.DESIGN_NUM}">
		<table>
		  <tr align="center" valign="middle">
		   <td colspan="4" style="margin-left: auto;">게시판
		   </td></tr>
		  <tr> <td>제 목</td><td><input name="board_title" type="text"value="${bvoUpdate.DESIGN_TITLE}"></td></tr>
		  <tr> <td>내 용</td><td><textarea rows="13" cols="50" name="board_content">${bvoUpdate.DESIGN_CONTENT}</textarea></td></tr>
		  <tr> <td>파일 첨부</td><td><input type="file" name="board_file"><br><input name="board_file02" type="hidden" value="${bvoUpdate.DESIGN_file}">${bvoUpdate.DESIGN_FILE}</td></tr>
		</table>
		<input type="submit" value="수정">
		<input type="reset" value="취소">
	</form>
</body>
</html>