<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
   function submit() {
	   frm.submit();
   }

	function checkout() {
		
	}
</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<%
	//객체 생성
	Calendar cal = Calendar.getInstance();

	//현재 날짜
	int NowYear = cal.get(Calendar.YEAR);
	int NowMonth = cal.get(Calendar.MONTH)+1;
	int NowDay = cal.get(Calendar.DATE);

	String selectYear = request.getParameter("year");
	String selectMonth =request.getParameter("month");
	
	int year, month;
	
	//처음 호출 시 
	if( selectYear == null & selectMonth == null){
		year = NowYear;
		month = NowMonth;
	} else {
		year = Integer.parseInt(selectYear);
		month = Integer.parseInt(selectMonth);
		if(month<0) { month=11; year=year-1; } 
        if(month>11) { month=0; year=year+1; }
	}
%>
<body>
<form action="MainHRCheckinAction.vc" method="post" id="frm" name="frm">
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
				    <td width=200><% out.print(NowYear + "-" + (NowMonth) + "-" + NowDay); %></td>
				<td>
					<button onclick="submit()">출근</button>
					<button onclick="submit()">퇴근</button>
				</td>
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
		<tr height='50'>
			<%
			
			//날짜 셋팅
			cal.set(year, month, 1);
			
			//선택월의 시작요일, 선택월의 마지막날짜
			int startDay = cal.get(Calendar.DAY_OF_WEEK);
			int endDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			int br = 0;
			
				//빈칸 	
				for( int i = 1; i < startDay; i++){
					out.println("<td>&nbsp;</td>");
					br++;
					if( (br%7) == 0){
						out.println("<br>");
					}
				}
				// 날짜
				for( int i = 1; i <= endDay; i++){
					out.println("<td><a href='#'>" + i + "</a></td>");
					br++;
					if((br%7)==0 && i != endDay){
						out.println("</tr><tr height='50'>");
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