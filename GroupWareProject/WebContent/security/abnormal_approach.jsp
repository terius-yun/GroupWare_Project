<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>비정상적인 접근입니다.</title>
		<script type="text/javascript">
			function loginBtn(){
				location.href="index.do";
			}
		</script>
	</head>
	<body>
		<h1>비정상적인 접근입니다. 확인 후 재시도해주세요.</h1>
		<button onclick="loginBtn()">로그인</button>
	</body>
</html>