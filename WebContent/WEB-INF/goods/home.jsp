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

	<!-- Navigation -->
	<jsp:include page="shopMenu.jsp" />

	<!-- Page Content -->
	<div class="container">
			<div class="row">

		<jsp:include page="goods_list.jsp" />
		<!-- /.col-lg-3 -->

		<div class="col-lg-9">

			<jsp:include page="shopSlide.jsp" />

			<div class="row">
				<!-- /.아이템1 -->
				<a href ="GoodsMainList.go?item=best,count=1"></a>
				<!-- /.아이템2 -->
				<a href ="GoodsMainList.go?item=outwear,count=1"></a>
				<!-- /.아이템3 -->
				<a href ="GoodsMainList.go?item=fulldress,count=1"></a>

				

				

				

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

