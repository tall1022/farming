<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.dao.*, member.model.*"%>
<%@ include file="sessionChk.jsp" %>
<!DOCTYPE html><html><head><meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">@import url("common.css");</style>
<script type="text/javascript">
	function chk() {
		if (frm.password.value != frm.password2.value) {
			alert("암호와 암호 확인이 다릅니다");
			frm.password.focus();	return false; 
		}
	}
</script></head><body>
<%	MemberDao md = MemberDao.getInstance();
	Member member  = md.select(id);	%>
<form action="update.action" method="post" name="frm" 
	onsubmit="return chk()">
	<input type="hidden" name="password3" 
	value="${password }">
	<%-- <input type="hidden" name="id" value="<%=member.getId()%>"> --%>
<table border="1"><caption>회원정보 수정</caption>
	<tr><th>아이디</th><td><%-- <%=member.getId() %> --%>
		<input type="text" name="id" readonly="readonly"
		value="${id}"></td></tr>
	<tr><th>암호</th><td><input type="password" name="password"
		required="required" autofocus="autofocus"></td></tr>
	<tr><th>암호확인</th><td><input type="password" 
		name="password2" required="required"></td></tr>
	<tr><th>이름</th><td><input type="text" name="name" 
		required="required" value="<%=member.getM_name()%>"></td></tr>
	<tr><th>주소1</th><td><input type="text" name="address"
		required="required" value="<%=member.getM_address1()%>"></td></tr>
	<tr><th>주소2</th><td><input type="text" name="address"
		required="required" value="<%=member.getM_address2()%>"></td></tr>
	<tr><th>전화</th><td><input type="tel" name="tel" 
		required="required" pattern="\d{3}-\d{3,4}-\d{4}" 
		value="<%=member.getM_tel()%>"></td></tr>
	<tr><th colspan="2"><input type="submit" value="확인"></th></tr>
</table>
</form>
</body>
</html>