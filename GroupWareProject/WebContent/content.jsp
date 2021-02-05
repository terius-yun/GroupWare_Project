<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>content</title>
	    <link rel="stylesheet" href="css/contentStyle.css">
	<script>
	function mainContentIcon(value){
		if(value == "1"){//출결,연차
			location.href="MainHRFormAction.vc";
		}else if(value == "2"){//게시판
			location.href="#";
		}else if(value == "3"){//주소록
			location.href="MemberAddressBookAction.do";
		}else if(value == "4"){//일정
			location.href="#";
		}
	}
	</script>
</head>
<body>
<div id="con">
    <button>
		<img class="imgbtn wimgbtn" src="img/w-cal.png" onclick="mainContentIcon(1)">
    </button>
	<button>
		<img class="imgbtn" src="img/board2.png" onclick="mainContentIcon(2)">
	</button>
	<br>
	<button>
		<img class="imgbtn " src="img/addr2.png" onclick="mainContentIcon(3)">
	</button>
	<button>
		<img class="imgbtn wimgbtn" src="img/w-sced.png" onclick="mainContentIcon(4)">
    </button>
	
</div>
</body>
</html>
