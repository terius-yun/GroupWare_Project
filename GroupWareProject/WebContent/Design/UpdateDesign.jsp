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
	<h1>등록</h1>
	<form action="UpdateDesign.dgi" method="post"
	enctype="multipart/form-data">
	<input type="hidden" name="design_num"value="${bvoUpdate.design_num}">
		<table>
		  <tr align="center" valign="middle">
		   <td colspan="4" style="margin-left: auto;">게시판
		   </td></tr>
		  <tr> <td>제 목</td><td><input name="board_title" type="text"value="${bvoUpdate.design_title}"></td></tr>
		  <tr> <td>내 용</td><td><textarea rows="13" cols="50" name="board_content">${bvoUpdate.design_content}</textarea></td></tr>
		  <tr> <td>파일 첨부</td><td><input type="file" name="gw_design_file"><br><input name="gw_design_file" type="hidden" value="${bvoUpdate.design_file}">${bvoUpdate.design_file}</td></tr>
		</table>
		<input type="submit" value="수정">
		<input type="reset" value="취소">
	</form>
</body>
</html>