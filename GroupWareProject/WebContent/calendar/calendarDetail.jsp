<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<style>
	table{
		width:60%; height:100px; margin:auto; text-align:center;
	}
</style>
</head>
<body>

 <table>
  	<tr>
  		<td>팀명</td>
  		<td>aaaa</td>
  	</tr>
  	<tr>
  		<td>프로젝트 이름</td>
  		<td>${calendardates.cal_title}</td>
  	</tr>
  	<tr>
  		<td>참여인원</td>
  		<td>${calendardates.cal_member}</td>
  	</tr>
  	<tr>
  		<td>프로젝트 내용</td>
  		<td>${calendardates.cal_content}</td>
  	</tr>
  	<tr>
  		<td>프로젝트 기간</td>
  		<td>${calendardates.cal_start_date} - ${calendardates.cal_end_date}</td>
  	</tr> 	
  </table>

</body>
</html>