<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html><html><head><meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="common.css">
<script type="text/javascript">
	function chk() {
		if (frm.password.value != frm.pass2.value) {
			alert("암호가 다릅니다");  frm.password.focus();
			frm.password.value=""; return false;
		}
	}
</script></head><body>
<form action="delete.action" name="frm" onsubmit="return chk()" >
	<input type="hidden" name="num" value="${num}">
	<input type="hidden" name="pass2" value="${board.password}">
	<input type="hidden" name="pageNum" value="${pageNum}">
	<fieldset><legend>암호</legend>
		<input type="password" name="password" required="required"
			autofocus="autofocus"><p>
		<input type="submit">
	</fieldset>
</form>
</body>
</html>