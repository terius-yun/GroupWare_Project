<%@page import="java.util.ArrayList"%>
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
  <link rel="stylesheet" type="text/css" href="css/mainCalendar.css">

  
  
  
  <script type="text/javascript">
  	function openPopup(){
  		window.name="win_pay";
  		var url="CalAddForm.cal";
		window.open( url, "addUpdate", "top=150, left=650, toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=600, height=600" );
  	}
  	
  	function detailOpen(){
  		location.href="CalendarDetail.cal";
  		
  	}
  	function detailView1(i){
  		teamName="개발팀";
  		
  		if(document.getElementById("detail1_"+i).style.display=="none"){
  			document.getElementById("detail1_"+i).style="display=inline;";
  		}else{
  			document.getElementById("detail1_"+i).style.display="none";
  		}
  	}
  	function detailView2(i){
  		teamName="디자인팀";
  		if(document.getElementById("detail2_"+i).style.display=="none"){
  			document.getElementById("detail2_"+i).style="display=inline;";
  		}else{
  			document.getElementById("detail2_"+i).style.display="none";
  		}
  	}
  	function detailView3(i){
  		teamName="기획팀";
  		if(document.getElementById("detail3_"+i).style.display=="none"){
  			document.getElementById("detail3_"+i).style="display=inline;";
  		}else{
  			document.getElementById("detail3_"+i).style.display="none";
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
  String Year=(String)request.getAttribute("year_");//나타내고자 하는 날짜
  String Month=(String)request.getAttribute("month_");
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
    <a href="CalendarDetail.cal?year=<%out.print(year-1);%>&month=<%out.print(month);%>">◀</a>
    <% out.print(year); %>년
    <a href="CalendarDetail.cal?year=<%out.print(year+1);%>&month=<%out.print(month);%>">▶</a>
    </td>
    <td align=center width=300> <!-- 월 -->
    <a href="CalendarDetail.cal?year=<%out.print(year);%>&month=<%out.print(month-1);%>">◀</a>
    <% out.print(month+1); %>월
    <a href="CalendarDetail.cal?year=<%out.print(year);%>&month=<%out.print(month+1);%>">▶</a>
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
   <tr height=50>
   
   <%
   cal.set(year, month, 1); //현재 날짜를 현재 월의 1일로 설정
   int startDay=cal.get(java.util.Calendar.DAY_OF_WEEK); //현재날짜(1일)의 요일
   int end=cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH); //이 달의 끝나는 날
   int br=0; //7일마다 줄 바꾸기

   
   
 
   //시작달과끝나는달 같을때
   for(int i=0; i<(startDay-1); i++) { //빈칸출력
    out.println("<td>&nbsp;</td>");
    br++;
    if((br%7)==0) {
     out.println("<br>");
    }
   }
   
 //1개발  2디자인 3개발
   String team1="";  String team2="";  String team3="";  
   String[] title1, title2,  title3;
   String[] member1, member2,  member3;
   String[] content1, content2,  content3;
   
   
   int[] start_Day=(int[])request.getAttribute("startDay");
   int[] endDay={1};
   int[] startMonth={1};
   int[] endMonth={1};
   int[] startYear={2000};
   int[] endYear={2000};
   int cal_count =(int) request.getAttribute("cal_count");
   
   title1=new String[cal_count+1]; title2=new String[cal_count+1];  title3=new String[cal_count+1];
   member1=new String[cal_count+1]; member2=new String[cal_count+1];  member3=new String[cal_count+1];
   content1=new String[cal_count+1]; content2=new String[cal_count+1];  content3=new String[cal_count+1];   
   
   String[] member_team={""};
   String[] cal_title={""};
   String[] cal_member={""};
   String[] cal_content={""};
 if(cal_count != -1){
   //getAttribute
   start_Day = (int[])request.getAttribute("startDay");
   endDay = (int[])request.getAttribute("endDay");
   startMonth = (int[])request.getAttribute("startMonth");
   endMonth = (int[])request.getAttribute("endMonth");
   startYear = (int[])request.getAttribute("startYear");
   endYear = (int[])request.getAttribute("endYear");
   
   member_team = (String[])request.getAttribute("member_team");
   cal_title=(String[])request.getAttribute("cal_title");
   cal_member=(String[])request.getAttribute("cal_member");
   cal_content=(String[])request.getAttribute("cal_content");
 
   
   for(int j=0; j<=cal_count ; j++){
	   if(startYear[j] <= year && endYear[j] >=year){
		   if(startMonth[j]<(month+1)){
			   startYear[j] = year;
			   startMonth[j] = (month+1);
			   start_Day[j] =1;
		   }else if(startYear[j]<year&&startMonth[j] >(month+1)){
			   startYear[j] = year;
			   startMonth[j] = (month+1);
			   start_Day[j] =1;
		   }else if(startYear[j]>year&&startMonth[j] >(month+1)){
			   start_Day[j] =0;
		   }
		   if(endYear[j] > year && endMonth[j]<=(month+1)){
			   endYear[j] = year;
			   endMonth[j] = (month+1);
			   endDay[j] = end;
		   }
		   if(endMonth[j] > (month+1)){
			   endYear[j]= year;
			   endMonth[j] = (month+1);
			   endDay[j] = end;
		   }else if(endMonth[j] <(month+1)){
			  endDay[j] = 0;
		   }
		   
	   }
   }
   
 }//if null
   
   for(int i=1; i<=end; i++) { //날짜출력
	   
	   
	   	   
    out.println("<td>" + i);
    
   
    for(int k=0; k<=cal_count ; k++){   	  	
    	
    	if(startYear[k]==year && endYear[k]==year && start_Day[k] == i && startMonth[k] == (month+1)&&startMonth[k] <= endMonth[k] && start_Day[k]<=endDay[k]){
 %>
    		<c:choose>
    			<c:when test="${adTeam.member_team eq '개발팀'}">
   <%
   					if(member_team[k].equals("개발팀")){
   						out.println("<p onclick='detailView1(");%><%=k%>
   						<%out.println(")' style='background-color:#FA5858;cursor: pointer'>개발팀</p>");
    					start_Day[k]++; 
    					
    					team1="개발팀";
    					title1[k]= cal_title[k];
    					member1[k]=cal_member[k];
    					content1[k]=cal_content[k];
    					
   					}				
   %>
    			</c:when>
    			<c:when test="${adTeam.member_team eq '디자인팀'}">
   <% 
   					if(member_team[k].equals("디자인팀")){
   						out.println("<p onclick='detailView2(");%><%=k%>
   						<%out.println(")' style='background-color:#58ACFA;cursor: pointer'>디자인팀</p>");
    					start_Day[k]++;
    					
    					team2="디자인팀";
    					title2[k]= cal_title[k];
    					member2[k]=cal_member[k];
    					content2[k]=cal_content[k];
    					
   					}   				
   %>
    			</c:when>
    			<c:when test="${adTeam.member_team eq '기획팀'}">
   <% 
   					if(member_team[k].equals("기획팀")){
   						out.println("<p onclick='detailView3(");%><%=k%>
   						<%out.println(")' style='background-color:#ACFA58;cursor: pointer'>기획팀</p>");
    					start_Day[k]++;
    					
    					team3="기획팀";
    					title3[k]= cal_title[k];
    					member3[k]=cal_member[k];
    					content3[k]=cal_content[k];
   					}				
   %>
    			</c:when>
    			<c:when test="${adTeam.member_team eq '대표'}">
   <%
					   if(member_team[k].equals("개발팀")){
						   out.println("<p onclick='detailView1(");%><%=k%>
	   						<%out.println(")' style='background-color:#FA5858;cursor: pointer'>개발팀</p>");
								team1="개발팀";
								title1[k]= cal_title[k];
								member1[k]=cal_member[k];
		    					content1[k]=cal_content[k];
							}
					   if(member_team[k].equals("디자인팀")){
						   out.println("<p onclick='detailView2(");%><%=k%>
	   						<%out.println(")' style='background-color:#58ACFA;cursor: pointer'>디자인팀</p>");
								team2="디자인팀";
								title2[k]= cal_title[k];
								member2[k]=cal_member[k];
		    					content2[k]=cal_content[k];
							}  
					   if(member_team[k].equals("기획팀")){
						   out.println("<p onclick='detailView3(");%><%=k%>
	   						<%out.println(")' style='background-color:#ACFA58;cursor: pointer'>기획팀</p>");
	   							team3="기획팀";
	   							title3[k]= cal_title[k];
	   							member3[k]=cal_member[k];
	   	    					content3[k]=cal_content[k];
	   						}	
    					start_Day[k]++;  
    					
   %>
    			</c:when>
    		</c:choose>   		
 <%
    	}
    }
    
//if null

    out.println("</td>");  
   
    
    
     
    br++;
    if((br%7)==0 && i!=end) {
     out.println("</tr><tr height=50>");
    }
   }
    
   
   
   
   while((br++)%7!=0) //말일 이후 빈칸출력
    out.println("<td>&nbsp;</td>");
   %>
   </tr>
  </table>
  </center>
  <br><br>
  
  <c:choose>
  	<c:when test="${adTeam.member_administrator eq '2'}">
		 	<form>
			  <input type="button" value="일정추가" onclick="openPopup()"> 
			  </form>	 	  
			<c:choose>
				<c:when test="${adTeam.member_team eq '개발팀'}">
					<form action="CalendarDevDelete.cal">
						<input type="submit" value="일정삭제">
					</form> 
				</c:when>
				<c:when test="${adTeam.member_team eq '디자인팀'}">
					<form action="CalendarDisDelete.cal">
						<input type="submit" value="일정삭제">
					</form> 
				</c:when>
				<c:when test="${adTeam.member_team eq '기획팀'}">
					<form action="CalendarPlaDelete.cal">
						<input type="submit" value="일정삭제">
					</form> 
				</c:when>
			</c:choose>
		</c:when>
	</c:choose>
	
  <p>
 
  <%for(int k=0;k<=cal_count;k++){
	  out.print("<div id='detail1_"+k+"' style='display:none;'>");
	  out.print("<table class='detailtable'");
	  out.print("<tr>");
	  out.print("<th>팀 이름</th>");
	  out.print("<th>"+team1+"</th>");
	  out.print("<th>프로젝트 이름</th>");
	  out.print("<td>"+title1[k]+"</td>");
	  out.print("<th>참여인원</th>");
	  out.print("<td>"+member1[k]+"</td>");
	  out.print("<th>프로젝트 내용</th>");
	  out.print("<td>"+content1[k]+"</td>");
	  out.print("</tr>");  
	  out.print("</table>"); 
	  out.print("</div>");
  } %>
  
  <p>
  
  <%for(int k=0;k<=cal_count;k++){
	  out.print("<div id='detail2_"+k+"' style='display:none;'>");
	  out.print("<table class='detailtable'");
	  out.print("<tr>");
	  out.print("<th>팀 이름</th>");
	  out.print("<th>"+team2+"</th>");
	  out.print("<th>프로젝트 이름</th>");
	  out.print("<td>"+title2[k]+"</td>");
	  out.print("<th>참여인원</th>");
	  out.print("<td>"+member2[k]+"</td>");
	  out.print("<th>프로젝트 내용</th>");
	  out.print("<td>"+content2[k]+"</td>");
	  out.print("</tr>");  
	  out.print("</table>"); 
	  out.print("</div>");
  } %>
  
  <p>

  <%for(int k=0;k<=cal_count;k++){
	  out.print("<div id='detail3_"+k+"' style='display:none;'>");
	  out.print("<table class='detailtable'");
	  out.print("<tr>");
	  out.print("<th>팀 이름</th>");
	  out.print("<th>"+team3+"</th>");
	  out.print("<th>프로젝트 이름</th>");
	  out.print("<td>"+title3[k]+"</td>");
	  out.print("<th>참여인원</th>");
	  out.print("<td>"+member3[k]+"</td>");
	  out.print("<th>프로젝트 내용</th>");
	  out.print("<td>"+content3[k]+"</td>");
	  out.print("</tr>");  
	  out.print("</table>"); 
	  out.print("</div>");
  } %>
 
 </body>
</html>