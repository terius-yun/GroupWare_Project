<%@page import="Gp.calendar.db.CalendarDAO"%>
<%@page import="Gp.calendar.db.CalendarVO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <title>calendar</title>
  <link rel="stylesheet" type="text/css" href="./css/mainCalendar.css">
  <script type="text/javascript">
  	function openPopup(){
  		window.name="win_pay";
  		var url="CalAddForm.cal";
		window.open( url, "addUpdate", "top=150, left=650, toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=600, height=600" );
  	}
  	
  	function detailOpen(){
  		location.href="CalendarDetail.cal";
  		
  	}
  	function detailView(){
  		if(document.getElementById("detail").style.display=="none"){
  			document.getElementById("detail").style="display=inline;";
  		}else{
  			document.getElementById("detail").style.display=="none";
  		}
  	}
  </script>
 </head>
 <body>
  <%
  java.util.Calendar cal=java.util.Calendar.getInstance(); //Calendar객체 cal생성
  int currentYear=cal.get(java.util.Calendar.YEAR); //현재 날짜 기억
  int currentMonth=cal.get(java.util.Calendar.MONTH);
  int currentDate=cal.get(java.util.Calendar.DATE);
  String Year=request.getParameter("year"); //나타내고자 하는 날짜
  String Month=request.getParameter("month");
  int year, month;
  if(Year == null && Month == null){ //처음 호출했을 때
  year=currentYear;
  month=currentMonth;
  }
  else { //나타내고자 하는 날짜를 숫자로 변환
   year=Integer.parseInt(Year);
   month=Integer.parseInt(Month);
   if(month<0) { month=11; year=year-1; } //1월부터 12월까지 범위 지정.
   if(month>11) { month=0; year=year+1; }
  }
  %>
  <center>
  <table border=0> <!-- 달력 상단 부분, -->
   <tr>
    <td align=left width=200> <!-- 년 도-->
    <a href="CalMainCalendar.cal?year=<%out.print(year-1);%>&month=<%out.print(month);%>">◀</a>
    <% out.print(year); %>년
    <a href="CalMainCalendar.cal?year=<%out.print(year+1);%>&month=<%out.print(month);%>">▶</a>
    </td>
    <td align=center width=300> <!-- 월 -->
    <a href="CalMainCalendar.cal?year=<%out.print(year);%>&month=<%out.print(month-1);%>">◀</a>
    <% out.print(month+1); %>월
    <a href="CalMainCalendar.cal?year=<%out.print(year);%>&month=<%out.print(month+1);%>">▶</a>
    </td>
    <td align=right width=200><% out.print(currentYear + "-" + (currentMonth+1) + "-" + currentDate); %></td>
   </tr>
  </table>
  <table border=1 cellspacing=0> <!-- 달력 부분 -->
   <tr>
    <td width=100>일</td> <!-- 일=1 -->
    <td width=100>월</td> <!-- 월=2 -->
    <td width=100>화</td> <!-- 화=3 -->
    <td width=100>수</td> <!-- 수=4 -->
    <td width=100>목</td> <!-- 목=5 -->
    <td width=100>금</td> <!-- 금=6 -->
    <td width=100>토</td> <!-- 토=7 -->
   </tr>
   <tr height=30>
   <%
   cal.set(year, month, 1); //현재 날짜를 현재 월의 1일로 설정
   int startDay=cal.get(java.util.Calendar.DAY_OF_WEEK); //현재날짜(1일)의 요일
   int end=cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH); //이 달의 끝나는 날
   int br=0; //7일마다 줄 바꾸기
   
   
   CalendarDAO cDao=CalendarDAO.getInstance();
   CalendarVO cVo=cDao.detail();
   String start_date=cVo.getCal_start_date();
   String end_date=cVo.getCal_end_date();
   String start_date_day="";
   String end_date_day="";
   String start_date_month="";
   String end_date_month="";
   String start_date_year="";
   String end_date_year="";
   //일
   if(start_date.substring(8,9).equals("0")){
	   start_date_day=start_date.substring(9,10);
   }else{
	   start_date_day=start_date.substring(8,10);
   }
   if(end_date.substring(8,9).equals("0")){
	   end_date_day=end_date.substring(9,10);
   }else{
	   end_date_day=end_date.substring(8,10);
   }
   //월
   if(start_date.substring(5,6).equals("0")){
	   start_date_month=start_date.substring(6,7);
   }else{
	   start_date_month=start_date.substring(5,7);
   }
   if(end_date.substring(5,6).equals("0")){
	   end_date_month=end_date.substring(6,7);
   }else{
	   end_date_month=end_date.substring(5,7);
   }
   //년
	start_date_year=start_date.substring(0,4);
	end_date_year=end_date.substring(0,4);
	   System.out.println("월"+start_date_month);
	   System.out.println("year"+start_date_year);
   
   int startDateDay=Integer.parseInt(start_date_day);
   int endDateDay=Integer.parseInt(end_date_day);
   int startDateMonth=Integer.parseInt(start_date_month);
   int endDateMonth=Integer.parseInt(end_date_month);
   int startDateYear=Integer.parseInt(start_date_year);
   int endDateYear=Integer.parseInt(end_date_year);
   
   //시작달과끝나는달 같을때
   if(startDateMonth==(month+1)&&endDateMonth==(month+1)){
   for(int i=0; i<(startDay-1); i++) { //빈칸출력
    out.println("<td>&nbsp;</td>");
    br++;
    if((br%7)==0) {
     out.println("<br>");
    }
   }
   for(int i=1; i<=end; i++) { //날짜출력
	   	   
	  if(i>=startDateDay&&i<=endDateDay){
		   out.println("<td style='background-color:yellow'>"+i+"</td>");
	  }else{
		out.println("<td>" + i + "</td>");  
	  }
    
    br++;
    if((br%7)==0 && i!=end) {
     out.println("</tr><tr height=30>");
    }
   }
   while((br++)%7!=0) //말일 이후 빈칸출력
    out.println("<td>&nbsp;</td>");
   
   //다를때
   }else if( (startDateMonth==(month+1)&&endDateMonth!=(month+1)) ||( (startDateMonth!=(month+1)&&endDateMonth==(month+1))) ){
   for(int i=0; i<(startDay-1); i++) { //빈칸출력
	    out.println("<td>&nbsp;</td>");
	    br++;
	    if((br%7)==0) {
	     out.println("<br>");
	    }
	   }
	   for(int i=1; i<=end; i++) { //날짜출력
		   
		   
		  if(i>=startDateDay){
			   out.println("<td style='background-color:yellow'>"+i+"</td>");
		  }else if(i<startDateDay&&i>endDateDay){
			out.println("<td>" + i + "</td>");  
		  }else if(i<=endDateDay){
		   out.println("<td style='background-color:yellow'>"+i+"</td>");
		  }else{
			out.println("<td>" + i + "</td>");  
		  }
	   
	   
	   
	   
	    br++;
	    if((br%7)==0 && i!=end) {
	     out.println("</tr><tr height=30>");
	    }
	   }
	   while((br++)%7!=0) //말일 이후 빈칸출력
	    out.println("<td>&nbsp;</td>");
   }
   %>
   </tr>
  </table>
  </center>
  <br><br>
  <form>
  <input type="button" value="일정변경" onclick="openPopup()"> 
  </form>
  
  <p>
  <h3 onclick="detailOpen()">상세보기 초기화</h3>
  <input type="button" value="상세보기" onclick="detailView()">
  <div id="detail" style="display:none">
  		<table>
  	<tr>
  		<td>팀명</td>
  		<td>${calendardates.member_team}</td>
  	</tr>
  	<tr>
  		<td>프로젝트 이름</td>
  		<td>${calendardates.cal_title}</td>
  	</tr>
  	<tr>
  		<td>참여인원</td>
  		<td>${calendardates.cal_member}</td>
  	</tr>
  	<tr>
  		<td>프로젝트 내용</td>
  		<td>${calendardates.cal_content}</td>
  	</tr>
  	<tr>
  		<td>프로젝트 기간</td>
  		<td>${calendardates.cal_start_date} - ${calendardates.cal_end_date}</td>
  	</tr> 	
  </table>
  </div>

 </body>
</html>