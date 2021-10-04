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
import model.NewtodoQueryBL;

/**
 * Servlet implementation class NewtodoQuery
 */
@WebServlet("/NewtodoQuery")
public class NewtodoQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewtodoQuery() {
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

		//セッションがあるかどうか確認
		HttpSession session           = request.getSession();
		InputInfoDto userInfoOnSession = (InputInfoDto)session.getAttribute("LOGIN_INFO");




		if (userInfoOnSession == null) {
			//セッション情報がない場合
			response.sendRedirect("Login");


		} else {
			//セッション情報がある場合


			boolean  isSuccess = true;
			String 	todoTask 	= request.getParameter("task")				; //todo
			String 	importance 	= request.getParameter("importance")		; //重要度


			int		IdNumber	= userInfoOnSession.getId()					;//セッションに格納したDTOのidを取得

			//ユーザーようのDBにtodoリストを送る。
			NewtodoQueryBL bl = new NewtodoQueryBL();

			//todoリストをDBに格納成功したかどうか、確認
			isSuccess = bl.isInsert(IdNumber, todoTask, importance);

			//格納したtodoリストをselect分で取得して、Headingに出力する。

			//DAOに接続
			if (isSuccess) {

				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/Heading.jsp");
				dispatcher.forward(request, response);
			} else {
				response.sendRedirect("htmls/insertfalse.html");
			}

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
