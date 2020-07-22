<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Shop Homepage - Start Bootstrap Template</title>

<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="css/shop-homepage.css" rel="stylesheet">

</head>

<body>
	<%	String menu = request.getParameter("menu");
		if (menu==null) menu="GoodsList.ag";
	//	String id= request.getParameter("id");
	%>
	<!-- Navigation -->
	<jsp:include page="shopMenu.jsp" />

	<!-- Page Content -->
	<div class="container">
			<div class="row">
			<jsp:include page="shopContent.jsp" />
			<!-- /.col-lg-3 -->
		<div class="col-lg-9">
			<div class="row">
<%-- 			<jsp:include page="admin_goods_list.jsp" /> --%>


			<jsp:include page="<%=menu %>" />

 			<script type="text/javascript"> 
//			location.href="GoodsList.ag" 
			</script>	 
			</div>
			<!-- /.row -->

		</div>
		<!-- /.col-lg-9 -->

	</div>
	<!-- /.row -->

	</div>
	<!-- /.container -->

	<!-- Footer -->
	<footer class="py-5 bg-dark">
		<div class="container">
			<p class="m-0 text-center text-white">Copyright &copy; Your
				Website 2019</p>
		</div>
		<!-- /.container -->
	</footer>

	<!-- Bootstrap core JavaScript -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>

