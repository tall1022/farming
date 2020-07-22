<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width" />
    <title>Login Demo - Kakao JavaScript SDK</title>
<style type="text/css">
table{
   width:50%;
   height:50%;
   margin:auto;
}
.container
{
    text-align: center;
    width: 100%;
    padding-right: 20px;
}
</style>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
</head>
<body>
   <form action="login.do">
      <br><br>
      <h2 align="center">로그인</h2>
      <table >
         <tr>
            <th style="width:300px;height:35px; text-align:center">아이디</th>
            <td><input type="text" name="id" required="required"
               autofocus="autofocus" style="width:400px;height:35px"></td>
         </tr>
         <tr>
            <th style="width:300px;height:35px; text-align:center">비밀번호</th>
            <td><input type="password" name="password" required="required" style="width:400px;height:35px"></td>
         </tr>
      </table>
      <div class="container">
             <a id="kakao-login-btn"></a>
    <a href="http://developers.kakao.com/logout"></a>
    <button type="submit" value="확인" style="border-radius:5px">로그인</button>
      </div>
   </form>    
    <script type='text/javascript'>
        //<![CDATA[
        // 사용할 앱의 JavaScript 키를 설정해 주세요.
        Kakao.init('de9e50d302c6e7d0c3f9fc8458ee75fe');
        // 카카오 로그인 버튼을 생성합니다.
        Kakao.Auth.createLoginButton({
            container: '#kakao-login-btn',
            success: function (authObj) {
                alert(JSON.stringify(authObj));
            },
            fail: function (err) {
                alert(JSON.stringify(err));
            }
        });
    </script>
</body>
</html>