<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.container
{
       text-align: center;
           width: 100%;
           padding-right: 20px;
}
table{
	width:50%;
	height:50%;
	margin:auto;
}

</style>

<script type="text/javascript">
	function idChk() {
		if (!frm.id.value) {
			alert("id입력한 후에 체크하세요");
			frm.id.focus();		
			return;		
		}
		window.open("idChk.do?id="+frm.id.value,"",
				"width=300 height=300");
	}
</script></head><body>
<form action="join.action" method="post" name="frm">
<br><br>
<h2 align="center">회원가입</h2>
<table border="1">
	<tr><th>아이디</th><td><input type="text" name="id" 
		required="required" autofocus="autofocus" style="width:400px;height:35px">
		<input type="button" value="ID체크" onclick="idChk()" style="width:120px;height:50px; text-align:center">
		</td></tr>
	<tr><th>비밀번호</th><td><input type="password" name="password"
		required="required" style="width:400px;height:35px"></td></tr>
	<tr><th>이름</th><td><input type="text" name="name"
		required="required" style="width:400px;height:35px"></td></tr>
	<tr><th>주소1</th><td><input type="text" name="address1"
		required="required" style="width:600px;height:35px"></td></tr>
	<tr><th>주소2</th><td><input type="text" name="address2"
		 style="width:600px;height:35px"></td></tr>
	<tr><th>전화번호</th><td><input type="tel" name="tel"
		required="required" placeholder="010-1234-4321" style="width:200px;height:35px"
			pattern="\d{3}-\d{3,4}-\d{4}"></td></tr>
</table>
	<div class="container">
	<button type="submit" value="확인" style="border-radius:5px">확인</button>
	</div>
</form>
</body>
</html>