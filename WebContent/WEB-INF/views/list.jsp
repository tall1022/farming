<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html><html><head><meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">@import url()</style>
</head><body>
<br><br>
<h2 align="center">게시판</h2>
<table style="margin-left: auto; margin-right: auto;">
   <tr>
      <th text-align: center>번호</th>
      <th>제목</th>
      <th>작성자</th>
      <th>조회수</th>
      <th>작성일</th>
   </tr>
<c:if test="${empty list }">
   <tr><th colspan="5">게시글 없습니다</th></tr>
</c:if>
<c:if test="${not empty list }"> 
   <c:forEach var="board" items="${list }">
      <tr><td>${tot}<c:set var="tot" value="${tot - 1}"/></td>
      <c:if test="${board.del=='n' }">
            <td title="${board.content}">
         <c:if test="${board.re_level > 0 }">      
            <img alt="" src="images/level.gif" height="10" 
               width="${board.re_level *10 }">
            <img alt="" src="images/re.gif">
         </c:if>
            <a href="content.do?num=${board.num}&pageNum=${currentPage}">
                  ${board.subject}</a> 
         <c:if test="${board.readcount > 30 }">
            <img alt="" src="images/hot.gif">
         </c:if>
         </td>
            <td>${board.writer}</td>
            <td>${board.readcount}</td>
            <td>${board.reg_date}</td></tr>
      </c:if>
      <c:if test="${board.del=='y'}">
         <th colspan="4">삭제된 글입니다</th></tr>
      </c:if>   
   </c:forEach>
</c:if>
</table>

<%--    <c:if test="${startPage > pagePerBlock }"> --%>
<%--       <button onclick="location.href='list.do?pageNum=${startPage-1}'">[이전]</button> --%>
<%--    </c:if> --%>
<%--    <c:forEach var="i" begin="${startPage}" end="${endPage}"> --%>
<%--       <c:if test="${i==currentPage }"> --%>
<%--             <button onclick="location.href='list.do?pageNum=${i}'" --%>
<!--                disabled="disabled"> -->
<%--             [${i}]</button> --%>
<%--       </c:if>    --%>
<%--       <c:if test="${i!=currentPage }">       --%>
<%--          <button onclick="location.href='list.do?pageNum=${i}'"> --%>
<%--          [${i}]</button> --%>
<%--       </c:if> --%>
<%--    </c:forEach> --%>
<%--    <c:if test="${endPage < totalPage }"> --%>
<%--       <button onclick="location.href='list.do?pageNum=${endPage+1}'">[다음]</button> --%>
<%--    </c:if> --%>

<hr/><!--template.jsp야!  -->
      <a class="btn btn-primary float-left">글목록</a>
      <a href="insertForm.do?pageNum=1" class="btn btn-primary float-right">글쓰기</a>
      <nav aria-label="Page navigation example">
         <ul class="pagination justify-content-center">
            <li class="page-item disabled"><a class="page-link" href="${startPage-1}"
               tabindex="-1">Previous</a></li>
            <li class="page-item"><a class="page-link" href="list.do?pageNum=${i}">${i}</a></li>
            <li class="page-item"><a class="page-link" href="list.do?pageNum=${i}">${i}</a></li>
            <li class="page-item"><a class="page-link" href="list.do?pageNum=${i}">${i}</a></li>
            <li class="page-item"><a class="page-link" href="list.do?pageNum=${endPage+1}">Next</a></li>
         </ul>
      </nav>

</body>
</html>



