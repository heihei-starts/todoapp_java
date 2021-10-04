<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登録画面</title>

</head>
<body>

	<h1>登録画面です。</h1>

	<h2>情報を入力してください。</h2>
		<form action="InputInfo" method="post">
			<p>名前を入力してください</p>
			<input type="text" name="name">
			<p>___________________</p>

			<p>パスワードを入力してください</p>
			<input type="password" name="password">
			<p>____________________</p>
			<input type="submit" value="登録">
		</form>

		<%--DAOに飛んでいきます--%>










</body>
</html>