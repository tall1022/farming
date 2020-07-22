<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.dao.*"%>
<!DOCTYPE html><html><head><meta charset="UTF-8">
<title>Insert title here</title></head><body>
<%	request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="member" class="member.model.Member"></jsp:useBean>
<jsp:setProperty property="*" name="member"/>
<%	MemberDao md = MemberDao.getInstance();
	int result = md.insert(member);
	System.out.println(result);
	if (result > 0) {%>
<script type="text/javascript">
	alert("회원가입을 환영합니다");
	location.href = "loginForm.do";
</script>
<%  } else { %>
<script type="text/javascript">
	alert("똑바로해");
	history.go(-1);
</script>
<%  } %>
</body>
</html>