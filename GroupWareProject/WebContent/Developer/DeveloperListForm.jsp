<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>        
<%
	int listcount=((Integer)request.getAttribute("listcount")).intValue();
	int nowpage=((Integer)request.getAttribute("page")).intValue();
	int maxpage=((Integer)request.getAttribute("maxpage")).intValue();
	int startpage=((Integer)request.getAttribute("startpage")).intValue();
	int endpage=((Integer)request.getAttribute("endpage")).intValue();
%>

<html>
<head>
    <title>개발팀</title>
    
    <style type="text/css">
        #topForm{
            text-align :right;
        }
        #Developer, #pageForm, #searchForm{
            text-align :center;
        }
        #bList{
            text-align :center;
        }
    </style>

    <script type="text/javascript">
        function writeForm(value){
        	if(value == "0"){//글쓰기
            	location.href="DeveloperWrite.dp";
        		}else if(value == "1"){//자유게시판
        		location.href="BoardListForm.bo";
        		}else if(value=="2"){//기획팀
        			location.href="PlanListForm.pl";
        		}else if(value=="3"){//디자인팀
        			location.href="DesignListForm.dgi";
        		}else {//공지사항
        			location.href="notice.dp";
        		}
        }
        
    </script>
    
</head>
<body>  
<div>개발팀 게시판</div>  
 <div id="wrap">
    <br>
     <div id="topForm">
        <c:if test="${sessionScope.sessionID!=null}"></c:if>   
            <c:if test="${team_name.equals('개발팀')}">
            <input type="button" value="글쓰기" onclick="writeForm(0)">
            </c:if>
            <input type="button" value="자유게시판" onclick="writeForm(1)">
            <input type="button" value="기획팀" onclick="writeForm(2)">
            <input type="button" value="디자인팀" onclick="writeForm(3)">
     </div>
    <br>
     <div id="Developer">
        <table id="bList" width="800" border="3" bordercolor="lightgray">
            <tr height="30">
                <td>제목</td>
                <td>작성자</td>
                <td>팀명</td>
                <td>작성일</td>
                <td>조회수</td>
            </tr>    
          	<!--공지용 for each새로 생성 -->
          	
            <c:forEach var="list" items="${lists}">
			<tr>
			<!-- 너 수정 -->
			<td><div align="center">
			
				<a href="DeveloperDetailAction.dp?num=${list.developer_num}">
				${list.developer_title}</a></div></td>
			<!-- 작성자 정보 -->
			<td><div align="center">${list.member_name}</div></td>
			<td><div align="center">${list.member_team}</div></td>
			<td><div align="center">${list.developer_writedate}</div></td>
			<td><div align="center">${list.developer_readcount}</div></td>
			</tr>
		</c:forEach>
        </table>
     </div>
    <br>
    <div>
    <table>
    	<tr align=center height=20>
			<td colspan=7 style="font-family: Tahoma; font-size: 10pt;">
				<%if(nowpage<=1){ %> [이전]&nbsp; <%}else{ %> <a
				href="./DeveloperListForm.dp?page=<%=nowpage-1 %>">[이전]</a>&nbsp; <%} %> <%for(int a=startpage;a<=endpage;a++){
				if(a==nowpage){%> [<%=a %>] <%}else{ %> <a
				href="./DeveloperListForm.dp?page=<%=a %>">[<%=a %>]
			</a>&nbsp; <%} %> <%} %> <%if(nowpage>=maxpage){ %> [다음] <%}else{ %> <a
				href="./DeveloperListForm.dp?page=<%=nowpage+1 %>">[다음]</a> <%} %>
			</td>
		</tr>
	</table>
	</div>
    <br>
    <div id="searchForm">
        <form action="DeveloperSearch.dp" method="post">
               <select name="searchName" size="1"> 
                  <option value="id">아이디</option>
                  <option value="title">글제목</option>
               </select> 
               <input type="text" name="searchValue"> 
               <input type="submit" value="찾기">
            </form>   
    </div>
</div>    
 
</body>
</html>