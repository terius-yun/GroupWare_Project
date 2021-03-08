<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/hrStyle.css">

<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<%
	boolean logoutFailed = false;
	if(request.getAttribute("logoutFailed_noCheckOut") != null){
		logoutFailed = (boolean)request.getAttribute("logoutFailed_noCheckOut");
	}
   //객체 생성
   Calendar cal = Calendar.getInstance();

   //현재 날짜
   int nowYear = cal.get(Calendar.YEAR);
   int nowMonth = cal.get(Calendar.MONTH);
   int nowDay = cal.get(Calendar.DATE);
   
   String cal_year = (String)request.getAttribute("cal_year");
   String cal_month = Integer.toString((int)request.getAttribute("cal_month"));
   
   int year, month;
   year = Integer.parseInt(cal_year);
   month = Integer.parseInt(cal_month);
%>
<body>
		<div id="pageBtn">
			<ul class="ulbtn">
				<li><button class="pagelink check" onclick="VcCal()">연차</button></li>
				<li><button class="pagelink check" onclick="HrCal()">근태</button></li>
			</ul>
		</div>
<form action="" method="post" id="frm" name="frm">
   	<div id="hrbtn">
		<button name="att" class="check" onclick="check()">출근</button>
		<input class="check" type="submit" value="퇴근" formaction="MainHRCheckoutAction.vc">
	</div>
   <table id="tab1">
      <tr>
         <td>
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
                <td id="currentDate" width=200><%=nowYear + "-" + (nowMonth+1) + "-" + nowDay%></td>
            </tr>
   </table>
   <table id="tab2" border="1">
      <tr id="yo">
         <td width="100">월</td>
         <td width="100">화</td>
         <td width="100">수</td>
         <td width="100">목</td>
         <td width="100">금</td>
         <td width="100">토</td>
         <td width="100">일</td>
      </tr>
      <tr height='100'>
         <%
         
         //날짜 셋팅
         cal.set(year, month-1, 1);
         
         //선택월의 시작요일, 선택월의 마지막날짜
         int start = cal.get(Calendar.DAY_OF_WEEK);
         int end = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
         int br = 0;
         
            //빈칸    
            for( int i = 1; i < start; i++){
               out.println("<td>&nbsp;</td>");
               br++;
               if( (br%7) == 0){
                  out.println("<br>");
               }
            }
            //시간 받아오기
            int []startYear = (int[])request.getAttribute("startYear");//달력이 시작되는 년
            int []startMonth = (int[])request.getAttribute("startMonth");//달력이 시작되는 월
            int []startDay = (int[])request.getAttribute("startDay");//달력이 시작되는 일
            String[]cal_checkin = (String[])request.getAttribute("cal_checkin");
            int []endYear = (int[])request.getAttribute("endYear");//달력이 끝나는 년
            int []endMonth = (int[])request.getAttribute("endMonth");//달력이 끝나는 월
            int []endDay = (int[])request.getAttribute("endDay");
            String[]cal_checkout = (String[])request.getAttribute("cal_checkout");
            int hrCount = (int)request.getAttribute("hrCount");
            
            // 날짜
            for( int i = 1; i <= end; i++){
               out.println("<td class='day' id='day"+i+"'>" + i);
               if( hrCount != 0 ){
                  for(int j = 0; j <hrCount; j++){
                     if(startYear[j]<= year
                           && year <= endYear[j]
                           && startMonth[j]==month 
                           && endMonth[j]== month
                           && startDay[j]== i
                           && startDay[j] <= endDay[j]
                           ){
                        out.println("<p id='checkin'> 출근 : " + cal_checkin[j] +"</p>");
                         out.println("<p id='checkout'> 퇴근 : " + cal_checkout[j] +"</p>");
                        startDay[j]++;
                     } 
                  }
               }

               out.println("</td>");
               br++;
               if((br%7)==0 && i != end){
                  out.println("</tr><tr class='day' height='100'>");
               }
            }
               while((br++)%7 != 0)
                   out.println("<td>&nbsp;</td>");
         %>         
      </tr>
   </table>
   
</form>
<script type="text/javascript">	
	function changeYear(changeValue){// 년도 이동
	   year = document.getElementById('yearP').value
	   if(changeValue == 1){//전년도 이동
	      year--;
	      var form = document.createElement('form');
	      form.setAttribute("name","changeYearForm");
	      form.setAttribute('method', 'post');
	      form.setAttribute('action', 'MainHRFormAction.vc');
	      
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
	      form.setAttribute('action', 'MainHRFormAction.vc');
	      
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
	         form.setAttribute('action', 'MainHRFormAction.vc');
	
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
	         form.setAttribute('action', 'MainHRFormAction.vc');
	
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
	      if(month > 12){//12월에서 다음달로 가기의 움직임
	         month = 1;
	         year++;
	         var form = document.createElement('form');
	         form.setAttribute("name","changeMonthForm");
	         form.setAttribute('method', 'post');
	         form.setAttribute('action', 'MainHRFormAction.vc');
	
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
	      }else{//12월에서 다음달로 가기를 제외한 움직임
	         var form = document.createElement('form');
	         form.setAttribute("name","changeMonthForm");
	         form.setAttribute('method', 'post');
	         form.setAttribute('action', 'MainHRFormAction.vc');
	
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
	
	function VcCal(){//연차관리로 이동
					location.href="MainVCFormAction.vc";
	}
	
	function HrCal(){//출결관리로 이동
		location.href="MainHRFormAction.vc";
	}
	
	function check(){// 출근 버튼 클릭시
	var today = document.getElementById('currentDate').innerText;
	var day = today.substring(today.lastIndexOf("-")+1);
	var todayCheckIn = document.getElementById('day'+day).children.length
		if(todayCheckIn == 0){
			alert("출근하였습니다.");
			document.frm.action="MainHRCheckinAction.vc";
			document.frm.submit();
		}else{
			location.href="MainHRFormAction.vc";
			alert("금일 출근은 이미 처리되었습니다.");	
		}
	}
	<% if(logoutFailed == true){%>
		document.addEventListener("DOMContentLoaded", function(){
			alert("퇴근이 처리되지 않았습니다.");
		});
	<%}%>
	
</script>
</body>
</html>