package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DeleteBL;
import model.InputInfoDto;

/**
 * Servlet implementation class Delete
 */
@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/html;charset=UTF-8");//文字コードの設定

		request.setCharacterEncoding("UTF-8"); //送信するときの文字コード

		HttpSession session           = request.getSession();
		InputInfoDto userInfoOnSession = (InputInfoDto)session.getAttribute("LOGIN_INFO");
		//セッションに情報が入ってるか確かめる。
		if (userInfoOnSession != null) {
			//セッションに情報が入ってる場合
			String 	todoTask = request.getParameter("id"); //リストのtodoタスク
			int		idNumber = userInfoOnSession.getId();	//ユーザーID
			boolean succese = true;							//deleteできたか確かめる。

			//DeleteBLを経由して、delete文を呼び出す
			DeleteBL bl = new DeleteBL();
			succese = bl.callDelete(idNumber, todoTask);

			if (succese) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/Heading.jsp");
				dispatcher.forward(request, response);
			} else {
				response.sendRedirect("deleteFail.html");
			}
		} else {
			//セッションに値が入ってなければ、ログイン画面に遷移
			response.sendRedirect("Login");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
