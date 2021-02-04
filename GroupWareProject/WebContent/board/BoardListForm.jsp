<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>        
<html>
<head>
    <title>디자인 게시글</title>
    
    <style type="text/css">
        #wrap {
            width: 800px;
            margin: 0 auto 0 auto;
        }
        #topForm{
            text-align :right;
        }
        #board, #pageForm, #searchForm{
            text-align :center;
        }
        #bList{
            text-align :center;
        }
    </style>

    <script type="text/javascript">
        function writeForm(value){
        	if(value == "0"){//글쓰기
            	location.href="BoardWrite.bo";
        		}else if(value == "1"){//개발팀
        		location.href="BoardDevelopment.bo";
        		}else if(value=="2"){//기획팀
        			location.href="BoardPlan.bo";
        		}else if(value=="3"){//디자인팀
        			location.href="DesignListForm.dgi";
        		}else {//공지사항
        			location.href="notice.bo";
        		}
        }
        function PageMove(page){
            location.href = "BoardListForm.bo?page="+page;
          }
    </script>
    
</head>
<body>  
<div>자유게시판</div>  
 <div id="wrap">
    <br>
     <div id="topForm">
        <c:if test="${sessionScope.sessionID!=null}"></c:if>   
            <input type="button" value="글쓰기" onclick="writeForm(0)">
            <input type="button" value="개발팀" onclick="writeForm(1)">
            <input type="button" value="기획팀" onclick="writeForm(2)">
            <input type="button" value="디자인팀" onclick="writeForm(3)">
         <c:if test="${sessionScope.sessionID.equals('gd9')}">
         	<input type="button" value="공지사항 작성" onclick="writeForm(4)">
         </c:if>
     </div>
    <br>
     <div id="board">
        <table id="bList" width="800" border="3" bordercolor="lightgray">
            <tr height="30">
                <td>제목</td>
                <td>작성자</td>
                <td>팀명</td>
                <td>작성일</td>
                <td>조회수</td>
            </tr>    
          	<!--공지용 for each새로 생성 -->
          	<c:forEach var="notice" items="${notices}">
          		<tr>
          		<td><!-- ${notice.board_notice_title} -->
          		</td>
          		</tr>
          	</c:forEach>
            <c:forEach var="list" items="${lists}">
			<tr>
			<!-- 너 수정 -->
			<td><div align="center">
			<b style="color: red">공지</b>
				<a href="BoardDetailAction.bo?num=${list.board_num}">
				${list.board_title}</a></div></td>
			<!-- 작성자 정보털림 -->
			<td><div align="center">${list.member_name}</div></td>
			<td><div align="center">${list.member_team }</div></td>
			<td><div align="center">${list.board_writedate}</div></td>
			<td><div align="center">${list.board_readcount}</div></td>
			</tr>
		</c:forEach>
        </table>
     </div>
    <br>
    <div class="toolbar-bottom">
  <div class="toolbar mt-lg">
    <div class="sorter">
      <ul class="pagination">
        <li><a href="javascript:PageMove(${paging.firstPageNo})">맨앞으로</a></li>
        <li><a href="javascript:PageMove(${paging.prevPageNo})">앞으로</a></li>
              <c:forEach var="i" begin="${paging.startPageNo}" end="${paging.endPageNo}" step="1">
                  <c:choose>
                      <c:when test="${i eq paging.pageNo}">
                <li class="active"><a href="javascript:PageMove(${i})">${i}</a></li>
                      </c:when>
                      <c:otherwise>
                        <li><a href="javascript:PageMove(${i})">${i}</a></li>
                      </c:otherwise>
                  </c:choose>
              </c:forEach>
        <li><a href="javascript:PageMove(${paging.nextPageNo})">뒤로</a></li>
        <li><a href="javascript:PageMove(${paging.finalPageNo})">맨뒤로</a></li>
      </ul>
    </div>
  </div>
</div>
    <br>
    <div id="searchForm">
        <form>
            <select name="opt">
                <option value="0">제목</option>
                <option value="1">내용</option>
                <option value="2">제목+내용</option>
                <option value="3">글쓴이</option>
            </select>
            <input type="text" size="20" name="condition"/>&nbsp;
            <input type="submit" value="검색"/>
        </form>    
    </div>
</div>    
 
</body>
</html>