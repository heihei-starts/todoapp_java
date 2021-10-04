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
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/html;charset=UTF-8"); //文字コードの設定

		//セッションにログイン情報があるか確認
		HttpSession session           = request.getSession();
		InputInfoDto userInfoOnSession = (InputInfoDto)session.getAttribute("LOGIN_INFO");

		if (userInfoOnSession != null) {
			//セッション情報があれば
			response.sendRedirect("TodoHeading");
		} else {

			//ない場合
			//ログイン画面に移動
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/login.jsp");
			dispatcher.forward(request, response);


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
