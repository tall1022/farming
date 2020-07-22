<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.dao.*,member.model.*"%>
<!DOCTYPE html><html><head><meta charset="UTF-8">
<title>Insert title here</title>
<% String id = request.getParameter("id"); %>
<script type="text/javascript">
	function wincl() {
		opener.frm.id.value="<%=id%>";
		window.close();
	}
</script>
</head><body>
<% 	MemberDao md = MemberDao.getInstance();
	Member member  = md.select(id);
	if (member==null) { %>
	<h2>사용가능한 ID입니다</h2>
	<input type="button" value="창닫기" onclick="wincl()">	
<%	} else { %>
<h2>사용중인 ID입니다</h2>
<form action="">
	아이디 : <input type="text" name="id" autofocus="autofocus"
		required="required"><p>
	<input type="submit">
</form>
<%  } %>
</body>
</html>