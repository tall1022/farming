<%@page import="goods.model.Goods"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	//request.setAttribute("gBean", gBean);
// 모니터의 최신등록제품의 g_num을 찾아서 내용 호출하는 함수 구현.
Goods goodsbean = (Goods) request.getAttribute("gBean");
%>
<div class="col-lg-4 col-md-6 mb-4">
	<div class="card h-100">
		<a href="#"><img class="card-img-top"
			src="./upload/<%=goodsbean.getG_image().split(",")[0]%>" width="700"--%>
			height="400" alt=""></a>
		<!-- 		<a href="#"><img class="card-img-top" -->
		<!-- 			src="http://placehold.it/700x400" alt=""></a> -->
		<div class="card-body">
			<h4 class="card-title">
				<a href="GoodsDetail.go?num=<%=goodsbean.getG_num()%>"><%=goodsbean.getG_name()%></a>
			</h4>
			<h5><%=goodsbean.getG_price()%>원
			</h5>
			<h5>
				남은수량 :<%=goodsbean.getG_amount()%>개
			</h5>

		</div>
		<div class="card-footer">
			<small class="text-muted">&#9733; &#9733; &#9733; &#9733;
				&#9734;</small>
		</div>
	</div>
</div>