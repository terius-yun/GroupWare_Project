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
	  <tr>
	  	<td>글번호</td><td>글제목</td>
	  	<td>작성날짜</td><td>조회수</td>
	   </tr>
	 </table>
	 <input type="submit" value="등록">
	 <input type="button" value="삭제" id="delete.bo">
	 </div>
	 </form>
</body>
</html>