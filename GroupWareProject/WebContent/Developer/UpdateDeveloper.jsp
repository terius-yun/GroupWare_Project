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
	<form action="UpdateDeveloper.dp" method="post"
	enctype="multipart/form-data">
	<input type="hidden" name="developer_num"value="${bvoUpdate.developer_num}">
		<table>
		  <tr align="center" valign="middle">
		   <td colspan="4" style="margin-left: auto;">게시판
		   </td></tr>
		  <tr> <td>제 목</td><td><input name="developer_title" type="text"value="${bvoUpdate.developer_title}"></td></tr>
		  <tr> <td>내 용</td><td><textarea rows="13" cols="50" name="developer_content">${bvoUpdate.developer_content}</textarea></td></tr>
		  <tr> <td>파일 첨부</td><td><input type="file" name="gw_developer_file"><br><input name="gw_developer_file02" type="hidden" value="${bvoUpdate.gw_developer_file}">${bvoUpdate.gw_developer_file}</td></tr>
		</table>
		<input type="submit" value="수정">
		<input type="reset" value="취소">
	</form>
</body>
</html>