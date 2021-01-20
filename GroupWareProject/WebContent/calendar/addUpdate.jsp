<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
<form action="CalendarAddUpdateAction.cal" method="get">
<table>
	<tr>
		<td><label for="pName">프로젝트 이름</label></td>
		<td><input type="text" name="cal_title" id="pName"></td>
	</tr>
	<tr>
		<td><label for="member">참가자</label></td>
		<td><input type="text" name="cal_member" id="member"></td>
	</tr>
	<tr>
		<td><label for="content">내용</label></td>
		<td><textarea rows="6" cols="60" name="cal_content" id="content"></textarea></td>
	</tr>
	<tr>
		<td><label for="start_End_Date">시작일 - 종료일</label><br><br></td>
		<td><jsp:include page="../datepicker/index.jsp"></jsp:include></td>
	</tr>
</table>
<input type="submit" value="수정">
<input type="reset" value="취소">
</form>
</body>
</html>