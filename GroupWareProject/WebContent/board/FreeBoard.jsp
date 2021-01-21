<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<style type="text/css">
	td{border: 1px solid;}
</style>
</head>
<body>
	 <h1>자유게시판</h1>
	 <br>
	 <form action="Board.bo">
	 <div>
	 <input type="button" value="개발팀 게시판" id="asd">
	 <input type="button" value="css팀 게시판 " id="dsa">
	 <table >
	  <tr align="center" valign="middle">
			<td colspan="5">자유 게시판</td>
		</tr>

		<tr align="center" valign="middle" bordercolor="#333333">
			<td><div align="center">번호</div></td>
			<td><div align="center">제목</div></td>
			<td><div align="center">작성자</div></td>
			<td><div align="center">날짜</div></td>
			<td><div align="center">조회수</div></td>
		</tr>
		<c:forEach var="list.bo" items="${어디서 받아와서 나열해}" varStatus="">
			<tr>
			<td>${varstatus}</td>
			<td>${title.bo}</td>
			<td>${name.bo}</td>
			<td>${writedate.bo}</td>
			<td>${board_num.bo}</td>
			</tr>
		</c:forEach>
	 </table>
	 <input type="submit" value="등록">
	 <input type="button" value="삭제" id="delete.bo">
	 </div>
	 </form>
	 
</body>
</html>