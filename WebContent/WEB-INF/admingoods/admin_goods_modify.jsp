<%@page import="goods.model.Goods"%>
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
//request.setAttribute("goodsbean", goodsbean);
Goods goodsbean=(Goods)request.getAttribute("v");
%>
<h1>상품수정</h1>
<form action="./GoodsModifyAction.ag" method="post">
<input type="hidden" name="num" value="<%=goodsbean.getG_num()%>">
<table border="1">
<tr><td>카테고리</td><td>
<select name="category">
<option value="outwear" <%if(goodsbean.getG_kind().equals("outwear")){%>selected<%}%>>아웃웨어</option>
<option value="fulldress" <%if(goodsbean.getG_kind().equals("fulldress")){%>selected<%}%>>정장/신사복</option>
<option value="Tshirts" <%if(goodsbean.getG_kind().equals("Tshirts")){%>selected<%}%>>티셔츠</option>
<option value="shirts" <%if(goodsbean.getG_kind().equals("shirts")){%>selected<%}%>>와이셔츠</option>

</select></td></tr>
<tr><td>상품이름</td><td><input type="text" name="name" value="<%=goodsbean.getG_name()%>"></td></tr>
<tr><td>판매가</td><td><input type="text" name="price" value="<%=goodsbean.getG_price()%>"></td></tr>
<tr><td>색깔</td><td><input type="text" name="color" value="<%=goodsbean.getG_color()%>"></td></tr>
<tr><td>수량</td><td><input type="text" name="amount" value="<%=goodsbean.getG_amount()%>"></td></tr>
<tr><td>사이즈</td><td><input type="text" name="size" value="<%=goodsbean.getG_size()%>"></td></tr>
<tr><td>인기상품</td>
<td><input type="radio" name="best" value="1" <%if(goodsbean.getG_best()==1){%>checked<%}%>>예
<input type="radio" name="best" value="0" <%if(goodsbean.getG_best()==0){%>checked<%}%>>아니오</td></tr>
<tr><td>제품정보</td><td><input type="text" name="content" value="<%=goodsbean.getG_content()%>"></td></tr>
<tr><td colspan="2"><input type="submit" value="상품수정">
<input type="reset" value="다시등록"></td></tr>
</table>
</form>
</body>
</html>



