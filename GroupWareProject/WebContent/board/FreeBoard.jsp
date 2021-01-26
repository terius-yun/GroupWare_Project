<%@page import="jsp.board.model.BoardVO"%>
<%@page import="java.util.List"%>
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
	 <h1><a href="main.do">홈</a>/자유게시판</h1>
	 <br>
	 <form action="Writer.bo">
	 <div>
	 <input type="button" value="개발팀 게시판" id="asd">
	 <input type="button" value="css팀 게시판 " id="dsa">
	 <table >
	  <tr align="center" valign="middle">
			<td colspan="6">자유 게시판</td>
		</tr>
		<tr align="center" valign="middle" bordercolor="#333333">
			<td><div align="center">번호</div></td>
			<td><div align="center">팀명</div></td>
			<td><div align="center">제목</div></td>
			<td><div align="center">작성자</div></td>
			<td><div align="center">날짜</div></td>
			<td><div align="center">조회수</div></td>
		</tr>
		
		
		<c:forEach var="list" items="${list}">
			<tr>
			<td><div align="center">${list.board_num}</div></td>
			<td><div align="center">${list.member_team}</div></td>
			<td><div align="center">${list.board_title}</div></td>
			<td><div align="center"><a href="boardModify.bo">${list.member_name}</a></div></td>
			<td><div align="center">${list.board_writedate}</div></td>
			<td><div align="center">${list.board_readcount}</div></td>
			</tr>
		</c:forEach>
	 </table>
	 <input type="submit" value="등록">
	 <input type="button" value="삭제">
	 </div>
	 </form>
	 
</body>
</html>