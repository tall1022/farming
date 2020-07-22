<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- WebContent/index.jsp -->
<%
//response.sendRedirect("./Main.me");

//관리자모드 상품등록
//http://localhost:8080/model2/GoodsAdd.ag
//response.sendRedirect("./GoodsAdd.ag");

//사용자모드 상품등록
//http://localhost:8080/model2/GoodsAdd.ag
response.sendRedirect("GoodsList.go");

//사용자모드 장바구니 저장 가상주소
//http://localhost:8080/model2/Model2/BasketAdd.ba
//response.sendRedirect("./GoodsList.go");

//관리자 모드 주문 목록 
//http://localhost:8080/Model2/AdminOrderList.ao
//response.sendRedirect("./AdminOrderList.ao");
%>

</body>
</html>