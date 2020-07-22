<%@page import="goods.model.Goods"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<body>

	<div>
		<h3>
			<a class="page-link" href="GoodsAdd.ag">상품등록</a>
		</h3>

	<h2 align="center">상품목록</h2>
	<br>
	<table class="table table-hover">
		<tr>
			<td>번호</td>
			<td>카테고리</td>
			<td>사진</td>
			<td>상품명</td>
			<td>단가</td>
			<td>수량</td>
			<td>등록일자</td>
			<td>수정/삭제</td>
		</tr>

		<c:if test="${empty goodsList }">
			<tr>
				<th colspan="8">등록된 상품이 없습니다</th>
			</tr>
		</c:if>
		<c:if test="${not empty goodsList }"> 
   			<c:forEach var="goods" items="${goodsList }">
     		 <tr>
     			 <td>${g_num}</td>
     			 <td>${goods.g_kind}</td>
				 <td><img src="upload/${goods.getG_image().split(',')[0]}" width="50" height="50" alt=""></td>
     			 <td>${goods.g_name}</td>
     			 <td>${goods.g_price}</td>
     			 <td>${goods.g_amount}</td>
     			 <td>${goods.g_reg_date}</td>

			<td><a href="GoodsModify.ag?g_num=${goods.g_num()}">수정</a>
				/<a href="GoodsDelete.ag?g_num=${goods.g_num()}">삭제</a></td>
		</tr>
		</c:forEach>
		</c:if>
		

	</table>
	      <nav aria-label="Page navigation example">
         <ul class="pagination justify-content-center">
         <c:if test="${startPage > pagePerBlock }">
            <li class="page-item"><a class="page-link" href="list.action?pageNum=${startPage-1}">
            Prev</a></li>
         </c:if>
         <c:if test="${startPage <= pagePerBlock }">
            <li class="page-item disabled"><a class="page-link" href="list.action?pageNum=${startPage-1}">
            Prev</a></li>
         </c:if>
         <c:forEach var="i" begin="${startPage }" end="${endPage }">
            <c:if test="${i==currentPage }">      
            <li class="page-item"><a class="page-link" href="#">${i}</a></li>
            </c:if>
            <c:if test="${i != currentPage }">
            <li class="page-item"><a class="page-link" href="list.action?pageNum=${i}">${i}</a></li>
            </c:if>
         </c:forEach>
         <c:if test="${endPage < totalPage }">   
            <li class="page-item"><a class="page-link" href="list.action?pageNum=${endPage+1}">Next</a></li>
         </c:if>
         <c:if test="${endPage >= totalPage }">   
            <li class="page-item disabled"><a class="page-link" href="list.action?pageNum=${endPage+1}">Next</a></li>
         </c:if>
            <li class="page-item disabled"><a class="page-link" href="GoodsAdd.ag">상품등록</a></li>
         </ul>
      </nav>
<h2>${g_num}</h2>
	</div>
</body>
</html>

