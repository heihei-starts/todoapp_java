package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.InputInfoDto;

/**
 * Servlet implementation class TodoHeading
 */
@WebServlet("/TodoHeading")
public class TodoHeading extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TodoHeading() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//todoリスト作成画面に移動
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/makeTodo.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/html;charset=UTF-8");//文字コードの設定

		HttpSession session           = request.getSession();
		InputInfoDto userInfoOnSession = (InputInfoDto)session.getAttribute("LOGIN_INFO");

		//セッションに情報が入ってない場合
		if (userInfoOnSession == null) {
			//セッションに情報が入ってない場合
			//ログイン画面に移動
			response.sendRedirect("Login");
		} else {
			//セッションに情報が入ってる場合

			//トップ画面に移動
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/Heading.jsp");
			dispatcher.forward(request, response);

		}






	}

}
