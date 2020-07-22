<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
@import url("common.css");
</style>
<script type="text/javascript">
	function chk() {
		if (frm.password.value != frm.pass2.value) {
			alert("암호가 다릅니다");
			frm.password.focus();
			frm.password.value = "";
			return false;
		}
	}
</script>
</head>
<body>
	<form action="update.do" name="frm" method="post"
		onsubmit="return chk()">
		<input type="hidden" name="num" value="${num}"> <input
			type="hidden" name="pageNum" value="${pageNum}"> <input
			type="hidden" name="pass2" value="${board.password}">
		<table>
			<caption>게시글 수정</caption>
			<tr>
				<th>번호</th>
				<td>${board.num}</td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="subject" required="required"
					autofocus="autofocus" value="${board.subject}"></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><input type="text" name="writer" required="required"
					value="${board.writer}"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><pre>
						<textarea rows="5" cols="30" name="content" required="required">${board.content}</textarea>
	</pre></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="email" name="email" required="required"
					value="${board.email}"></td>
			</tr>
			<tr>
				<th>암호</th>
				<td><input type="password" name="password" required="required"></td>
			</tr>
			<tr>
				<th colspan="2"><input type="submit"></th>
			</tr>
		</table>
	</form>
</body>
</html>