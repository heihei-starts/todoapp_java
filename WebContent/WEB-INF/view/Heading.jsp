<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="model.NewtodoQueryBL" 	%>
<%@ page import="model.TodoListDto" 	%>
<%@ page import="model.TodoListDao" 	%>
<%@ page import="model.InputInfoDto"	%>
<%@ page import="java.util.*" 			%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>todoリスト</title>
</head>
<body>
<%--todoリスト追加 変更 削除 --%>

	<%--select文をここに記す。 --%>
	<%

		InputInfoDto userInfoOnSession = (InputInfoDto)session.getAttribute("LOGIN_INFO");


		List<TodoListDto> 	todolist 	= new ArrayList<>()		;
		NewtodoQueryBL 		bl 			= new NewtodoQueryBL()	;

		//todolistにtodoの取得したDBをいれる

		todolist = bl.isSelect(userInfoOnSession.getId());

	%>

	<a href="TodoHeading">新規</a>

	<%--todoリスト作成 --%>
	<p>todoリスト一覧</p>



	<%--新規 --%>


	<%
		if (todolist != null && todolist.size() > 0) {

	%>

			<table>
				<tr>
					<th>タスク</th>
					<th>重要度</th>
				</tr>

			<%
				for (TodoListDto dto : todolist) {
			%>

				<tr>
					<td><%=dto.getTitle() %></td>
			<%
				   switch (dto.getImportance()) {
				   case "most":
			%>
					<td>＊＊＊＊＊</td>

				<%
				 	break;
				   case "more":
				%>
					<td>＊＊＊＊</td>
				<%
					break;
				   case "normal":
				%>
					<td>＊＊＊</td>
				<%
				 	break;
				   case "less":
				%>
					<td>＊＊</td>
				<%
				 	break;
				   case "least":
				%>
					<td>＊</td>
				<%
					break;
				   }

				%>
				 	<td><a href="Delete?id=<%=dto.getTitle()%>">削除</a></td>
				</tr>

				<%
				}
				%>

			</table>


	<%
		} else {


	%>




	<%--todoリストない場合　 --%>
	<p>まだ作成されていません</p>

	<%
		}

	%>


	<a href="Logout">ログアウトする。</a>

</body>
</html>