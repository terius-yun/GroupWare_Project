<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
	<head>
		<style>
			a{
				text-decoration: none;
				color:black;
			}
			#yo{
				text-align: center;
			}
			#tab2 {
				border-collapse: collapse;
			}
			#tab2 td {
				border: 1px solid black;
			}
			#tab1{
				margin-top: 100px;
				margin-bottom: 20px;
				text-align: center;
			}
		</style>
		<script>
			function openPage(i) {
//  				window.open('addVCForm.vc','addVC', 'top=50px, left=50px, width=400px , height=400px');
				popYear = document.getElementById('yearP').value;
				popMonth = document.getElementById('monthP').value;
				popDay = i;
					
				console.log(document.getElementById('yearP').value);
				console.log(document.getElementById('monthP').value);
				console.log(i);
				
				YYMMDD = popYear +"/"+ popMonth +"/"+ popDay

				var form = document.createElement('form');
				form.setAttribute("name","popPost");

				form.setAttribute('method', 'post');
				form.setAttribute('action', 'AddVCFormAction.vc');

				document.charset = "utf-8";
					var hiddenField = document.createElement('input');
					hiddenField.setAttribute('type', 'hidden');
					hiddenField.setAttribute('name', "YYMMDD");
					hiddenField.setAttribute('value', YYMMDD);
					form.appendChild(hiddenField);
				document.body.appendChild(form);
				form.submit();
	//			location.href = "mainVC.vc"
				return false;
			}
		</script>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<%
			Calendar cal = Calendar.getInstance();
		
			int cuyear = cal.get(Calendar.YEAR);
			int cumonth = cal.get(Calendar.MONTH);
			int cuday = cal.get(Calendar.DATE);
			
			String Year = request.getParameter("year");
			String Month = request.getParameter("month");
			
			int year, month;
			if(Year == null & Month == null){
				year = cuyear;
				month = cumonth;
			} else {
				year = Integer.parseInt(Year);
				month = Integer.parseInt(Month);
				if(month<0) { month=11; year=year-1; } //1월부터 12월까지 범위 지정.
				if(month>11) { month=0; year=year+1; }
			}
		%>
		<form action="AddVCFormAction.vc" id="frm">
			<table id="tab1">
				<tr>
				    <td width=200> <!-- 년 도-->
				    	<a href="mainVC.vc?year=<%out.print(year-1);%>&month=<%out.print(month);%>">◀</a>
				    <% out.print(year); %>년<input type="hidden" id="yearP" value=<%=year%>>
				    <a href="mainVC.vc?year=<%out.print(year+1);%>&month=<%out.print(month);%>">▶</a>
				    </td>
				    <td width=300> <!-- 월 -->
				    <a href="mainVC.vc?year=<%out.print(year);%>&month=<%out.print(month-1);%>">◀</a>
				    <% out.print(month+1); %>월<input type="hidden" id="monthP" value=<%=month+1%>>
				    <a href="mainVC.vc?year=<%out.print(year);%>&month=<%out.print(month+1);%>">▶</a>
				    </td>
				    <td width=200><% out.print(cuyear + "-" + (cumonth+1) + "-" + cuday); %></td>
				    <td> <input type="button" value="근태"></td>
			   </tr>
			   </table>
			   <table id="tab2">
				<tr id="yo">
					<td width="100">일</td>
					<td width="100">월</td>
					<td width="100">화</td>
					<td width="100">수</td>
					<td width="100">목</td>
					<td width="100">금</td>
					<td width="100">토</td>				
				</tr>
				
				<tr height="50">
				<%
				cal.set(year, month, 1);
				
				int start = cal.get(Calendar.DAY_OF_WEEK);
				int end = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
				int br = 0;
				
				for(int i =0; i <(start -1); i++){
					out.println("<td> &nbsp;</td>");
					br++;
					if((br%7)==0){
						out.println("<br>");
					}
				}	
				//연차 받아오기
				int []startDay = (int[])request.getAttribute("startDay");
				int []endDay = (int[])request.getAttribute("endDay");
				int vcCount = (int)request.getAttribute("vcCount");
				System.out.println("총받아와야하는 이달의 연차갯수의 인덱스: "+vcCount);
				//연차받아오기
				for(int i = 1; i <= end; i++){
					out.println("<td id='day"+i+"><a href='#' onclick='openPage("+i+")'>" + i + "</a><br>");
					//연차 출력하기
					for(int j= 0; j<=vcCount; j++){
						if(startDay[j]== i && startDay[j] <= endDay[j]){ 
							out.println("<p>오전반차</p>");
							startDay[j]++;
						}
					}
					//연차 출력하기
					
					out.println("</td>");
					br++;
					if((br%7) == 0 && i != end){
						out.println("</tr> <tr height=50>");
					}
				}
				while((br++)%7 != 0)
					out.println("<td>&nbsp;</td>");
				
				
				%>
				</tr>
			</table>
		</form>
	</body>
</html>