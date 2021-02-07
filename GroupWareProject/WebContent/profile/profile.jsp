<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지 프로필</title>
<script type="text/javascript">
	function updateProfile() {
		location.href="updateProfile.do";
	}
</script>
<style type="text/css">
	body {
		text-align: center;
	}
</style>
</head>
<body>
	<h1>마이 프로필</h1>
	<table>
	<c:forEach var="member" items="${requestScope.memberInfo}">
		<tr>
			<td>사번</td>
			<td>${member.emp_num}</td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td>${member.member_pw}</td>
		</tr>
		<tr>
			<td>이름</td>
			<td>${member.member_name}</td>
		</tr>
		<tr>
			<td>생년월일</td>
			<td>${member.member_birth}</td>
		</tr>
		<tr>
			<td>전화번호</td>
			<td>${member.member_pNum}</td>
		</tr>
		<tr>
			<td>이메일</td>
			<td>${member.member_email}</td>
		</tr>
		<tr>
			<td>계좌정보</td>
			<td>${member.member_bank_account}</td>
		</tr>
		<tr>
			<td>부서</td>
			<td>${member.member_team}</td>
		</tr>
		<tr>
			<td>직급</td>
			<td>${member.member_rank}</td>
		</tr>
		<tr>
			<td>관리자 권한</td>
			<td>${member.member_administrator}</td>
		</tr>
		<tr>
			<td>등록일</td>
			<td>${member.member_regdate}</td>
		</tr>
		</c:forEach>
	</table>
	<br>
	<button id="updateBtn" onclick="updateProfile()">수정</button>
</body>
</html>