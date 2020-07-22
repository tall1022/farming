<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.dao.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%	String id = request.getParameter("id");
	String password = request.getParameter("password");
	MemberDao md = MemberDao.getInstance();
	int result = md.loginChk(id, password);
	System.out.println("result:"+result);
	if (result > 0) {  
		session.setAttribute("id", id); %>
<script type="text/javascript">
	location.href="index.action";
</script>		
<%	} else if (result == 0) { %>
<script type="text/javascript">
	alert("비밀번호를 확인하세요"); 
	history.go(-1);
</script>	
<%  } else { %>
<script type="text/javascript">
	alert("없는 ID입니다"); 
	history.go(-1);
</script>
<%  } %>
</body>
</html>