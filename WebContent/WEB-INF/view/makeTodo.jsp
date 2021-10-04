<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規作成</title>
</head>
<body>

	<form action="NewtodoQuery" method="get">
		<label>リスト</label>
		<input type="text" name="task"><br>
		<label>重要度</label>
		<input type="radio" name="importance" value="most">5
		<input type="radio" name="importance" value="more">4
		<input type="radio" name="importance" value="normal">3
		<input type="radio" name="importance" value="less">2
		<input type="radio" name="importance" value="least">1

		<br>


		<input type="submit" value="作成">




	</form>

</body>
</html>