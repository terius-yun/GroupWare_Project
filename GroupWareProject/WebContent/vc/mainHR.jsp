<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/hrStyle.css">
<script type="text/javascript">
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
      if(month > 12){
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
      }else{
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
function VcCal(){
				location.href="MainVCFormAction.vc";
}
function HrCal(){
	location.href="MainHRFormAction.vc";
}
function check(){
		alert("출근하였습니다.");	
	}
</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<%
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
		<input name="att" class="check" type="submit" value="출근" formaction="MainHRCheckinAction.vc"
		onclick="check()">
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
                <td width=200><%=nowYear + "-" + (nowMonth+1) + "-" + nowDay%></td>
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
         System.out.println("이번 달 말일 : " + end);
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
            int []startYear = (int[])request.getAttribute("startYear");//연차가 시작되는 년
            int []startMonth = (int[])request.getAttribute("startMonth");//연차가 시작되는 월
            int []startDay = (int[])request.getAttribute("startDay");//연차가 시작되는 일
            String[]cal_checkin = (String[])request.getAttribute("cal_checkin");
            int []endYear = (int[])request.getAttribute("endYear");//연차가 끝나는 년
            int []endMonth = (int[])request.getAttribute("endMonth");//연차가 끝나는 월
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
</body>
</html>