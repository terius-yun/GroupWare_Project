<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>주소록</title>
		
		<style>
		.menu a {
		 cursor:pointer;
		}
		.menu .hide {
		 display:none;
		}
		</style>
		<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<script>
		$(document).ready(function(){
			$(".menu>a").click(function(){
				
				var submenu = $(this).next("ul");
				
				if( submenu.is(":visible") ){
					submenu.slideUp();
				}else{
					submenu.slideDown();
				}
			});
		  });
		</script>
	</head>
	<body>
	
	<div>
	  <ul>
	    <li class="menu"> 
	    	<!-- 대표 -->
	      <c:forEach var="team1" items="${requestScope.team1}">
		   <a>${team1.member_team}</a>
		   </c:forEach>
		   <c:forEach var="team1member" items="${requestScope.team1}">
	      	<ul class="hide">
	      	<li>${team1member.member_name }</li>
		  <!-- 대표 -->
	      </ul>
	      </c:forEach>
	    </li>
	    </ul>
	</div>
	
	</body>
</html>