<%@ page isELIgnored="false" language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
	
		<style type="text/css">
			#footer{
				clear: both;
			}
		 	body{
		 		width: 1050px;
		 	}
		</style>
		<meta charset="utf-8">
		<title>메인 화면</title>
	</head>
	<body>
		    <div id="wrap">
		        <div id="header">
		            <jsp:include page="header.jsp" />
		        </div>
		        <hr>
		        <div id="main">
		        <c:set var="contentPage" value="${param.contentPage}"/>
		        <c:choose>
		        	<c:when test="${contentPage==null}">
		        		<jsp:include page="content.jsp"/>
		        	</c:when>    	
			        <c:otherwise>
			            <jsp:include page="${contentPage}" />
			        </c:otherwise>
		        </c:choose>
		        </div>
		        <div id="footer">
		        	<jsp:include page="footer.jsp" />
		        </div>
		    </div>
    
	</body>
</html>