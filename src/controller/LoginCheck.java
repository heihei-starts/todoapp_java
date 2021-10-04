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
import model.LoginCheckBL;

/**
 * Servlet implementation class LoginCheck
 */
@WebServlet("/LoginCheck")
public class LoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginCheck() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/html;charset=UTF-8");//文字コードの設定

		request.setCharacterEncoding("UTF-8");

		HttpSession session           = request.getSession();
		InputInfoDto userInfoOnSession = (InputInfoDto)session.getAttribute("LOGIN_INFO");
		//セッションにログイン情報があるか確認
		//ある場合
		if (userInfoOnSession != null) {
			//TodoHeadingクラスに飛ぶ
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/Heading.jsp");
			dispatcher.forward(request, response);

		} else {

			boolean success = true; //ログインできたかどうか確認する変数


			//セッションに情報が格納されてない場合
			//ログイン画面からのリクエストパラメーターを取得
			String checkName = request.getParameter("logname"); //ユーザー名の取得
			String checkPass = request.getParameter("logpass");

			InputInfoDto dto = new InputInfoDto();

			//登録ずみでログインできるかチェック
			//DAo殿橋渡しをしてくれるクラスにいく。
			LoginCheckBL logic = new LoginCheckBL();
			dto = logic.check(checkName, checkPass);

			//dtoに値が入ってるか確認
			if (dto.getName() != null) {
				//セッション作成
				session.setAttribute("LOGIN_INFO", dto);
				//ログイン成功

			} else {
				//ログイン失敗
				success =  false;


			}

			//ログイン成功か失敗かによっていく先が異なる。
			if (success) {//成功
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/Heading.jsp");
				dispatcher.forward(request, response);
			} else {//失敗
				response.sendRedirect("Login");

			}
		}

	}

}
