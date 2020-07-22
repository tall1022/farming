<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html><html><head><meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">@import url("common.css");</style>
<script type="text/javascript">
	function chk() {
		if (frm.password.value != frm.confirmPass.value) {
			alert('암호가 암호확인과 다릅니다'); frm.password.focus();
			frm.password.value = ""; return false;
		}
	}
</script>
</head><body>
<form action="insert.action" method="post" name="frm" 
	onsubmit="return chk()">
	<input type="hidden" name="num" value="${num}">
	<input type="hidden" name="ref" value="${ref}">
	<input type="hidden" name="re_level" value="${re_level}">
	<input type="hidden" name="re_step" value="${re_step}">
	<input type="hidden" name="pageNum" value="${pageNum}">
<table><caption>게시글 입력</caption>	
	<c:if test="${no != null}">
		<tr><th>제목</th><td><input type="text" name="subject" 
		required="required" autofocus="autofocus" value="답변=>"></td></tr>
	</c:if>
	<c:if test="${no == null}">
		<tr><th>제목</th><td><input type="text" name="subject" 
		required="required" autofocus="autofocus"></td></tr>
	</c:if>
	<tr><th>작성자</th><td><input type="text" name="writer" 
		required="required"></td></tr>
	<tr><th>이메일</th><td><input type="email" name="email" 
		required="required"></td></tr>
	<tr><th>암호</th><td><input type="password" name="password" 
		required="required"></td></tr>
	<tr><th>암호확인</th><td><input type="password" 
		name="confirmPass" required="required"></td></tr>
	<tr><th>내용</th><td><textarea rows="5" cols="30" 
		name="content" required="required"></textarea> </td></tr>
	<tr><th colspan="2"><input type="submit" value="확인"></th></tr>
</table>
</form>
</body>
</html>