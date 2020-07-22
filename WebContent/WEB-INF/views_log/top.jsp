<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.dao.*,member.model.*,"%>
 <%
 	String id=request.getParameter("id");
 %>
<header id="header" class="alt">
			<h1><strong><a href="index.action">${id}님 알로하</a></strong> by Templated</h1>
				<nav id="nav">
					<ul>
						<li><a href="index.action">Home</a></li>
						<li><a href="list.action">Board</a></li>
						<li><a href="index.action">Shop</a></li>
						<li><a href="logout.action">MyPage</a></li>
						<li><a href="logout.action">Logout</a></li>
					</ul>
				</nav>
</header>

		
    