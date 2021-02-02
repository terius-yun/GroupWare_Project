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
			function openPage(i) {// 연차등록
				popYear = document.getElementById('yearP').value;//현재 달력의 년
				popMonth = document.getElementById('monthP').value;//현재 달력의 월
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
				return false;
			}
			
			function changeYear(changeValue){// 년도 이동
				year = document.getElementById('yearP').value
				if(changeValue == 1){//전년도 이동
					year--;
					var form = document.createElement('form');
					form.setAttribute("name","changeYearForm");
					form.setAttribute('method', 'post');
					form.setAttribute('action', 'MainVCFormAction.vc');
					
					document.charset = "utf-8";
						var hiddenField = document.createElement('input');//년 정보
						hiddenField.setAttribute('type', 'hidden');
						hiddenField.setAttribute('name', "year");
						hiddenField.setAttribute('value', year);
						form.appendChild(hiddenField);
					document.body.appendChild(form);
					form.submit();
					return false;
				}else{//다음년도 이동
					year++;
					var form = document.createElement('form');
					form.setAttribute("name","changeYearForm");
					form.setAttribute('method', 'post');
					form.setAttribute('action', 'MainVCFormAction.vc');
					
					document.charset = "utf-8";
						var hiddenField = document.createElement('input');//년 정보
						hiddenField.setAttribute('type', 'hidden');
						hiddenField.setAttribute('name', "year");
						hiddenField.setAttribute('value', year);
						form.appendChild(hiddenField);
					document.body.appendChild(form);
					form.submit();
					return false;
				}
			}
			
			function changeMonth(changeValue){// 월 이동
				year = document.getElementById('yearP').value
				month = document.getElementById('monthP').value;
				if(changeValue == 1){//전달로 가기.
					month--;
					if(month <=0){// 1월에서 전달로 가기시 전년으로 이동
						month = 12;
						year--;
						var form = document.createElement('form');
						form.setAttribute("name","changeMonthForm");
						form.setAttribute('method', 'post');
						form.setAttribute('action', 'MainVCFormAction.vc');
	
						document.charset = "utf-8";
							var hiddenField = document.createElement('input');//월 정보
							hiddenField.setAttribute('type', 'hidden');
							hiddenField.setAttribute('name', "month");
							hiddenField.setAttribute('value', month);
							form.appendChild(hiddenField);
							
							var hiddenField2 = document.createElement('input');//년 정보
							hiddenField2.setAttribute('type', 'hidden');
							hiddenField2.setAttribute('name', "year");
							hiddenField2.setAttribute('value', year);
							form.appendChild(hiddenField2);
						document.body.appendChild(form);
						form.submit();
						return false;
					}else{// 1월에서 전달로 가기를 제외한 움직임
						var form = document.createElement('form');
						form.setAttribute("name","changeMonthForm");
						form.setAttribute('method', 'post');
						form.setAttribute('action', 'MainVCFormAction.vc');
	
						document.charset = "utf-8";
							var hiddenField = document.createElement('input');
							hiddenField.setAttribute('type', 'hidden');
							hiddenField.setAttribute('name', "month");
							hiddenField.setAttribute('value', month);
							form.appendChild(hiddenField);
							
							var hiddenField2 = document.createElement('input');//년 정보
							hiddenField2.setAttribute('type', 'hidden');
							hiddenField2.setAttribute('name', "year");
							hiddenField2.setAttribute('value', year);
							form.appendChild(hiddenField2);
						document.body.appendChild(form);
						form.submit();
						return false;
					}
				}else if(changeValue == 2){//다음달로 가기.
					month++;
					if(month > 12){
						month = 1;
						year++;
						var form = document.createElement('form');
						form.setAttribute("name","changeMonthForm");
						form.setAttribute('method', 'post');
						form.setAttribute('action', 'MainVCFormAction.vc');
	
						document.charset = "utf-8";
							var hiddenField = document.createElement('input');//월 정보
							hiddenField.setAttribute('type', 'hidden');
							hiddenField.setAttribute('name', "month");
							hiddenField.setAttribute('value', month);
							form.appendChild(hiddenField);
							
							var hiddenField2 = document.createElement('input');//년 정보
							hiddenField2.setAttribute('type', 'hidden');
							hiddenField2.setAttribute('name', "year");
							hiddenField2.setAttribute('value', year);
							form.appendChild(hiddenField2);
						document.body.appendChild(form);
						form.submit();
						return false;
					}else{
						var form = document.createElement('form');
						form.setAttribute("name","changeMonthForm");
						form.setAttribute('method', 'post');
						form.setAttribute('action', 'MainVCFormAction.vc');
	
						document.charset = "utf-8";
							var hiddenField = document.createElement('input');//월 정보
							hiddenField.setAttribute('type', 'hidden');
							hiddenField.setAttribute('name', "month");
							hiddenField.setAttribute('value', month);
							form.appendChild(hiddenField);
							
							var hiddenField2 = document.createElement('input');//년 정보
							hiddenField2.setAttribute('type', 'hidden');
							hiddenField2.setAttribute('name', "year");
							hiddenField2.setAttribute('value', year);
							form.appendChild(hiddenField2);
						document.body.appendChild(form);
						form.submit();
						return false;
					}
				}
			}
			function hrCal(){
				location.href="MainHRFormAction.vc";
			}
		</script>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<%
			Calendar cal = Calendar.getInstance();
		
			int cuyear = cal.get(Calendar.YEAR);//시스템상 현재 년
			int cumonth = cal.get(Calendar.MONTH);//시스템상 현재 월
			int cuday = cal.get(Calendar.DATE);//시스템상 현재 일
			
			String cal_year = (String)request.getAttribute("cal_year"); //액션통해 년 받아오기
			String cal_month = Integer.toString((int)request.getAttribute("cal_month"));//액션통해 월 받아오기
			
			int year, month;
				year = Integer.parseInt(cal_year);
				month = Integer.parseInt(cal_month);

		%>
		<button onclick="hrCal()">근태</button>
		<form action="AddVCFormAction.vc" id="frm">
			<table id="tab1">
				<tr>
				    <td width=200> <!-- 년 도-->
					    <a href="#" onclick="changeYear(1)">◀</a>
					    <% out.print(year); %>년<input type="hidden" id="yearP" value=<%=year%>>
				    	<a href="#"  onclick="changeYear(2)">▶</a>
				    </td>
				    <td width=300> <!-- 월 -->
				    	<a href="#"  onclick="changeMonth(1)">◀</a>
				    	<% out.print(month); %>월<input type="hidden" id="monthP" value=<%=month%>>
				    	<a href="#"  onclick="changeMonth(2)">▶</a>
				    </td>
				    <td width=200>
				    	<% out.print(cuyear + "-" + (cumonth+1) + "-" + cuday); %>
				    </td>
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
				cal.set(year, month-1, 1);
				
				int start = cal.get(Calendar.DAY_OF_WEEK);
				int end = cal.getActualMaximum(Calendar.DAY_OF_MONTH);//말일
				System.out.println("이달 말일은?: "+end);
				int br = 0;
				
				for(int i =0; i <(start -1); i++){
					out.println("<td> &nbsp;</td>");
					br++;
					if((br%7)==0){
						out.println("<br>");
					}
				}	
				//연차 받아오기
				int []startYear = (int[])request.getAttribute("startYear");//연차가 시작되는 년
				int []endYear = (int[])request.getAttribute("endYear");//연차가 끝나는 년
				int []startMonth = (int[])request.getAttribute("startMonth");//연차가 시작되는 월
				int []endMonth = (int[])request.getAttribute("endMonth");//연차가 끝나는 월
				int []startDay = (int[])request.getAttribute("startDay");//연차가 시작되는 일
				int []endDay = (int[])request.getAttribute("endDay");//연차가 끝나는 일
				String []content = (String[])request.getAttribute("content");//연차 사유
				int vcCount = (int)request.getAttribute("vcCount");//이번년도에 출력되어야할 연차의 갯수
				System.out.println("총받아와야하는 이해의 연차갯수 : "+vcCount);
				//연차받아오기
				for(int i = 1; i <= end; i++){
					out.println("<td id='day"+i+"><a href='#' onclick='openPage("+i+")'>" + i + "</a><br>");
					//연차 출력하기
					if(vcCount != 0){
						for(int j= 0; j<vcCount; j++){
							if(	startYear[j]<= year
								&& year <= endYear[j]
								&& startMonth[j]==month 
								&& endMonth[j]== month
								&& startDay[j]== i
								&& startDay[j] <= endDay[j]){ 
									out.println("<p>"+content[j]+"</p>");
									startDay[j]++;
							}
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