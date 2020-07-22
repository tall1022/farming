<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML><html>
<head>
<title>Spatial by TEMPLATED</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="assets/css/main.css" />
<link rel="stylesheet" href="css/bootstrap.css">
<script type="text/javascript" src="/team/js/jquery.js"></script>
</head>
<body class="landing">
	<%	String menu = request.getParameter("menu");
		if (menu==null) menu="mainBoard.action";
		String id= request.getParameter("id");
	%>
	<!-- Header -->
	<jsp:include page="top.jsp" />
	<div id="top1"></div>
	<a href="#menu" class="navPanelToggle"><span class="fa fa-bars"></span></a>
	<!-- Banner -->
	<jsp:include page="banner.jsp" />
	<!-- 게시판 -->
	<jsp:include page="<%=menu%>" />
	

	<!-- Footer -->
	<jsp:include page="bottom.jsp" />

	<!-- Scripts -->
	<script src="/team/assets/js/jquery.min.js"></script>
	<script src="/team/assets/js/skel.min.js"></script>
	<script src="/team/assets/js/util.js"></script>
	<script src="/team/assets/js/main.js"></script>

</body>
</html>