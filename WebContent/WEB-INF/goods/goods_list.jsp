<%@page import="goods.model.Goods"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
//request.setAttribute("goodsList", goodsList);
List goodsList=(List)request.getAttribute("goodsList");
%>
<!-- <h1>상품목록</h1> -->
<!-- <h3><a href="./BasketList.ba">장바구니</a></h3> -->
<!-- <h3><a href="./OrderList.or">주문목록</a></h3> -->
<!-- <table border="1"> -->
<!-- <tr><td><a href="./GoodsList.go">전체</a></td> -->
<!-- <td><a href="GoodsList.go?item=best">컴퓨터</a></td> -->
<!-- <td><a href="./GoodsList.go?item=outwear">모니터</a></td> -->
<!-- <td><a href="./GoodsList.go?item=fulldress">키보드/신사복</a></td> -->
<!-- <td><a href="./GoodsList.go?item=Tshirts">마우스</a></td> -->
<!-- </table> -->
<div class="col-lg-3">

	<h1 class="my-4">
		<a href="main.shop">파밍하기</a>
	</h1>
	<div class="list-group">
		<a href="GoodsList.go?item=best" class="list-group-item">모니터 파밍</a> 
		<a href="GoodsList.go?item=outwear"	class="list-group-item">키보드 파밍</a> 
		<a href="GoodsList.go?item=fulldress"	class="list-group-item">마우스 파밍</a>
	</div>

</div>
<!-- <table border="1"> -->
<!-- <tr> -->
<%-- <% --%>
<!-- //for(int i=0;i<goodsList.size();i++){ -->
<!-- //	Goods goodsbean=(Goods)goodsList.get(i); -->
<!-- 	%> -->
<%-- <td><img src="./upload/<%=goodsbean.getG_image().split(",")[0] %>" width="130" height="130"><br> --%>
<%--     <a href="GoodsDetail.go?num=<%=goodsbean.getG_num()%>"><%=goodsbean.getG_name() %></a><br> --%>
<%--     <%=goodsbean.getG_price() %>원 --%>
<!-- </td>	 -->
<%-- 	<% --%>
<!-- //} -->
<!-- %> -->
<!-- </tr> -->
</table>
</body>
</html>



