<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
</head>
<body>

	<h1>ログイン画面です。</h1>

	<h2>ユーザー名とパスワードを入力してください。</h2>

	<form action="LoginCheck" method="post">
		<p>ユーザー名:</p>
		<input type="text" name="logname">
		<p>パスワード:</p>
		<input type="password" name="logpass">
		<br>
		<input type="submit" value="ログイン">

	</form>

	<a href="Register">登録画面に行く。</a>
</body>
</html>