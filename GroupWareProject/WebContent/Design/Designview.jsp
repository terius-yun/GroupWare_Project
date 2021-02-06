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
<script type="text/javascript">
function button_event(){
	if (confirm("정말 삭제하시겠습니까??") == true){
	    
		location.href='DeleteDesignAction.dgi?num=${view.design_num}'
	}else{   //취소
	    return;
	}
}
</script>
</head>
<body>
	<h1><a href="main.do">홈</a>/등록</h1>
	<form action="Updateform.dgi" method="post">
	<input type="hidden"name="design_num"value="${view.design_num}">
	
		<table>
		  <tr align="center" valign="middle">
		   <td colspan="4" style="margin-left: auto;">게시판
		   </td></tr>
		  <tr> <td>제 목</td><td>${view.design_title}</td></tr>
		  <tr> <td>내 용</td><td>${view.design_content}</td></tr>
		  <tr> <td>파일 첨부</td><td><img src="UploadFolder/${view.gw_design_file}"><br>${view.gw_design_file}</td></tr>
		</table>
		<button name="답글">답글</button>
		<c:if test="${view.emp_num!=sessionID}">
		<input type="submit" value="수정">
		<input type="button" value="삭제" onclick="button_event();">
		</c:if>
		<c:if test="${view.emp_num!=sessionID}">
		<input type="hidden" value="수정">
		<input type="hidden" value="삭제" onclick="button_event();">
		</c:if>
	</form>
	
</body>
</html>