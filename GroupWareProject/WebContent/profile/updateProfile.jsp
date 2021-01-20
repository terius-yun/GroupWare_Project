<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>프로필 수정</title>
	</head>
	<body>
	<h1>프로필 수정하기</h1>
		<form action="MemberProfileUpdateAction.do" method="post">
			<table>
				<tr>
					<td>비밀번호</td>
					<td><input type="text" name="member_pw"></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="member_name"></td>
				</tr>
				<tr>
					<td>전화번호</td>
					<td><input type="text" name="member_pNum"></td>
				</tr>
				<tr>
					<td>이메일</td>
					<td><input type="text" name="member_email"></td>
				</tr>
				<tr>
					<td>계좌정보</td>
					<td><input type="text" name="member_bank_account"></td>
				</tr>
			</table>
			<input type="submit" value="완료">
		</form>
		<p>이외의 다른 항목의 수정은 관리자에게 문의하십시오.</p>
	</body>
</html>